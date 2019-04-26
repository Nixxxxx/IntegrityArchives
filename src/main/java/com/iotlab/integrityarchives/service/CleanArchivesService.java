package com.iotlab.integrityarchives.service;

import com.iotlab.integrityarchives.common.service.BaseService;
import com.iotlab.integrityarchives.entity.CleanArchives;

import java.util.List;

public interface CleanArchivesService extends BaseService<CleanArchives> {


    /**
     * 通过指定用户id查询到用户的信息
     *
     * @param userId
     * @return
     */
    CleanArchives findByUserId(Integer userId);

    /**
     * 通过Id查询
     * @param id
     * @return
     */
    CleanArchives findById(Integer id);


    /**
     * 分页查询
     *
     * @param cleanArchives 查询条件
     * @return
     */
    List<CleanArchives> findByPage(CleanArchives cleanArchives);


    /**
     * 更新
     *
     * @param cleanArchives
     */
    void update(CleanArchives cleanArchives);

    /**
     * 删除
     *
     * @param ids
     */
    void delete(List<Long> ids);

    void save(CleanArchives cleanArchives);


}
