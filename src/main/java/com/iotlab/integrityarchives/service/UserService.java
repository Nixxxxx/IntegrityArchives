package com.iotlab.integrityarchives.service;


import com.iotlab.integrityarchives.entity.User;

import java.util.List;

public interface UserService {

    public boolean login(String username, String password);

    public boolean create(User user);

    public boolean delete(Integer id);

    public boolean update(User user);

    public User findOne(Integer id);

    public List<User> findAll();

    public Integer countAll();

    public List<User> findByPage(Integer page, int quantity);

    public User findByUsername(String username);
}
