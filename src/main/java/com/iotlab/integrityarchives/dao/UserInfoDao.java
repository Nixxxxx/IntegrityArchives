package com.iotlab.integrityarchives.dao;

import com.iotlab.integrityarchives.config.MyMapper;
import com.iotlab.integrityarchives.entity.UserInfo;

import java.util.List;

public interface UserInfoDao extends MyMapper<UserInfo> {
    //通过指定用户id查询到用户的信息
    UserInfo findByUserId(Integer userId);

    //通过姓名模糊查询或者工号唯一查询列表
    List<UserInfo> findListByWord(String word);

}
