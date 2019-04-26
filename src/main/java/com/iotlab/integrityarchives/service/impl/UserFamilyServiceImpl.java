package com.iotlab.integrityarchives.service.impl;

import com.iotlab.integrityarchives.common.service.impl.BaseServiceImpl;
import com.iotlab.integrityarchives.dao.UserFamilyDao;

import com.iotlab.integrityarchives.entity.Userfamily;
import com.iotlab.integrityarchives.service.UserFamilyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;


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
public class UserFamilyServiceImpl extends BaseServiceImpl<Userfamily> implements UserFamilyService {
    @Autowired
    private UserFamilyDao userFamilyDao;

    @Override
    public List<Userfamily> findAll() {
        return userFamilyDao.selectAll();
    }

    @Override
    public List<Userfamily> findByUserId(Integer userId) {
        Example example = new Example(Userfamily.class);
        example.and().andEqualTo("userId",userId);
        return userFamilyDao.selectByExample(example);
                //selectByPrimaryKey(example);
    }

    @Override
    public void save(Userfamily Userfamily) {
        try {
            //passwordHelper.encryptPassword(user); //加密
            userFamilyDao.insert(Userfamily);
        } catch (Exception e) {
            e.printStackTrace();
            // throw new GlobalException(e.getMessage());
        }
    }

    @Override
    public void update(Userfamily userfamily) {
        if (userfamily.getUserId() != 0) {
            try {
                this.updateNotNull(userfamily);
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
                this.batchDelete(ids, "id", Userfamily.class);
            } catch (Exception e) {
                e.printStackTrace();
                // throw new GlobalException(e.getMessage());
            }
        }
    }
}
