package ru.pet.portal.api.service;

import org.springframework.web.multipart.MultipartFile;
import ru.pet.portal.store.entity.User;

public interface ImageService {
    byte[] getImageProfile(String imageName);

    void updateImageProfile(User user, MultipartFile file);
}
