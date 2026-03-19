package com.njau.function;

import com.njau.NetSDKDemo.FExceptionCallBack_Imp;
import com.njau.NetSDKDemo.HCNetSDK;
import com.njau.NetSDKDemo.osSelect;
import com.njau.entity.HCNetSDKInfo;
import com.njau.utils.ConfigUtils;
import com.njau.utils.ErrorUtils;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;

public class HCNetSDKInit {
    private HCNetSDK hcNetSDK;
    private FExceptionCallBack_Imp fExceptionCallBack;
    private int lUserID = -1;//用户句柄
    private int lDChannel;  //通道号

    public HCNetSDKInit() throws ErrorUtils {
        init();
    }

    public HCNetSDKInfo HCNetSDKInit() {
        return new HCNetSDKInfo(hcNetSDK, fExceptionCallBack, lUserID, lDChannel);
    }

    //动态库加载
    private boolean createSDKInstance() {
        if (hcNetSDK == null) {
            synchronized (HCNetSDK.class) {
                String strDllPath = "";
                try {
                    if (osSelect.isWindows())
                        //win系统加载库路径
                        strDllPath = System.getProperty("user.dir") + "/lib/HCNetSDK.dll";

                    else if (osSelect.isLinux())
                        //Linux系统加载库路径
                        strDllPath = System.getProperty("user.dir") + "/lib/libhcnetsdk.so";
//                        strDllPath = "/opt/alarm/lib/libhcnetsdk.so";
                    hcNetSDK = (HCNetSDK) Native.loadLibrary(strDllPath, HCNetSDK.class);
                } catch (Exception ex) {
                    System.out.println("loadLibrary: " + strDllPath + " Error: " + ex.getMessage());
                    return false;
                }
            }
        }
        return true;
    }

    //登录
    public void login(String ip, short port, String user, String psw) {

        //注册
        HCNetSDK.NET_DVR_USER_LOGIN_INFO m_strLoginInfo = new HCNetSDK.NET_DVR_USER_LOGIN_INFO();//设备登录信息
        HCNetSDK.NET_DVR_DEVICEINFO_V40 m_strDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V40();//设备信息

        String m_sDeviceIP = ip;//设备ip地址
        m_strLoginInfo.sDeviceAddress = new byte[HCNetSDK.NET_DVR_DEV_ADDRESS_MAX_LEN];
        System.arraycopy(m_sDeviceIP.getBytes(), 0, m_strLoginInfo.sDeviceAddress, 0, m_sDeviceIP.length());

        String m_sUsername = user;//设备用户名
        m_strLoginInfo.sUserName = new byte[HCNetSDK.NET_DVR_LOGIN_USERNAME_MAX_LEN];
        System.arraycopy(m_sUsername.getBytes(), 0, m_strLoginInfo.sUserName, 0, m_sUsername.length());

        String m_sPassword = psw;//设备密码
        m_strLoginInfo.sPassword = new byte[HCNetSDK.NET_DVR_LOGIN_PASSWD_MAX_LEN];
        System.arraycopy(m_sPassword.getBytes(), 0, m_strLoginInfo.sPassword, 0, m_sPassword.length());

        m_strLoginInfo.wPort = port;
        m_strLoginInfo.bUseAsynLogin = false; //是否异步登录：0- 否，1- 是
        m_strLoginInfo.byLoginMode=0;  //0- SDK私有协议，1- ISAPI协议
        m_strLoginInfo.write();

        lUserID = hcNetSDK.NET_DVR_Login_V40(m_strLoginInfo, m_strDeviceInfo);
        if (lUserID== -1) {
            System.out.println("登录失败，错误码为" + hcNetSDK.NET_DVR_GetLastError());
            return;
        } else {
            System.out.println("【HCNetSDK】:" + ip + ":设备登录成功！");
            //相机一般只有一个通道号，热成像相机有2个通道号，通道号为1或1,2
            //byStartDChan为IP通道起始通道号, 预览回放NVR的IP通道时需要根据起始通道号进行取值
            //byStartDChan为IP通道起始通道号, 预览回放NVR的IP通道时需要根据起始通道号进行取值,NVR起始通道号一般是33或者1开始
            lDChannel = (int)m_strDeviceInfo.struDeviceV30.byStartDChan;
            System.out.println("【HCNetSDK】:设备起始通道号为：" + lDChannel + "!");
//            getIPChannelInfo(lUserID);
            return;
        }
    }

