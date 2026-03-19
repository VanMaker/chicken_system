package com.njau.function;

import com.njau.NetSDKDemo.HCNetSDK;
import com.njau.utils.ConfigUtils;
import com.njau.utils.ErrorUtils;
import com.njau.utils.TimeUtils;

import java.io.File;
import java.util.HashMap;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;

public class DownloadTask extends TimerTask {
    private HCNetSDK hcNetSDK;
    private int userID;
    private int m_lLoadHandle;
    private ExecutorService executorService;
    private ConfigUtils configUtils;
    private TimeUtils timeUtils;

    public DownloadTask(HCNetSDK hcNetSDK, int userID, ExecutorService executorService, ConfigUtils configUtils, TimeUtils timeUtils) {
        this.hcNetSDK = hcNetSDK;
        this.userID = userID;
        this.executorService = executorService;
        this.configUtils = configUtils;
        this.timeUtils = timeUtils;
    }

    //定时器函数
    @Override
    public void run() {
        String[] modules = null;
        String[] drop_channels = null;
        String[] voice_channels = null;
        String[] channels = null;
        String start_hour = null;
        String period = null;
        HashMap<String, Integer> downloadTime;
        try {
            modules = configUtils.getProperties("modules").split(",");
            drop_channels = configUtils.getProperties("drop_channels").split(",");
            voice_channels = configUtils.getProperties("voice_channels").split(",");
        } catch (ErrorUtils e) {
            e.printStackTrace();
        }
        String[] times = timeUtils.get_YMD_HMS_Time();
        String YMDTime = times[0];
        String HMSTime = times[1];
        System.out.println("开始下载时间：" + YMDTime + " " + HMSTime);
        for (String module : modules) {
            if ("drop".equals(module)) {
                channels = drop_channels;
                try {
                    start_hour = configUtils.getProperties("drop_start_hour");
                    period = configUtils.getProperties("drop_period");
                } catch (ErrorUtils e) {
                    e.printStackTrace();
                }
            } else if ("voice".equals(module)) {
                channels = voice_channels;
                try {
                    start_hour = configUtils.getProperties("voice_start_hour");
                    period = configUtils.getProperties("voice_period");
                } catch (ErrorUtils e) {
                    e.printStackTrace();
                }
            }
            downloadTime = timeUtils.getDownloadTime(module, Integer.parseInt(start_hour), Integer.parseInt(period));
            for (String channel : channels) {
                download(userID, Integer.parseInt(channel), module, downloadTime);
            }
        }
    }

    private void download(int userID, int channel, String module, HashMap<String, Integer> downloadTime) {
        HCNetSDK.NET_DVR_PLAYCOND net_dvr_playcond = new HCNetSDK.NET_DVR_PLAYCOND();
        net_dvr_playcond.read();
        net_dvr_playcond.dwChannel=channel; //通道号 NVR设备路数小于32路的起始通道号从33开始，依次增加


        //开始时间
        net_dvr_playcond.struStartTime.dwYear = downloadTime.get("Year");
        net_dvr_playcond.struStartTime.dwMonth = downloadTime.get("Month");
        net_dvr_playcond.struStartTime.dwDay = downloadTime.get("Day");
        net_dvr_playcond.struStartTime.dwHour = downloadTime.get("StartHour");
        net_dvr_playcond.struStartTime.dwMinute = downloadTime.get("StartMinute");
        net_dvr_playcond.struStartTime.dwSecond = downloadTime.get("StartSecond");
        //停止时间
        net_dvr_playcond.struStopTime.dwYear = downloadTime.get("Year");
        net_dvr_playcond.struStopTime.dwMonth = downloadTime.get("Month");
        net_dvr_playcond.struStopTime.dwDay = downloadTime.get("Day");
        net_dvr_playcond.struStopTime.dwHour = downloadTime.get("EndHour");
        net_dvr_playcond.struStopTime.dwMinute = downloadTime.get("EndMinute");
        net_dvr_playcond.struStopTime.dwSecond = downloadTime.get("EndSecond");
        net_dvr_playcond.write();


        String YMDTime = downloadTime.get("Year") + "-" + downloadTime.get("Month") + "-" + downloadTime.get("Day");
        String HMSTime = downloadTime.get("StartHour") + "-" + downloadTime.get("StartMinute") + "-" + downloadTime.get("StartSecond");
        String filePath = System.getProperty("user.dir") + "/Detection_Data/" + module + "/" + YMDTime;
        mkdir(filePath);


        String fileName = filePath + "/"+ channel + "@" + YMDTime + "@" + HMSTime + ".mp4";
        System.out.println("文件存储路径："+ fileName); //输出路径


        m_lLoadHandle = hcNetSDK.NET_DVR_GetFileByTime_V40(userID, fileName, net_dvr_playcond);
        if (m_lLoadHandle >= 0) {
            hcNetSDK.NET_DVR_PlayBackControl(m_lLoadHandle, HCNetSDK.NET_DVR_PLAYSTART, 0, null);
            DownloadThread downloadThread = new DownloadThread(hcNetSDK);
            executorService.submit(downloadThread);
        }else {
            System.out.println("按时间下载失败");
            System.out.println("last error " + hcNetSDK.NET_DVR_GetLastError());
            return;
        }
    }

    private void mkdir(String filePath) {
        File folder = new File(filePath);
        // 如果文件夹不存在
        if (!folder.exists()) {
            // 创建文件夹
            boolean success = folder.mkdirs();
            if (success) {
                System.out.println("文件夹创建成功: " + filePath);
            } else {
                System.out.println("文件夹创建失败");
            }
        } else {
            System.out.println("文件夹已存在: " + filePath);
        }
    }
}
