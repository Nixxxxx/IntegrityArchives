package com.iotlab.integrityarchives.service;

import com.iotlab.integrityarchives.common.service.BaseService;
import com.iotlab.integrityarchives.entity.Admin;
import com.iotlab.integrityarchives.entity.User;
import com.iotlab.integrityarchives.entity.UserInfo;

import java.util.List;
import java.util.Map;

public interface UserInfoService extends BaseService<UserInfo> {



    /**
     *   通过指定用户id查询到用户的信息
     * @param
     * @return
     */
    //UserInfo findByUserId(Integer id);

    Map<String, Object>  exportWordFile(UserInfo userInfo);



    /**
     * 通过姓名或者工号模糊查询列表
     * @param word
     * @return
     */
    List<UserInfo> findListByWord(String word);

      UserInfo findUserInfoByuserId(Integer userId);











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
