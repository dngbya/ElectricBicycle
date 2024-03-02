package com.example.demo.controller;

import com.example.demo.utils.AliOSSUtils;
import com.example.demo.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    AliOSSUtils aliOSSUtils;
    @PostMapping
    public R upload(MultipartFile image) throws IOException {
        log.info("文件上传，文件名：{}",image.getOriginalFilename());
        log.info("文件上传，文件：{}",image);
        String url=aliOSSUtils.upload(image);
        log.info("文件路径为：{}",url);
        return R.success(url);
    }
}
