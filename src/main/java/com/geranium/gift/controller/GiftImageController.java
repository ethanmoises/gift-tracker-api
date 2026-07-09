package com.geranium.gift.controller;

import com.geranium.gift.model.dto.GiftImageResponseDTO;
import com.geranium.gift.service.GiftImageService;
import io.minio.GetObjectResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*")
@RestController
public class GiftImageController {

    private final GiftImageService giftImageService;

    public GiftImageController(GiftImageService giftImageService) {
        this.giftImageService = giftImageService;
    }

    @PostMapping(
            value = "/api/gifts/{giftId}/images",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public GiftImageResponseDTO uploadImage(
            @PathVariable Long giftId,
            @RequestPart("file") MultipartFile file) {

        return giftImageService.uploadImage(giftId, file);
    }

    @GetMapping("/api/gift-images/{imageId}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long imageId) {

        try (GetObjectResponse image = giftImageService.getImage(imageId)) {

            byte[] bytes = image.readAllBytes();

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(image.headers().get("Content-Type")))
                    .body(bytes);

        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve image.", e);
        }
    }

    @DeleteMapping("/api/gift-images/{imageId}")
    public void deleteImage(@PathVariable Long imageId) {
        giftImageService.deleteImage(imageId);
    }
}