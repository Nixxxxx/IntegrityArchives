package com.jiang.kaoyan.service.impl;

import com.iotlab.integrityarchives.dao.UserDao;
import com.iotlab.integrityarchives.entity.User;
import com.iotlab.integrityarchives.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean login(String username, String password) {
        User user = userDao.findByUsername(username);
        if(user != null && user.getPassword().equals(password))
            return true;
        return false;
    }

    @Override
    public boolean create(User user) {
        if(userDao.create(user) == 1)
            return true;
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        if(userDao.delete(id) == 1)
            return true;
        return false;
    }

    @Override
    public boolean update(User user) {
        if(userDao.update(user) == 1)
            return true;
        return false;
    }

    @Override
    public User findOne(Integer id) {
        return userDao.findOne(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public List<User> findByPage(Integer page, int quantity) {
        Map<String, Object> map = new HashMap<>();
        map.put("start", (page-1)*10);
        map.put("quantity", quantity);
        return userDao.findByPage(map);
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public Integer countAll() {
        return userDao.countAll();
    }
}
