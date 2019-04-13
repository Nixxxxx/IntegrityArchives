package com.iotlab.integrityarchives.dao;

import com.iotlab.integrityarchives.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface AdminDao {

    public Integer create(Admin admin);

    public Integer delete(Integer id);

    public Integer update(Admin admin);

    public Admin findOne(Integer id);

    public List<Admin> findAll();

    public Integer countAll();

    public List<Admin> findByPage(Map<String, Object> map);

    public Admin findByUsername(String username);
}
