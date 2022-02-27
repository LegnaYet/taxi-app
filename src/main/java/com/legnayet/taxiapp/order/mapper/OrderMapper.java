package com.legnayet.taxiapp.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.legnayet.taxiapp.order.entity.Order;

public interface OrderMapper extends BaseMapper<Order> {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}