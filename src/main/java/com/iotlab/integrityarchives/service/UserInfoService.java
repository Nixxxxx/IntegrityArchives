package com.iotlab.integrityarchives.service;

import com.iotlab.integrityarchives.common.service.BaseService;
import com.iotlab.integrityarchives.entity.Admin;
import com.iotlab.integrityarchives.entity.User;
import com.iotlab.integrityarchives.entity.UserInfo;

import java.util.List;

public interface UserInfoService extends BaseService<UserInfo> {



    /**
     *   通过指定用户id查询到用户的信息
     * @param id
     * @return
     */
    UserInfo findByUserId(Integer id);





    /**
     * 通过姓名或者工号模糊查询列表
     * @param word
     * @return
     */
    List<UserInfo> findListByWord(String word);













    /**
     * 更新
     *
     * @param userInfo
     */
    void update(UserInfo userInfo);

    /**
     * 删除
     *
     * @param ids
     */
    void delete(List<Long> ids);

    void save(UserInfo userInfo);


}
