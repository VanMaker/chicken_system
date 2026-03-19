package com.njau.function;

import com.njau.entity.*;
import com.njau.mapper.DetectionMapper;
import com.njau.utils.*;
import org.apache.ibatis.session.SqlSession;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.TimerTask;

public class ComputeTask extends TimerTask {
    private DetectionMapper detectionMapperYun;
    private DetectionMapper detectionMapperLocal;
    private TimeUtils timeUtils;
    private ConfigUtils configUtils;
    private HttpRequestUtils httpRequestUtils;
    private AesUtil aesUtil;

    public ComputeTask(ConfigUtils configUtils, TimeUtils timeUtils, AesUtil aesUtil) {
        this.timeUtils = timeUtils;
        this.configUtils = configUtils;
        this.aesUtil = aesUtil;
        this.httpRequestUtils = new HttpRequestUtils();
    }

    @Override
    public void run() {
        SqlSession sqlSessionYun = MapperUtils.getSqlSession("yun");
        SqlSession sqlSessionLocal = MapperUtils.getSqlSession("localhost");
        detectionMapperYun = sqlSessionYun.getMapper(DetectionMapper.class);
        detectionMapperLocal = sqlSessionLocal.getMapper(DetectionMapper.class);
        Auto_info_xjr auto_info;
//        String house_id = null;
//        String house_name = null;
        LocalDate today = LocalDate.now();
        String date = today.toString(); //date变量可以为当天日期或者为历史日期
//        date ="2024-12-31";

        String[] auto_infos = httpRequestUtils.Device_Propertys(aesUtil,date);
        String dead_r = auto_infos[1];
        String dead = auto_infos[2];
        String feed = auto_infos[3];
        String drink = auto_infos[4];
        String hum = auto_infos[6];
        String temp = auto_infos[5];
        String layegg = auto_infos[7];
        String awe = auto_infos[8];
        try{
            auto_info = detectionMapperYun.selectAuto_info_xjr();
            System.out.println(auto_info);
            detectionMapperLocal.insertAuto_info_xjr(auto_info);
        }
        catch(Exception e){
            auto_info = detectionMapperLocal.selectAuto_info_xjr();
            System.out.println("111");
        }
//        try{
//            inputInfoList = detectionMapperYun.selectInputInfo();
//            detectionMapperLocal.insertInputInfo(inputInfoList);
//        } catch (Exception e){
//            System.out.println("111");
//            inputInfoList = detectionMapperLocal.selectInputInfo();
//        }
//        try {
//            house_id = configUtils.getProperties("house_id");
//            house_name = configUtils.getProperties("house_name");
//        } catch (ErrorUtils e) {
//            e.printStackTrace();
//        }

//        String[] auto_infos = httpRequestUtils.Device_Propertys(house_id, house_name);
//        String temp = auto_infos[1];
//        String humi = auto_infos[47];
//        String water_intake = auto_infos[108];
//        String food_intake = auto_infos[111];


        Deadweight_loss_rate dead_r0= calculate_Deadweight_loss_rate(dead_r);//死淘汰率
        Quantity_of_food_intake food_i = calculate_quantity_of_food_intake(feed);//采食量
        Water_intake water_i = calculate_Water_intake(drink);//饮水量
        EggInfo eggInfo = calculate_Egg_production(layegg);//产蛋率
        Average_egg a_eggs = calculate_average_egg(awe);//平均蛋重
        Environmental_T e_t = Environmental_T(temp);// 温度
        Environmental_H e_h = Environmental_H(hum);// 湿度

        keepResult(dead_r0, food_i, water_i, eggInfo, a_eggs, e_t, e_h);

        showScore(dead_r0, food_i, water_i, eggInfo, a_eggs, e_t, e_h);

        if (sqlSessionYun != null) {
            sqlSessionYun.commit();
            sqlSessionYun.close();
        }
        if (sqlSessionLocal != null) {
            sqlSessionLocal.commit();
            sqlSessionLocal.close();
        }

    }


