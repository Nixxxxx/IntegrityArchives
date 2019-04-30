package com.iotlab.integrityarchives.TestDao;

import com.iotlab.integrityarchives.dao.CleanArchiveDao;
import com.iotlab.integrityarchives.entity.CleanArchive;
import org.junit.Ignore;
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
@Ignore
public class TestCleanArchiveDao {

    @Autowired
    private CleanArchiveDao cleanArchiveDao;

    @Test
    public void testcleanArchiveDao() {
        System.out.println(cleanArchiveDao.findById(2));
    }


}