    //初始化
    public void init() throws ErrorUtils {
        ConfigUtils cu = new ConfigUtils();
        String NVR_ip = cu.getProperties("NVR_ip");
        String NVR_username = cu.getProperties("NVR_username");
        String NVR_password = cu.getProperties("NVR_password");
        short NVR_port = Short.parseShort(cu.getProperties("NVR_port"));
        //动态库加载
        if (hcNetSDK == null) {
            if (!createSDKInstance()) {
                System.out.println("Load SDK fail");
                return;
            }
        }
        //linux系统建议调用以下接口加载组件库
        if (osSelect.isLinux()) {
            HCNetSDK.BYTE_ARRAY ptrByteArray1 = new HCNetSDK.BYTE_ARRAY(256);
            HCNetSDK.BYTE_ARRAY ptrByteArray2 = new HCNetSDK.BYTE_ARRAY(256);
            //这里是库的绝对路径，请根据实际情况修改，注意改路径必须有访问权限
            String strPath1 = System.getProperty("user.dir") + "/lib/libcrypto.so.1.1";
            String strPath2 = System.getProperty("user.dir") + "/lib/libssl.so.1.1";
//            String strPath1 = "/opt/alarm/lib/libcrypto.so.1.1";
//            String strPath2 = "/opt/alarm/lib/libssl.so.1.1";

            System.arraycopy(strPath1.getBytes(), 0, ptrByteArray1.byValue, 0, strPath1.length());
            ptrByteArray1.write();
            hcNetSDK.NET_DVR_SetSDKInitCfg(3, ptrByteArray1.getPointer());

            System.arraycopy(strPath2.getBytes(), 0, ptrByteArray2.byValue, 0, strPath2.length());
            ptrByteArray2.write();
            hcNetSDK.NET_DVR_SetSDKInitCfg(4, ptrByteArray2.getPointer());

            String strPathCom = System.getProperty("user.dir") + "/lib/";
//            String strPathCom = "/opt/alarm/lib/";
            HCNetSDK.NET_DVR_LOCAL_SDK_PATH struComPath = new HCNetSDK.NET_DVR_LOCAL_SDK_PATH();
            System.arraycopy(strPathCom.getBytes(), 0, struComPath.sPath, 0, strPathCom.length());
            struComPath.write();
            hcNetSDK.NET_DVR_SetSDKInitCfg(2, struComPath.getPointer());
        }
        // SDK初始化，一个程序只需要调用一次
        boolean initSuc = hcNetSDK.NET_DVR_Init();
        // 异常消息回调
        if(fExceptionCallBack == null) {
            fExceptionCallBack = new FExceptionCallBack_Imp();
        }
        Pointer pUser = null;
        if (!hcNetSDK.NET_DVR_SetExceptionCallBack_V30(0, 0, fExceptionCallBack, pUser)) {
            return;
        }
        System.out.println("【HCNetSDK】:设置异常消息回调成功！");
        // 启动SDK写日志
        hcNetSDK.NET_DVR_SetLogToFile(3, System.getProperty("user.dir") + "/lib/sdkLog", false);
        // 登录
        login(NVR_ip, NVR_port, NVR_username, NVR_password);
    }

    //获取IP通道
    public void getIPChannelInfo(int userID) {
        IntByReference ibrBytesReturned = new IntByReference(0);//获取IP接入配置参数
        HCNetSDK.NET_DVR_IPPARACFG_V40 m_strIpparaCfg = new HCNetSDK.NET_DVR_IPPARACFG_V40();
        m_strIpparaCfg.write();
        //lpIpParaConfig 接收数据的缓冲指针
        Pointer lpIpParaConfig = m_strIpparaCfg.getPointer();
        boolean bRet = hcNetSDK.NET_DVR_GetDVRConfig(userID, HCNetSDK.NET_DVR_GET_IPPARACFG_V40, 0, lpIpParaConfig, m_strIpparaCfg.size(), ibrBytesReturned);
        m_strIpparaCfg.read();

        for (int iChannum = 0; iChannum < m_strIpparaCfg.dwDChanNum; iChannum++) {
            int channum = iChannum + m_strIpparaCfg.dwStartDChan;
            m_strIpparaCfg.struStreamMode[iChannum].read();
            if (m_strIpparaCfg.struStreamMode[iChannum].byGetStreamType == 0) {
                m_strIpparaCfg.struStreamMode[iChannum].uGetStream.setType(HCNetSDK.NET_DVR_IPCHANINFO.class);
                m_strIpparaCfg.struStreamMode[iChannum].uGetStream.struChanInfo.read();
                if (m_strIpparaCfg.struStreamMode[iChannum].uGetStream.struChanInfo.byEnable == 1) {
                    System.out.println("IP通道" + channum + "在线");
                } else {
                    System.out.println("IP通道" + channum + "不在线");
                }
            }
        }
    }

}