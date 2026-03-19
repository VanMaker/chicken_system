package com.njau.entity;

import java.sql.Timestamp;

public class Score {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String temp;//'温度',
    private String temp_score;//'温度得分',
    private String hum;//'湿度',
    private String hum_score;// '湿度得分',
    private String co2 ;//'二氧化碳',
    private String co2_score;//'二氧化碳得分',
    private String nh3;// '氨气',
    private String nh3_score;//'氨气得分',
    private String env_score ;//'环境分',
    private String health_score;//'健康检测',
    private String sound;//'声音异常占比',
    private String sound_score;// '声音异常占比得分',
    private String dead; //'死淘数',
    private String dead_r;// '死淘率',
    private String dead_score ;//'死淘分',
    private String bs ;//'血便',
    private String ws;//'稀便',
    private String ww ;//'水便',
    private String soil_rate; //'粪便比例',
    private String soil_score; //'粪便得分',
    private String information_score;//'生产信息分',
    private String layegg;//'产蛋率',
    private String layegg_score ;//'产蛋率得分',
    private String dys; //'畸形',
    private String dys_score;// '畸形得分',
    private String damage;//'破损蛋',
    private String weight;// '体重',
    private String weight_score; //'体重得分',
    private String eve; //'均匀度',
    private String eve_score;//均匀度得分',
    private String awe; //'平均蛋重',
    private String awe_score;//'平均蛋重得分',
    private String behavior_score; //'行为表现得分',
    private String drink;//'今日耗水量',
    private String drink_score ;//'饮水得分',
    private String feed;// '今日饲料量',
    private String feed_score;//'采食得分',
    private String total_score; //'总分',
    private Timestamp time;

