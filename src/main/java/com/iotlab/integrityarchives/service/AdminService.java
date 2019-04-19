package com.iotlab.integrityarchives.service;


import com.iotlab.integrityarchives.common.service.BaseService;
import com.iotlab.integrityarchives.entity.Admin;

import java.util.List;

public interface AdminService extends BaseService<Admin> {


    /**
     * 查询所有的管理员
     *
     * @return
     */
    List<Admin> findAll();

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    Admin findById(Integer id);

    /**
     * 根据Name查询用户数据
     *
     * @param username
     * @return
     */
    Admin findByName(String username);


    /**
     * 分页查询
     *
     * @param admin 查询条件
     * @return
     */
    List<Admin> findByPage(Admin admin);

    void save(Admin admin);

    /**
     * 更新
     *
     * @param user
     */
    void update(Admin user);

    /**
     * 删除
     *
     * @param ids
     */
    void delete(List<Long> ids);
}
