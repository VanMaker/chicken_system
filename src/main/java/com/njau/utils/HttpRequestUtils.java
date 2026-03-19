package com.njau.utils;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class HttpRequestUtils {

    public HttpRequestUtils() {

    }

//    private String Access_Token() throws IOException {
//        URL url = new URL("http://data.znmc.co/open/api/auth/token/get");
//
//        // 打开连接
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("POST");
//        connection.setRequestProperty("Content-Type", "application/json; utf-8");
//        connection.setRequestProperty("Accept", "application/json");
//        connection.setDoOutput(true);
//
//        // 准备JSON数据
//        JSONObject jsonData = new JSONObject();
//        jsonData.put("appId", "njlkqy_api");
//        jsonData.put("appSecret", "d8b13c8aa59bf1449780f937d92c6259");
//
//        // 将JSON数据写入输出流
//        try (OutputStream os = connection.getOutputStream()) {
//            byte[] input = jsonData.toString().getBytes("utf-8");
//            os.write(input, 0, input.length);
//        }
//
//        // 读取响应
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
//            StringBuilder response = new StringBuilder();
//            String responseLine;
//            while ((responseLine = br.readLine()) != null) {
//                response.append(responseLine.trim());
//            }
//            // 解析返回的JSON并获取accessToken
//            JSONObject jsonObject = new JSONObject(new JSONTokener(response.toString()));
//            JSONObject dataObject = jsonObject.getJSONObject("data");
//            String accessToken = dataObject.getString("accessToken");
//            return accessToken;
//
//        }
//    }



    public String[] Device_Propertys(AesUtil aesUtil,String date){
        LocalDate today = LocalDate.now();
        String date1 = today.toString(); //变量用于保存系统的当前日期
//        LocalDateTime now = LocalDateTime.now();
//        System.out.println(now);
        String apiUrl;
        if(date == date1){
            apiUrl = "https://fmu.wodecorp.cn/flockSimu/appV1/thirdPartyDecision/getMetricsForBuilding?validationStr=" + AesUtil.generate_key();
        }
        else{
            apiUrl = "https://fmu.wodecorp.cn/flockSimu/appV1/thirdPartyDecision/getMetricsForBuilding?validationStr=" + AesUtil.generate_key() + "&date=" + date;
        }//传入的日期形参与系统日期进行比较，若相等则传入的url中不附带参数；若不相等，则以传入的date指定的日期为准，并附带参数
        System.out.println(apiUrl);
//        String accessToken = "";
        String value = null;
        String[] resultStrings = null; //用于保存返回json数据
//        try {
//            accessToken = Access_Token();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

//        String authHeader = "TOKEN " + accessToken;
//        System.out.println(authHeader);
        try {
            // 创建URL对象
            URL url = new URL(apiUrl);
            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
//            connection.setRequestProperty("Authorization", authHeader);
            connection.setDoOutput(true);
            // 准备JSON数据
            JSONObject jsonData = new JSONObject();
//            jsonData.put("houseId", houseId);
            // 将JSON数据写入输出流
//            try (OutputStream os = connection.getOutputStream()) {
//                byte[] input = jsonData.toString().getBytes("utf-8");
//                os.write(input, 0, input.length);
//            }
            // 读取响应
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
//                    System.out.println(responseLine);
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
                JSONObject jsonObject = new JSONObject(new JSONTokener(response.toString())); //从字符流中解析出json格式的数据
//                JSONArray dataArray = jsonObject.getJSONArray("data");  //获取返回数据格式中索引为"data"字段的部分json数组
//                System.out.println(dataArray);
//                System.out.println(jsonObject);
                JSONObject metricsObject = jsonObject.getJSONObject("data").getJSONObject("metrics"); //获取metrics索引的json对象数据
//                JSONObject propertysArray = dataArray.getJSONObject(0).getJSONArray("metrics");
//                System.out.println(propertysArray);
//                System.out.println(metricsObject.length());
                //存储数据
                resultStrings = new String[metricsObject.length()+2]; //第一个特征字段为id
                resultStrings[1] = String.valueOf(metricsObject.get("deathRate"));
                resultStrings[2] = String.valueOf(metricsObject.get("deathEliminationQty"));
                resultStrings[3] = String.valueOf(metricsObject.get("dayConsumeMaterial"));
                resultStrings[4] = String.valueOf(metricsObject.get("waterIntake"));
                resultStrings[5] = String.valueOf(metricsObject.get("temperature"));
                resultStrings[6] = String.valueOf(metricsObject.get("humidity"));
                resultStrings[7] = String.valueOf(metricsObject.get("layEggRate"));
                resultStrings[8] = String.valueOf(metricsObject.get("layEggQty"));
//                resultStrings[0] = houseId;
//                System.out.println(resultStrings.length);
//                for (int i = 0; i < propertysArray.length(); i++) {
//                    System.out.println(propertysArray.length());
                    // 遍历propertys数组中的每个温度属性
//                    JSONObject propertyObject = propertysArray.getJSONObject(i);
//                    System.out.println(propertyObject);
//                    if (propertyObject.has("value")) {
//                        value = propertyObject.getString("value");
//                        if (value.isEmpty()) {
//                            // 如果为空，则赋值为null
//                            value = "0.0";
//                        }
//                        resultStrings[i+1] = value;
//                    }
                    Timestamp time = new Timestamp(System.currentTimeMillis());
                    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String sf_time = sf.format(time);
                    resultStrings[metricsObject.length()+1] = sf_time;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        // 4号舍数据传回来有问题，需要换位置
//        if (houseId.equals("544")){
//            String[] temp = new String[12];
//            String info = resultStrings[35];
//            for (int i = 0; i < temp.length; i++) {
//                temp[i] = resultStrings[i+36];
//            }
//            for (int i = 0; i < temp.length; i++) {
//                resultStrings[i+35] = temp[i];
//            }
//            resultStrings[47] = info;
//        }
        insertIntoTable(resultStrings);
        return resultStrings;
    }


    private void insertIntoTable (String[] value) {
        String DB_URL = "jdbc:mysql://121.196.198.106:3306/wens_sub_health_1?useSSL=false&serverTimezone=GMT%2B8";
        String USER = "root";
        String PASS = "njau#B307";
        try {
            // 加载并注册JDBC驱动
//                Class.forName("com.mysql.cj.jdbc.Driver");
            StringBuilder sql = new StringBuilder("INSERT INTO auto_info_xjr (dead_r,dead,feed,drink,temp,hum,layegg,awe,time");
//            for (int i = 0; i < value.length; i++) {
//                sql.append("T").append(i).append(", ");
//            }
//            // 移除最后的逗号和空格
//            sql.setLength(sql.length() - 2);
            sql.append(") VALUES (");
            for (int i = 1; i <= value.length-1; i++) {
                sql.append("?, ");
            }
            sql.setLength(sql.length() - 2); // 移除最后的逗号和空格
            sql.append(")");
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            try {
                connection = DriverManager.getConnection(DB_URL, USER, PASS);
                // 创建PreparedStatement对象
                preparedStatement = connection.prepareStatement(sql.toString());
                // 设置参数
                for (int i = 1; i <= value.length-2; i++) {
                    preparedStatement.setString(i, value[i]); // 注意索引从1开始
                }
                Timestamp time = new Timestamp(System.currentTimeMillis());
                preparedStatement.setString(value.length-1, String.valueOf(time));
                // 执行插入操作
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // 关闭资源
                try {
                    if (connection != null) connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
