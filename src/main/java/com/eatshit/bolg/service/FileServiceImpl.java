package com.eatshit.bolg.service;

import com.eatshit.bolg.common.JsonResponse;
import com.eatshit.bolg.component.OssComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements IFileService {
    @Autowired
    private OssComponent ossComponent;

    @Override
    public JsonResponse<Void> uploading() {
        ossComponent.uploading();
        return new JsonResponse<>();
    }
}
