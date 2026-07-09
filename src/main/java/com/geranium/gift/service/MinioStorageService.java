package com.geranium.gift.service;

import com.geranium.gift.config.MinioProperties;
import com.geranium.gift.exception.FileDeleteException;
import com.geranium.gift.exception.FileUploadException;
import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.errors.MinioException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import java.util.UUID;

@Service
public class MinioStorageService {

    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024; // 5 MB

    private static final Set<String> ALLOWED_CONTENT_TYPES = Set.of(
            "image/jpeg",
            "image/png",
            "image/webp"
    );

    private final MinioClient minioClient;
    private final MinioProperties properties;

    public MinioStorageService(
            MinioClient minioClient,
            MinioProperties properties) {

        this.minioClient = minioClient;
        this.properties = properties;
    }

    public String upload(MultipartFile file) {

        validateFile(file);

        String objectName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        try (InputStream inputStream = file.getInputStream()) {

            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(properties.getBucket())
                            .object(objectName)
                            .stream(
                                    inputStream,
                                    file.getSize(),
                                    -1L
                            )
                            .contentType(file.getContentType())
                            .build()
            );

            return objectName;

        } catch (IOException | MinioException e) {
            throw uploadException(e);
        }
    }

    public GetObjectResponse download(String objectName) {

        try {

            return minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(properties.getBucket())
                            .object(objectName)
                            .build()
            );

        } catch (Exception e) {
            throw new RuntimeException("Failed to download file.", e);
        }
    }

    public void delete(String objectName) {

        try {

            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(properties.getBucket())
                            .object(objectName)
                            .build()
            );

        } catch (MinioException e) {
            throw deleteException(e);
        }
    }

    private void validateFile(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new FileUploadException("File cannot be empty.");
        }

        if (file.getSize() > MAX_FILE_SIZE) {
            throw new FileUploadException("File size must not exceed 5 MB.");
        }

        if (!ALLOWED_CONTENT_TYPES.contains(file.getContentType())) {
            throw new FileUploadException(
                    "Only JPEG, PNG, and WEBP images are allowed."
            );
        }
    }

    private FileUploadException uploadException(Throwable cause) {
        return new FileUploadException(
                "Failed to upload file.",
                cause
        );
    }

    private FileDeleteException deleteException(Throwable cause) {
        return new FileDeleteException(
                "Failed to delete file.",
                cause
        );
    }
}