package com.geranium.gift.service.impl;

import com.geranium.gift.exception.ResourceNotFoundException;
import com.geranium.gift.mapper.GiftImageMapper;
import com.geranium.gift.model.dto.GiftImageResponseDTO;
import com.geranium.gift.model.entity.Gift;
import com.geranium.gift.model.entity.GiftImage;
import com.geranium.gift.repository.GiftImageRepository;
import com.geranium.gift.repository.GiftRepository;
import com.geranium.gift.service.GiftImageService;
import com.geranium.gift.service.MinioStorageService;
import io.minio.GetObjectResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
@Transactional
public class GiftImageServiceImpl implements GiftImageService {

    private final GiftRepository giftRepository;
    private final GiftImageRepository giftImageRepository;
    private final MinioStorageService storageService;
    private final GiftImageMapper giftImageMapper;

    public GiftImageServiceImpl(
            GiftRepository giftRepository,
            GiftImageRepository giftImageRepository,
            MinioStorageService storageService,
            GiftImageMapper giftImageMapper) {

        this.giftRepository = giftRepository;
        this.giftImageRepository = giftImageRepository;
        this.storageService = storageService;
        this.giftImageMapper = giftImageMapper;
    }

    @Override
    public GiftImageResponseDTO uploadImage(Long giftId, MultipartFile file) {

        Gift gift = giftRepository.findById(giftId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Gift not found with id: " + giftId));

        String objectName = storageService.upload(file);

        GiftImage image = new GiftImage();
        image.setObjectName(objectName);
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setUploadedAt(LocalDateTime.now());

        gift.addImage(image);

        GiftImage savedImage = giftImageRepository.save(image);

        return giftImageMapper.toResponseDTO(savedImage);
    }

    @Override
    @Transactional(readOnly = true)
    public GetObjectResponse getImage(Long imageId) {

        GiftImage image = giftImageRepository.findById(imageId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Image not found with id: " + imageId));

        return storageService.download(image.getObjectName());
    }

    @Override
    public void deleteImage(Long imageId) {

        GiftImage image = giftImageRepository.findById(imageId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Image not found with id: " + imageId));

        storageService.delete(image.getObjectName());

        Gift gift = image.getGift();
        gift.removeImage(image);

        giftImageRepository.delete(image);
    }
}