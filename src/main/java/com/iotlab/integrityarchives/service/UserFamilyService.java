package com.iotlab.integrityarchives.service;


import com.iotlab.integrityarchives.common.service.BaseService;
import com.iotlab.integrityarchives.entity.UserFamily;

import java.util.List;

public interface UserFamilyService extends BaseService<UserFamily> {


    /**
     * 查询所有的管理员
     *
     * @return
     */
    List<UserFamily> findAll();

    /**
     * 根据ID查询
     *
     * @param userId
     * @return
     */
    List<UserFamily> findByUserId(Integer userId);






    void save(UserFamily UserFamily);

    /**
     * 更新
     *
     * @param UserFamily
     */
    void update(UserFamily UserFamily);

    /**
     * 删除
     *
     * @param ids
     */
    void delete(List<Long> ids);
}
