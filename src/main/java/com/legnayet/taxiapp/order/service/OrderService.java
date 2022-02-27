package com.legnayet.taxiapp.order.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.legnayet.taxiapp.order.entity.Order;
import com.legnayet.taxiapp.order.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public IPage<Order> getOrderPageList(Integer isShow, Integer pageNum, Integer pageSize) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Page<Order> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Order> orderQw = new QueryWrapper<>();
        if (isShow != null && isShow != 0){
            orderQw.eq("is_show",isShow);
        }
        orderQw.orderByDesc("created_at");
        IPage<Order> orderIPage = orderMapper.selectPage(page, orderQw);
        List<Order> records = orderIPage.getRecords();
        for (Order record : records) {
            Date createdAt = record.getCreatedAt();
            record.setCreatedAtSdf(sdf.format(createdAt));
        }
        return orderIPage;
    }

    public Map<String, Object> getOrderList() {
        Map<String,Object> result = new HashMap<>();
        List<Order> orders = orderMapper.selectList(null);
        if (!CollectionUtils.isEmpty(orders)){
            List<Order> repOrder = orders.stream().filter(order -> 1 == order.getIsShow()).collect(Collectors.toList());
            result.put("repOrder",repOrder);
            result.put("getOrder",orders);
        }
        return result;
    }
}
