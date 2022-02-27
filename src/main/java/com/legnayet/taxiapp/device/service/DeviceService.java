package com.legnayet.taxiapp.device.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.legnayet.taxiapp.device.entity.Device;
import com.legnayet.taxiapp.device.mapper.DeviceMapper;
import com.legnayet.taxiapp.order.entity.Order;
import com.legnayet.taxiapp.order.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private OrderMapper orderMapper;

    public Map<String, Object> getDeviceList() {
        Map<String,Object> result = new HashMap<>();
        List<Device> devices = deviceMapper.selectList(null);
        if (!CollectionUtils.isEmpty(devices)){
            List<Device> onlineDevice = devices.stream().filter(device -> "在线".equals(device.getIsOnline())).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(onlineDevice)){
                for (Device device : onlineDevice) {
                    String deviceId = device.getDeviceId();
                    QueryWrapper<Order> orderQw = new QueryWrapper<>();
                    orderQw.eq("device_id",deviceId);
                    List<Order> orders = orderMapper.selectList(orderQw);
                    if (!CollectionUtils.isEmpty(orders)){
                        List<Order> repOrder = orders.stream().filter(order -> 1 == order.getIsShow()).collect(Collectors.toList());
                        device.setRepOrder(repOrder);
                        device.setGetOrder(orders);
                    }
                }
            }
            List<Device> offlineDevice = devices.stream().filter(device -> "离线".equals(device.getIsOnline())).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(offlineDevice)){
                for (Device device : offlineDevice) {
                    String deviceId = device.getDeviceId();
                    QueryWrapper<Order> orderQw = new QueryWrapper<>();
                    orderQw.eq("device_id",deviceId);
                    List<Order> orders = orderMapper.selectList(orderQw);
                    if (!CollectionUtils.isEmpty(orders)){
                        List<Order> repOrder = orders.stream().filter(order -> 1 == order.getIsShow()).collect(Collectors.toList());
                        device.setRepOrder(repOrder);
                        device.setGetOrder(orders);
                    }
                }
            }
            result.put("online",onlineDevice);
            result.put("offline",offlineDevice);
        }

        return result;
    }

    public IPage<Device> getDevicePageList(String isOnline, Integer pageNum, Integer pageSize) {
        Page<Device> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Device> deviceQw = new QueryWrapper<>();
        if (StringUtils.isNotBlank(isOnline)){
            deviceQw.eq("is_online",isOnline);
        }
        IPage<Device> deviceIPage = deviceMapper.selectPage(page, deviceQw);
        List<Device> records = deviceIPage.getRecords();
        for (Device record : records) {
            String deviceId = record.getDeviceId();
            QueryWrapper<Order> orderQw = new QueryWrapper<>();
            orderQw.eq("device_id",deviceId);
            List<Order> orders = orderMapper.selectList(orderQw);
            if (!CollectionUtils.isEmpty(orders)){
                List<Order> repOrder = orders.stream().filter(order -> 1 == order.getIsShow()).collect(Collectors.toList());
                record.setRepOrder(repOrder);
                record.setGetOrder(orders);
            }
        }
        return deviceIPage;
    }
}
