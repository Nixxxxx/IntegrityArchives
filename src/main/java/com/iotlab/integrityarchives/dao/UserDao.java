package com.iotlab.integrityarchives.dao;


import com.iotlab.integrityarchives.config.MyMapper;
import com.iotlab.integrityarchives.entity.User;
import com.iotlab.integrityarchives.entity.UserInfo;

import java.util.List;


public interface UserDao extends MyMapper<User> {

    List<UserInfo> findUserInfoByUserId(Integer id);

    /*V1.0 不采用通用mapper
    public Integer create(User user);

    public Integer delete(Integer id);

    public Integer update(User user);

    public User findOne(Integer id);

    public List<User> findAll();

    public Integer countAll();

    public List<User> findByPage(Map<String, Object> map);

    public User findByUsername(String username);*/
}
