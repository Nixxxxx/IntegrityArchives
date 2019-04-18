
package com.iotlab.integrityarchives.service;

import com.iotlab.integrityarchives.common.service.BaseService;
import com.iotlab.integrityarchives.entity.User;
import com.iotlab.integrityarchives.entity.UserInfo;

import java.util.List;

public interface UserService extends BaseService<User> {


    /**
     * 查询所有的用户
     * @return
     */
    List<User> findAll();

    /**
     * 通过姓名和工号模糊查询用户信息
     * @param word
     * @return
     */
    List<UserInfo> findByNameOrWorld(String word);


    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    User findById(Integer id);

    /**
     * 根据Name查询用户数据
     *
     * @param username
     * @return
     */
    User findByName(String username);

    /**
     * 更新
     *
     * @param user
     */
    void update(User user);

    /**
     * 删除
     *
     * @param ids
     */
    void delete(List<Long> ids);

   /* *
     * 获取系统设置数据
     *
     * @return
    Setting findSetting();

    *//**
     * 更新设置信息
     *
     * @param setting
     *//*
    void updateSetting(Setting setting);*/
}
