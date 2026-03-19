package com.njau.NetSDKDemo;

import com.sun.jna.Pointer;

public class FExceptionCallBack_Imp implements HCNetSDK.FExceptionCallBack {
    public void invoke(int dwType, int lUserID, int lHandle, Pointer pUser) {
        System.out.println("异常事件类型:"+dwType);
        return;
    }
}
