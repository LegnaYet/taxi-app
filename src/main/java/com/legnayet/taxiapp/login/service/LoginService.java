package com.legnayet.taxiapp.login.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.legnayet.taxiapp.common.MyException;
import com.legnayet.taxiapp.login.entity.User;
import com.legnayet.taxiapp.login.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private UserMapper userMapper;

    public User login(String userName, String password) {
        QueryWrapper<User> userQw = new QueryWrapper<>();
        userQw.eq("login",userName).last("limit 1");
        User user = userMapper.selectOne(userQw);
        if (user == null){
            throw new MyException("用户不存在");
        }
        if (!user.getPassword().equals(password)){
            throw new MyException("密码错误");
        }
        return user;
    }
}
