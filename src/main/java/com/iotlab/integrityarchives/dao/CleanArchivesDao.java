package com.iotlab.integrityarchives.dao;

import com.iotlab.integrityarchives.config.MyMapper;
import com.iotlab.integrityarchives.entity.CleanArchives;

import java.util.List;

/**
 * @author created by Zhangdazhuang
 * @version v.0.1
 * @Description TODO
 * @date 2019/4/24
 * @备注
 **/
public interface CleanArchivesDao extends MyMapper<CleanArchives> {


    /**
     * 通过姓名或者工号模糊查询列表
     * @param word
     * @return
     */
    List<CleanArchives> findListByWord(String word);


    List<CleanArchives> findAllList();


    CleanArchives findById(Integer id);

    CleanArchives findByUserId(Integer userId);




}





