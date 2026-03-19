package com.njau.entity;

import com.njau.NetSDKDemo.HCNetSDK;
import com.njau.NetSDKDemo.FExceptionCallBack_Imp;

public class HCNetSDKInfo {
    private HCNetSDK hCNetSDK;
    private FExceptionCallBack_Imp fExceptionCallBack;
    private int lUserID;//用户句柄
    private int lDChannel;  //通道号

    public HCNetSDKInfo(HCNetSDK hCNetSDK, FExceptionCallBack_Imp fExceptionCallBack, int lUserID, int lDChannel) {
        this.hCNetSDK = hCNetSDK;
        this.fExceptionCallBack = fExceptionCallBack;
        this.lUserID = lUserID;
        this.lDChannel = lDChannel;
    }

    public HCNetSDK gethCNetSDK() {
        return hCNetSDK;
    }

    public void sethCNetSDK(HCNetSDK hCNetSDK) {
        this.hCNetSDK = hCNetSDK;
    }

    public FExceptionCallBack_Imp getfExceptionCallBack() {
        return fExceptionCallBack;
    }

    public void setfExceptionCallBack(FExceptionCallBack_Imp fExceptionCallBack) {
        this.fExceptionCallBack = fExceptionCallBack;
    }

    public int getlUserID() {
        return lUserID;
    }

    public void setlUserID(int lUserID) {
        this.lUserID = lUserID;
    }

    public int getlDChannel() {
        return lDChannel;
    }

    public void setlDChannel(int lDChannel) {
        this.lDChannel = lDChannel;
    }
}
