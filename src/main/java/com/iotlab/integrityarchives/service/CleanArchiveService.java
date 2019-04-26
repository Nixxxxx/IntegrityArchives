package com.iotlab.integrityarchives.service;

import com.iotlab.integrityarchives.common.service.BaseService;
import com.iotlab.integrityarchives.entity.CleanArchive;

import java.util.List;

public interface CleanArchiveService extends BaseService<CleanArchive> {

    /**
     * 通过指定用户id查询到用户的信息
     *
     * @param userId
     * @return
     */
    CleanArchive findByUserId(Integer userId);

    /**
     * 通过Id查询
     * @param id
     * @return
     */
    CleanArchive findById(Integer id);


    /**
     * 分页查询
     *
     * @param cleanArchive 查询条件
     * @return
     */
    List<CleanArchive> findByPage(CleanArchive cleanArchive);


    /**
     * 更新
     *
     * @param cleanArchive
     */
    void update(CleanArchive cleanArchive);

    /**
     * 删除
     *
     * @param ids
     */
    void delete(List<Long> ids);

    void save(CleanArchive cleanArchive);


}
