package com.njau.entity;

import java.sql.Timestamp;

public class DropInfo {
    private int id; // 序号
    private String channel; // 通道
    private String equip; // 摄像头/拾音器
    private String name; // 文件名称
    private String water; // 水便
    private String loose; // 稀便
    private String blood; // 血便
    private String sum; // 总数
    private String base_score; // 总数
    private String health_score; // 健康得分
    private String total_score; // 总得分
    private String date; // 检测日期
    private Timestamp time; //录入时间

    public DropInfo() {

    }

    public DropInfo(String channel, String name, String water, String loose, String blood, String sum,String base_score, String health_score, String total_score, String date, Timestamp time) {
        this.channel = channel;
        this.equip = getEquip(channel);
        this.name = name;
        this.water = water;
        this.loose = loose;
        this.blood = blood;
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

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public String getLoose() {
        return loose;
    }

    public void setLoose(String loose) {
        this.loose = loose;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
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
                equip = "4号舍2层粪带";
                break;
            case "39":
                equip = "3号位摄像头";
                break;
            case "40":
                equip = "4号舍3层粪带";
                break;
            case "41":
                equip = "4号位摄像头";
                break;
            case "42":
                equip = "4号舍1层粪带";
                break;
            case "43":
                equip = "4号舍4层粪带";
                break;
            default:
                equip = "未知设备";
                break;
        }
        return equip;
    }

    @Override
    public String toString() {
        return "DropInfo{" +
                "id=" + id +
                ", channel='" + channel + '\'' +
                ", equip='" + equip + '\'' +
                ", name='" + name + '\'' +
                ", water='" + water + '\'' +
                ", loose='" + loose + '\'' +
                ", blood='" + blood + '\'' +
                ", sum='" + sum + '\'' +
                ", health_score='" + health_score + '\'' +
                ", total_score='" + total_score + '\'' +
                ", date='" + date + '\'' +
                ", time=" + time +
                '}';
    }
}
