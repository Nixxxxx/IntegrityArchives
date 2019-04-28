package com.iotlab.integrityarchives.TestService;

import com.iotlab.integrityarchives.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author created by Zhangdazhuang
 * @version v.0.1
 * @Description TODO
 * @date 2019/4/28
 * @备注
 **/

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAdminService {

    @Autowired
    private AdminService adminService;

    @Test
    public void testselectByNumber() {

        System.out.println(adminService.findByNumber("005909"));

    }


}
