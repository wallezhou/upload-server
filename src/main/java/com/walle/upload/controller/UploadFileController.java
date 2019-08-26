package com.walle.upload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/upload")
public class UploadFileController {
    @RequestMapping("/file")
    @ResponseBody
    public String  uploadFile(){
        return "succus";
    }
}
