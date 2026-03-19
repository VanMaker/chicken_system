package com.njau.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class TimeUtils {

    public String[] get_YMD_HMS_Time() {
        Date time = new Date(System.currentTimeMillis());
        HashMap<String, Integer> downloadTime = new HashMap<String, Integer>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        downloadTime.put("Year", Integer.parseInt(sdf.format(time).substring(0, 4)));
        downloadTime.put("Month", Integer.parseInt(sdf.format(time).substring(5, 7)));
        downloadTime.put("Day", Integer.parseInt(sdf.format(time).substring(8, 10)));
        downloadTime.put("Hour", Integer.parseInt(sdf.format(time).substring(11, 13)));
        downloadTime.put("Minute", Integer.parseInt(sdf.format(time).substring(14, 16)));
        downloadTime.put("Second", Integer.parseInt(sdf.format(time).substring(17, 19)));
        String YMDTime = downloadTime.get("Year") + "-" + downloadTime.get("Month") + "-" + downloadTime.get("Day");
        String HMSTime = downloadTime.get("Hour") + "-" + downloadTime.get("Minute") + "-" + downloadTime.get("Second");
        String[] times = {YMDTime, HMSTime};
        return times;
    }

    //设置任务启动时间
    public Date getScheduleTime(int hour) {
        // 创建一个Calendar实例，并设置时间为当前时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date()); // 设置为当前时间
        // 测试用
//        calendar.add(Calendar.SECOND, 2);
        // 正式版
        // hour指示的时刻启动
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        if (calendar.getTime().before(new Date())) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return calendar.getTime();// 返回一个Date类型的变量，并赋值为计算后的时间
    }


    public HashMap<String, Integer> getDownloadTime(String module, int hour, int period) {
        Date time = new Date(System.currentTimeMillis());
        // 创建一个Calendar实例，并设置时间为当前时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time); // 设置为当前时间
        Date startTime = null;
        Date endTime = null;
        HashMap<String, Integer> downloadTime = new HashMap<>();
        // 设置开始和结束时间
        if ("voice".equals(module)) {
            // 正式版
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            startTime = calendar.getTime();
            calendar.add(Calendar.HOUR_OF_DAY, period);
            endTime = calendar.getTime();
            // 测试用
            // 固定时间2021-4-29 10：28：00 —— 2021-4-29 10：30：00
//            calendar.set(Calendar.YEAR, 2024);
//            calendar.set(Calendar.MONTH, 3);
//            calendar.set(Calendar.DAY_OF_MONTH, 15);
//            calendar.set(Calendar.HOUR_OF_DAY, 13);
//            calendar.set(Calendar.MINUTE, 00);
//            calendar.set(Calendar.SECOND, 0);
//            startTime = calendar.getTime();
//            calendar.add(Calendar.MINUTE, 5);
//            endTime = calendar.getTime();
        }else if ("drop".equals(module)) {
            // 正式版
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            startTime = calendar.getTime();
            calendar.add(Calendar.HOUR_OF_DAY, period);
            endTime = calendar.getTime();
            // 测试用
            // 固定时间2021-4-29 10：28：00 —— 2021-4-29 10：30：00
//            calendar.set(Calendar.YEAR, 2024);
//            calendar.set(Calendar.MONTH, 3); //  比当前月份少1
//            calendar.set(Calendar.DAY_OF_MONTH, 15);
//            calendar.set(Calendar.HOUR_OF_DAY, 13);
//            calendar.set(Calendar.MINUTE, 0);
//            calendar.set(Calendar.SECOND, 0);
//            startTime = calendar.getTime();
//            calendar.add(Calendar.MINUTE, 5);
//            endTime = calendar.getTime();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        downloadTime.put("Year", Integer.parseInt(sdf.format(startTime).substring(0, 4)));
        downloadTime.put("Month", Integer.parseInt(sdf.format(startTime).substring(5, 7)));
        downloadTime.put("Day", Integer.parseInt(sdf.format(startTime).substring(8, 10)));
        downloadTime.put("StartHour", Integer.parseInt(sdf.format(startTime).substring(11, 13)));
        downloadTime.put("EndHour", Integer.parseInt(sdf.format(endTime).substring(11, 13)));
        downloadTime.put("StartMinute", Integer.parseInt(sdf.format(startTime).substring(14, 16)));
        downloadTime.put("EndMinute", Integer.parseInt(sdf.format(endTime).substring(14, 16)));
        downloadTime.put("StartSecond", Integer.parseInt(sdf.format(startTime).substring(17, 19)));
        downloadTime.put("EndSecond", Integer.parseInt(sdf.format(endTime).substring(17, 19)));
        return downloadTime;
    }

}
