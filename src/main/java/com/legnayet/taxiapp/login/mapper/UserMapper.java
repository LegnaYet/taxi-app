package com.legnayet.taxiapp.login.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.legnayet.taxiapp.login.entity.User;

public interface UserMapper extends BaseMapper<User> {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}