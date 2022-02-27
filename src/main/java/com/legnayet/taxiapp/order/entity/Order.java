package com.legnayet.taxiapp.order.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * t_order
 * @author 
 */
@Data
@TableName("t_order")
public class Order implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String path;

    private String deviceId;

    private Date createdAt;

    @TableField(exist = false)
    private String createdAtSdf;

    private Integer isShow;

    private static final long serialVersionUID = 1L;
}