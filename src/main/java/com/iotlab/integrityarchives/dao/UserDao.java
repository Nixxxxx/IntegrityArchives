package com.iotlab.integrityarchives.dao;


import com.iotlab.integrityarchives.config.MyMapper;
import com.iotlab.integrityarchives.entity.User;


public interface UserDao extends MyMapper<User> {

    int insertUserReturnId(User user);
}
