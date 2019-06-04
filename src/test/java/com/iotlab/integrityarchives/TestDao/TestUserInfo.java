package com.iotlab.integrityarchives.TestDao;

import com.iotlab.integrityarchives.dao.UserInfoDao;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author created by CodingZhangxin
 * @version v.0.1
 * @Description TODO
 * @date 2019/6/2
 * @备注
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class TestUserInfo {

    @Autowired
    private UserInfoDao userInfoDao;

    @Test
    public void testPrintUserInfo() {
        System.out.print(userInfoDao.PrintUserInfo(43).getCleanArchive());
    }

}
