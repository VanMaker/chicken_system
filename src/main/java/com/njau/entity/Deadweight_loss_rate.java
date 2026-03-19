package com.njau.entity;

import java.sql.Timestamp;

public class Deadweight_loss_rate {
    private  int id;
    private  String M;
    private  String A;
    private  String base_score;
    private String health_score;
    private String total_score;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private Timestamp time; //录入时间


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getM() {
        return M;
    }

    public void setM(String m) {
        M = m;
    }

    public String getA() {
        return A;
    }

    public void setA(String a) {
        A = a;
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

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Deadweight_loss_rate(float m, float a, float baseHealth, float healthScore, float totalScore, String date, Timestamp time) {

        String Sm = String.valueOf(m);
        String Sa = String.valueOf(a);
        String SbaseHealth = String.valueOf(baseHealth);
        String ShealthScore = String.valueOf(healthScore);
        String StotalScore = String.valueOf(totalScore);
        String Sdate = String.valueOf(date);

        this.M = Sm;
        this.A = Sa;
        this.base_score = SbaseHealth;
        this.health_score = ShealthScore;
        this.total_score = StotalScore;
        this.date = Sdate;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Deadweight_loss_rate{" +
                "id=" + id +
                ", M='" + M + '\'' +
                ", A='" + A + '\'' +
                ", base_score='" + base_score + '\'' +
                ", health_score='" + health_score + '\'' +
                ", total_score='" + total_score + '\'' +
                ", date='" + date + '\'' +
                ", time=" + time +
                '}';
    }
}