    // 采食量
    private Quantity_of_food_intake calculate_quantity_of_food_intake(String feed) {
        float AFI = 10.16F;
        float FI = 9.68F;
        float base_health;
        float behaviour_score = 0;
        float total_score = 0;
        String date = null;
        Timestamp time;

//        AFI = Float.parseFloat(feed) / 1000;  //来自小巨人的数据，单位转换为吨
//        FI = Float.parseFloat(inputInfoList.getFI());
//        AFI = 10.105f;
        date = timeUtils.get_YMD_HMS_Time()[0];

        base_health = 100 - Math.abs(AFI-FI)/FI*100*3;
        behaviour_score = Float.parseFloat(new DecimalFormat("#.###").format(base_health * 0.511f));
        total_score = Float.parseFloat(new DecimalFormat("#.###").format(base_health * 0.1252f));
        time = new Timestamp(System.currentTimeMillis());
        Quantity_of_food_intake quantity_of_food_intake = new Quantity_of_food_intake(AFI,FI,base_health,behaviour_score,total_score,date,time);
//        System.out.println("采食量基础得分"+quantity_of_food_intake.getBase_score());
//        System.out.println("采食量表现得分"+quantity_of_food_intake.getBehaviour_score());
//        System.out.println("采食量总得分"+quantity_of_food_intake.getTotal_score());
        return quantity_of_food_intake;
    }

    // 死淘率
//    private Deadweight_loss_rate calculate_Deadweight_loss_rate(InputInfo inputInfoList) {
    private Deadweight_loss_rate calculate_Deadweight_loss_rate(String dead_r) {
        float M = 358;
        float A = 86513;
        float ST;
        float dead_r_0;
        float base_health;
        float health_score = 0;
        float total_score = 0;
        String date = null;
        Timestamp time;

//        A = Float.parseFloat(inputInfoList.getA4());
//        M = Float.parseFloat(inputInfoList.getM4());
        dead_r_0 = Float.parseFloat(dead_r);
        date = timeUtils.get_YMD_HMS_Time()[0];
//        ST = Float.parseFloat(new DecimalFormat("#.###").format(M/A*100));
        ST = Float.parseFloat(new DecimalFormat("#.###").format(dead_r_0));
        if(ST<=0.002){
            base_health = 100;
        }else {
            base_health = (float) (100-(ST-0.2)/0.1*15);
        }
        health_score = Float.parseFloat(new DecimalFormat("#.###").format(base_health * 0.325f));
        total_score = Float.parseFloat(new DecimalFormat("#.###").format(base_health * 0.0826f));
        time = new Timestamp(System.currentTimeMillis());
        Deadweight_loss_rate deadweight_loss_rate = new Deadweight_loss_rate(M,A,base_health,health_score,total_score,date,time);
//        System.out.println(deadweight_loss_rate);
        return deadweight_loss_rate;
    }

    // 饮水量
    private Water_intake calculate_Water_intake(String water_intake) {
        float DW=159;
        float HW=224;
        float AWI=17550;
        float A=86155;
        float base_score=0;
        float behaviour_score = 0;
        float total_score = 0;
        String date = null;
        Timestamp time;

//        DW = Float.parseFloat(inputInfoList.getDW());
//        HW = Float.parseFloat(inputInfoList.getHW());
        date = timeUtils.get_YMD_HMS_Time()[0];
//        AWI = Float.parseFloat(water_intake);  // 获取数据来自小巨人
        AWI = 17550;
//        A = Float.parseFloat(inputInfoList.getA());

        if(AWI*1000/A<=DW){
//            System.out.println(DW*A);
            base_score = 100-Math.abs(AWI*1000-DW*A)/(DW*A)*300;
        }else if(AWI*1000/A>=HW){
//            System.out.println(HW*A);
            base_score = 100-Math.abs(AWI*1000-HW*A)/(HW*A)*300;
        }else{
            base_score = 100;
        }
        behaviour_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.489f));
        total_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.1198f));
        time = new Timestamp(System.currentTimeMillis());
        Water_intake water = new Water_intake(DW,HW,A,AWI,base_score,behaviour_score,total_score,date,time);
