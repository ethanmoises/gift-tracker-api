package com.geranium.gift.model.dto;

import java.time.LocalDateTime;

public class GiftImageResponseDTO {

    private Long id;
    private String objectName;
    private String originalFileName;
    private String contentType;
    private Long size;
    private LocalDateTime uploadedAt;

    public GiftImageResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getObjectName() {
        return objectName;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public String getContentType() {
        return contentType;
    }

    public Long getSize() {
        return size;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }
}