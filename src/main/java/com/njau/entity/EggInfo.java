package com.njau.entity;

import java.sql.Timestamp;

public class EggInfo {
    private  int id;
    private  String AWE;
    private  String C;
    private  String AWC;
    private String APE;
    private  String CD;
    private String production_score;
    private String total_score;
    private String date;
    private Timestamp time;


    public String getAPE() {
        return APE;
    }

    public void setAPE(String APE) {
        this.APE = APE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAWE() {
        return AWE;
    }

    public void setAWE(String AWE) {
        this.AWE = AWE;
    }

    public String getC() {
        return C;
    }

    public void setC(String c) {
        C = c;
    }

    public String getAWC() {
        return AWC;
    }

    public void setAWC(String AWC) {
        this.AWC = AWC;
    }

    public String getCD() {
        return CD;
    }

    public void setCD(String CD) {
        this.CD = CD;
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

    public EggInfo(float AWE, float C, float AWC, float APE, float CD, float productionScore, float totalScore, String date, Timestamp time) {

        String SAWE = String.valueOf(AWE);
        String SC = String.valueOf(C);
        String SAWC = String.valueOf(AWC);
        String SAPE = String.valueOf(APE);
        String SCD = String.valueOf(CD);
        String SproductionScore = String.valueOf(productionScore);
        String StotalScore = String.valueOf(totalScore);
        String Sdate = String.valueOf(date);

        this.AWE = SAWE;
        this.C = SC;
        this.AWC = SAWC;
        this.APE = SAPE;
        this.CD = SCD;
        this.production_score = SproductionScore;
        this.total_score = StotalScore;
        this.date = Sdate;
        this.time = time;
    }

    @Override
    public String toString() {
        return "EggInfo{" +
                "id=" + id +
                ", AWE='" + AWE + '\'' +
                ", C='" + C + '\'' +
                ", AWC='" + AWC + '\'' +
                ", APE='" + APE + '\'' +
                ", CD='" + CD + '\'' +
                ", production_score='" + production_score + '\'' +
                ", total_score='" + total_score + '\'' +
                ", date='" + date + '\'' +
                ", time=" + time +
                '}';
    }
}
