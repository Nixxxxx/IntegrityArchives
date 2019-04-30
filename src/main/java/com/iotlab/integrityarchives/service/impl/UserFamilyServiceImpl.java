package com.iotlab.integrityarchives.service.impl;

import com.iotlab.integrityarchives.common.service.impl.BaseServiceImpl;
import com.iotlab.integrityarchives.dao.UserFamilyDao;

import com.iotlab.integrityarchives.entity.UserFamily;
import com.iotlab.integrityarchives.service.UserFamilyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;


import java.util.Date;
import java.util.List;

/**
 * @author created by Zhangdazhuang
 * @version v.0.1
 * @Description TODO
 * @date 2019/4/25
 * @备注
 **/
@Service
@SuppressWarnings("all")
public class UserFamilyServiceImpl extends BaseServiceImpl<UserFamily> implements UserFamilyService {
    @Autowired
    private UserFamilyDao userFamilyDao;

    @Override
    public List<UserFamily> findAll() {
        return userFamilyDao.selectAll();
    }

    @Override
    public List<UserFamily> findByUserId(Integer userId) {
        Example example = new Example(UserFamily.class);
        example.and().andEqualTo("userId",userId);
        return userFamilyDao.selectByExample(example);
                //selectByPrimaryKey(example);
    }

    @Override
    public void save(UserFamily userFamily) {
        try {
            //passwordHelper.encryptPassword(user); //加密
            userFamily.setCreateTime(new Date());
            userFamily.setLastEditTime(userFamily.getCreateTime());
            userFamilyDao.insert(userFamily);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(UserFamily userFamily) {
        if (userFamily.getUserId() != 0) {
            try {
                userFamily.setLastEditTime(new Date());
                this.updateNotNull(userFamily);
            } catch (Exception e) {
                e.printStackTrace();
                //throw new GlobalException(e.getMessage());
            }
        }
    }

    @Override
    public void delete(List<Long> ids) {
        if (!ids.isEmpty()) {
            try {
                this.batchDelete(ids, "id", UserFamily.class);
            } catch (Exception e) {
                e.printStackTrace();
                // throw new GlobalException(e.getMessage());
            }
        }
    }
}
