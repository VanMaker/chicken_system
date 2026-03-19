package com.njau.entity;

import java.sql.Timestamp;

public class OmInfo {
    private int shed;
    private int col;
    private int rowNum;
    private int layer;
    private int cameraId;
    private String filename;
    private String cagePoint;
    private int open;
    private int summary;
    private double omRatio;
    private String date;
    private String yunPath;
    private Timestamp time;

    public String getYunPath() {
        return yunPath;
    }

    public void setYunPath(String yunPath) {
        this.yunPath = yunPath;
    }

    public OmInfo() {

    }

    public int getShed() {
        return shed;
    }

    public void setShed(int shed) {
        this.shed = shed;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public int getCameraId() {
        return cameraId;
    }

    public void setCameraId(int cameraId) {
        this.cameraId = cameraId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getCagePoint() {
        return cagePoint;
    }

    public void setCagePoint(String cagePoint) {
        this.cagePoint = cagePoint;
    }

    public int getOpen() {
        return open;
    }

    public void setOpen(int open) {
        this.open = open;
    }

    public int getSummary() {
        return summary;
    }

    public void setSummary(int summary) {
        this.summary = summary;
    }

    public double getOmRatio() {
        return omRatio;
    }

    public void setOmRatio(double omRatio) {
        this.omRatio = omRatio;
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
