package com.iotlab.integrityarchives.service.impl;

import com.iotlab.integrityarchives.dao.AdminDao;
import com.iotlab.integrityarchives.entity.Admin;
import com.iotlab.integrityarchives.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public boolean login(String username, String password) {
        Admin admin = adminDao.findByUsername(username);
        if(admin != null && admin.getPassword().equals(password))
            return true;
        return false;
    }

    @Override
    public Admin findByUsername(String username) {
        return adminDao.findByUsername(username);
    }

    @Override
    public boolean create(Admin admin) {
        if(adminDao.create(admin) == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        if(adminDao.delete(id) == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Admin admin) {
        if(adminDao.update(admin) == 1) {
            return true;
        }
        return false;
    }

    @Override
    public Admin findOne(Integer id) {
        return adminDao.findOne(id);
    }

    @Override
    public List<Admin> findAll() {
        return adminDao.findAll();
    }

    @Override
    public List<Admin> findByPage(Integer page, int quantity) {
        Map<String, Object> map = new HashMap<>();
        map.put("start", (page-1)*10);
        map.put("quantity", quantity);
        return adminDao.findByPage(map);
    }

    @Override
    public Integer countAll() {
        return adminDao.countAll();
    }
}
