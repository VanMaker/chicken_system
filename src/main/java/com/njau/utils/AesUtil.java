package com.njau.utils;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 密钥必须为16字节或者16字节的倍数的字节型数据。
 * 明文必须为16字节或者16字节的倍数的字节型数据，如果不够16字节需要进行补全。
 */
public class AesUtil {
    public AesUtil(){

    }

    private static AES aes = null;

    /**
     * 16字节
     */
    private static String keyStr = "wdbc";
    //长度为16位的盐值
    private static final String IV_KEY = "a7F3h9J1kL5MnP2q";

    static {
        // 构建
        //随机生成密钥
        SecretKeySpec secretKeySpec = new SecretKeySpec(getBytes(keyStr, 16), SymmetricAlgorithm.AES.getValue());
        IvParameterSpec ivParameterSpec = new IvParameterSpec(IV_KEY.getBytes());
        aes = new AES(Mode.CBC, Padding.ZeroPadding, secretKeySpec, ivParameterSpec);
    }

    /**
     * 破解时密匙长度是规定了的
     * 如果密匙长度有问题会报错：Key length not 128/192/256 bits.
     * 意思就比如密匙长度不是16位 就会报错~
     * 所以需要填充密匙长度
     *
     * @param s
     * @param length
     * @return
     */
    private static byte[] getBytes(String s, int length) {
        int fixLength = length - s.getBytes().length;
        if (s.getBytes().length < length) {
            byte[] S_bytes = new byte[length];
            System.arraycopy(s.getBytes(), 0, S_bytes, 0, s.getBytes().length);
            for (int x = length - fixLength; x < length; x++) {
                S_bytes[x] = 0x00;
            }
            return S_bytes;
        }
        return s.getBytes();
    }

    /**
     * 加密
     *
     * @param content
     * @return
     */
    public static String encryptHex(String content) {
        // 加密
        byte[] encrypt = aes.encrypt(content);
        // 加密为16进制表示
        String encryptHex = aes.encryptHex(content);
        return encryptHex;
    }

    /**
     * 解密
     *
     * @param encryptHex
     * @return
     */
    public static String decryptHex(String encryptHex) {
        // 解密为字符串
        String decryptStr = aes.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
        return decryptStr;
    }

    public static String generate_key() {
        String content = Long.toString(System.currentTimeMillis());
//        System.out.println(content);
        // 加密为16进制表示
        String encryptHex = encryptHex(content);
//        System.out.println(encryptHex);
        // 解密为字符串
//        String decryptStr = decryptHex(encryptHex);
//        System.out.println(decryptStr);
        return encryptHex;
    }//返回生成的密钥

}

