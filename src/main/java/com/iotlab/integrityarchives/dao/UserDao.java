package com.iotlab.integrityarchives.dao;


import com.iotlab.integrityarchives.config.MyMapper;
import com.iotlab.integrityarchives.entity.User;

import java.util.List;


public interface UserDao extends MyMapper<User> {

    int insertUserReturnId(User user);

    int countUserNumber(String userNumber);

    List<String> findAllNumber();

    String findNumberByuserId(Integer userId);
    
    public User findUserById(Integer id);

}
