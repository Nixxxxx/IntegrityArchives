package com.iotlab.integrityarchives.service;


import com.iotlab.integrityarchives.common.service.BaseService;
import com.iotlab.integrityarchives.entity.Admin;
import com.iotlab.integrityarchives.entity.Userfamily;

import java.util.List;

public interface UserFamilyService extends BaseService<Userfamily> {


    /**
     * 查询所有的管理员
     *
     * @return
     */
    List<Userfamily> findAll();

    /**
     * 根据ID查询
     *
     * @param userId
     * @return
     */
    List<Userfamily> findByUserId(Integer userId);






    void save(Userfamily Userfamily);

    /**
     * 更新
     *
     * @param Userfamily
     */
    void update(Userfamily Userfamily);

    /**
     * 删除
     *
     * @param ids
     */
    void delete(List<Long> ids);
}
