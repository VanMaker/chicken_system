package com.njau.entity;

import java.sql.Timestamp;

public class ScoreInfo {
    private int id; // 序号
    private String health_score; // 健康状况得分
    private String behaviour_score; // 行为表现得分
    private String production_score; // 生产性能得分
    private String environment_score; // 舍内环境得分
    private String total_score; // 总得分
    private String date; // 检测日期
    private Timestamp time; //录入时间

    public ScoreInfo() {

    }

    public ScoreInfo(String health_score, String behaviour_score, String production_score, String environment_score, String total_score, String date, Timestamp time) {
        this.health_score = health_score;
        this.behaviour_score = behaviour_score;
        this.production_score = production_score;
        this.environment_score = environment_score;
        this.total_score = total_score;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHealth_score() {
        return health_score;
    }

    public void setHealth_score(String health_score) {
        this.health_score = health_score;
    }

    public String getBehaviour_score() {
        return behaviour_score;
    }

    public void setBehaviour_score(String behaviour_score) {
        this.behaviour_score = behaviour_score;
    }

    public String getProduction_score() {
        return production_score;
    }

    public void setProduction_score(String production_score) {
        this.production_score = production_score;
    }

    public String getEnvironment_score() {
        return environment_score;
    }

    public void setEnvironment_score(String environment_score) {
        this.environment_score = environment_score;
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

    @Override
    public String toString() {
        return "ScoreInfo{" +
                "id=" + id +
                ", health_score='" + health_score + '\'' +
                ", behaviour_score='" + behaviour_score + '\'' +
                ", production_score='" + production_score + '\'' +
                ", environment_score='" + environment_score + '\'' +
                ", total_score='" + total_score + '\'' +
                ", date='" + date + '\'' +
                ", time=" + time +
                '}';
    }
}
