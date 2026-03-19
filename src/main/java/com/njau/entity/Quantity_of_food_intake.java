package com.njau.entity;

import java.sql.Timestamp;

public class Quantity_of_food_intake {
    private  int id;
    private  String AFI;
    private  String FI;
    private  String base_score;

    public String getBehaviour_score() {
        return behaviour_score;
    }

    public void setBehaviour_score(String behaviour_score) {
        this.behaviour_score = behaviour_score;
    }

    private String behaviour_score;
    private String total_score;
    private String date;
    private Timestamp time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAFI() {
        return AFI;
    }

    public void setAFI(String AFI) {
        this.AFI = AFI;
    }

    public String getFI() {
        return FI;
    }

    public void setFI(String FI) {
        this.FI = FI;
    }

    public String getBase_score() {
        return base_score;
    }

    public void setBase_score(String base_score) {
        this.base_score = base_score;
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

    public Quantity_of_food_intake(float m, float a, float baseHealth, float healthScore, float totalScore, String date, Timestamp time) {

        String Sm = String.valueOf(m);
        String Sa = String.valueOf(a);
        String SbaseHealth = String.valueOf(baseHealth);
        String ShealthScore = String.valueOf(healthScore);
        String StotalScore = String.valueOf(totalScore);
        String Sdate = String.valueOf(date);

        this.AFI = Sm;
        this.FI = Sa;
        this.base_score = SbaseHealth;
        this.behaviour_score = ShealthScore;
        this.total_score = StotalScore;
        this.date = Sdate;
        this.time = time;
    }
}
