package ru.pet.portal.api.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.pet.portal.api.config.ExamPortalProperties;
import ru.pet.portal.api.exception.NotFoundException;
import ru.pet.portal.api.service.ImageService;
import ru.pet.portal.api.util.FileUtil;
import ru.pet.portal.store.entity.UserE;
import ru.pet.portal.store.repository.UserRepository;

@Service
@Slf4j
@Profile("!minio")
public class ImageServiceImpl implements ImageService {

    private final String dir;
    private final String defaultImage;
    private final UserRepository userRepository;


    public ImageServiceImpl(UserRepository userRepository, ExamPortalProperties examPortalProperties) {
        this.userRepository = userRepository;
        this.dir = examPortalProperties.getImageUploadDirectory();
        this.defaultImage = examPortalProperties.getImageDefault();
    }

    @Override
    public void updateImageProfile(UserE userE, MultipartFile file) {
        String oldImage = userE.getProfileImage();
        String newImage = FileUtil.save(dir, file);
        FileUtil.delete(dir, oldImage, defaultImage);
        userE.setProfileImage(newImage);
        userRepository.save(userE);
    }

    @Override
    public byte[] getImageProfile(String imageName) {
        try {
            return FileUtil.readFile(dir, imageName);
        } catch (NotFoundException ex) {
            log.error("Image not found", ex);
            return FileUtil.readFile(dir, defaultImage);
        }
    }
}