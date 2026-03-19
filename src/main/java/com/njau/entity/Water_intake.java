package com.njau.entity;

import java.sql.Timestamp;

public class Water_intake {
    private  int id;
    private  String DW;
    private  String HW;
    private  String AWI;
    private  String A;
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

    public String getDW() {
        return DW;
    }

    public void setDW(String DW) {
        this.DW = DW;
    }

    public String getHW() {
        return HW;
    }

    public void setHW(String HW) {
        this.HW = HW;
    }

    public String getAWI() {
        return AWI;
    }

    public void setAWI(String AWI) {
        this.AWI = AWI;
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

    public Water_intake(float DW, float HW, float A, float AWI ,float baseHealth, float healthScore, float totalScore, String date, Timestamp time) {

        String Sm = String.valueOf(DW);
        String Sa = String.valueOf(HW);
        String SA = String.valueOf(A);
        String SAWI = String.valueOf(AWI);
        String SbaseHealth = String.valueOf(baseHealth);
        String ShealthScore = String.valueOf(healthScore);
        String StotalScore = String.valueOf(totalScore);
        String Sdate = String.valueOf(date);

        this.DW = Sm;
        this.HW = Sa;
        this.A = SA;
        this.AWI = SAWI;
        this.base_score = SbaseHealth;
        this.behaviour_score = ShealthScore;
        this.total_score = StotalScore;
        this.date = Sdate;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Water_intake{" +
                "id=" + id +
                ", DW='" + DW + '\'' +
                ", HW='" + HW + '\'' +
                ", AWI='" + AWI + '\'' +
                ", A='" + A + '\'' +
                ", base_score='" + base_score + '\'' +
                ", behaviour_score='" + behaviour_score + '\'' +
                ", total_score='" + total_score + '\'' +
                ", date='" + date + '\'' +
                ", time=" + time +
                '}';
    }
}
