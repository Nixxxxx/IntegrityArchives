package com.iotlab.integrityarchives.service.impl;

import com.iotlab.integrityarchives.common.service.impl.BaseServiceImpl;
import com.iotlab.integrityarchives.dao.UserDao;
import com.iotlab.integrityarchives.entity.User;
import com.iotlab.integrityarchives.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@SuppressWarnings("all")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Autowired
    private UserDao userDao;



/*    @Autowired
    private PasswordHelper passwordHelper;*/

    @Override
    public List<User> findAll() {

        return userDao.selectAll();
    }



    @Override
    public User findById(Integer id) {
        return userDao.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int  insertUserReturnId(User user) {
        try {
            //passwordHelper.encryptPassword(user); //加密

            return userDao.insertUserReturnId(user);
        } catch (Exception e) {
            e.printStackTrace();
             throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    @Transactional
    public void update(User user) {
        if (user.getId() != 0) {
            try {
                if (user.getUserPasswd() != null && !"".equals(user.getUserPasswd())) {
                    //passwordHelper.encryptPassword(user); //加密
                }
                this.updateNotNull(user);
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
                //通用mapper自带的批量删除
                this.batchDelete(ids, "id", User.class);
            } catch (Exception e) {
                e.printStackTrace();
                // throw new GlobalException(e.getMessage());
            }
        }
    }

    @Override
    public int countUserNumber(String userNumber) {
        return userDao.countUserNumber(userNumber);
    }

    @Override
    public User findByNumber(String number) {
        if (!number.isEmpty()) {
            User user = new User();
            user.setUserNumber(number);
            List<User> users = userDao.select(user);
            return users.size() == 0 ? null : users.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<User> findByPage(User user) {
        System.out.println("从前台传入的User信息为:"+user);
        Example example = new Example(User.class);
        if (!StringUtils.isEmpty(user.getUserNumber())) {
            //adminNumber是实体类中属性 userNumber 驼峰命名转化为 user_number
            example.createCriteria().andLike("userNumber", "%" + user.getUserNumber()+ "%").orLike("name","%"+user.getUserNumber()+"%");
        }
        List<User> list = userDao.selectByExample(example);
        return list;
    }

    @Override
    public List<String> numberList() {
        return userDao.findAllNumber();
    }

    @Override
    public String findoldNumberById(Integer id) {
        return userDao.findNumberByuserId(id);
    }
}