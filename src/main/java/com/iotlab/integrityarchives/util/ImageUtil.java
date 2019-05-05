package com.iotlab.integrityarchives.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author created by Zhangdazhuang
 * @version v.0.1
 * @Description TODO
 * @date 2019/4/30
 * @备注
 **/
public class ImageUtil {

    public static String imagePath(MultipartFile file,String fileName) {
        if (file.isEmpty()) {
            return "false";
        }

        int size = (int) file.getSize();
        System.out.println("保存的文件名为"+fileName + "-->" + size);
        String path = "D:/upload";
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("linux")){
            path="/usr/upload";
        }
        File dest = new File(path + "/" +fileName);
        if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            //根据系统的不同，保存到不同的路径
            file.transferTo(dest);
            if(os.toLowerCase().startsWith("linux")){
                return fileName;  //zhangxin.png
            }
            else return dest.getPath();  //D:/upload/zhangxin.png
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        }


    }
}
