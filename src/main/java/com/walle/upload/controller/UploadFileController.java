package com.walle.upload.controller;

import lombok.extern.java.Log;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
@RequestMapping("/upload")
@Log
public class UploadFileController {

    @RequestMapping(value = "/file", produces = "application/json")
    @CrossOrigin  //允许跨域请求
    public Object uploadFile(HttpServletRequest request) throws IOException {
        Map resultMap = new HashMap();
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        // 1. build an iterator
        Iterator<String> itr = multipartHttpServletRequest.getFileNames();
        MultipartFile mpf = null;
        File dir = new File("D:\\cloud_uploads");
        if (!dir.exists()){
            dir.mkdirs();
        }
        // 2. get each file
        while (itr.hasNext()) {
            // 2.1 get next MultipartFile
            mpf = multipartHttpServletRequest.getFile(itr.next());
            // System.out.println(mpf.getOriginalFilename() + " uploaded! ");
            log.info(mpf.getOriginalFilename() + " uploaded! " + "---" + mpf.getSize() / 1024 + " Kb");
            log.info(mpf.getContentType());
            InputStream inputStream = mpf.getInputStream();
            String contextPath = request.getContextPath();
            String CurrentClassFilePath = this.getClass().getResource("").getPath();
            String realPath = request.getSession().getServletContext().getRealPath("");
            log.info("realpath---" + realPath);
            log.info("contextPath---" + contextPath);
            log.info("CurrentClassFilePath---" + CurrentClassFilePath);
            InputStream excelInputStream = mpf.getInputStream();
            try {
                // 拷贝文件到指定的目录
                FileUtils.copyInputStreamToFile(inputStream,
                        new File(dir.getAbsolutePath()+ File.separator + mpf.getOriginalFilename()));
                // 判断上传的文件的格式是否符合要求
            } catch (Exception e) {
                e.printStackTrace();
                log.info("拷贝文件失败" + e.getMessage());
            }
        }
        return resultMap;

    }

}
