package com.iotlab.integrityarchives.dao;

import com.iotlab.integrityarchives.config.MyMapper;
import com.iotlab.integrityarchives.dto.UserInfoResult;
import com.iotlab.integrityarchives.entity.UserInfo;

import java.util.List;

public interface UserInfoDao extends MyMapper<UserInfo> {
    //通过指定用户id查询到用户的信息
    //UserInfo findByUserId(Integer userId);

    //通过姓名模糊查询或者工号唯一查询列表
    List<UserInfo> findListByWord(String word);

    /**
     * 查询用户信息实体,导出UserInfo（家庭成员信息）
     * @param userId
     * @return
     */
    UserInfo findUserInfoByuserId(Integer userId);

    /**
     * 导出三表信息 userInfo cleanArchive PersonDecla
     * @param userId
     * @return
     */
    UserInfoResult PrintUserInfo(Integer userId);
}
