package com.iotlab.integrityarchives.service.impl;

import com.iotlab.integrityarchives.common.service.impl.BaseServiceImpl;
import com.iotlab.integrityarchives.dao.UserDao;
import com.iotlab.integrityarchives.entity.User;
import com.iotlab.integrityarchives.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void save(User user) {
        try {
            //passwordHelper.encryptPassword(user); //加密
            userDao.insert(user);
        } catch (Exception e) {
            e.printStackTrace();
            // throw new GlobalException(e.getMessage());
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
    public User findByName(String username) {
        if (!username.isEmpty()) {
            User user = new User();
            user.setUserNumber(username);
            return userDao.select(user).get(0);
        } else {
            return new User();
        }
    }






   /* @Autowired
    private UserDao userDao;

    @Override
    public boolean login(String username, String password) {
        User user = userDao.findByUsername(username);
        if(user != null && user.getPassword().equals(password))
            return true;
        return false;
    }

    @Override
    public boolean create(User user) {
        if(userDao.create(user) == 1)
            return true;
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        if(userDao.delete(id) == 1)
            return true;
        return false;
    }

    @Override
    public boolean update(User user) {
        if(userDao.update(user) == 1)
            return true;
        return false;
    }

    @Override
    public User findOne(Integer id) {
        return userDao.findOne(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public List<User> findByPage(Integer page, int quantity) {
        Map<String, Object> map = new HashMap<>();
        map.put("start", (page-1)*10);
        map.put("quantity", quantity);
        return userDao.findByPage(map);
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public Integer countAll() {
        return userDao.countAll();
    }*/
}