//        System.out.println("饮水量基础得分"+water.getBase_score());
//        System.out.println("饮水量表现得分"+water.getBehaviour_score());
//        System.out.println("饮水量总得分"+water.getTotal_score());
        return water;
    }

    // 产蛋率
    private EggInfo calculate_Egg_production(String layegg) {
        float rate=Float.parseFloat(layegg);
        float APE;
        float base_score=0;
        float production_score = 0;
        float total_score = 0;
        String date = null;
        Timestamp time;

        APE = 87;
        date = timeUtils.get_YMD_HMS_Time()[0];
//        if(rate>=APE){
//            base_score = 100;
//        }else{
//            base_score=100-Math.abs(rate-APE)*10;
//        }
        base_score = 100;
        production_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.316f));
//        production_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.278f));
//        total_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.0703f));
        total_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.1547f));
        time = new Timestamp(System.currentTimeMillis());
        EggInfo eggInfo = new EggInfo(78541, 86155, 86155, APE,  rate, production_score, total_score, date, time);
        System.out.println(eggInfo);
        return eggInfo;
    }

    // 畸形蛋
//    private Deformed_eggs calculate_Deformed(InputInfo inputInfoList) {
//        float J;
//        float D;
//        float P;
//        float base_score=0;
//        float production_score = 0;
//        float total_score = 0;
//        String date = null;
//        Timestamp time;
//
//        if (inputInfoList.getJ().equals("")){
//            J = 0;
//        }else{
//            J = Float.parseFloat(inputInfoList.getJ());
//        }
//
//        if (inputInfoList.getD().equals("")){
//            D = 0;
//        }else{
//            D = Float.parseFloat(inputInfoList.getD());
//        }
//
//        if (inputInfoList.getP().equals("")){
//            P = 0;
//        }else{
//            P = Float.parseFloat(inputInfoList.getP());
//        }
//
//        if(D == 0.0){
//            base_score = 0;
//        }else{
//            float JX=J/D;
//            float PS=P/D;
//            base_score = 100-JX*100-PS*100;
//        }
//        date = timeUtils.get_YMD_HMS_Time()[0];
//        production_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.212f));
//        total_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.0536f));
//        time = new Timestamp(System.currentTimeMillis());
//        Deformed_eggs deformed_eggs = new Deformed_eggs(J,D,P,base_score,production_score,total_score,date,time);
//        return deformed_eggs;
//    }

    // 体重
//    private Weight calculate_weight(InputInfo inputInfoList) {
//        float TZ1;
//        float TZ2;
//        float base_score=0;
//        float production_score = 0;
//        float total_score = 0;
//        String date = null;
//        Timestamp time;
//
//        TZ1 = Float.parseFloat(inputInfoList.getTZ1());
//        TZ2 = Float.parseFloat(inputInfoList.getTZ2());
//        date = timeUtils.get_YMD_HMS_Time()[0];
//        base_score = 100-TZ1-2*TZ2;
//        production_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.257f));
//        total_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.065f));
//        time = new Timestamp(System.currentTimeMillis());
//        Weight weight = new Weight(TZ1,TZ2,base_score,production_score,total_score,date,time);
//        return weight;
//    }

    // 均匀度
//    private Uniformity calculate_uniformity(InputInfo inputInfoList) {
//        float U;
//        float base_score=0;
//        float production_score = 0;
//        float total_score = 0;
//        String date = null;
//        Timestamp time;
//
//        U = Float.parseFloat(inputInfoList.getU()) / 100;
//        date = timeUtils.get_YMD_HMS_Time()[0];
//        if(U>=0.9){
//            base_score = 100;
//        }else if(U>=0.84 && U<0.9){
//            base_score=90;
//        }else if(U>=0.77 && U<0.84){
//            base_score=75;
//        }else if(U>=0.7 && U<0.77){
//            base_score=60;
//        }else{
//            base_score=50;
//        }

//        production_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.249f));
//        total_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.063f));
//        time = new Timestamp(System.currentTimeMillis());
//        Uniformity uniformity = new Uniformity(U,base_score,production_score,total_score,date,time);
//        return uniformity;
//    }St

    // 平均蛋重
    private Average_egg calculate_average_egg(String awe) {
        float WAEW=Float.parseFloat(awe);
        float PAEW=64;
        float base_score=0;
        float production_score = 0;
        float total_score = 0;
        String date = null;
        Timestamp time;

        date = timeUtils.get_YMD_HMS_Time()[0];
//        if(WAEW>=PAEW){
//            base_score = 100;
//        }else{
//            base_score=100-(PAEW-WAEW)*10;
//        }
        base_score = 100;
        production_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.216f));
