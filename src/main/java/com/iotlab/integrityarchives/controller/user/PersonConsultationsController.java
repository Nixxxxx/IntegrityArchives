package com.iotlab.integrityarchives.controller.user;

import com.iotlab.integrityarchives.common.controller.BaseController;
import com.iotlab.integrityarchives.config.MyConfig;
import com.iotlab.integrityarchives.dto.QueryPage;
import com.iotlab.integrityarchives.dto.ResponseCode;
import com.iotlab.integrityarchives.entity.Admin;
import com.iotlab.integrityarchives.entity.CleanArchive;
import com.iotlab.integrityarchives.entity.PersonConsultations;
import com.iotlab.integrityarchives.enums.EnableStatusEnum;
import com.iotlab.integrityarchives.service.CleanArchiveService;
import com.iotlab.integrityarchives.service.PersonConsultationsService;
import com.iotlab.integrityarchives.service.UserService;
import com.iotlab.integrityarchives.util.FileType;

import io.swagger.annotations.Api;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@SuppressWarnings("all")
@RequestMapping("/user/personConsultations")
@Api(tags = "个人述职联述", value = "测试")
public class PersonConsultationsController extends BaseController {
	
	@Autowired
    private PersonConsultationsService personConsultationsService;
	@Autowired
	private UserService userService;

    @GetMapping(value = "/findByUserId")
    public ResponseCode findByUserId(@RequestParam("userId") Integer userId) {
        return ResponseCode.success(personConsultationsService.findByUserId(userId));
    }

    @PostMapping(value = "/upload")
    public void upload(@RequestParam("file") MultipartFile file, Integer userId, String year) {
        String os = System.getProperty("os.name");
        String userName=userService.findUserById(userId).getName();
        String fileName = file.getOriginalFilename();//文件原始名称
        //拼接原来的文件名
        fileName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())+"-"+userName+"-"+year+"年个人述职述联文件"+fileName.substring(fileName.lastIndexOf("."));//自定义名称防止上传文件名冲突

        String filePath = MyConfig.getProfile();
        if(os.toLowerCase().startsWith("linux")){
            filePath="/usr/upload/";
        }
        System.out.print("从配置文件中得到的路径为:"+filePath);
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            //上传成后记录到数据库中    id,user_id,year,path
            PersonConsultations personConsultations = new PersonConsultations();
            personConsultations.setUserId(userId);
            personConsultations.setYear(year);
            personConsultations.setPath(dest.getAbsolutePath());
            personConsultations.setName(userName);
            personConsultationsService.save(personConsultations);
        } catch (IOException e) {
            e.printStackTrace();
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
