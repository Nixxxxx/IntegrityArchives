package com.iotlab.integrityarchives.service;


import com.iotlab.integrityarchives.entity.Admin;

import java.util.List;

public interface AdminService {

    public boolean login(String username, String password);

    public boolean create(Admin admin);

    public boolean delete(Integer id);

    public boolean update(Admin admin);

    public Admin findOne(Integer id);

    public List<Admin> findAll();

    public Integer countAll();

    public List<Admin> findByPage(Integer page, int quantity);

    public Admin findByUsername(String username);
}
