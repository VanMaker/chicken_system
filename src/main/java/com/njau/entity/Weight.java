package com.njau.entity;

import java.sql.Timestamp;

public class Weight {
    private  int id;
    private  String TZ1;
    private  String TZ2;
    private  String base_score;
    private String production_score;
    private String total_score;
    private String date;
    private Timestamp time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTZ1() {
        return TZ1;
    }

    public void setTZ1(String TZ1) {
        this.TZ1 = TZ1;
    }

    public String getTZ2() {
        return TZ2;
    }

    public void setTZ2(String TZ2) {
        this.TZ2 = TZ2;
    }

    public String getBase_score() {
        return base_score;
    }

    public void setBase_score(String base_score) {
        this.base_score = base_score;
    }

    public String getProduction_score() {
        return production_score;
    }

    public void setProduction_score(String production_score) {
        this.production_score = production_score;
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
    public Weight(float TZ1, float TZ2,  float baseHealth, float productionScore, float totalScore, String date, Timestamp time) {

        String STZ1 = String.valueOf(TZ1);
        String STZ2 = String.valueOf(TZ2);
        String SbaseHealth = String.valueOf(baseHealth);
        String SproductionScore = String.valueOf(productionScore);
        String StotalScore = String.valueOf(totalScore);
        String Sdate = String.valueOf(date);

        this.TZ1 = STZ1;
        this.TZ2 = STZ2;
        this.base_score = SbaseHealth;
        this.production_score = SproductionScore;
        this.total_score = StotalScore;
        this.date = Sdate;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Weight{" +
                "id=" + id +
                ", TZ1='" + TZ1 + '\'' +
                ", TZ2='" + TZ2 + '\'' +
                ", base_score='" + base_score + '\'' +
                ", production_score='" + production_score + '\'' +
                ", total_score='" + total_score + '\'' +
                ", date='" + date + '\'' +
                ", time=" + time +
                '}';
    }
}
