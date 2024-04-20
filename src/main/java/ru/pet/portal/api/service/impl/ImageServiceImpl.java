package ru.pet.portal.api.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.pet.portal.api.config.ExamPortalConfiguration;
import ru.pet.portal.api.service.ImageService;
import ru.pet.portal.api.util.FileUtil;
import ru.pet.portal.store.entity.User;
import ru.pet.portal.store.repository.UserRepository;

@Service
public class ImageServiceImpl implements ImageService {

    private final String dir;
    private final String defaultImage;
    private final UserRepository userRepository;


    public ImageServiceImpl(UserRepository userRepository, ExamPortalConfiguration examPortalConfiguration) {
        this.userRepository = userRepository;
        this.dir = examPortalConfiguration.getImageUploadDirectory();
        this.defaultImage = examPortalConfiguration.getImageDefault();
    }

    @Override
    public void updateImageProfile(User user, MultipartFile file) {
        String oldImage = user.getProfileImage();
        String newImage = FileUtil.save(dir, file);
        FileUtil.delete(dir, oldImage, defaultImage);
        user.setProfileImage(newImage);
        userRepository.save(user);
    }

    @Override
    public byte[] getImageProfile(String imageName) {
        return FileUtil.readFile(dir, imageName);
    }
}
