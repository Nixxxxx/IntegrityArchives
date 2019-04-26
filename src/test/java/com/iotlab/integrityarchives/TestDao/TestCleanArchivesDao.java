package com.iotlab.integrityarchives.TestDao;

import com.iotlab.integrityarchives.dao.CleanArchivesDao;
import com.iotlab.integrityarchives.entity.CleanArchives;
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
public class TestCleanArchivesDao {

    @Autowired
    private CleanArchivesDao cleanArchivesDao;

    @Test
    public void testcleanArchivesDao() {
        System.out.println(cleanArchivesDao.findById(2));
    }


}
