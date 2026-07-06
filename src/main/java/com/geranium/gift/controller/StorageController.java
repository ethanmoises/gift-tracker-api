package com.geranium.gift.controller;

import com.geranium.gift.model.dto.StorageResponseDTO;
import com.geranium.gift.service.MinioStorageService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/storage")
public class StorageController {

    private final MinioStorageService storageService;

    public StorageController(MinioStorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping(
            value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public StorageResponseDTO upload(

            @RequestPart("file")
            MultipartFile file) {

        String objectName = storageService.upload(file);

        return new StorageResponseDTO(objectName);
    }

}