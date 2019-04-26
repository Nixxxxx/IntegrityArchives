package com.iotlab.integrityarchives.service;

import com.iotlab.integrityarchives.common.service.BaseService;
import com.iotlab.integrityarchives.entity.PersonDecla;
import com.iotlab.integrityarchives.entity.UserInfo;

import java.util.List;

public interface PersonDeclaService extends BaseService<PersonDecla> {



    /**
     *   通过指定用户id查询到用户的信息
     * @param userId
     * @return
     */
    PersonDecla findByUserId(Integer userId);


    /**
     *   通过指定用户id查询到用户的信息
     * @param Id
     * @return
     */
    PersonDecla findById(Integer Id);













    /**
     * 分页查询
     *
     * @param personDecla 查询条件
     * @return
     */
    List<PersonDecla> findByPage(PersonDecla personDecla);



    /**
     * 更新
     *
     * @param personDecla
     */
    void update(PersonDecla personDecla);

    /**
     * 删除
     *
     * @param ids
     */
    void delete(List<Long> ids);

    void save(PersonDecla personDecla);


}
