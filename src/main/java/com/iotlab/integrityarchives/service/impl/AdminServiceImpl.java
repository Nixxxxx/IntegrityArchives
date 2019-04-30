package com.iotlab.integrityarchives.service.impl;

import com.iotlab.integrityarchives.common.service.impl.BaseServiceImpl;
import com.iotlab.integrityarchives.dao.AdminDao;
import com.iotlab.integrityarchives.entity.Admin;
import com.iotlab.integrityarchives.service.AdminService;
import com.iotlab.integrityarchives.util.Md5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
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
            admin.setCreateTime(new Date());
            admin.setLastEditTime(admin.getCreateTime());
            adminDao.insert(admin);
        } catch (Exception e) {
            e.printStackTrace();
            // throw new GlobalException(e.getMessage());
        }
    }

    @Override
    public List<String> numberList() {
        return adminDao.findAllNumber();
    }

    @Override
    public String findoldNumberById(Integer id) {
        return adminDao.findNumberByadminId(id);
    }

    @Override
    @Transactional
    public void update(Admin admin) {
        if (admin.getId() != 0) {
            try {
                if (admin.getAdminPasswd() != null && !"".equals(admin.getAdminPasswd())) {
                    admin.setLastEditTime(new Date());
                    admin.setAdminPasswd(Md5Util.MD5Encode(admin.getAdminPasswd(), "utf-8"));
                    this.updateNotNull(admin);
                }
            } catch (Exception e) {
                e.printStackTrace();
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
    public int countAdminNumber(String adminNumber) {
        return adminDao.countAdminNumber(adminNumber);
    }


    @Override
    public Admin findByNumber(String number) {
        if (!number.isEmpty()) {
            Admin admin = new Admin();
            admin.setAdminNumber(number);
            List<Admin> admins = adminDao.select(admin);
            return admins.size() == 0 ? null : admins.get(0);
        } else {
            return new Admin();
        }
    }

    @Override
    public List<Admin> findByPage(Admin admin) {
        Example example = new Example(Admin.class);
        if (!StringUtils.isEmpty(admin.getAdminNumber())) {
            example.createCriteria().andLike("adminNumber", "%" + admin.getAdminNumber() + "%");
        }
        List<Admin> list = adminDao.selectByExample(example);
        return list;
    }

}

