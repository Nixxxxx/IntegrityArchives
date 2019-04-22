package com.iotlab.integrityarchives.service.impl;


import com.iotlab.integrityarchives.common.service.impl.BaseServiceImpl;
import com.iotlab.integrityarchives.dao.AdminDao;
import com.iotlab.integrityarchives.entity.Admin;
import com.iotlab.integrityarchives.service.AdminService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@SuppressWarnings("all")
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {

    @Autowired
    private AdminDao adminDao;


/*    @Autowired
    private PasswordHelper passwordHelper;*/

    @Override
    public List<Admin> findAll() {
        return adminDao.selectAll();
    }

    @Override
    public Admin findById(Integer id) {
        return adminDao.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public void save(Admin admin) {
        try {
            //passwordHelper.encryptPassword(admin); //加密
            adminDao.insert(admin);
        } catch (Exception e) {
            e.printStackTrace();
           // throw new GlobalException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void update(Admin admin) {
        if (admin.getId() != 0) {
            try {
                if (admin.getAdminPasswd() != null && !"".equals(admin.getAdminPasswd())) {
                    //passwordHelper.encryptPassword(admin); //加密
                }
                this.updateNotNull(admin);
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
                this.batchDelete(ids, "id", Admin.class);
            } catch (Exception e) {
                e.printStackTrace();
               // throw new GlobalException(e.getMessage());
            }
        }
    }



    @Override
    public Admin findByName(String username) {
        if (!username.isEmpty()) {
            Admin admin = new Admin();
            admin.setAdminNumber(username);
            return adminDao.select(admin).get(0);
        } else {
            return new Admin();
        }
    }

    @Override
    public List<Admin> findByPage(Admin admin) {
        Example example = new Example(Admin.class);
        if (!StringUtils.isEmpty(admin.getAdminNumber())) {
            example.createCriteria().andLike("adminNumber", "%" + admin.getAdminNumber()+ "%");
        }
        List<Admin> list = adminDao.selectByExample(example);
        return list;
    }

}

