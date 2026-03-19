package com.njau.entity;

public class Score_New {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String env_score;
    private String health_score;
    private String information_score;
    private String behavior_score;
    private String total_score;
    public Score_New(String env_score, String health_score, String information_score, String behavior_score, String total_score) {
        this.env_score = env_score;
        this.health_score = health_score;
        this.information_score = information_score;
        this.behavior_score = behavior_score;
        this.total_score = total_score;
    }
    public String getEnv_score() {return env_score;}
    public void setEnv_score(String env_score) {this.env_score = env_score;}
    public String getHealth_score() {return health_score;}
    public void setHealth_score(String health_score) {this.health_score = health_score;}
    public String getInformation_score() {return information_score;}
    public void setInformation_score(String information_score) {this.information_score = information_score;}
    public String getBehavior_score() {return behavior_score;}
    public void setBehavior_score(String behavior_score) {this.behavior_score = behavior_score;}
    public String getTotal_score() {return total_score;}
    public void setTotal_score(String total_score) {this.total_score = total_score;}
}