    public Score(String temp, String temp_score, String hum, String hum_score, String co2, String co2_score, String nh3, String nh3_score, String env_score, String health_score, String sound, String sound_score, String dead, String dead_r, String dead_score, String bs, String ws, String ww, String soil_rate, String soil_score, String information_score, String layegg, String layegg_score, String dys, String dys_score, String damage, String weight, String weight_score, String eve, String eve_score, String awe, String awe_score, String behavior_score, String drink, String drink_score, String feed, String feed_score, String total_score, Timestamp time) {
        this.temp = temp;
        this.temp_score = temp_score;
        this.hum = hum;
        this.hum_score = hum_score;
        this.co2 = co2;
        this.co2_score = co2_score;
        this.nh3 = nh3;
        this.nh3_score = nh3_score;
        this.env_score = env_score;
        this.health_score = health_score;
        this.sound = sound;
        this.sound_score = sound_score;
        this.dead = dead;
        this.dead_r = dead_r;
        this.dead_score = dead_score;
        this.bs = bs;
        this.ws = ws;
        this.ww = ww;
        this.soil_rate = soil_rate;
        this.soil_score = soil_score;
        this.information_score = information_score;
        this.layegg = layegg;
        this.layegg_score = layegg_score;
        this.dys = dys;
        this.dys_score = dys_score;
        this.damage = damage;
        this.weight = weight;
        this.weight_score = weight_score;
        this.eve = eve;
        this.eve_score = eve_score;
        this.awe = awe;
        this.awe_score = awe_score;
        this.behavior_score = behavior_score;
        this.drink = drink;
        this.drink_score = drink_score;
        this.feed = feed;
        this.feed_score = feed_score;
        this.total_score = total_score;
        this.time = time;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getTemp_score() {
        return temp_score;
    }

    public void setTemp_score(String temp_score) {
        this.temp_score = temp_score;
    }

    public String getHum() {
        return hum;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }

    public String getHum_score() {
        return hum_score;
    }

    public void setHum_score(String hum_score) {
        this.hum_score = hum_score;
    }

    public String getCo2() {
        return co2;
    }

    public void setCo2(String co2) {
        this.co2 = co2;
    }

    public String getCo2_score() {
        return co2_score;
    }

    public void setCo2_score(String co2_score) {
        this.co2_score = co2_score;
    }

    public String getNh3() {
        return nh3;
    }

    public void setNh3(String nh3) {
        this.nh3 = nh3;
    }

    public String getNh3_score() {
        return nh3_score;
    }

    public void setNh3_score(String nh3_score) {
        this.nh3_score = nh3_score;
    }

    public String getEnv_score() {
        return env_score;
    }

    public void setEnv_score(String env_score) {
        this.env_score = env_score;
    }

    public String getHealth_score() {
        return health_score;
    }

    public void setHealth_score(String health_score) {
        this.health_score = health_score;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getSound_score() {
        return sound_score;
    }

    public void setSound_score(String sound_score) {
        this.sound_score = sound_score;
    }

    public String getDead() {
        return dead;
    }

    public void setDead(String dead) {
        this.dead = dead;
    }

    public String getDead_r() {
        return dead_r;
    }

    public void setDead_r(String dead_r) {
        this.dead_r = dead_r;
    }

    public String getDead_score() {
        return dead_score;
    }

    public void setDead_score(String dead_score) {
        this.dead_score = dead_score;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

    public String getWs() {
        return ws;
    }

    public void setWs(String ws) {
        this.ws = ws;
    }

    public String getWw() {
        return ww;
    }

    public void setWw(String ww) {
        this.ww = ww;
    }

    public String getSoil_score() {
        return soil_score;
    }

    public void setSoil_score(String soil_score) {
        this.soil_score = soil_score;
    }

    public String getInformation_score() {
        return information_score;
    }

    public void setInformation_score(String information_score) {
        this.information_score = information_score;
    }

    public String getLayegg() {
        return layegg;
    }

    public void setLayegg(String layegg) {
        this.layegg = layegg;
    }

    public String getLayegg_score() {
        return layegg_score;
    }

    public void setLayegg_score(String layegg_score) {
        this.layegg_score = layegg_score;
    }

    public String getDys() {
        return dys;
    }

    public void setDys(String dys) {
        this.dys = dys;
    }

    public String getDys_score() {
        return dys_score;
    }

    public void setDys_score(String dys_score) {
        this.dys_score = dys_score;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }


    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWeight_score() {
        return weight_score;
    }

    public void setWeight_score(String weight_score) {
        this.weight_score = weight_score;
    }

    public String getEve() {
        return eve;
    }

    public void setEve(String eve) {
        this.eve = eve;
    }

    public String getEve_score() {
        return eve_score;
    }

    public void setEve_score(String eve_score) {
        this.eve_score = eve_score;
    }

    public String getAwe() {
        return awe;
    }

    public void setAwe(String awe) {
        this.awe = awe;
    }

    public String getAwe_score() {
        return awe_score;
    }

    public void setAwe_score(String awe_score) {
        this.awe_score = awe_score;
    }

    public String getBehavior_score() {
        return behavior_score;
    }

    public void setBehavior_score(String behavior_score) {
        this.behavior_score = behavior_score;
    }

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public String getDrink_score() {
        return drink_score;
    }

    public void setDrink_score(String drink_score) {
        this.drink_score = drink_score;
    }

    public String getFeed() {
        return feed;
    }

    public void setFeed(String feed) {
        this.feed = feed;
    }

    public String getFeed_score() {
        return feed_score;
    }

    public void setFeed_score(String feed_score) {
        this.feed_score = feed_score;
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

    public String getSoil_rate() {
        return soil_rate;
    }

    public void setSoil_rate(String soil_rate) {
        this.soil_rate = soil_rate;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", temp='" + temp + '\'' +
                ", temp_score='" + temp_score + '\'' +
                ", hum='" + hum + '\'' +
                ", hum_score='" + hum_score + '\'' +
                ", co2='" + co2 + '\'' +
                ", co2_score='" + co2_score + '\'' +
                ", nh3='" + nh3 + '\'' +
                ", nh3_score='" + nh3_score + '\'' +
                ", env_score='" + env_score + '\'' +
                ", health_score='" + health_score + '\'' +
                ", sound='" + sound + '\'' +
                ", sound_score='" + sound_score + '\'' +
                ", dead='" + dead + '\'' +
                ", dead_r='" + dead_r + '\'' +
                ", dead_score='" + dead_score + '\'' +
                ", bs='" + bs + '\'' +
                ", ws='" + ws + '\'' +
                ", ww='" + ww + '\'' +
                ", soil_rate='" + soil_rate + '\'' +
                ", soil_score='" + soil_score + '\'' +
                ", information_score='" + information_score + '\'' +
                ", layegg='" + layegg + '\'' +
                ", layegg_score='" + layegg_score + '\'' +
                ", dys='" + dys + '\'' +
                ", dys_score='" + dys_score + '\'' +
                ", damage='" + damage + '\'' +
                ", weight='" + weight + '\'' +
                ", weight_score='" + weight_score + '\'' +
                ", eve='" + eve + '\'' +
                ", eve_score='" + eve_score + '\'' +
                ", awe='" + awe + '\'' +
                ", awe_score='" + awe_score + '\'' +
                ", behavior_score='" + behavior_score + '\'' +
                ", drink='" + drink + '\'' +
                ", drink_score='" + drink_score + '\'' +
                ", feed='" + feed + '\'' +
                ", feed_score='" + feed_score + '\'' +
                ", total_score='" + total_score + '\'' +
                ", time=" + time +
                '}';
    }
}
