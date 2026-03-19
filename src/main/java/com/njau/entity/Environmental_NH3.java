package com.njau.entity;

import java.sql.Timestamp;

public class Environmental_NH3 {


    private  int id;

    private  String NH3;
    private  String base_score;
    private String health_score;
    private String total_score;
    private String date;
    private Timestamp time; //录入时间


    public Environmental_NH3(float NH3, float baseHealth, float healthScore, float totalScore, String date, Timestamp time) {
//        String Sm = String.valueOf(t);
//        String Sa = String.valueOf(h);
//        String Sco2 = String.valueOf(CO2);
        String Snh3 = String.valueOf(NH3);
        String SbaseHealth = String.valueOf(baseHealth);
        String ShealthScore = String.valueOf(healthScore);
        String StotalScore = String.valueOf(totalScore);
        String Sdate = String.valueOf(date);

//        this.T = Sm;
//        this.H = Sa;
//        this.CO2 = Sco2;
        this.NH3 = Snh3;
        this.base_score = SbaseHealth;
        this.health_score = ShealthScore;
        this.total_score = StotalScore;
        this.date = Sdate;
        this.time = time;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getNH3() {
        return NH3;
    }

    public void setNH3(String NH3) {
        this.NH3 = NH3;
    }

    public String getBase_score() {
        return base_score;
    }

    public void setBase_score(String base_score) {
        this.base_score = base_score;
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
}
