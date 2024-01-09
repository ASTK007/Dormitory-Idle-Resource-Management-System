package com.hzcu_fleakmarket.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@CrossOrigin
public class FileUploadController {
    
    
@PostMapping("/upload")
    public String up(@RequestPart MultipartFile photo,HttpServletRequest request) throws IllegalStateException, IOException{
        String path =ResourceUtils.getURL("classpath:").getPath()+"/upload/";
        File dir = new File(path);
        if(!dir.exists()){
            dir.mkdir();
        }
        String uuid = UUID.randomUUID().toString();
		String oldName = photo.getOriginalFilename();
		String suffix = oldName.substring(oldName.lastIndexOf("."));
		String newFile = uuid+suffix;
		File file = new File(path+newFile);
        photo.transferTo(file);

        return "http://localhost:8081/upload/"+file.getName();
    }

    }