//        production_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.216f));
//        total_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.0547f));
        total_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.1547f));
        time = new Timestamp(System.currentTimeMillis());
        Average_egg average_egg = new Average_egg(WAEW,PAEW,base_score,production_score,total_score,date,time);
//        System.out.println(average_egg);
        return average_egg;
    }


    // 鸡舍环境-温度
    private Environmental_T Environmental_T(String temp) {
        float T; //温度
        float base_score;
        float behaviour_score = 0;
        float total_score = 0;
        String date = null;
        Timestamp time;

        T = Float.parseFloat(temp);  // 来自小巨人的数据
//        T = 23.8f;  // 来自小巨人的数据
        date = timeUtils.get_YMD_HMS_Time()[0];
        if(23<=T && T<=25){
            base_score = 100;
        }else if(T<18||T>29){
            base_score = 20;
        }else {
            base_score = 60;
        }

        behaviour_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.535f));
        total_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.1327f));
        time = new Timestamp(System.currentTimeMillis());
        Environmental_T environmental_T = new Environmental_T(T,base_score,behaviour_score,total_score,date,time);
//        System.out.println(environmental_T);
        return environmental_T;
    }

    //鸡舍环境-湿度
    private Environmental_H Environmental_H(String humi) {
        float H;//湿度
        float base_score;
        float behaviour_score = 0;
        float total_score = 0;
        String date = null;
        Timestamp time;

        H = Float.parseFloat(humi); // 来自小巨人的数据
//        H = 74; // 来自小巨人的数据
        date = timeUtils.get_YMD_HMS_Time()[0];
        if(55<=H && H<=65){
            base_score = 100;
        }else if(H<40||H>75){
            base_score = 20;
        }else {
            base_score = 60;
        }

        behaviour_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.465f));
        total_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.1153f));
        time = new Timestamp(System.currentTimeMillis());
        Environmental_H environmental_H = new Environmental_H(H,base_score,behaviour_score,total_score,date,time);
//        System.out.println(environmental_H);
        return environmental_H;
    }

    //鸡舍环境-co2
//    private Environmental_CO2 Environmental_CO2(InputInfo inputInfoList) {
//        float CO2;
//        float base_score;
//        float behaviour_score = 0;
//        float total_score = 0;
//        String date = null;
//        Timestamp time;
//
//        if(inputInfoList.getCO2().equals("")){
//            CO2 = 0;
//        }else{
//            CO2 = Float.parseFloat(inputInfoList.getCO2());
//        }
//
//        date = timeUtils.get_YMD_HMS_Time()[0];
//        if(CO2<=1500){
//            base_score = 100;
//        }else if(CO2>4000){
//            base_score = 20;
//        }else {
//            base_score = 60;
//        }
//
//        behaviour_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.225f));
//        total_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.0558f));
//        time = new Timestamp(System.currentTimeMillis());
//        Environmental_CO2 environmental_CO2 = new Environmental_CO2(CO2,base_score,behaviour_score,total_score,date,time);
//        return environmental_CO2;
//    }

    //鸡舍环境-nh3
