package com.iotlab.integrityarchives.util;

import com.iotlab.integrityarchives.entity.UserFamily;
import com.iotlab.integrityarchives.entity.UserInfo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author created by Zhangdazhuang
 * @version v.0.1
 * @Description TODO
 * @date 2019/5/2
 * @备注
 **/
public class PrintUtil {



    @SuppressWarnings("unchecked")
    public static File createWord(Map dataMap,String templateName,String filePath,String fileName){

        try {
//创建配置实例
            Configuration configuration = new Configuration(new Version("2.3.8"));

//设置编码
            configuration.setDefaultEncoding("UTF-8");

//ftl模板文件
            configuration.setClassForTemplateLoading(PrintUtil.class,"/");

//获取模板
            Template template = configuration.getTemplate(templateName);

//输出文件
            File outFile = new File(File.separator+fileName);
//如果输出目标文件夹不存在，则创建
            if (!outFile.getParentFile().exists()){
                outFile.getParentFile().mkdirs();
            }
//将模板和数据模型合并生成文件
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"UTF-8"));
//生成文件
            template.process(dataMap, out);
//关闭流
            out.flush();
            out.close();
            return outFile;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 生成文件名字
     * @return
     */
    public static String creatFileName() {
/** 文件名称，唯一字符串 */
        Random r = new Random();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
        StringBuffer sb = new StringBuffer();
        sb.append(sdf1.format(new Date()));
        sb.append("_");
        sb.append(r.nextInt(100));
//文件唯一名称
        String fileOnlyName = "干部信息表" + sb + ".doc";
        return fileOnlyName;
    }

    /**
     * 导出文件
     * @throws IOException
     */
    public static void exportMillCertificateWord( HttpServletResponse response, Map map,String filePath,String templateName) throws IOException {
        File file = null;
        InputStream fin = null;
        ServletOutputStream out = null;
        try {
            String fileName = PrintUtil.creatFileName();
            file = PrintUtil.createWord(map, templateName, filePath,fileName);
            fin = new FileInputStream(file);
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/msword");
            response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode(fileName, "UTF-8"))));
            out = response.getOutputStream();
            byte[] buffer = new byte[512]; // 缓冲区
            int bytesToRead = -1;
// 通过循环将读入的Word文件的内容输出到浏览器中
            while((bytesToRead = fin.read(buffer)) != -1) {
                out.write(buffer, 0, bytesToRead);
            }
        }finally {
            if(fin != null) fin.close();
            if(out != null) out.close();
            if(file != null) file.delete(); // 删除临时文件
        }

    }




}
