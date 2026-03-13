package ru.pet.portal.api.service.impl;

import io.minio.*;
import io.minio.errors.MinioException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.pet.portal.api.config.ExamPortalProperties;
import ru.pet.portal.api.service.ImageService;
import ru.pet.portal.api.util.FileUtil;
import ru.pet.portal.store.entity.UserE;
import ru.pet.portal.store.repository.UserRepository;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class S3ImageServiceImpl implements ImageService {

    private final MinioClient minioClient;
    private final UserRepository userRepository;
    private final ExamPortalProperties examPortalProperties;

    @Value("${minio.bucket}")
    private String bucketName;

    @Override
    public void updateImageProfile(UserE userE, MultipartFile file) {
        try {
            String oldImage = userE.getProfileImage();
            String originalFilename = file.getOriginalFilename();
            String fileExtension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            String newImage = UUID.randomUUID() + fileExtension;

            // Удаляем старое изображение, если оно не является дефолтным
            if (oldImage != null && !oldImage.equals(examPortalProperties.getImageDefault())) {
                try {
                    minioClient.removeObject(
                            RemoveObjectArgs.builder()
                                    .bucket(bucketName)
                                    .object(oldImage)
                                    .build()
                    );
                } catch (MinioException e) {
                    log.error("Error removing old image from S3", e);
                }
            }

            // Сохраняем новое изображение с случайным именем
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(newImage)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );

            userE.setProfileImage(newImage);
            userRepository.save(userE);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException | MinioException e) {
            log.error("Error uploading image to S3", e);
            throw new RuntimeException("Failed to upload image", e);
        }
    }

    @Override
    public byte[] getImageProfile(String imageName) {
        if (Objects.equals(imageName, examPortalProperties.getImageDefault())) {
            return getDefaultImageProfile();
        }

        try (InputStream stream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object(imageName)
                        .build()
        )) {
            return stream.readAllBytes();
        } catch (MinioException | IOException | InvalidKeyException | NoSuchAlgorithmException e) {
            log.error("Error retrieving image from S3", e);
            try {
                // Возвращаем дефолтное изображение
                return FileUtil.readFile(examPortalProperties.getImageUploadDirectory(),
                        examPortalProperties.getImageDefault());
            } catch (Exception ex) {
                log.error("Error retrieving default image from file system", ex);
                throw ex;
            }
        }
    }

    private byte[] getDefaultImageProfile() {
        try {
            // Возвращаем дефолтное изображение
            return FileUtil.readFile(examPortalProperties.getImageUploadDirectory(),
                    examPortalProperties.getImageDefault());
        } catch (Exception ex) {
            log.error("Error retrieving default image from file system", ex);
            throw ex;
        }
    }
}