package com.njau.function;

import com.njau.utils.ConfigUtils;
import com.njau.utils.ErrorUtils;
import com.njau.utils.TimeUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class DeleteTask extends TimerTask {
    private TimeUtils timeUtils;
    private ConfigUtils configUtils;

    public DeleteTask(ConfigUtils configUtils, TimeUtils timeUtils) {
        this.timeUtils = timeUtils;
        this.configUtils = configUtils;

    }

    @Override
    public void run() {
        String[] modules = null;
        List<File> fileLists = new ArrayList<File>();
        try {
            modules = configUtils.getProperties("modules").split(",");
        } catch (ErrorUtils e) {
            e.printStackTrace();
        }
        for (String module : modules) {
            String path = System.getProperty("user.dir") + "/Detection_Data/" + module + "/";
            File[] files = new File(path).listFiles();
            for (int i = 0; i < files.length; i++) {
                fileLists.add(files[i]);
            }
        }
        //实现文件删除
        for (int i = 0; i < fileLists.size(); i++) {
            if(fileLists.get(i).exists()){
//                System.out.println(fileLists.get(i).getName());
                boolean flag = fileLists.get(i).delete();
                if(flag){
                    System.out.println(fileLists.get(i).getName()+"文件已成功删除！");
                }
                else{
                    System.out.println(fileLists.get(i).getName()+"文件删除失败！");
                }
            }
            else{
                System.out.println("文件不存在！");
            }
        }

    }

}
