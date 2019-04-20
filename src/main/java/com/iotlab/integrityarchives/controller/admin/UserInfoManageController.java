package com.iotlab.integrityarchives.controller.admin;

import com.iotlab.integrityarchives.common.controller.BaseController;
import com.iotlab.integrityarchives.dto.QueryPage;
import com.iotlab.integrityarchives.dto.ResponseCode;
import com.iotlab.integrityarchives.entity.UserInfo;
import com.iotlab.integrityarchives.service.UserInfoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author created by Zhangdazhuang
 * @version v.0.1
 * @Description TODO
 * @date 2019/4/17
 * @备注
 **/
@RestController
@SuppressWarnings("all")
@RequestMapping("/manage/userinfo")
@Api(tags="干部信息控制API",value="测试")
public class UserInfoManageController extends BaseController {
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 根据姓名模糊查询或者工号精确查询用户详细信息
     * 得到List列表，list每个元素代表某个用户的个人用户信息
     * 测试Url:
     *
     * @param word
     * @return
     */
    @GetMapping(value = "/findUserInfoByWord")
    public ResponseCode findUserInfoByNameOrNumber(@RequestParam("word") String word) {
        return ResponseCode.success(userInfoService.findListByWord(word));
    }


    @GetMapping(value = "/findUserInfoById")
    public ResponseCode findUserInfoById(@RequestParam("id") Integer id) {
        return ResponseCode.success(userInfoService.findByUserId(id));
    }


    /**
     * 分页查询
     *
     * @param queryPage
     * @param userInfo
     * @return
     */
    @PostMapping(value = "/findByPage")
    public ResponseCode findByPage(QueryPage queryPage, UserInfo userInfo) {
        return ResponseCode.success(super.selectByPageNumSize(queryPage, () -> userInfoService.findByPage(userInfo)));
    }


    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/save")
    public ResponseCode save(@RequestBody UserInfo userinfo) {
        try {
            userInfoService.save(userinfo);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResponseCode update(@RequestBody UserInfo userinfo) {
        try {
            userInfoService.update(userinfo);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping(value = "/delete")
    public ResponseCode delete(@RequestBody List<Long> ids) {
        try {
            userInfoService.delete(ids);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }


}
