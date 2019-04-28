
package com.iotlab.integrityarchives.service;

import com.iotlab.integrityarchives.common.service.BaseService;
import com.iotlab.integrityarchives.entity.User;

import java.util.List;

public interface UserService extends BaseService<User> {


    /**
     * 查询所有的用户
     *
     * @return
     */
    List<User> findAll();

    /**
     * 根据number查询用户数据
     *
     * @param username
     * @return
     */
    User findByNumber(String number);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    User findById(Integer id);


    int insertUserReturnId(User user);

    /**
     * 分页查询
     *
     * @param user 查询条件
     * @return
     */
    List<User> findByPage(User user);


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

    int countUserNumber(String userNumber);
}
