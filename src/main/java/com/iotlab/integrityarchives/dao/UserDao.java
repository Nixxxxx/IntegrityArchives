package com.iotlab.integrityarchives.dao;

import com.iotlab.integrityarchives.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserDao {

    public Integer create(User user);

    public Integer delete(Integer id);

    public Integer update(User user);

    public User findOne(Integer id);

    public List<User> findAll();

    public Integer countAll();

    public List<User> findByPage(Map<String, Object> map);

    public User findByUsername(String username);
}
