package com.njau.main;

import com.njau.entity.HCNetSDKInfo;
import com.njau.function.*;
import com.njau.utils.AesUtil;
import com.njau.utils.ConfigUtils;
import com.njau.utils.ErrorUtils;
import com.njau.utils.TimeUtils;

import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainRun {
    private static ExecutorService executorService;
    private static ConfigUtils configUtils;
    private static TimeUtils timeUtils;

    //下载任务
    public static void downloadSchedule(ConfigUtils configUtils, TimeUtils timeUtils) throws ErrorUtils {
        int download_start_time = Integer.parseInt(configUtils.getProperties("download_start_time"));
        int download_period = Integer.parseInt(configUtils.getProperties("download_period"));
        System.out.println("【下载】任务已预设，启动时间：" + download_start_time + "：00,循环周期：" + download_period + "小时。");
        // HCNetSDK初始化
        HCNetSDKInfo hcNetSDKInfo = new HCNetSDKInit().HCNetSDKInit();
        Timer download = new Timer();
        DownloadTask downloadTask = new DownloadTask(hcNetSDKInfo.gethCNetSDK(), hcNetSDKInfo.getlUserID(), executorService, configUtils, timeUtils);
        download.scheduleAtFixedRate(downloadTask, timeUtils.getScheduleTime(download_start_time), download_period * 60 * 60 * 1000);
    }


    //检测任务
    private static void detectionSchedule(ConfigUtils configUtils, TimeUtils timeUtils) throws ErrorUtils {
        int detect_period = Integer.parseInt(configUtils.getProperties("detect_period"));
        long periodMs = detect_period * 60L * 1000L;
        System.out.println("【检测】任务已预设，立刻启动：" + ",循环周期：" + detect_period + "分钟。");
        Timer detection = new Timer();
        DetectionTask detectionTask = new DetectionTask(executorService);
        detection.scheduleAtFixedRate(detectionTask, 0L, periodMs);
    }


    //计算任务
    private static void computeSchedule(ConfigUtils configUtils, TimeUtils timeUtils, AesUtil aesUtil) throws ErrorUtils {
        int compute_start_time = Integer.parseInt(configUtils.getProperties("compute_start_time"));
        int compute_period = Integer.parseInt(configUtils.getProperties("compute_period"));
        System.out.println("【计算】任务已预设，启动时间：" + compute_start_time + "：00,循环周期：" + compute_period + "小时。");
        Timer compute = new Timer();
        ComputeTask computeTask = new ComputeTask(configUtils, timeUtils, aesUtil);
        computeTask.run();
        compute.scheduleAtFixedRate(computeTask, timeUtils.getScheduleTime(compute_start_time), compute_period * 60 * 60 * 1000);
    }


    //清理任务
    private static void deleteSchedule(ConfigUtils configUtils, TimeUtils timeUtils) throws ErrorUtils {
        int delete_start_time = Integer.parseInt(configUtils.getProperties("delete_start_time"));
        int delete_period = Integer.parseInt(configUtils.getProperties("delete_period"));
        Timer delete = new Timer();
        DeleteTask deleteTask = new DeleteTask(configUtils, timeUtils);
        deleteTask.run();
        delete.scheduleAtFixedRate(deleteTask, timeUtils.getScheduleTime(delete_start_time), delete_period * 24 * 60 * 60 * 1000);
    }


    public static void main(String[] args) throws ErrorUtils {
        AesUtil aesUtil = new AesUtil();
        configUtils = new ConfigUtils();
        timeUtils = new TimeUtils();
        // 启动线程池
        executorService = Executors.newFixedThreadPool(Integer.parseInt(configUtils.getProperties("threads")));
        // 执行下载
//        downloadSchedule(configUtils, timeUtils);
//         执行检测
        detectionSchedule(configUtils, timeUtils);
//         执行计算
//        computeSchedule(configUtils, timeUtils, aesUtil);
        // 执行清理
        deleteSchedule(configUtils, timeUtils);
    }

}