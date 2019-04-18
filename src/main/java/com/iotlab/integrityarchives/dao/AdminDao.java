package com.iotlab.integrityarchives.dao;


import com.iotlab.integrityarchives.config.MyMapper;
import com.iotlab.integrityarchives.entity.Admin;

public interface AdminDao extends MyMapper<Admin> {

    Admin findById(Integer id);
}
