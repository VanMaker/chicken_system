package com.njau.function;

import com.njau.NetSDKDemo.HCNetSDK;
import com.sun.jna.ptr.IntByReference;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DownloadThread extends Thread {
    private int m_lLoadHandle;
    private HCNetSDK hcNetSDK;

    public DownloadThread(HCNetSDK hcNetSDK) {
        this.hcNetSDK = hcNetSDK;
    }

    //定时器函数
    @Override
    public void run() {
        IntByReference nPos = new IntByReference(0);
        hcNetSDK.NET_DVR_PlayBackControl(m_lLoadHandle, HCNetSDK.NET_DVR_PLAYGETPOS, 0, nPos);
        if (nPos.getValue() > 100) {
            m_lLoadHandle=-1;
            System.out.println( "由于网络原因或DVR忙,下载异常终止!");
        }
        if (nPos.getValue() == 100) {
            Date nowTime = new Date(System.currentTimeMillis());
            SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            System.out.println("结束下载时间："+sdFormatter.format(nowTime));
            m_lLoadHandle=-1;
            System.out.println("按时间下载结束!");
        }
    }
}
