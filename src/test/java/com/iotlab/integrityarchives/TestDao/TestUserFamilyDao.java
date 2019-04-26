package com.iotlab.integrityarchives.TestDao;

import com.iotlab.integrityarchives.dao.CleanArchivesDao;
import com.iotlab.integrityarchives.dao.UserDao;
import com.iotlab.integrityarchives.dao.UserFamilyDao;
import com.iotlab.integrityarchives.entity.Userfamily;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author created by Zhangdazhuang
 * @version v.0.1
 * @Description TODO
 * @date 2019/4/24
 * @备注
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserFamilyDao {

    @Autowired
    private UserFamilyDao userFamilyDao;

    @Autowired
    private UserDao userDao;


    @Test
    public void testUserDao(){
        System.out.println("查出来的用户工号数量为:"+userDao.countUserNumber("0451362"));
    }

    @Test
    public void testUserFamilyDao() {
        System.out.println(userFamilyDao.selectAll());
    }


}
