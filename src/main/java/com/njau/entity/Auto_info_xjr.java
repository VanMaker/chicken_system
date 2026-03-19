package com.njau.entity;

import java.sql.Timestamp;
import java.lang.String;

public class Auto_info_xjr {
    private int id;
    private String dead_r;
    private String dead;
    private String feed;
    private String drink;
    private String temp;
    private String hum;
    private String layegg;
    private String awe;
    private Timestamp time;

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getDead_r() {return dead_r;}

    public void setDead_r(String dead_r) {this.dead_r = dead_r;}

    public String dead() {return dead;}

    public void setDead(String dead) {this.dead = dead;}

    public String getFeed() {return feed;}

    public void setFeed(String feed) {this.feed = feed;}

    public String getDrink() {return drink;}

    public void setDrink(String drink) {this.drink = drink;}

    public String getTemp() {return temp;}

    public void setTemp(String temp) {this.temp = temp;}

    public String getHum() {return hum;}

    public void setHum() {this.hum = hum;}

    public String getLayegg() {return layegg;}

    public void setLayegg(String layegg) {this.layegg = layegg;}

    public String getAwe() {return awe;}

    public void setAwe(String awe) {this.awe = awe;}

    public Timestamp getTime() {return time;}

    public void setTime(Timestamp time) {this.time = time;}

    public Auto_info_xjr(int id, String dead_r, String dead, String feed, String drink, String temp,String hum, String layegg,String awe, Timestamp time){
            this.id = id;
            this.dead_r = dead_r;
            this.dead = dead;
            this.feed = feed;
            this.drink = drink;
            this.temp = temp;
            this.hum = hum;
            this.layegg = layegg;
            this.awe = awe;
            this.time = time;
    }

    @Override
    public String toString(){
        return "auto_info_xjr{"+
                "id=" + id +
                ", daed_r='" + dead_r + '\'' +
                ", dead='" + dead + '\'' +
                ", feed='" + feed + '\'' +
                ", drink='" + drink + '\'' +
                ", temp='" + temp + '\'' +
                ", hum='" + hum + '\'' +
                ", layegg='" + layegg + '\'' +
                ", awe='" + awe + '\'' +
                ", time=" + time +
                '}';
    }
}
