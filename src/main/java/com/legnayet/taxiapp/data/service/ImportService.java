package com.legnayet.taxiapp.data.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.legnayet.taxiapp.device.entity.Device;
import com.legnayet.taxiapp.device.mapper.DeviceMapper;
import com.legnayet.taxiapp.order.entity.Order;
import com.legnayet.taxiapp.order.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * description: ImportService
 * date: 2022/2/27 2:19 上午
 * author: legna_yet
 * version: 1.0
 */
@Slf4j
@Service
public class ImportService {

    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private OrderMapper orderMapper;

//    @PostConstruct
    public void importData(){
        String json = readJsonFile("/Users/legna_yet/IdeaProjects/taxi-app/src/main/resources/data.json");
        JSONObject data = JSONObject.parseObject(json);
        JSONArray array = data.getJSONArray("data");
        for (int i = 0; i < array.size(); i++) {
            JSONObject deviceJson = array.getJSONObject(i);
//            Integer id = deviceJson.getInteger("id");
            String deviceId = deviceJson.getString("deviceId");
            Integer deviceType = deviceJson.getInteger("deviceType");
            String area = deviceJson.getString("area");
            String version = deviceJson.getString("version");
            String temp = deviceJson.getString("temp");
            String hum = deviceJson.getString("hum");
            String position = deviceJson.getString("position");
            String station = deviceJson.getString("station");
            String location = deviceJson.getString("location");
            Date lastOnlineDate = deviceJson.getDate("lastOnlineDate");
            String isOnline = deviceJson.getString("isOnline");
            String status = deviceJson.getString("status");
            String mbat = deviceJson.getString("mbat");
            Integer responseTimes = deviceJson.getInteger("responseTimes");

            Device device = new Device();
//            device.setId(id);
            device.setDeviceId(deviceId);
            device.setDeviceType(deviceType);
            device.setArea(area);
            device.setHum(hum);
            device.setIsOnline(isOnline);
            device.setLocation(location);
            device.setMbat(mbat);
            device.setPosition(position);
            device.setVersion(version);
            device.setTemp(temp);
            device.setStation(station);
            device.setLastOnlineDate(lastOnlineDate);
            device.setStatus(status);
            device.setResponseTimes(responseTimes);
            deviceMapper.insert(device);

            JSONArray orderArr = deviceJson.getJSONArray("orderTimes");
            for (int j = 0; j < orderArr.size(); j++) {
                JSONObject orderJson = orderArr.getJSONObject(j);

//                Integer orderId = orderJson.getInteger("id");
                String path = orderJson.getString("path");
                Date createdAt = orderJson.getDate("createdAt");
                Integer isShow = orderJson.getInteger("isShow");
                Order order = new Order();
//                order.setId(orderId);
                order.setCreatedAt(createdAt);
                order.setIsShow(isShow);
                order.setPath(path);
                order.setDeviceId(deviceId);
                orderMapper.insert(order);
            }

        }
    }

    //读取json文件
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

//    @PostConstruct
    public void setUnRep(){
        List<Order> orders = orderMapper.selectList(null);
        for (int i = 0; i < orders.size();) {
            Order order = orders.get(i);
            order.setIsShow(0);
            orderMapper.updateById(order);

            Random random = new Random();
            int i1 = random.nextInt(5);
            i+=i1;
        }
    }

}
