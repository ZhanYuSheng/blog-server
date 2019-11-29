package com.eatshit.bolg.controller;

import com.eatshit.bolg.common.JsonResponse;
import com.eatshit.bolg.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private IFileService fileService;

    @RequestMapping("/uploading")
    public JsonResponse<Void> uploading(){
        return fileService.uploading();
    }

//    public JsonResponse<Void> uploadImg(){
//
//    }
}
