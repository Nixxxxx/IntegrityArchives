package com.iotlab.integrityarchives.dao;


import com.iotlab.integrityarchives.config.MyMapper;
import com.iotlab.integrityarchives.entity.User;

import java.util.List;


public interface UserDao extends MyMapper<User> {

    List<User> findById(Integer id);

}
