package com.legnayet.taxiapp.device.mapper;

import com.legnayet.taxiapp.device.entity.Device;
import org.springframework.stereotype.Repository;

public interface DeviceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Device record);

    int insertSelective(Device record);

    Device selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);
}