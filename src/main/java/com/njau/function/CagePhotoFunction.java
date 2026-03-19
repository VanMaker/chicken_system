package com.njau.function;

import com.njau.entity.*;
import com.njau.mapper.DetectionMapper;
import com.njau.utils.*;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CagePhotoFunction<SqlSession> {
    private TimeUtils timeUtils;
    private ConfigUtils configUtils;
    private FileUtilsExt fileUtilsExt;

    public CagePhotoFunction() {
        this.timeUtils = new TimeUtils();
        this.configUtils = new ConfigUtils();
        this.fileUtilsExt = new FileUtilsExt();
    }

    public void  fetchAndProcess() {
        String outDir = "Detection_Folder";
        String baseUrl = "http://121.199.0.105:10031";
        try {
            outDir = configUtils.getProperties("outDir");
            baseUrl = configUtils.getProperties("baseUrl");
            if (outDir != null && !outDir.trim().isEmpty()) outDir = outDir.trim();
            if (baseUrl != null && !baseUrl.trim().isEmpty()) baseUrl = baseUrl.trim();
        } catch (ErrorUtils e) {
            e.printStackTrace();
        }
        CloudImageFetcher fetcher = new CloudImageFetcher();
        Path outPath = Paths.get(System.getProperty("user.dir")).getParent().resolve(outDir);

        List<CagePhotoInfo> cpis = new ArrayList<>();
        try {
            // 原有逻辑
            cpis = pollOnce();
        } catch (Throwable t) {
            t.printStackTrace(); // 一定要有
        }
        if(cpis.size() != 0){

            for (CagePhotoInfo cpi: cpis) {
                int id = cpi.getId().intValueExact();
                String fileName = cpi.getFilename();
                String url = String.format("%s/transfer/photos/image?id=%d", baseUrl, id);
                Path downloadPath = outPath.resolve(fileName);

                try {
                    outPath = fileUtilsExt.resetDir(outPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fetcher.downloadTo(url, downloadPath);
                    System.out.println("saved: " + downloadPath);
                    // TODO: 在这里进行你的图片处理
                    int clusterFlag = clusterModel(cpi);
//                    System.out.println("clusterFlag");
                    int omFlag = omModel(cpi);
//                    System.out.println("omFlag");
                    int eggFlag = eggModel(cpi);
//                    System.out.println("eggFlag");
                    int combFlag = combModel(cpi);
//                    System.out.println("combFlag");
//                    int clusterFlag = 1 ;
//                    int omFlag = 1 ;
//                    int eggFlag = 1 ;
//                    int combFlag = 1 ;
                    if(clusterFlag == 1 && omFlag == 1 && eggFlag == 1 && combFlag ==1){
                        changeStatu(cpi, 1);
                    }else {
                        changeStatu(cpi, 2);
                    }
                } catch (Exception e) {
                    System.out.println("failed id=" + id + " url=" + url + " err=" + e.getMessage());
                }
            }
        }
    }

    public List<CagePhotoInfo> pollOnce() {

        org.apache.ibatis.session.SqlSession sqlSessionYun = MapperUtils.getSqlSession("yun");
        DetectionMapper detectionMapperYun = sqlSessionYun.getMapper(DetectionMapper.class);

        int limit = 100;
        try {
            limit = Integer.parseInt(configUtils.getProperties("limit"));
        } catch (ErrorUtils e) {
            e.printStackTrace();
        }catch (NumberFormatException e) {
            e.printStackTrace();
        }
        String date = timeUtils.get_YMD_HMS_Time()[0];

        detectionMapperYun.claimCagePhotos(date, limit);

        date = "2026-01-23";

        List<CagePhotoInfo> cpis = detectionMapperYun.selectCagePhoto(date);
        if (sqlSessionYun != null) {
            sqlSessionYun.commit();
            sqlSessionYun.close();
        }
        System.out.println(cpis);
        return cpis;
    }

    public int clusterModel(CagePhotoInfo cpi) {
        org.apache.ibatis.session.SqlSession sqlSessionYun = MapperUtils.getSqlSession("yun");
        DetectionMapper detectionMapperYun = sqlSessionYun.getMapper(DetectionMapper.class);

        Map<String,Path> modelParams = getModelParam();
        String envPath = modelParams.get("clusterEnvPath").toString();
        String runPath = modelParams.get("clusterRunPath").toString();
        Path resultPath = modelParams.get("clusterResultPath");
        Path historyTxtPath = modelParams.get("clusterHistoryTxtPath");
        Path historyImagePath = modelParams.get("clusterHistoryImagePath");

        ClusterInfo clusterInfo = new ClusterInfo();

        try {
            resultPath = fileUtilsExt.resetDir(resultPath);
            historyTxtPath = fileUtilsExt.ensureDir(historyTxtPath);
            historyImagePath = fileUtilsExt.ensureDir(historyImagePath);

            ProcessBuilder processBuilder = new ProcessBuilder(envPath, runPath);
            processBuilder.redirectOutput(ProcessBuilder.Redirect.DISCARD);
            processBuilder.redirectError(ProcessBuilder.Redirect.DISCARD);
            Process process = processBuilder.start();
            int code = process.waitFor();
            if (code != 0) {
                throw new IllegalStateException("cluster process failed, exitCode=" + code);
            }

            List<String> files = fileUtilsExt.listFileNames(resultPath);
            String imagePath = "";
            String txtPath = "";
            for (String file: files) {
                String lower = file.toLowerCase();
                if (lower.endsWith(".png") || lower.endsWith(".jpg") || lower.endsWith(".jpeg")) {
                    imagePath = file;
                } else if (lower.endsWith(".txt")) {
                    txtPath = file;
                }
            }
            if (txtPath.isEmpty()) throw new IllegalStateException("no txt result in " + resultPath);
            if (imagePath.isEmpty()) throw new IllegalStateException("no image result in " + resultPath);

            double agg;
            String aggStr;

            try (BufferedReader in = Files.newBufferedReader(Paths.get(txtPath), StandardCharsets.UTF_8)) {
                String line;
                while ((line = in.readLine()) != null) {
                    line = line.trim();
                    if (!line.isEmpty()) break;
                }
                if (line == null) throw new IllegalArgumentException("empty file: " + txtPath);
                if (line.startsWith("\uFEFF")) line = line.substring(1).trim();
                // 去掉最外层 { }
                if (line.startsWith("{")) line = line.substring(1);
                if (line.endsWith("}")) line = line.substring(0, line.length() - 1);
                // 分割成 key:value 对（这里假设 value 不含逗号；你这个场景成立）
                String[] parts = line.split(",");
                Map<String, String> kv = new HashMap<>();
                for (String part : parts) {
                    String[] pair = part.split(":", 2); // 只分成两段，避免 value 里再有 :
                    if (pair.length != 2) continue;
                    String key = stripQuotes(pair[0].trim());
                    String val = pair[1].trim();
                    kv.put(key, val);
                }
                // 取 agg
                String aggRaw = kv.get("agg");
                if (aggRaw == null) throw new IllegalArgumentException("missing agg");
                agg = Double.parseDouble(stripQuotes(aggRaw).trim());
                // 取 aggStr
                String aggStrRaw = kv.get("aggStr");
                if (aggStrRaw == null) throw new IllegalArgumentException("missing aggStr");
                aggStr = stripQuotes(aggStrRaw.trim());
            }
            String yunPath = uploadPhoto(imagePath);
            clusterInfo.setYunPath(yunPath);

            clusterInfo.setAgg(agg);
            clusterInfo.setCagePoint(cpi.getCagePoint());
            clusterInfo.setAggStr(aggStr);
            clusterInfo.setCol(cpi.getCol());
            clusterInfo.setDate(timeUtils.get_YMD_HMS_Time()[0]);
            clusterInfo.setFilename(cpi.getFilename());
            clusterInfo.setShed(cpi.getShed());
            clusterInfo.setLayer(cpi.getLayer());
            clusterInfo.setRowNum(cpi.getRowNum());
            clusterInfo.setCameraId(cpi.getCameraId());
            clusterInfo.setTime(new Timestamp(System.currentTimeMillis()));

            fileUtilsExt.copyToDir(imagePath, historyImagePath.toString());
            fileUtilsExt.copyToDir(txtPath, historyTxtPath.toString());

            int clusterFlag = detectionMapperYun.insertClusterInfo(clusterInfo);
            sqlSessionYun.commit();
            System.out.println("---Cluster Module Detect Finished!!!");
            return clusterFlag == 1 ? 1 : 2;
        } catch (Exception e) {
            if (sqlSessionYun != null) {
                try { sqlSessionYun.rollback(); } catch (Exception ignore) {}
            }
            e.printStackTrace();
            return 2;
        } finally {
            if (sqlSessionYun != null) sqlSessionYun.close();
        }
    }

    private String stripQuotes(String s) {
        s = s.trim();
        if (s.startsWith("\"") && s.endsWith("\"") && s.length() >= 2) {
            return s.substring(1, s.length() - 1);
        }
        return s;
    }

    public Map<String,Path> getModelParam() {
        Map<String,Path> modelParams = new HashMap<>();
        Path javaParentPath = Paths.get(System.getProperty("user.dir")).getParent();
        String resultDir = "Results_Folder";
        String historyDir = "History_Folder";
        String clusterProject = "Cluster_Detection_Application";
        String combProject = "Comb_Detection_Application";
        String eggProject = "Eggs_Detection_Application";
        String omProject = "OpenMouth_Detection_Application";

        String clusterEnv = "/home/yc/SOFTWARE/ANACONDA/envs/Cluster_Detection_Application/bin/python";
        String combEnv = "/home/yc/SOFTWARE/ANACONDA/envs/Comb_Detection/bin/python";
        String eggEnv = "/home/yc/SOFTWARE/ANACONDA/envs/yolov8/bin/python";
        String omEnv = "/home/yc/SOFTWARE/ANACONDA/envs/OpenMouth_Detection_Application/bin/python";

        String clusterRun = "detect.py";
        String combRun = "ultralytics-main/combdetection6.py";
        String eggRun = "detect.py";
        String omRun = "omDetect.py";

        try {
            resultDir = configUtils.getProperties("resultDir");
            historyDir = configUtils.getProperties("historyDir");

            clusterProject = configUtils.getProperties("cluster");
            combProject = configUtils.getProperties("comb");
            eggProject = configUtils.getProperties("egg");
            omProject = configUtils.getProperties("om");

            clusterEnv = configUtils.getProperties("clusterEnvPath");
            combEnv = configUtils.getProperties("combEnvPath");
            eggEnv = configUtils.getProperties("eggEnvPath");
            omEnv = configUtils.getProperties("omEnvPath");

            clusterRun = configUtils.getProperties("clusterRunPath");
            combRun = configUtils.getProperties("combRunPath");
            eggRun = configUtils.getProperties("eggRunPath");
            omRun = configUtils.getProperties("omRunPath");

        } catch (ErrorUtils e) {
            e.printStackTrace();
        }

        Path resultDirPath = javaParentPath.resolve(resultDir);
        Path historyDirPath = javaParentPath.resolve(historyDir);

        Path clusterParentPath = javaParentPath.resolve(clusterProject);
        modelParams.put("clusterParentPath", clusterParentPath);
        Path combParentPath = javaParentPath.resolve(combProject);
        modelParams.put("combParentPath", combParentPath);
        Path eggParentPath = javaParentPath.resolve(eggProject);
        modelParams.put("eggParentPath", eggParentPath);
        Path omParentPath = javaParentPath.resolve(omProject);
        modelParams.put("omParentPath", omParentPath);

        Path clusterEnvPath = Paths.get(clusterEnv);
        modelParams.put("clusterEnvPath", clusterEnvPath);
        Path combEnvPath = Paths.get(combEnv);
        modelParams.put("combEnvPath", combEnvPath);
        Path eggEnvPath = Paths.get(eggEnv);
        modelParams.put("eggEnvPath", eggEnvPath);
        Path omEnvPath = Paths.get(omEnv);
        modelParams.put("omEnvPath", omEnvPath);

        Path clusterRunPath = clusterParentPath.resolve(clusterRun);
        modelParams.put("clusterRunPath", clusterRunPath);
        Path combRunPath = combParentPath.resolve(combRun);
        modelParams.put("combRunPath", combRunPath);
        Path eggRunPath = eggParentPath.resolve(eggRun);
        modelParams.put("eggRunPath", eggRunPath);
        Path omRunPath = omParentPath.resolve(omRun);
        modelParams.put("omRunPath", omRunPath);

        Path clusterResultPath = resultDirPath.resolve("cluster");
        modelParams.put("clusterResultPath", clusterResultPath);
        Path combResultPath = resultDirPath.resolve("Comb");
        modelParams.put("combResultPath", combResultPath);
        Path eggResultPath = resultDirPath.resolve("Eggs");
        modelParams.put("eggResultPath", eggResultPath);
        Path omResultPath = resultDirPath.resolve("Om");
        modelParams.put("omResultPath", omResultPath);

        Path clusterHistoryImagePath = historyDirPath.resolve("cluster").resolve("image");
        modelParams.put("clusterHistoryImagePath", clusterHistoryImagePath);
        Path clusterHistoryTxtPath = historyDirPath.resolve("cluster").resolve("txt");
        modelParams.put("clusterHistoryTxtPath", clusterHistoryTxtPath);

        Path combHistoryImagePath = historyDirPath.resolve("Comb").resolve("image");
        modelParams.put("combHistoryImagePath", combHistoryImagePath);
        Path combHistoryTxtPath = historyDirPath.resolve("Comb").resolve("txt");
        modelParams.put("combHistoryTxtPath", combHistoryTxtPath);

        Path eggHistoryImagePath = historyDirPath.resolve("Eggs").resolve("image");
        modelParams.put("eggHistoryImagePath", eggHistoryImagePath);
        Path eggHistoryTxtPath = historyDirPath.resolve("Eggs").resolve("txt");
        modelParams.put("eggHistoryTxtPath", eggHistoryTxtPath);

        Path omHistoryImagePath = historyDirPath.resolve("Om").resolve("image");
        modelParams.put("omHistoryImagePath", omHistoryImagePath);
        Path omHistoryTxtPath = historyDirPath.resolve("Om").resolve("txt");
        modelParams.put("omHistoryTxtPath", omHistoryTxtPath);

        return modelParams;
    }

    private void changeStatu(CagePhotoInfo cpi, int flag) {
        org.apache.ibatis.session.SqlSession sqlSessionYun = MapperUtils.getSqlSession("yun");
        DetectionMapper detectionMapperYun = sqlSessionYun.getMapper(DetectionMapper.class);
        switch (flag) {
            case 1 ->{
                detectionMapperYun.markCagePhotoDone(cpi.getId());
            }
            case 2 ->{
                detectionMapperYun.markCagePhotoFailed(cpi.getId());
            }
        }
        if (sqlSessionYun != null) {
            sqlSessionYun.commit();
            sqlSessionYun.close();
        }
    }

    public int eggModel(CagePhotoInfo cpi) {
        org.apache.ibatis.session.SqlSession sqlSessionYun = MapperUtils.getSqlSession("yun");
        DetectionMapper detectionMapperYun = sqlSessionYun.getMapper(DetectionMapper.class);

        Map<String,Path> modelParams = getModelParam();
        String envPath = modelParams.get("eggEnvPath").toString();
        String runPath = modelParams.get("eggRunPath").toString();
        Path resultPath = modelParams.get("eggResultPath");
        Path historyTxtPath = modelParams.get("eggHistoryTxtPath");
        Path historyImagePath = modelParams.get("eggHistoryImagePath");

        EggsInfo eggInfo = new EggsInfo();

        try {
            resultPath = fileUtilsExt.resetDir(resultPath);
            historyTxtPath = fileUtilsExt.ensureDir(historyTxtPath);
            historyImagePath = fileUtilsExt.ensureDir(historyImagePath);

            ProcessBuilder processBuilder = new ProcessBuilder(envPath, runPath);
            Process process = processBuilder.start();
            processBuilder.redirectOutput(ProcessBuilder.Redirect.DISCARD);
            processBuilder.redirectError(ProcessBuilder.Redirect.DISCARD);
            int code = process.waitFor();
            if (code != 0) {
                throw new IllegalStateException("egg process failed, exitCode=" + code);
            }


            List<String> files = fileUtilsExt.listFileNames(resultPath);
            String imagePath = "";
            String txtPath = "";
            for (String file: files) {
                String lower = file.toLowerCase();
                if (lower.endsWith(".png") || lower.endsWith(".jpg") || lower.endsWith(".jpeg")) {
                    imagePath = file;
                } else if (lower.endsWith(".txt")) {
                    txtPath = file;
                }
            }
            if (txtPath.isEmpty()) throw new IllegalStateException("no txt result in " + resultPath);
            if (imagePath.isEmpty()) throw new IllegalStateException("no image result in " + resultPath);

            int egg;

            try (BufferedReader in = Files.newBufferedReader(Paths.get(txtPath), StandardCharsets.UTF_8)) {
                String line;
                while ((line = in.readLine()) != null) {
                    line = line.trim();
                    if (!line.isEmpty()) break;
                }
                if (line == null) throw new IllegalArgumentException("empty file: " + txtPath);
                if (line.startsWith("\uFEFF")) line = line.substring(1).trim();

                String[] parts = line.split(",");
                Map<String, String> kv = new HashMap<>();

                for (String part : parts) {
                    String[] pair = part.split(":", 2); // 只分两段，避免 value 里再有 :
                    if (pair.length != 2) continue;
                    String key = stripQuotes(pair[0].trim());
                    String val = stripQuotes(pair[1].trim());
                    kv.put(key, val);
                }
                // 取 egg
                String eggRaw = kv.get("egg");
                if (eggRaw == null) throw new IllegalArgumentException("missing egg");
                egg = (int) Math.round(Double.parseDouble(eggRaw.trim()));
            }

            String yunPath = uploadPhoto(imagePath);
            eggInfo.setYunPath(yunPath);
            eggInfo.setEgg(egg);
            eggInfo.setCagePoint(cpi.getCagePoint());
            eggInfo.setCol(cpi.getCol());
            eggInfo.setDate(timeUtils.get_YMD_HMS_Time()[0]);
            eggInfo.setFilename(cpi.getFilename());
            eggInfo.setShed(cpi.getShed());
            eggInfo.setLayer(cpi.getLayer());
            eggInfo.setRowNum(cpi.getRowNum());
            eggInfo.setCameraId(cpi.getCameraId());
            eggInfo.setTime(new Timestamp(System.currentTimeMillis()));

            fileUtilsExt.copyToDir(imagePath, historyImagePath.toString());
            fileUtilsExt.copyToDir(txtPath, historyTxtPath.toString());

            int eggFlag = detectionMapperYun.insertEggsInfo(eggInfo);

            sqlSessionYun.commit();
            System.out.println("---Egg Module Detect Finished!!!");
            return eggFlag == 1 ? 1 : 2;
        } catch (Exception e) {
            if (sqlSessionYun != null) {
                try { sqlSessionYun.rollback(); } catch (Exception ignore) {}
            }
            e.printStackTrace();
            return 2;
        } finally {
            if (sqlSessionYun != null) sqlSessionYun.close();
        }
    }

    public int combModel(CagePhotoInfo cpi) {
        org.apache.ibatis.session.SqlSession sqlSessionYun = MapperUtils.getSqlSession("yun");
        DetectionMapper detectionMapperYun = sqlSessionYun.getMapper(DetectionMapper.class);

        Map<String,Path> modelParams = getModelParam();
        String envPath = modelParams.get("combEnvPath").toString();
        String runPath = modelParams.get("combRunPath").toString();
        Path resultPath = modelParams.get("combResultPath");
        Path historyTxtPath = modelParams.get("combHistoryTxtPath");
        Path historyImagePath = modelParams.get("combHistoryImagePath");

        CombInfo combInfo = new CombInfo();

        try {
            resultPath = fileUtilsExt.resetDir(resultPath);
            historyTxtPath = fileUtilsExt.ensureDir(historyTxtPath);
            historyImagePath = fileUtilsExt.ensureDir(historyImagePath);

            ProcessBuilder processBuilder = new ProcessBuilder(envPath, runPath);
//            processBuilder.redirectErrorStream(true);
            processBuilder.redirectOutput(ProcessBuilder.Redirect.DISCARD);
            processBuilder.redirectError(ProcessBuilder.Redirect.DISCARD);
            Process process = processBuilder.start();
            int code = process.waitFor();
            if (code != 0) {
                throw new IllegalStateException("comb process failed, exitCode=" + code);
            }
//
//            String output;
//            try (var in = new java.io.BufferedReader(
//                    new java.io.InputStreamReader(process.getInputStream(), java.nio.charset.StandardCharsets.UTF_8))) {
//                output = in.lines().collect(java.util.stream.Collectors.joining("\n"));
//            }
//
//            int code = process.waitFor();
//            if (code != 0) {
//                throw new IllegalStateException("om process failed, exitCode=" + code + "\n---- process output ----\n" + output);
//            }


            List<String> files = fileUtilsExt.listFileNames(resultPath);
            String imagePath = "";
            String txtPath = "";
            for (String file: files) {
                String lower = file.toLowerCase();
                if (lower.endsWith(".png") || lower.endsWith(".jpg") || lower.endsWith(".jpeg")) {
                    imagePath = file;
                } else if (lower.endsWith(".txt")) {
                    txtPath = file;
                }
            }
            if (txtPath.isEmpty()) throw new IllegalStateException("no txt result in " + resultPath);
            if (imagePath.isEmpty()) throw new IllegalStateException("no image result in " + resultPath);

            int score;

            try (BufferedReader in = Files.newBufferedReader(Paths.get(txtPath), StandardCharsets.UTF_8)) {
                String line;
                while ((line = in.readLine()) != null) {
                    line = line.trim();
                    if (!line.isEmpty()) break;
                }
                if (line == null) throw new IllegalArgumentException("empty file: " + txtPath);
                if (line.startsWith("\uFEFF")) line = line.substring(1).trim();

                // 去掉最外层 { }
                if (line.startsWith("{")) line = line.substring(1);
                if (line.endsWith("}")) line = line.substring(0, line.length() - 1);

                String[] parts = line.split(",");
                Map<String, String> kv = new HashMap<>();

                for (String part : parts) {
                    String[] pair = part.split(":", 2); // 只分两段，避免 time 里 16:07:47 被拆
                    if (pair.length != 2) continue;
                    String key = stripQuotes(pair[0].trim());      // "id" -> id
                    String val = stripQuotes(pair[1].trim());      // "2026-..." -> 2026-...
                    kv.put(key, val);
                }
                // 取 score
                String scoreRaw = kv.get("score");
                if (scoreRaw == null) throw new IllegalArgumentException("missing score");
                score = (int) Math.round(Double.parseDouble(scoreRaw.trim()));
            }

            String yunPath = uploadPhoto(imagePath);
            combInfo.setYunPath(yunPath);
            combInfo.setScore(score);
            combInfo.setCagePoint(cpi.getCagePoint());
            combInfo.setCol(cpi.getCol());
            combInfo.setDate(timeUtils.get_YMD_HMS_Time()[0]);
            combInfo.setFilename(cpi.getFilename());
            combInfo.setShed(cpi.getShed());
            combInfo.setLayer(cpi.getLayer());
            combInfo.setRowNum(cpi.getRowNum());
            combInfo.setCameraId(cpi.getCameraId());
            combInfo.setTime(new Timestamp(System.currentTimeMillis()));

            fileUtilsExt.copyToDir(imagePath, historyImagePath.toString());
            fileUtilsExt.copyToDir(txtPath, historyTxtPath.toString());

            int combFlag = detectionMapperYun.insertCombInfo(combInfo);

            sqlSessionYun.commit();
            System.out.println("---Comb Module Detect Finished!!!");
            return combFlag == 1 ? 1 : 2;
        } catch (Exception e) {
            if (sqlSessionYun != null) {
                try { sqlSessionYun.rollback(); } catch (Exception ignore) {}
            }
            e.printStackTrace();
            return 2;
        } finally {
            if (sqlSessionYun != null) sqlSessionYun.close();
        }
    }

    public int omModel(CagePhotoInfo cpi) {
        org.apache.ibatis.session.SqlSession sqlSessionYun = MapperUtils.getSqlSession("yun");
        DetectionMapper detectionMapperYun = sqlSessionYun.getMapper(DetectionMapper.class);

        Map<String,Path> modelParams = getModelParam();
        String envPath = modelParams.get("omEnvPath").toString();
        String runPath = modelParams.get("omRunPath").toString();
        Path resultPath = modelParams.get("omResultPath");
        Path historyTxtPath = modelParams.get("omHistoryTxtPath");
        Path historyImagePath = modelParams.get("omHistoryImagePath");

        OmInfo omInfo = new OmInfo();

        try {
            resultPath = fileUtilsExt.resetDir(resultPath);
            historyTxtPath = fileUtilsExt.ensureDir(historyTxtPath);
            historyImagePath = fileUtilsExt.ensureDir(historyImagePath);

            ProcessBuilder processBuilder = new ProcessBuilder(envPath, runPath);
//            processBuilder.redirectErrorStream(true);
            processBuilder.redirectOutput(ProcessBuilder.Redirect.DISCARD);
            processBuilder.redirectError(ProcessBuilder.Redirect.DISCARD);
            Process process = processBuilder.start();
            int code = process.waitFor();
            if (code != 0) {
                throw new IllegalStateException("om process failed, exitCode=" + code);
            }


            List<String> files = fileUtilsExt.listFileNames(resultPath);
            String imagePath = "";
            String txtPath = "";
            for (String file: files) {
                String lower = file.toLowerCase();
                if (lower.endsWith(".png") || lower.endsWith(".jpg") || lower.endsWith(".jpeg")) {
                    imagePath = file;
                } else if (lower.endsWith(".txt")) {
                    txtPath = file;
                }
            }
            if (txtPath.isEmpty()) throw new IllegalStateException("no txt result in " + resultPath);
            if (imagePath.isEmpty()) throw new IllegalStateException("no image result in " + resultPath);

            int open;
            int summary;
            double omRatio;

            try (BufferedReader in = Files.newBufferedReader(Paths.get(txtPath), StandardCharsets.UTF_8)) {
                String line;
                while ((line = in.readLine()) != null) {
                    line = line.trim();
                    if (!line.isEmpty()) break;
                }
                if (line == null) throw new IllegalArgumentException("empty file: " + txtPath);
                if (line.startsWith("\uFEFF")) line = line.substring(1).trim();

                String[] parts = line.split(",");
                Map<String, String> kv = new HashMap<>();

                for (String part : parts) {
                    String[] pair = part.split(":", 2); // 只分两段，避免 value 里再有 :
                    if (pair.length != 2) continue;
                    String key = stripQuotes(pair[0].trim());
                    String val = stripQuotes(pair[1].trim());
                    kv.put(key, val);
                }
                // 取 open ,summary ,omRatio;
                String openRaw = kv.get("open");
                if (openRaw == null) throw new IllegalArgumentException("missing open");
                open = (int) Math.round(Double.parseDouble(openRaw.trim()));

                String summaryRaw = kv.get("summary");
                if (summaryRaw == null) throw new IllegalArgumentException("missing summary");
                summary = (int) Math.round(Double.parseDouble(summaryRaw.trim()));

                String omRatioRaw = kv.get("omRatio");
                if (omRatioRaw == null) throw new IllegalArgumentException("missing omRatio");
                omRatio = Double.parseDouble(omRatioRaw.replace("%", "").trim());
            }

            String yunPath = uploadPhoto(imagePath);
            omInfo.setYunPath(yunPath);
            omInfo.setOpen(open);
            omInfo.setSummary(summary);
            omInfo.setOmRatio(omRatio);
            omInfo.setCagePoint(cpi.getCagePoint());
            omInfo.setCol(cpi.getCol());
            omInfo.setDate(timeUtils.get_YMD_HMS_Time()[0]);
            omInfo.setFilename(cpi.getFilename());
            omInfo.setShed(cpi.getShed());
            omInfo.setLayer(cpi.getLayer());
            omInfo.setRowNum(cpi.getRowNum());
            omInfo.setCameraId(cpi.getCameraId());
            omInfo.setTime(new Timestamp(System.currentTimeMillis()));

            fileUtilsExt.copyToDir(imagePath, historyImagePath.toString());
            fileUtilsExt.copyToDir(txtPath, historyTxtPath.toString());

            int omFlag = detectionMapperYun.insertOmInfo(omInfo);

            sqlSessionYun.commit();
            System.out.println("---Om Module Detect Finished!!!");
            return omFlag == 1 ? 1 : 2;
        } catch (Exception e) {
            if (sqlSessionYun != null) {
                try { sqlSessionYun.rollback(); } catch (Exception ignore) {}
            }
            e.printStackTrace();
            return 2;
        } finally {
            if (sqlSessionYun != null) sqlSessionYun.close();
        }
    }

    public String uploadPhoto(String filePath) {
        PhotoUploader uploader = new PhotoUploader();
        String cloudUrl = "http://127.0.0.1:5131/CHD/photos/upload";
        try {
            cloudUrl = configUtils.getProperties("cloudUrl");
        } catch (ErrorUtils e) {
            e.printStackTrace();
        }


        UploadResp r = null;
        String savePath = "unkown";
        try {
            r = uploader.upload(cloudUrl, filePath);
            savePath = r.savedPath;
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println("uploaded: " + r.savedPath);
        return savePath;
    }

}
