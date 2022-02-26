package com.legnayet.taxiapp.device.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * t_device
 * @author 
 */
@Data
@TableName("t_device")
public class Device implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String deviceId;

    private Integer deviceType;

    private String area;

    private String version;

    private String temp;

    private String hum;

    private String position;

    private String station;

    private String location;

    private Date lastOnlineDate;

    private String isOnline;

    private Integer responseTimes;

    private String status;

    private String mbat;

    private static final long serialVersionUID = 1L;
}