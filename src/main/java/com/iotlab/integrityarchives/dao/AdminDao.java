package com.iotlab.integrityarchives.dao;


import com.iotlab.integrityarchives.config.MyMapper;
import com.iotlab.integrityarchives.entity.Admin;

import java.util.List;

public interface AdminDao extends MyMapper<Admin> {


     int countAdminNumber(String adminNumber);

     List<String> findAllNumber();

     String findNumberByadminId(Integer adminId);

}
