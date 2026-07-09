package com.geranium.gift.service;

import com.geranium.gift.model.dto.GiftImageResponseDTO;
import io.minio.GetObjectResponse;
import org.springframework.web.multipart.MultipartFile;

public interface GiftImageService {

    GiftImageResponseDTO uploadImage(Long giftId, MultipartFile file);

    GetObjectResponse getImage(Long imageId);

    void deleteImage(Long imageId);

}