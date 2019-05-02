package com.iotlab.integrityarchives.service.impl;


import com.iotlab.integrityarchives.common.service.impl.BaseServiceImpl;
import com.iotlab.integrityarchives.dao.UserInfoDao;
import com.iotlab.integrityarchives.entity.User;
import com.iotlab.integrityarchives.entity.UserFamily;
import com.iotlab.integrityarchives.entity.UserInfo;
import com.iotlab.integrityarchives.service.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@SuppressWarnings("all")
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo> implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;


    @Override
    @Transactional
    public void save(UserInfo userInfo) {
        try {
            //passwordHelper.encryptPassword(user); //加密
            userInfo.setCreateTime(new Date());
            userInfo.setLastEditTime(userInfo.getCreateTime());
            userInfoDao.insert(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
            // throw new GlobalException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void update(UserInfo userInfo) {
        if (userInfo.getId() != 0) {
            try {
                userInfo.setLastEditTime(new Date());
                this.updateNotNull(userInfo);
            } catch (Exception e) {
                e.printStackTrace();
                //throw new GlobalException(e.getMessage());
            }
        }
    }

    @Override
    @Transactional
    public void delete(List<Long> ids) {
        if (!ids.isEmpty()) {
            try {
                //通用mapper自带的批量删除,通过主键来删除  userId对应实体中的属性
                this.batchDelete(ids, "userId", UserInfo.class);
            } catch (Exception e) {
                e.printStackTrace();
                // throw new GlobalException(e.getMessage());
            }
        }
    }


   /* @Override
    public UserInfo findByUserId(Integer userId) {
        return userInfoDao.findByUserId(userId);
    }*/


    @Override
    public List<UserInfo> findListByWord(String word) {
        return userInfoDao.findListByWord(word);
    }

    @Override
    public UserInfo findUserInfoByuserId(Integer userId) {
        return userInfoDao.findUserInfoByuserId(userId);
    }


    /**
     * word文件导出
     */
    public  Map<String, Object> exportWordFile(UserInfo userInfo) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        List<UserFamily> userFamilyList = userInfo.getUserFamilyList();
        dataMap.put("name", userInfo.getName());
        dataMap.put("gender", userInfo.getGender());
        dataMap.put("dateOfBirth", userInfo.getDateOfBirth());
        dataMap.put("nation", userInfo.getNation());
        dataMap.put("nativePlace", userInfo.getNativePlace());
        dataMap.put("chushengdi", userInfo.getPlaceOfBirth());
        dataMap.put("rudangdate", userInfo.getDateOfJoinParty());
        dataMap.put("canjiadate", userInfo.getDateOfJoinWork());
        dataMap.put("zhuanchang", userInfo.getFamiliarMajorAndSpecialty());
        dataMap.put("jiangcheng", userInfo.getRewardsAndPunishment());
        dataMap.put("niandukaohe", userInfo.getAnnualAssessmentResults());
        dataMap.put("zhuanye", userInfo.getTechnicalPosition());
        dataMap.put("jiankang", userInfo.getPhysicalCondition());
        dataMap.put("quanrizhi", userInfo.getFullTimeDegree());
        dataMap.put("yuanxiao", userInfo.getFullTimeGraduatedUniversityAndMajor());
        dataMap.put("zaizhi", userInfo.getPartTimeDegree());
        dataMap.put("biyeyuanxiao", userInfo.getPartTimeGraduatedUniversityAndMajor());
        dataMap.put("xianren", userInfo.getCurrentPosition());
        dataMap.put("jianli", userInfo.getResume());
        return dataMap;
    }


}