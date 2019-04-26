package com.iotlab.integrityarchives.dao;

import com.iotlab.integrityarchives.config.MyMapper;
import com.iotlab.integrityarchives.entity.CleanArchive;

import java.util.List;

/**
 * @author created by Zhangdazhuang
 * @version v.0.1
 * @Description TODO
 * @date 2019/4/24
 * @备注
 **/
public interface CleanArchiveDao extends MyMapper<CleanArchive> {

    /**
     * 通过姓名或者工号模糊查询列表
     * @param word
     * @return
     */
    List<CleanArchive> findListByWord(String word);

    List<CleanArchive> findAllList();

    CleanArchive findById(Integer id);

    CleanArchive findByUserId(Integer userId);

}





