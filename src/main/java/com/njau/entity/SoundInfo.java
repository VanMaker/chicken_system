package com.njau.entity;

import java.sql.Timestamp;

public class SoundInfo {
    private int id; // 序号
    private String channel; // 通道
    private String equip; // 哪个摄像头/拾音器
    private String name; // 文件名称
    private String cough; // 咳嗽
    private String chirp; // 鸣叫
    private String sum; // 总数
    private  String base_score;
    private String health_score; // 健康得分
    private String total_score; // 总得分
    private String date; // 检测日期
    private Timestamp time; //录入时间

    public SoundInfo() {

    }

    public SoundInfo(String channel, String name, String cough, String chirp, String sum, String base_score,String health_score, String total_score, String date, Timestamp time) {
        this.channel = channel;
        this.equip = getEquip(channel);
        this.name = name;
        this.cough = cough;
        this.chirp = chirp;
        this.sum = sum;
        this.base_score = base_score;
        this.health_score = health_score;
        this.total_score = total_score;
        this.date = date;
        this.time = time;
    }

    public String getBase_score() {
        return base_score;
    }

    public void setBase_score(String base_score) {
        this.base_score = base_score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getEquip() {
        return equip;
    }

    public void setEquip(String equip) {
        this.equip = equip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCough() {
        return cough;
    }

    public void setCough(String cough) {
        this.cough = cough;
    }

    public String getChirp() {
        return chirp;
    }

    public void setChirp(String chirp) {
        this.chirp = chirp;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getHealth_score() {
        return health_score;
    }

    public void setHealth_score(String health_score) {
        this.health_score = health_score;
    }

    public String getTotal_score() {
        return total_score;
    }

    public void setTotal_score(String total_score) {
        this.total_score = total_score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getEquip(String channel) {
        String equip = null;
        switch (channel) {
            case "33":
                equip = "1号位拾音器";
                break;
            case "34":
                equip = "2号位拾音器";
                break;
            case "35":
                equip = "3号位拾音器";
                break;
            case "36":
                equip = "4号位拾音器";
                break;
            case "37":
                equip = "1号位摄像头";
                break;
            case "38":
                equip = "2号位摄像头";
                break;
            case "39":
                equip = "1号位拾音器";
                break;
            case "40":
                equip = "4号位摄像头";
                break;
            case "41":
                equip = "2号位拾音器";
                break;
            case "42":
                equip = "4号位摄像头";
                break;
            case "43":
                equip = "3号位摄像头";
                break;
            case "44":
                equip = "3号位拾音器";
                break;
            case "45":
                equip = "4号位拾音器";
                break;
            case "46":
                equip = "5号位拾音器";
                break;
            case "47":
                equip = "6号位拾音器";
                break;
            case "48":
                equip = "7号位拾音器";
                break;
            default:
                equip = "未知设备";
                break;
        }
        return equip;
    }

    @Override
    public String toString() {
        return "SoundInfo{" +
                "id=" + id +
                ", channel='" + channel + '\'' +
                ", equip='" + equip + '\'' +
                ", name='" + name + '\'' +
                ", cough='" + cough + '\'' +
                ", chirp='" + chirp + '\'' +
                ", sum='" + sum + '\'' +
                ", health_score='" + health_score + '\'' +
                ", total_score='" + total_score + '\'' +
                ", date='" + date + '\'' +
                ", time=" + time +
                '}';
    }
}
