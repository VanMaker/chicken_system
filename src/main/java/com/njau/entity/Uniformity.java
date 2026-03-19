package com.njau.entity;

import java.sql.Timestamp;

public class Uniformity {
    private  int id;
    private  String U;
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

    public String getU() {
        return U;
    }

    public void setU(String u) {
        U = u;
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
    public Uniformity(float U,float baseHealth, float productionScore, float totalScore, String date, Timestamp time) {

        String SU = String.valueOf(U);
        String SbaseHealth = String.valueOf(baseHealth);
        String SproductionScore = String.valueOf(productionScore);
        String StotalScore = String.valueOf(totalScore);
        String Sdate = String.valueOf(date);

        this.U = SU;
        this.base_score = SbaseHealth;
        this.production_score = SproductionScore;
        this.total_score = StotalScore;
        this.date = Sdate;
        this.time = time;
    }
}
