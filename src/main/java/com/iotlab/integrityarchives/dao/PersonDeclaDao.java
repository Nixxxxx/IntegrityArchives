package com.iotlab.integrityarchives.dao;

import com.iotlab.integrityarchives.config.MyMapper;
import com.iotlab.integrityarchives.entity.CleanArchives;
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

    PersonDecla findPersonDecalByUserId(Integer id);
    /**
     * 通过姓名或者工号模糊查询列表
     * @param word
     * @return
     */
    List<PersonDecla> findListByWord(String word);
}
