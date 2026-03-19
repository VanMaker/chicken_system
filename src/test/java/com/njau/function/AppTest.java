//package com.njau.function;
//
//import com.njau.entity.InputInfo;
//import com.njau.mapper.DetectionMapper;
//import com.njau.utils.ErrorUtils;
//import com.njau.utils.MapperUtils;
//import org.apache.ibatis.session.SqlSession;
//import org.junit.Test;
//
///**
// * Unit test for simple App.
// */
//public class AppTest
//{
//    SqlSession sqlSessionYun = MapperUtils.getSqlSession("yun");
//    SqlSession sqlSessionLocal = MapperUtils.getSqlSession("localhost");
//    DetectionMapper detectionMapperYun = sqlSessionYun.getMapper(DetectionMapper.class);
//    DetectionMapper detectionMapperLocal = sqlSessionLocal.getMapper(DetectionMapper.class);
//    /**
//     * Rigorous Test :-)
//     */
//    @Test
//    public void shouldAnswerWithTrue() throws ErrorUtils {
//
////        HttpRequestUtils hu = new HttpRequestUtils();
////        String[] auto_infos = hu.Device_Propertys(EquipInfo.houseId4, EquipInfo.houseName4);
////        InputInfo inputInfoList = null;
////        try{
////            inputInfoList = detectionMapperYun.selectInputInfo();
////        } catch (Exception e){
////            inputInfoList = detectionMapperLocal.selectInputInfo();
////        }
////        System.out.println(inputInfoList);
//        InputInfo inputInfoList = null;
//        float J = 0;
////        try{
//        inputInfoList = detectionMapperYun.selectInputInfo();
////        } catch (Exception e){
////            inputInfoList = detectionMapperLocal.selectInputInfo();
////        }
//
//        if (inputInfoList.getJ().equals("")){
//            J = 0;
//        }else{
//            J = Float.parseFloat(inputInfoList.getJ());
//        }
//        System.out.println(J);
//
//
//
//
//
////        HttpRequestUtils httpRequestUtils = new HttpRequestUtils();
////        ConfigUtils configUtils = new ConfigUtils();
////        String house_id = null;
////        String house_name = null;
////        try {
////            house_id = configUtils.getProperties("house_id");
////            house_name = configUtils.getProperties("house_name");
////        } catch (ErrorUtils e) {
////            e.printStackTrace();
////        }
////        String[] auto_infos = httpRequestUtils.Device_Propertys(house_id, house_name);
////        String temp = auto_infos[1];
////        String humi = auto_infos[47];
////        String water_intake = auto_infos[108];
////        String food_intake = auto_infos[111];
////        System.out.println(temp);
////        System.out.println(humi);
////        System.out.println(water_intake);
////        System.out.println(food_intake);
////
//
//
////        ConfigUtils cu = new ConfigUtils();
////        String[] modules = null;
////        String[] drop_channels = null;
////        String[] voice_channels = null;
////        String[] channels = null;
////        HashMap<String, Integer> downloadTime = null;
////        try {
////            modules = cu.getProperties("modules").split(",");
////            drop_channels = cu.getProperties("drop_channels").split(",");
////            voice_channels = cu.getProperties("voice_channels").split(",");
////        } catch (ErrorUtils e) {
////            e.printStackTrace();
////        }
////        Date nowTime = new Date(System.currentTimeMillis());
////        String[] times = new TimeUtils().get_YMD_HMS_Time();
////        String YMDTime = times[0];
////        String HMSTime = times[1];
////        System.out.println("开始下载时间：" + YMDTime + " " + HMSTime);
////        for (String module : modules) {
//////            downloadTime = getDownloadTime(nowTime, module);
////            if ("drop".equals(module)) {
////                channels = drop_channels;
////            } else if ("voice".equals(module)) {
////                channels = voice_channels;
////            }
////            for (String channel : channels) {
////                System.out.println(module);
////                System.out.println(Integer.parseInt(channel));
//////                download(userID, Integer.parseInt(channel), module, downloadTime);
////            }
////        }
//
////
//
//
//
//
//
//
//
//
//////        Date nowTime = new Date(System.currentTimeMillis());
//////        String[] times = TimeUtils.getTime(nowTime);
//////        String YMDTime = times[0];
////
////        InputInfo inputInfoList = null;
////        try{
////            inputInfoList = detectionMapperYun.selectInputInfo();
////        } catch (Exception e){
////            inputInfoList = detectionMapperLocal.selectInputInfo();
////        }
////        float J;
////        float D;
////        float P;
////        float base_score=0;
////        float production_score = 0;
////        float total_score = 0;
////        String date = null;
////        Timestamp time;
////
////        J = Float.parseFloat(inputInfoList.getJ());
////        D = Float.parseFloat(inputInfoList.getD());
////        P = Float.parseFloat(inputInfoList.getP());
////        if(D == 0.0){
////            base_score = 0;
////        }else{
////            float JX=J/D;
////            float PS=P/D;
////            base_score = 100-JX*100-PS*100;
////        }
////        date = inputInfoList.getDate();
////            production_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.212f));
////            total_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.0536f));
////            time = new Timestamp(System.currentTimeMillis());
////            Deformed_eggs deformed_eggs = new Deformed_eggs(J,D,P,base_score,production_score,total_score,date,time);
//////            detectionMapperYun.insertDeformed_eggsInfo(deformed_eggs);
//////            detectionMapperLocal.insertDeformed_eggsInfo(deformed_eggs);
////            System.out.println(base_score);
//
//
////        System.out.println(YMDTime);
//
//
////        Deadweight_loss_rate dead_r= calculate_Deadweight_loss_rate();//死淘汰率
////        Quantity_of_food_intake food_i = calculate_quantity_of_food_intake();//采食量
////        Water_intake water_i = calculate_Water_intake();//饮水量
////        EggInfo eggInfo = calculate_Egg_production();//产蛋率
////        Deformed_eggs d_eggs = calculate_Deformed();//畸形蛋
////        Weight weight = calculate_weight();//体重
////        Uniformity uniformity = calculate_uniformity();//均匀度
////        Average_egg a_eggs = calculate_average_egg();//平均蛋重
////        Environmental_T e_t = Environmental_T();
////        Environmental_H e_h = Environmental_H();
////        Environmental_CO2 e_co2 = Environmental_CO2();
////        Environmental_NH3 e_nh3 = Environmental_NH3();
////        //存储结果
////        saveResults(dead_r, food_i, water_i, eggInfo, d_eggs, weight, uniformity, a_eggs, e_t, e_h, e_co2, e_nh3);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////        String date = "2021-4-29";
////        List<SoundInfo> ss = detectionMapper.selectSoundInfo(date);
////        for (SoundInfo s: ss) {
////            System.out.println(s);
////        }
////        List<DropInfo> dd = detectionMapper.selectDropInfo(date);
////        for (DropInfo d: dd) {
////            System.out.println(d);
////        }
//
//
////        float TZ1;
////        float TZ2;
////        float base_score=0;
////        float production_score = 0;
////        float total_score = 0;
////        String date = null;
////        Timestamp time;
////        float AWE;
////        float C;
////        float AWC;
////        float APE;
////        float base_score=0;
////        float production_score = 0;
////        float total_score = 0;
////        String date = null;
////        Timestamp time;
////
////        InputInfo inputInfoList = detectionMapper.selectInputInfo();
////        AWE = Float.parseFloat(inputInfoList.getAWE());
////        C = Float.parseFloat(inputInfoList.getC());
////        AWC = Float.parseFloat(inputInfoList.getAWC());
////        APE = Float.parseFloat(inputInfoList.getAPE());
////        date = inputInfoList.getDate();
////        float num=(C+AWC)/2*7;
////        float CD=AWE/num;
////        if(CD>=APE){
////            base_score = 100;
////        }else{
////            base_score=100-Math.abs(CD-APE)*1000;
////        }
////        production_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.219f));
////        total_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.0554f));
////        time = new Timestamp(System.currentTimeMillis());
////        EggInfo eggInfo = new EggInfo(AWE, C, AWC, APE,  CD, production_score, total_score, date, time);
////        detectionMapper.insert_eggInfo(eggInfo);
////        TZ1 = Float.parseFloat(inputInfoList.getTZ1());
////        TZ2 = Float.parseFloat(inputInfoList.getTZ2());
////        date = inputInfoList.getDate();
////        base_score = 100-TZ1*100-2*TZ2*100;
////        production_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.203f));
////        total_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.0514f));
////        time = new Timestamp(System.currentTimeMillis());
////        Weight weight = new Weight(TZ1,TZ2,base_score,production_score,total_score,date,time);
////        System.out.println(weight);
////        detectionMapper.insertWeight(weight);
////        System.out.println("已更新体重得分");
//
//
//
////        System.out.println("hello world");
////        Date nowTime = new Date(System.currentTimeMillis());
////        Calendar calendar = Calendar.getInstance();
////        calendar.setTime(nowTime); // 设置为当前时间
////        calendar.set(Calendar.MONTH, 3);
////
////        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime()));
////        String YMDTime = new SimpleDateFormat("yyyy-MM-dd").format(nowTime);
////        String HMSTime = new SimpleDateFormat("hh-mm-ss").format(nowTime);
////        System.out.println("开始下载时间：" + YMDTime + " " + HMSTime);
////        String sFileName = System.getProperty("user.dir").replace("TransferData", "Detection_Data") +"/" + "drop" + "/" + YMDTime + "/"+ HMSTime + ".mp4";
////        System.out.println("文件存储路径："+ sFileName); //输出路径
////
////
////        float M;
////        float A;
////        float ST;
////        float base_health;
////        float health_score = 0;
////        float total_score = 0;
////        String date = null;
////        Timestamp time;
////        DetectionMapper detectionMapper = null;
////        InputInfo inputInfoList = detectionMapper.selectInputInfo();
////        A = Float.parseFloat(inputInfoList.getA4());
////        M = Float.parseFloat(inputInfoList.getM4());
////        date = inputInfoList.getDate();
////        ST = Float.parseFloat(new DecimalFormat("#.###").format(M/A));
////        if(ST<=0.002){
////            base_health = 100;
////        }else {
////            base_health = (float) (100-(ST-0.2)/0.1*15);
////        }
////        time = new Timestamp(System.currentTimeMillis());
////        Deadweight_loss_rate deadweight_loss_rate = new Deadweight_loss_rate(M,A,base_health,health_score,total_score,date,time);
////        detectionMapper.insertDeadweight_loss_rateInfo(deadweight_loss_rate);
//
////         数据库连接信息
////        String driver="com.mysql.cj.jdbc.Driver";
////        String url = "jdbc:mysql://localhost:3306/wens_sub_health?useSSL=false";
////        String username = "root";
////        String password = "njau#B307";
////
////        // SQL插入语句
////        String sql = "insert into sound_info (channel, equip, name, cough, chirp, sum, health_score, total_score, date) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
////        try (
////                // 建立数据库连接
////                Connection conn = DriverManager.getConnection(url, username, password);
////                // 创建PreparedStatement对象，用于执行SQL语句
////                PreparedStatement pstmt = conn.prepareStatement(sql)
////        ) {
////            // 设置参数值
////            pstmt.setString(1, "33");
////            pstmt.setString(2, "1号位拾音器");
////            pstmt.setString(3, "33@2024-3-9@19-58-0.wav");
////            pstmt.setString(4, "15");
////            pstmt.setString(5, "38");
////            pstmt.setString(6, "53");
////            pstmt.setString(7, "19.8");
////            pstmt.setString(8, "5.03");
////            pstmt.setString(9, "2024-3-9");
////
////            // 执行SQL语句
////            int rowsInserted = pstmt.executeUpdate();
////            if (rowsInserted > 0) {
////                System.out.println("数据插入成功！");
////            } else {
////                System.out.println("数据插入失败！");
////            }
////        } catch (Exception e) {
////            System.out.println("数据库连接或执行SQL语句出错！");
////            e.printStackTrace();
////        }
////        File result = new File("/hdda/projects/system/Wens_Detection_System/Wens_Broiler_Detection/Result_Detection/voice/33@2024-3-9@19-58-0/result.txt");
////        FileInputStream fis = new FileInputStream(result);
////        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
////        BufferedReader br = new BufferedReader(isr);
////        String line;
////        int cough = 0;
////        int chirp = 0;
////        int sum = 0;
////        int score = 0;
////        String health_score = null;
////        String total_score = null;
////        String filename = null;
////        //解析每一行json，封装成一个对象
////        while ((line = br.readLine()) != null) {
////            if (line.contains("{")) {
////                JSONObject jsonObject = new JSONObject(line);
////                filename = jsonObject.getString("name");
////                //每一行的字段进行累加
////                cough = cough + Integer.parseInt(jsonObject.getString("cough"));
////                chirp = chirp + Integer.parseInt(jsonObject.getString("chirp"));
////                sum = sum + Integer.parseInt(jsonObject.getString("sum"));
////            }
////        }
////
////        System.out.println(sum);
//
//
//    }
//
//    // 采食量
////    private Quantity_of_food_intake calculate_quantity_of_food_intake() {
////        float AFI;
////        float FI;
////        float base_health;
////        float behaviour_score = 0;
////        float total_score = 0;
////        String date = null;
////        Timestamp time;
////
////        InputInfo inputInfoList = null;
////        try{
////            inputInfoList = detectionMapperYun.selectInputInfo();
////        } catch (Exception e){
////            inputInfoList = detectionMapperLocal.selectInputInfo();
////        }
////
////        AFI = Float.parseFloat(inputInfoList.getAFI());
////        FI = Float.parseFloat(inputInfoList.getFI());
////        date = inputInfoList.getDate();
////
////        base_health = 100 - Math.abs(AFI-FI)/FI*100*3;
////        behaviour_score = Float.parseFloat(new DecimalFormat("#.###").format(base_health * 0.511f));
////        total_score = Float.parseFloat(new DecimalFormat("#.###").format(base_health * 0.1252f));
////        time = new Timestamp(System.currentTimeMillis());
////        Quantity_of_food_intake quantity_of_food_intake = new Quantity_of_food_intake(AFI,FI,base_health,behaviour_score,total_score,date,time);
////        detectionMapperYun.insertQuantity_of_food_intake(quantity_of_food_intake);
////        detectionMapperLocal.insertQuantity_of_food_intake(quantity_of_food_intake);
////        System.out.println("已更新【采食量】得分");
////        return quantity_of_food_intake;
////    }
////
////    // 死淘率
////    private Deadweight_loss_rate calculate_Deadweight_loss_rate() {
////        float M;
////        float A;
////        float ST;
////        float base_health;
////        float health_score = 0;
////        float total_score = 0;
////        String date = null;
////        Timestamp time;
////
////        InputInfo inputInfoList = null;
////        try{
////            inputInfoList = detectionMapperYun.selectInputInfo();
////        } catch (Exception e){
////            inputInfoList = detectionMapperLocal.selectInputInfo();
////        }
////
////        A = Float.parseFloat(inputInfoList.getA4());
////        M = Float.parseFloat(inputInfoList.getM4());
////        date = inputInfoList.getDate();
////        ST = Float.parseFloat(new DecimalFormat("#.###").format(M/A));
////        if(ST<=0.002){
////            base_health = 100;
////        }else {
////            base_health = (float) (100-(ST-0.2)/0.1*15);
////        }
////        health_score = Float.parseFloat(new DecimalFormat("#.###").format(base_health * 0.325f));
////        total_score = Float.parseFloat(new DecimalFormat("#.###").format(base_health * 0.0876f));
////        time = new Timestamp(System.currentTimeMillis());
////        Deadweight_loss_rate deadweight_loss_rate = new Deadweight_loss_rate(M,A,base_health,health_score,total_score,date,time);
////        detectionMapperYun.insertDeadweight_loss_rateInfo(deadweight_loss_rate);
////        detectionMapperLocal.insertDeadweight_loss_rateInfo(deadweight_loss_rate);
////        System.out.println("已更新【死淘率】得分");
////        return deadweight_loss_rate;
////    }
////
////    // 饮水量
////    private Water_intake calculate_Water_intake() {
////        float DW;
////        float HW;
////        float AWI;
////        float A;
////        float base_score=0;
////        float behaviour_score = 0;
////        float total_score = 0;
////        String date = null;
////        Timestamp time;
////
////        InputInfo inputInfoList = null;
////        try{
////            inputInfoList = detectionMapperYun.selectInputInfo();
////        } catch (Exception e){
////            inputInfoList = detectionMapperLocal.selectInputInfo();
////        }
////
////        DW = Float.parseFloat(inputInfoList.getDW());
////        HW = Float.parseFloat(inputInfoList.getHW());
////        date = inputInfoList.getDate();
////        AWI = Float.parseFloat(inputInfoList.getAWI());
////        A = Float.parseFloat(inputInfoList.getA());
////        if(AWI<DW*A){
////            base_score = 100-Math.abs(AWI-DW*A)/(DW*A)*300;
////        }else if(AWI<HW*A){
////            base_score = 100-Math.abs(AWI-HW*A)/(HW*A)*300;
////        }
////        behaviour_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.489f));
////        total_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.1198f));
////        time = new Timestamp(System.currentTimeMillis());
////        Water_intake water_intake = new Water_intake(DW,HW,A,AWI,base_score,behaviour_score,total_score,date,time);
////        detectionMapperYun.insertWater_intakeInfo(water_intake);
////        detectionMapperLocal.insertWater_intakeInfo(water_intake);
////        System.out.println("已更新【饮水量】得分");
////        return water_intake;
////    }
////
////    // 产蛋率
////    private EggInfo calculate_Egg_production() {
////        float AWE;
////        float C;
////        float AWC;
////        float APE;
////        float base_score=0;
////        float production_score = 0;
////        float total_score = 0;
////        String date = null;
////        Timestamp time;
////
////        InputInfo inputInfoList = null;
////        try{
////            inputInfoList = detectionMapperYun.selectInputInfo();
////        } catch (Exception e){
////            inputInfoList = detectionMapperLocal.selectInputInfo();
////        }
////
////        AWE = Float.parseFloat(inputInfoList.getAWE());
////        C = Float.parseFloat(inputInfoList.getC());
////        AWC = Float.parseFloat(inputInfoList.getAWC());
////        APE = Float.parseFloat(inputInfoList.getAPE());
////        date = inputInfoList.getDate();
////        float num=(C+AWC)/2*7;
////        float CD=AWE/num;
////        if(CD>=APE){
////            base_score = 100;
////        }else{
////            base_score=100-Math.abs(CD-APE)*1000;
////        }
////        production_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.219f));
////        total_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.0554f));
////        time = new Timestamp(System.currentTimeMillis());
////        EggInfo eggInfo = new EggInfo(AWE, C, AWC, APE,  CD, production_score, total_score, date, time);
////        detectionMapperYun.insert_eggInfo(eggInfo);
////        detectionMapperLocal.insert_eggInfo(eggInfo);
////        System.out.println("已更新【产蛋率】得分");
////        return eggInfo;
////    }
////
////    // 畸形蛋
////    private Deformed_eggs calculate_Deformed() {
////        float J;
////        float D;
////        float P;
////        float base_score=0;
////        float production_score = 0;
////        float total_score = 0;
////        String date = null;
////        Timestamp time;
////
////        InputInfo inputInfoList = null;
////        try{
////            inputInfoList = detectionMapperYun.selectInputInfo();
////        } catch (Exception e){
////            inputInfoList = detectionMapperLocal.selectInputInfo();
////        }
////
////        J = Float.parseFloat(inputInfoList.getJ());
////        D = Float.parseFloat(inputInfoList.getD());
////        P = Float.parseFloat(inputInfoList.getP());
////        date = inputInfoList.getDate();
////        float JX=J/D;
////        float PS=P/D;
////        base_score = 100-JX*100-PS*100;
////        production_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.212f));
////        total_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.0536f));
////        time = new Timestamp(System.currentTimeMillis());
////        Deformed_eggs deformed_eggs = new Deformed_eggs(J,D,P,base_score,production_score,total_score,date,time);
////        detectionMapperYun.insertDeformed_eggsInfo(deformed_eggs);
////        detectionMapperLocal.insertDeformed_eggsInfo(deformed_eggs);
////        System.out.println("已更新【畸形蛋】得分");
////        return deformed_eggs;
////    }
////
////    // 体重
////    private Weight calculate_weight() {
////        float TZ1;
////        float TZ2;
////        float base_score=0;
////        float production_score = 0;
////        float total_score = 0;
////        String date = null;
////        Timestamp time;
////
////        InputInfo inputInfoList = null;
////        try{
////            inputInfoList = detectionMapperYun.selectInputInfo();
////        } catch (Exception e){
////            inputInfoList = detectionMapperLocal.selectInputInfo();
////        }
////
////        TZ1 = Float.parseFloat(inputInfoList.getTZ1());
////        TZ2 = Float.parseFloat(inputInfoList.getTZ2());
////        date = inputInfoList.getDate();
////        base_score = 100-TZ1*100-2*TZ2*100;
////        production_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.203f));
////        total_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.0514f));
////        time = new Timestamp(System.currentTimeMillis());
////        Weight weight = new Weight(TZ1,TZ2,base_score,production_score,total_score,date,time);
////        detectionMapperYun.insertWeight(weight);
////        detectionMapperLocal.insertWeight(weight);
////        System.out.println("已更新【体重】得分");
////        return weight;
////    }
////
////    // 均匀度
////    private Uniformity calculate_uniformity() {
////        float U;
////        float base_score=0;
////        float production_score = 0;
////        float total_score = 0;
////        String date = null;
////        Timestamp time;
////
////        InputInfo inputInfoList = null;
////        try{
////            inputInfoList = detectionMapperYun.selectInputInfo();
////        } catch (Exception e){
////            inputInfoList = detectionMapperLocal.selectInputInfo();
////        }
////
////        U = Float.parseFloat(inputInfoList.getU());
////        date = inputInfoList.getDate();
////        if(U>=0.9){
////            base_score = 100;
////        }else if(U>=0.84 && U<0.9){
////            base_score=90;
////        }else if(U>=0.77 && U<0.84){
////            base_score=75;
////        }else if(U>=0.7 && U<0.77){
////            base_score=60;
////        }else{
////            base_score=50;
////        }
////
////        production_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.196f));
////        total_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.0496f));
////        time = new Timestamp(System.currentTimeMillis());
////        Uniformity uniformity = new Uniformity(U,base_score,production_score,total_score,date,time);
////        detectionMapperYun.insertUniformity(uniformity);
////        detectionMapperLocal.insertUniformity(uniformity);
////        System.out.println("已更新【均匀度】得分");
////        return uniformity;
////    }
////
////    // 平均蛋重
////    private Average_egg calculate_average_egg() {
////        float WAEW;
////        float PAEW;
////        float base_score=0;
////        float production_score = 0;
////        float total_score = 0;
////        String date = null;
////        Timestamp time;
////
////        InputInfo inputInfoList = null;
////        try{
////            inputInfoList = detectionMapperYun.selectInputInfo();
////        } catch (Exception e){
////            inputInfoList = detectionMapperLocal.selectInputInfo();
////        }
////
////        WAEW= Float.parseFloat(inputInfoList.getWAEW());
////        PAEW= Float.parseFloat(inputInfoList.getPAEW());
////        date = inputInfoList.getDate();
////        if(WAEW>=PAEW){
////            base_score = 100;
////        }else{
////            base_score=100-(PAEW-WAEW)*10;
////        }
////        production_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.17f));
////        total_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.043f));
////        time = new Timestamp(System.currentTimeMillis());
////        Average_egg average_egg = new Average_egg(WAEW,PAEW,base_score,production_score,total_score,date,time);
////        detectionMapperYun.insertAverage_egg(average_egg);
////        detectionMapperLocal.insertAverage_egg(average_egg);
////        System.out.println("已更新【平均蛋重】得分");
////        return average_egg;
////    }
////
////    // 鸡舍环境-温度
////    private Environmental_T Environmental_T() {
////        float T; //温度
////        float base_score;
////        float behaviour_score = 0;
////        float total_score = 0;
////        String date = null;
////        Timestamp time;
////
////        InputInfo inputInfoList = null;
////        try{
////            inputInfoList = detectionMapperYun.selectInputInfo();
////        } catch (Exception e){
////            inputInfoList = detectionMapperLocal.selectInputInfo();
////        }
////
////        T = Float.parseFloat(inputInfoList.getT());
////        date = inputInfoList.getDate();
////        if(23<=T && T<=25){
////            base_score = 100;
////        }else if(T<18||T>29){
////            base_score = 20;
////        }else {
////            base_score = 60;
////        }
////
////        behaviour_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.283f));
////        total_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.0699f));
////        time = new Timestamp(System.currentTimeMillis());
////        Environmental_T environmental_T = new Environmental_T(T,base_score,behaviour_score,total_score,date,time);
////        detectionMapperYun.insertEnvironmental_T(environmental_T);
////        detectionMapperLocal.insertEnvironmental_T(environmental_T);
////        System.out.println("已更新【鸡舍环境-温度】得分");
////        return environmental_T;
////    }
////
////    //鸡舍环境-湿度
////    private Environmental_H Environmental_H() {
////        float H;//湿度
////        float base_score;
////        float behaviour_score = 0;
////        float total_score = 0;
////        String date = null;
////        Timestamp time;
////
////        InputInfo inputInfoList = null;
////        try{
////            inputInfoList = detectionMapperYun.selectInputInfo();
////        } catch (Exception e){
////            inputInfoList = detectionMapperLocal.selectInputInfo();
////        }
////
////        H = Float.parseFloat(inputInfoList.getH());
////        date = inputInfoList.getDate();
////        if(55<=H && H<=65){
////            base_score = 100;
////        }else if(H<40||H>75){
////            base_score = 20;
////        }else {
////            base_score = 60;
////        }
////
////        behaviour_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.246f));
////        total_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.0610f));
////        time = new Timestamp(System.currentTimeMillis());
////        Environmental_H environmental_H = new Environmental_H(H,base_score,behaviour_score,total_score,date,time);
////        detectionMapperYun.insertEnvironmental_H(environmental_H);
////        detectionMapperLocal.insertEnvironmental_H(environmental_H);
////        System.out.println("已更新【鸡舍环境-湿度】得分");
////        return environmental_H;
////    }
////
////    //鸡舍环境-co2
////    private Environmental_CO2 Environmental_CO2() {
////        float CO2;
////        float base_score;
////        float behaviour_score = 0;
////        float total_score = 0;
////        String date = null;
////        Timestamp time;
////
////        InputInfo inputInfoList = null;
////        try{
////            inputInfoList = detectionMapperYun.selectInputInfo();
////        } catch (Exception e){
////            inputInfoList = detectionMapperLocal.selectInputInfo();
////        }
////
////        CO2 = Float.parseFloat(inputInfoList.getCO2());
////        date = inputInfoList.getDate();
////        if(CO2<=1500){
////            base_score = 100;
////        }else if(CO2>4000){
////            base_score = 20;
////        }else {
////            base_score = 60;
////        }
////
////        behaviour_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.225f));
////        total_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.0558f));
////        time = new Timestamp(System.currentTimeMillis());
////        Environmental_CO2 environmental_CO2 = new Environmental_CO2(CO2,base_score,behaviour_score,total_score,date,time);
////        detectionMapperYun.insertEnvironmental_CO2(environmental_CO2);
////        detectionMapperLocal.insertEnvironmental_CO2(environmental_CO2);
////        System.out.println("已更新【鸡舍环境-CO2】得分");
////        return environmental_CO2;
////    }
////
////    //鸡舍环境-nh3
////    private Environmental_NH3 Environmental_NH3() {
////        float NH3;
////        float base_score;
////        float behaviour_score = 0;
////        float total_score = 0;
////        String date = null;
////        Timestamp time;
////
////        InputInfo inputInfoList = null;
////        try{
////            inputInfoList = detectionMapperYun.selectInputInfo();
////        } catch (Exception e){
////            inputInfoList = detectionMapperLocal.selectInputInfo();
////        }
////
////        NH3 = Float.parseFloat(inputInfoList.getNH3());
////        date = inputInfoList.getDate();
////        if(10<=NH3){
////            base_score = 20;
////        }else if(NH3<6){
////            base_score = 100;
////        }else {
////            base_score = 60;
////        }
////
////        behaviour_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.247f));
////        total_score = Float.parseFloat(new DecimalFormat("#.###").format(base_score * 0.0613f));
////        time = new Timestamp(System.currentTimeMillis());
////        Environmental_NH3 environmental_nh3 = new Environmental_NH3(NH3,base_score,behaviour_score,total_score,date,time);
////        detectionMapperYun.insertEnvironmental_NH3(environmental_nh3);
////        detectionMapperLocal.insertEnvironmental_NH3(environmental_nh3);
////        System.out.println("已更新【鸡舍环境-NH3】得分");
////        return environmental_nh3;
////    }
////
////    private void analysis(List<String> resultPaths, String module) {
////        if (resultPaths == null) {
////            return;
////        }
////        for (String resultPath : resultPaths) {
////            File result = new File(resultPath);
////            try {
////                FileInputStream fis = new FileInputStream(result);
////                InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
////                BufferedReader br = new BufferedReader(isr);
////                String line;
////                if ("voice".equals(module)) {
////                    int cough = 0;
////                    int chirp = 0;
////                    int sum = 0;
////                    int score = 0;
////                    String health_score = null;
////                    String total_score = null;
////                    String filename = null;
////                    //解析每一行json，封装成一个对象
////                    while ((line = br.readLine()) != null) {
////                        if (line.contains("{")) {
////                            JSONObject jsonObject = new JSONObject(line);
////                            filename = jsonObject.getString("name");
////                            //每一行的字段进行累加
////                            cough = cough + Integer.parseInt(jsonObject.getString("cough"));
////                            chirp = chirp + Integer.parseInt(jsonObject.getString("chirp"));
////                            sum = sum + Integer.parseInt(jsonObject.getString("sum"));
////                        }
////                    }
////                    String[] strs = filename.split("@");
////                    float rate = (float) cough/(float) sum;
////                    if(0 <= rate && rate < 0.2) {
////                        score = 100;
////                    }else if(0.2 <= rate && rate < 0.8) {
////                        score = 60;
////                    }else {
////                        score = 20;
////                    }
////                    health_score = new DecimalFormat("#.###").format(score * 0.33f);//基础分
////                    total_score = new DecimalFormat("#.###").format(score * 0.0838f);
////                    Timestamp time = new Timestamp(System.currentTimeMillis());
////                    SoundInfo soundInfo = new SoundInfo(strs[0], filename, String.valueOf(cough), String.valueOf(chirp), String.valueOf(sum),String.valueOf(score), health_score, total_score, strs[1], time);
////                    detectionMapperYun.insertSoundResult(soundInfo);
////                    detectionMapperLocal.insertSoundResult(soundInfo);
////                } else if ("drop".equals(module)) {
////                    int water = 0;
////                    int loose = 0;
////                    int blood = 0;
////                    int sum = 0;
////                    float score = 0;
////                    String health_score = null;
////                    String total_score = null;
////                    String filename = null;
////                    //解析每一行json，封装成一个对象
////                    while ((line = br.readLine()) != null) {
////                        if (line.contains("{")) {
////                            JSONObject jsonObject = new JSONObject(line);
////                            filename = jsonObject.getString("name");
////                            //每一行的字段进行累加
////                            water = water + Integer.parseInt(jsonObject.getString("water"));
////                            loose = loose + Integer.parseInt(jsonObject.getString("loose"));
////                            blood = blood + Integer.parseInt(jsonObject.getString("blood"));
////                            sum = sum + Integer.parseInt(jsonObject.getString("sum"));
////                        }
////                    }
////                    String[] strs = filename.split("@");
////                    score = 100.0f - (float) sum / 5000.0f * 100.0f;
////                    health_score = new DecimalFormat("#.###").format(score * 0.345f);
////                    total_score = new DecimalFormat("#.###").format(score * 0.0876f);
////                    Timestamp time = new Timestamp(System.currentTimeMillis());
////                    DropInfo dropInfo = new DropInfo(strs[0], filename, String.valueOf(water), String.valueOf(loose), String.valueOf(blood), String.valueOf(sum),String.valueOf(score), health_score, total_score, strs[1], time);
////                    detectionMapperYun.insertDropResult(dropInfo);
////                    detectionMapperLocal.insertDropResult(dropInfo);
////                }
////                br.close();
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////        }
////        System.out.println("已更新【"+ module +"】得分");
////    }
////
////    private void saveResults(Deadweight_loss_rate dead_rate, Quantity_of_food_intake food_i, Water_intake water_i, EggInfo eggInfo, Deformed_eggs d_eggs, Weight weights, Uniformity uniformity, Average_egg a_eggs, Environmental_T e_t, Environmental_H e_h, Environmental_CO2 e_co2, Environmental_NH3 e_nh3) {
////        String temp = e_t.getT();//'温度'
////        String temp_score = e_t.getHealth_score();//'温度得分'
////        String hum = e_h.getH();//'湿度'
////        String hum_score = e_h.getHealth_score();// '湿度得分'
////        String co2 = e_co2.getCO2();//'二氧化碳'
////        String co2_score = e_co2.getHealth_score();//'二氧化碳得分'
////        String nh3 = e_nh3.getNH3();// '氨气',
////        String nh3_score = e_nh3.getHealth_score();//'氨气得分'
////        String env_score = String.valueOf(Float.parseFloat(temp_score) + Float.parseFloat(hum_score) + Float.parseFloat(co2_score) + Float.parseFloat(nh3_score));//'环境分',
////        // ====================================================
////        Date nowTime = new Date(System.currentTimeMillis());
////        String[] times = TimeUtils.getTime(nowTime);
//////        String date = times[0];
////        String date = "2024-3-9";
////        List<SoundInfo> ss = null;
////        try{
////            ss = detectionMapperYun.selectSoundInfo(date);
////        } catch (Exception e){
////            ss = detectionMapperLocal.selectSoundInfo(date);
////        }
////        date = "2021-4-29";
////        List<DropInfo> dd = null;
////        try{
////            dd = detectionMapperYun.selectDropInfo(date);
////        } catch (Exception e){
////            dd = detectionMapperLocal.selectDropInfo(date);
////        }
////        float cough = 0;
////        float sum = 0;
////        float s_s = 0;
////        int water = 0;
////        int loose = 0;
////        int blood = 0;
////        float d_s = 0;
////        for (SoundInfo s: ss) {
////            cough += Float.parseFloat(s.getCough());
////            sum += Float.parseFloat(s.getSum());
////            s_s += Float.parseFloat(s.getHealth_score());
////        }
////        for (DropInfo d: dd) {
////            water += Integer.parseInt(d.getWater());
////            loose += Integer.parseInt(d.getLoose());
////            blood += Integer.parseInt(d.getBlood());
////            d_s += Float.parseFloat(d.getHealth_score());
////        }
////        String sound = String.valueOf(cough/sum);//'声音异常占比'
////        String sound_score = String.valueOf(s_s/ss.size());
////        String bs = String.valueOf(blood);//'血便'
////        String ws = String.valueOf(loose);//'稀便'
////        String ww = String.valueOf(water);//'水便'
////        String soil_score = String.valueOf(d_s/dd.size()); //'粪便得分'
////        String dead = dead_rate.getM(); //'死淘数'
////        float A = Float.parseFloat(dead_rate.getA());
////        float M = Float.parseFloat(dead_rate.getM());
////        String dead_r = String.valueOf(new DecimalFormat("#.###").format(M/A));// '死淘率'
////        String dead_score = dead_rate.getHealth_score();//'死淘分'
////        String health_score = String.valueOf(Float.parseFloat(sound_score) + Float.parseFloat(soil_score) + Float.parseFloat(dead_score));//'健康检测',
////        String layegg = eggInfo.getCD();//'产蛋率'
////        String layegg_score = eggInfo.getProduction_score();//'产蛋率得分'
////        float J = Float.parseFloat(d_eggs.getJ());
////        float D = Float.parseFloat(d_eggs.getD());
////        float P = Float.parseFloat(d_eggs.getP());
////        String dys = String.valueOf(J/D);// 畸形蛋率
////        String damage = String.valueOf(P/D);// 破损蛋率
////        String dys_score = d_eggs.getProduction_score();// '畸形得分'
////        String awe = a_eggs.getWAEW(); //'平均蛋重',
////        String awe_score = a_eggs.getProduction_score();//'平均蛋重得分',
////        String weight = weights.getTZ1();// '体重',
////        String weight_score = weights.getProduction_score(); //'体重得分',
////        String eve = uniformity.getU(); //'均匀度',
////        String eve_score = uniformity.getProduction_score();//均匀度得分',
////        String information_score = String.valueOf(Float.parseFloat(layegg_score) + Float.parseFloat(dys_score) + Float.parseFloat(awe_score) + Float.parseFloat(weight_score) + Float.parseFloat(eve_score));//'生产信息分',
////        String drink = water_i.getAWI();//'今日耗水量',
////        String drink_score = water_i.getBehaviour_score();//'饮水得分',
////        String feed = food_i.getAFI();// '今日饲料量',
////        String feed_score = food_i.getBehaviour_score();//'采食得分',
////        String behavior_score = String.valueOf(Float.parseFloat(drink_score) + Float.parseFloat(feed_score)); //'行为表现得分',
////        String total_score = String.valueOf(Float.parseFloat(env_score) + Float.parseFloat(health_score) + Float.parseFloat(information_score) + Float.parseFloat(behavior_score)); //'总分',
////        Timestamp time = new Timestamp(System.currentTimeMillis());
////        Score score = new Score(temp, temp_score, hum, hum_score, co2, co2_score, nh3, nh3_score, env_score, health_score, sound, sound_score, dead, dead_r, dead_score, bs, ws, ww, soil_score, information_score, layegg, layegg_score, dys, dys_score, damage, weight, weight_score, eve, eve_score, awe, awe_score, behavior_score, drink, drink_score, feed, feed_score, total_score, time);
////        detectionMapperYun.insert_Score(score);
////        detectionMapperLocal.insert_Score(score);
////        System.out.println("已更新【Score】总得分");
////    }
//}
//