//    private Environmental_NH3 Environmental_NH3(InputInfo inputInfoList) {
//        float NH3;
//        float base_score;
//        float behaviour_score = 0;
//        float total_score = 0;
//        String date = null;
//        Timestamp time;
//
//        if(inputInfoList.getNH3().equals("")){
//            NH3 = 0;
//        }else{
//            NH3 = Float.parseFloat(inputInfoList.getNH3());
//        }
//
//        date = timeUtils.get_YMD_HMS_Time()[0];
//        if(10<=NH3){
//            base_score = 20;
//        }else if(NH3<6){
//            base_score = 100;
//        }else {
//            base_score = 60;
//        }
//
//        behaviour_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.247f));
//        total_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.0613f));
//        time = new Timestamp(System.currentTimeMillis());
//        Environmental_NH3 environmental_nh3 = new Environmental_NH3(NH3,base_score,behaviour_score,total_score,date,time);
//        return environmental_nh3;
//    }

    // 存储结果
    private void keepResult(Deadweight_loss_rate dead_r, Quantity_of_food_intake food_i, Water_intake water_i, EggInfo eggInfo, Average_egg a_eggs, Environmental_T e_t, Environmental_H e_h) {
        detectionMapperYun.insertQuantity_of_food_intake(food_i);
        detectionMapperLocal.insertQuantity_of_food_intake(food_i);
//        System.out.println(food_i);
        System.out.println("已更新【采食量】得分");

        detectionMapperYun.insertDeadweight_loss_rateInfo(dead_r);
        detectionMapperLocal.insertDeadweight_loss_rateInfo(dead_r);
//        System.out.println(dead_r);
        System.out.println("已更新【死淘率】得分");

        detectionMapperYun.insertWater_intakeInfo(water_i);
        detectionMapperLocal.insertWater_intakeInfo(water_i);
//        System.out.println(water_i);
        System.out.println("已更新【饮水量】得分");

        detectionMapperYun.insert_eggInfo(eggInfo);
        detectionMapperLocal.insert_eggInfo(eggInfo);
//        System.out.println(eggInfo);
        System.out.println("已更新【产蛋率】得分");

//        detectionMapperYun.insertDeformed_eggsInfo(d_eggs);
//        detectionMapperLocal.insertDeformed_eggsInfo(d_eggs);
//        System.out.println("已更新【畸形蛋】得分");
//
//        detectionMapperYun.insertWeight(weight);
//        detectionMapperLocal.insertWeight(weight);
//        System.out.println("已更新【体重】得分");
//
//        detectionMapperYun.insertUniformity(uniformity);
//        detectionMapperLocal.insertUniformity(uniformity);
//        System.out.println("已更新【均匀度】得分");

        detectionMapperYun.insertAverage_egg(a_eggs);
        detectionMapperLocal.insertAverage_egg(a_eggs);
        System.out.println("已更新【平均蛋重】得分");

        detectionMapperYun.insertEnvironmental_T(e_t);
        detectionMapperLocal.insertEnvironmental_T(e_t);
        System.out.println("已更新【鸡舍环境-温度】得分");

        detectionMapperYun.insertEnvironmental_H(e_h);
        detectionMapperLocal.insertEnvironmental_H(e_h);
        System.out.println("已更新【鸡舍环境-湿度】得分");

//        detectionMapperYun.insertEnvironmental_CO2(e_co2);
//        detectionMapperLocal.insertEnvironmental_CO2(e_co2);
//        System.out.println("已更新【鸡舍环境-CO2】得分");
//
//        detectionMapperYun.insertEnvironmental_NH3(e_nh3);
//        detectionMapperLocal.insertEnvironmental_NH3(e_nh3);
//        System.out.println("已更新【鸡舍环境-NH3】得分");

    }

    // 推送分数
    private Score_New showScore(Deadweight_loss_rate dead_rate, Quantity_of_food_intake food_i, Water_intake water_i, EggInfo eggInfo, Average_egg a_eggs, Environmental_T e_t, Environmental_H e_h) {
        String temp = e_t.getT();//'温度'
        String temp_score = e_t.getBase_score();//'温度得分'
        temp_score = new DecimalFormat("#.##").format(Float.parseFloat(temp_score));
        String e_temp_score = e_t.getHealth_score();//'温度得分'
        e_temp_score = new DecimalFormat("#.##").format(Float.parseFloat(e_temp_score));
        String total_t_s = e_t.getTotal_score();//'温度总分'
        total_t_s = new DecimalFormat("#.##").format(Float.parseFloat(total_t_s));


        String hum = e_h.getH();//'湿度'
        String hum_score = e_h.getBase_score();// '湿度得分'
        hum_score = new DecimalFormat("#.##").format(Float.parseFloat(hum_score));
        String e_hum_score = e_h.getHealth_score();// '湿度得分'
        e_hum_score = new DecimalFormat("#.##").format(Float.parseFloat(e_hum_score));
        String total_h_s = e_h.getTotal_score();//'湿度总分'
        total_h_s = new DecimalFormat("#.##").format(Float.parseFloat(total_h_s));


//        String co2 = e_co2.getCO2();//'二氧化碳'
//        String co2_score = e_co2.getBase_score();//'二氧化碳得分'
//        co2_score = new DecimalFormat("#.##").format(Float.parseFloat(co2_score));
//        String e_co2_score = e_co2.getHealth_score();//'二氧化碳得分'
//        e_co2_score = new DecimalFormat("#.##").format(Float.parseFloat(e_co2_score));
//        String total_co2_s = e_co2.getTotal_score();//'二氧化碳总分'
//        total_co2_s = new DecimalFormat("#.##").format(Float.parseFloat(total_co2_s));
//
//
//        String nh3 = e_nh3.getNH3();// '氨气',
//        String nh3_score = e_nh3.getBase_score();//'氨气得分'
//        nh3_score = new DecimalFormat("#.##").format(Float.parseFloat(nh3_score));
//        String e_nh3_score = e_nh3.getHealth_score();//'氨气得分'
//        e_nh3_score = new DecimalFormat("#.##").format(Float.parseFloat(e_nh3_score));
//        String total_nh3_s = e_nh3.getTotal_score();//'氨气总分'
//        total_nh3_s = new DecimalFormat("#.##").format(Float.parseFloat(total_nh3_s));


//        String env_score = String.valueOf(Float.parseFloat(e_temp_score) + Float.parseFloat(e_hum_score) + Float.parseFloat(e_co2_score) + Float.parseFloat(e_nh3_score));//'环境分',
        String env_score = String.valueOf(Float.parseFloat(e_temp_score) + Float.parseFloat(e_hum_score));//'环境分',
        env_score = new DecimalFormat("#.##").format(Float.parseFloat(env_score));


//        String date = timeUtils.get_YMD_HMS_Time()[0];
        String date = "2024-4-15";
        List<SoundInfo> ss = null;
        try{
            ss = detectionMapperYun.selectSoundInfo(date);
        } catch (Exception e){
            ss = detectionMapperLocal.selectSoundInfo(date);
        }
        List<DropInfo> dd = null;
        try{
            dd = detectionMapperYun.selectDropInfo(date);
        } catch (Exception e){
            dd = detectionMapperLocal.selectDropInfo(date);
        }
        float cough = 0;
        float sum = 0;
        float s_s = 0;
        float h_s_s = 0;
        float t_s_s = 0;
        int water = 0;
        int loose = 0;
        int blood = 0;
        float d_s = 0;
        float h_d_s = 0;
        float t_d_s = 0;
        float s_score = 0;
        float d_score = 0;
        for (SoundInfo s: ss) {
            cough += Float.parseFloat(s.getCough());
            sum += Float.parseFloat(s.getSum());
            s_s += Float.parseFloat(s.getBase_score());
            h_s_s += Float.parseFloat(s.getHealth_score());
            t_s_s += Float.parseFloat(s.getTotal_score());
        }
        for (DropInfo d: dd) {
            water += Integer.parseInt(d.getWater());
            loose += Integer.parseInt(d.getLoose());
            blood += Integer.parseInt(d.getBlood());
            d_s += Float.parseFloat(d.getBase_score());
            h_d_s += Float.parseFloat(d.getHealth_score());
            t_d_s += Float.parseFloat(d.getTotal_score());
        }

        float rate = cough / sum;
        if(0 <= rate && rate < 0.2) {
            s_score = 100;
        }else if(0.2 <= rate && rate < 0.8) {
            s_score = 60;
        }else {
            s_score = 20;
        }
        String sound = String.valueOf(rate);//'声音异常占比'
        sound = new DecimalFormat("#.##").format(Float.parseFloat(sound)*100);
        String sound_score = String.valueOf(s_score);
        sound_score = new DecimalFormat("#.##").format(Float.parseFloat(sound_score));
        String h_sound_score = String.valueOf(s_score * 0.33f);
        h_sound_score = new DecimalFormat("#.##").format(Float.parseFloat(h_sound_score));
        String t_sound_score = String.valueOf(s_score * 0.0838f);
        t_sound_score = new DecimalFormat("#.##").format(Float.parseFloat(t_sound_score));


        String bs = String.valueOf(blood);//'血便'
        String ws = String.valueOf(loose);//'稀便'
        String ww = String.valueOf(water);//'水便'

        String soil_rate = String.valueOf((blood +  loose + water) / 86155);
        soil_rate = new DecimalFormat("#.###").format(Float.parseFloat(soil_rate) * 100.0f);
        d_score = 100.0f - Float.parseFloat(soil_rate);
        String soil_score = String.valueOf(d_score); //'粪便得分'
        soil_score = new DecimalFormat("#.##").format(Float.parseFloat(soil_score));
        String h_soil_score = String.valueOf(d_score * 0.345f);
        h_soil_score = new DecimalFormat("#.##").format(Float.parseFloat(h_soil_score));
        String t_soil_score = String.valueOf(d_score * 0.0876f);
        t_soil_score = new DecimalFormat("#.##").format(Float.parseFloat(t_soil_score));


        String dead = dead_rate.getM(); //'死淘数'
        float A = Float.parseFloat(dead_rate.getA());
        float M = Float.parseFloat(dead_rate.getM());
        String dead_r = String.valueOf(new DecimalFormat("#.##").format(M/A*100));// '死淘率'
        String dead_score = dead_rate.getBase_score();//'死淘分'
        dead_score = new DecimalFormat("#.##").format(Float.parseFloat(dead_score));
        String h_dead_score = dead_rate.getHealth_score();//'死淘分'
        h_dead_score = new DecimalFormat("#.##").format(Float.parseFloat(h_dead_score));
        String t_dead_score = dead_rate.getTotal_score();//'死淘分'
        t_dead_score = new DecimalFormat("#.##").format(Float.parseFloat(t_dead_score));


        String health_score = String.valueOf(Float.parseFloat(h_sound_score) + Float.parseFloat(h_soil_score) + Float.parseFloat(h_dead_score));//'健康检测',
        health_score = new DecimalFormat("#.##").format(Float.parseFloat(health_score));


        String layegg = eggInfo.getCD();//'产蛋率'
        layegg = new DecimalFormat("#.#").format(Float.parseFloat(layegg));
        String APE = eggInfo.getAPE();
        float base = 0;
        if(Float.parseFloat(layegg)>=Float.parseFloat(APE)){
            base = 100;
        }else{
            base=100-Math.abs(Float.parseFloat(layegg)-Float.parseFloat(APE))*10;
        }
        String layegg_base_score = String.valueOf(base);
        String i_layegg_score = eggInfo.getProduction_score();//'产蛋率得分'
        i_layegg_score = new DecimalFormat("#.##").format(Float.parseFloat(i_layegg_score));
        String t_layegg_score = eggInfo.getTotal_score();//'产蛋率得分'
        t_layegg_score = new DecimalFormat("#.##").format(Float.parseFloat(t_layegg_score));


//        float J = Float.parseFloat(d_eggs.getJ());
//        float D = Float.parseFloat(d_eggs.getD());
//        float P = Float.parseFloat(d_eggs.getP());
//        String dys = null;
//        String damage = null;
//        if (D == 0.0){
//            dys = "0.0";// 畸形蛋率
//            damage = "0.0";;// 破损蛋率
//        }else{
//            dys = String.valueOf(J/D);// 畸形蛋率
//            damage = String.valueOf(P/D);// 破损蛋率
//        }
//        String dys_score = d_eggs.getBase_score();// '畸形得分'
//        dys_score = new DecimalFormat("#.##").format(Float.parseFloat(dys_score));
//        String p_dys_score = d_eggs.getProduction_score();// '畸形得分'
//        p_dys_score = new DecimalFormat("#.##").format(Float.parseFloat(p_dys_score));
//        String t_dys_score = d_eggs.getTotal_score();// '畸形得分'
//        t_dys_score = new DecimalFormat("#.##").format(Float.parseFloat(t_dys_score));


        String awe = a_eggs.getWAEW(); //'平均蛋重',
        String awe_score = a_eggs.getBase_score();//'平均蛋重得分',
        awe_score = new DecimalFormat("#.##").format(Float.parseFloat(awe_score));
        String p_awe_score = a_eggs.getProduction_score();//'平均蛋重得分',
        p_awe_score = new DecimalFormat("#.##").format(Float.parseFloat(p_awe_score));
        String t_awe_score = a_eggs.getTotal_score();//'平均蛋重得分',
        t_awe_score = new DecimalFormat("#.##").format(Float.parseFloat(t_awe_score));


//        String weight = weights.getTZ1();// '体重',
//        String weight_score = weights.getBase_score(); //'体重得分',
//        weight_score = new DecimalFormat("#.##").format(Float.parseFloat(weight_score));
//        String p_weight_score = weights.getProduction_score(); //'体重得分',
//        p_weight_score = new DecimalFormat("#.##").format(Float.parseFloat(p_weight_score));
//        String t_weight_score = weights.getTotal_score(); //'体重得分',
//        t_weight_score = new DecimalFormat("#.##").format(Float.parseFloat(t_weight_score));
//
//
//        String eve = uniformity.getU(); //'均匀度',
//        String eve_score = uniformity.getBase_score();//均匀度得分',
//        eve_score = new DecimalFormat("#.##").format(Float.parseFloat(eve_score));
//        String p_eve_score = uniformity.getProduction_score();//均匀度得分',
//        p_eve_score = new DecimalFormat("#.##").format(Float.parseFloat(p_eve_score));
//        String t_eve_score = uniformity.getTotal_score();//均匀度得分',
//        t_eve_score = new DecimalFormat("#.##").format(Float.parseFloat(t_eve_score));


//        String information_score = String.valueOf(Float.parseFloat(i_layegg_score) + Float.parseFloat(p_dys_score) + Float.parseFloat(p_awe_score) + Float.parseFloat(p_weight_score) + Float.parseFloat(p_eve_score));//'生产信息分',
        String information_score = String.valueOf(Float.parseFloat(i_layegg_score) + Float.parseFloat(p_awe_score));//'生产信息分',
        information_score = new DecimalFormat("#.##").format(Float.parseFloat(information_score));


        String drink = water_i.getAWI();//'今日耗水量',
        String drink_score = water_i.getBase_score();//'饮水得分',
        drink_score = new DecimalFormat("#.##").format(Float.parseFloat(drink_score));
        String b_drink_score = water_i.getBehaviour_score();//'饮水得分',
        b_drink_score = new DecimalFormat("#.##").format(Float.parseFloat(b_drink_score));
        String t_drink_score = water_i.getTotal_score();//'饮水得分',
        t_drink_score = new DecimalFormat("#.##").format(Float.parseFloat(t_drink_score));


        String feed = food_i.getAFI();// '今日饲料量',
        String feed_score = food_i.getBase_score();//'采食得分',
        feed_score = new DecimalFormat("#.##").format(Float.parseFloat(feed_score));
        String b_feed_score = food_i.getBehaviour_score();//'采食得分',
        b_feed_score = new DecimalFormat("#.##").format(Float.parseFloat(b_feed_score));
        String t_feed_score = food_i.getTotal_score();//'采食得分',
        t_feed_score = new DecimalFormat("#.##").format(Float.parseFloat(t_feed_score));


        String behavior_score = String.valueOf(Float.parseFloat(b_drink_score) + Float.parseFloat(b_feed_score)); //'行为表现得分',
        behavior_score = new DecimalFormat("#.##").format(Float.parseFloat(behavior_score));


        String total_score = String.valueOf(Float.parseFloat(total_t_s) + Float.parseFloat(total_h_s)
                + Float.parseFloat(t_sound_score) + Float.parseFloat(t_soil_score) + Float.parseFloat(t_dead_score) + Float.parseFloat(t_layegg_score)
                + Float.parseFloat(t_awe_score) + Float.parseFloat(t_drink_score) + Float.parseFloat(t_feed_score)); //'总分',
        total_score = new DecimalFormat("#.##").format(Float.parseFloat(total_score));


        Timestamp time = new Timestamp(System.currentTimeMillis());
        Score_New score_new= new Score_New(env_score,health_score,information_score,behavior_score,total_score);
//        Score score = new Score(temp, temp_score, hum, hum_score, env_score, health_score, sound, sound_score, dead, dead_r, dead_score, bs, ws, ww, soil_rate, soil_score, information_score, layegg, layegg_base_score, awe, awe_score, behavior_score, drink, drink_score, feed, feed_score, total_score, time);
//
//        detectionMapperYun.insert_Score(score);
//        detectionMapperLocal.insert_Score(score);
          detectionMapperYun.insert_score_new(score_new);
          detectionMapperLocal.insert_score_new(score_new);

        System.out.println("已更新【Score】总得分");
//        System.out.println(score);
        return score_new;
    }
}
