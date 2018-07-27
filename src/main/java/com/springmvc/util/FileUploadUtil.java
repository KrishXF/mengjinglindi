package com.springmvc.util;

import com.springmvc.common.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public  class FileUploadUtil {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //上传图片方法
    public static String uploadFile(HttpServletRequest request,MultipartFile file) throws FileNotFoundException, IOException {

        //获取文件的项目的根路径，不同框架获取的方式不一样，这里使用struts2的获取方式
        String basePath = request.getSession().getServletContext().getRealPath("/")+Constant.CardLogoPath;
        String filename = getFileName(file);
        //拼接成完整的指定的文件路径名，创建新文件
        String filePath = basePath+filename;
//        File newfile = new File(filePath);
//        if(newfile.exists()){
//            newfile.delete();
//        }
//
//        //使用输入流读取前台的file文件
//        InputStream is=new FileInputStream(file);
//
//        //循环读取输入流文件内容，通过输出流将内容写入新文件
//        OutputStream os=new FileOutputStream(newfile);
//        byte buffer[]=new byte[1024];
//        int cnt=0;
//        while((cnt=is.read(buffer))>0){
//            os.write(buffer, 0, cnt);
//        }
//        //关闭输入输出流
//        os.close();
//        is.close();

        File newFile = new File(filePath);

        // 写文件到磁盘
        file.transferTo(newFile);
        return filePath;
    }

    public static String getFileName(MultipartFile file){

        String str = file.getOriginalFilename();
        //获取文件类型，即后缀名
        String suffix = str.substring(str.lastIndexOf("."));

        //用 当前日期+UUID作为文件名避免重名
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(new Date()).replaceAll("-", "");
        String name = dateStr + UUID.randomUUID().toString().replaceAll("-", "");
        return name + suffix;
    }
}
