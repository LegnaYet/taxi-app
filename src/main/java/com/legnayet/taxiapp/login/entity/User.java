package com.legnayet.taxiapp.login.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * t_user
 * @author 
 */
@Data
@TableName("t_user")
public class User implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 登录账号
     */
    private String login;

    /**
     * 密码
     */
    private String password;

    /**
     * 名称
     */
    private String name;

    private static final long serialVersionUID = 1L;
}