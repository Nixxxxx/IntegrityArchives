package com.iotlab.integrityarchives.controller.admin;

import com.iotlab.integrityarchives.common.controller.BaseController;
import com.iotlab.integrityarchives.dto.QueryPage;
import com.iotlab.integrityarchives.dto.ResponseCode;
import com.iotlab.integrityarchives.entity.CleanArchive;
import com.iotlab.integrityarchives.entity.PersonConsultations;
import com.iotlab.integrityarchives.entity.PersonDecla;
import com.iotlab.integrityarchives.entity.User;
import com.iotlab.integrityarchives.entity.UserInfo;
import com.iotlab.integrityarchives.enums.EnableStatusEnum;
import com.iotlab.integrityarchives.service.CleanArchiveService;
import com.iotlab.integrityarchives.service.PersonConsultationsService;
import com.iotlab.integrityarchives.service.PersonDeclaService;
import com.iotlab.integrityarchives.service.UserInfoService;
import com.iotlab.integrityarchives.service.UserService;
import com.iotlab.integrityarchives.util.FileType;
import com.iotlab.integrityarchives.util.Md5Util;
import io.swagger.annotations.Api;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author created by Zhangdazhuang
 * @version v.0.1
 * @Description TODO
 * @date 2019/4/17
 * @备注
 **/
@RestController
@SuppressWarnings("all")
@RequestMapping("/manage/user")
@Api(tags = "用户信息控制API", value = "测试")
public class UserManageController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private PersonDeclaService personDeclaService;
    @Autowired
    private CleanArchiveService cleanArchiveService;
    @Autowired
    private PersonConsultationsService personConsultationsService;

    /**
     * 根据id查询指定的用户
     * 测试成功 URL:http://127.0.0.1:8080/manage/user/findById?id=1
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/findById")
    public ResponseCode findById(@RequestParam("id") Integer id) {
        return ResponseCode.success(userService.findById(id));
    }

    /**
     * 查询所有
     * 测试成功 url:http://127.0.0.1:8080/manage/user/findAll
     *
     * @return
     */
    @GetMapping(value = "/findAll")
    public List<User> findAll() {
        System.out.println("进入访问用户列表路径");
        return userService.findAll();
    }

    /**
     * 分页查询
     *
     * @param queryPage
     * @param user
     * @return
     */
    @PostMapping(value = "/findByPage")
    public ResponseCode findByPage(QueryPage queryPage, User user) {
        return ResponseCode.success(super.selectByPageNumSize(queryPage, () -> userService.findByPage(user)));
    }
    
    /**
     * 分页查询
     *
     * @param queryPage
     * @param user
     * @return
     */
    @PostMapping(value = "/findPersonConsultationsByPage")
    public ResponseCode findPersonConsultationsByPage(QueryPage queryPage, PersonConsultations personConsultations) {
        return ResponseCode.success(super.selectByPageNumSize(queryPage, () -> personConsultationsService.findByPage(personConsultations)));
    }

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/save")
    public ResponseCode save(@RequestBody User user) {
        try {
            user.setEnableStatus(EnableStatusEnum.PASS.getCode());
            user.setCreateTime(new Date());
            user.setLastEditTime(user.getCreateTime());
            user.setUserPasswd(Md5Util.MD5Encode(user.getUserPasswd(), "utf8"));
            //判断用户工号（登录帐号）是否存在
            if (userService.countUserNumber(user.getUserNumber()) >= 1) {
                return ResponseCode.RepeaterrorType();
            } else {
                //插入后，直接把id返回给主键
                userService.insertUserReturnId(user);
                System.out.println("用户id为" + user.getId());


                UserInfo userInfo = new UserInfo();
                userInfo.setUserId(user.getId());
                userInfo.setName(user.getName());
                userInfo.setCreateTime(user.getCreateTime());
                userInfo.setLastEditTime(user.getLastEditTime());
                userInfo.setEnableStatus(EnableStatusEnum.PASS.getCode());
                userInfoService.save(userInfo);

                PersonDecla personDecla = new PersonDecla();
                personDecla.setUserId(user.getId());
                personDecla.setCreateTime(user.getCreateTime());
                personDecla.setLastEditTime(user.getLastEditTime());
                personDecla.setEnableStatus(EnableStatusEnum.PASS.getCode());
                personDeclaService.save(personDecla);

                CleanArchive cleanArchive = new CleanArchive();
                cleanArchive.setUserId(user.getId());
                cleanArchive.setCreateTime(user.getCreateTime());
                cleanArchive.setLastEditTime(user.getLastEditTime());
                cleanArchive.setEnableStatus(EnableStatusEnum.PASS.getCode());
                cleanArchiveService.save(cleanArchive);

                return ResponseCode.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResponseCode update(@RequestBody User user) {
        try {
            user.setLastEditTime(new Date());

            //判断管理员工号是否存在
            List<String> numberList=userService.numberList();

            //得到原来的number
            String oldNumber=userService.findoldNumberById(user.getId());
            //得到现在的number
            String currentNumber=user.getUserNumber();

            if(user.getUserPasswd() != null && !user.getUserPasswd().isEmpty())
                user.setUserPasswd(Md5Util.MD5Encode(user.getUserPasswd(), "utf8"));
            if(currentNumber.equals(oldNumber)){
                userService.update(user);
                return ResponseCode.success();
            }else if(numberList.contains(currentNumber)){
                return ResponseCode.RepeaterrorType();
            }else{
                userService.update(user);
                return ResponseCode.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping(value = "/delete")
    public ResponseCode delete(@RequestBody List<Long> ids) {
        try {
            userService.delete(ids);
            userInfoService.delete(ids);
            personDeclaService.delete(ids);
            cleanArchiveService.delete(ids);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
    
    @PostMapping(value = "/personConsultationsdelete")
    public ResponseCode personConsultationsdelete(String id) {
    	try {
    		personConsultationsService.del(id);
    		return ResponseCode.success();
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw new RuntimeException(e.getMessage());
    	}
    }
    
    @GetMapping(value = "/downloadWord")
    public void downloadWord(String userId, String year, HttpServletResponse response,HttpServletRequest request) {
    	 PersonConsultations personConsultations = new PersonConsultations();
         personConsultations.setUserId(Integer.parseInt(userId));
         personConsultations.setYear(year);
         PersonConsultations down =  personConsultationsService.findByUserIdAndYear(personConsultations);
         
         FileInputStream fis = null;
         XWPFDocument document;
         try {
             //获取文件路径
             String filepath = down.getPath();
             File file = new File(filepath);
             String filename = file.getName();
             fis = new FileInputStream(file);
             //设置文件名及后缀
             response.setHeader("Content-Disposition", "attachment; filename=" +
                     new String(filename.getBytes(), "ISO-8859-1"));
             //response.setHeader("content-Type", "doc");
             String fileType = FileType.getFileType(filepath);
             if ("docx".equals(fileType) || "doc".equals(fileType)) {//Office的doc与docx输出流，使用poi-ooxml 3.17可用
                 document = new XWPFDocument(OPCPackage.open(fis));
                 document.write(response.getOutputStream());
             } else {//其它文件输出，如wps等
                 IOUtils.copy(fis, response.getOutputStream());
                 response.flushBuffer();
             }
         } catch (Exception e) {
			e.printStackTrace();
		}
    }


}
