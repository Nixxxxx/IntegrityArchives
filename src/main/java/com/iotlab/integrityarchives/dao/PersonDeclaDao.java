package com.iotlab.integrityarchives.dao;

import com.iotlab.integrityarchives.config.MyMapper;
import com.iotlab.integrityarchives.entity.CleanArchive;
import com.iotlab.integrityarchives.entity.PersonDecla;

import java.util.List;

/**
 * @author created by Zhangdazhuang
 * @version v.0.1
 * @Description TODO
 * @date 2019/4/24
 * @备注
 **/
public interface PersonDeclaDao extends MyMapper<PersonDecla> {


    /**
     * 通过姓名或者工号模糊查询列表
     * @param word
     * @return
     */
    List<PersonDecla> findListByWord(String word);

    /**
     * 默认查找所有的廉政列表
     * @return
     */
    List<PersonDecla>  findAllList();


    PersonDecla findById(Integer id);

    PersonDecla findByUserId(Integer userId);



}
