package ru.pet.portal.api.service;

import org.springframework.web.multipart.MultipartFile;
import ru.pet.portal.store.entity.UserE;

public interface ImageService {
    byte[] getImageProfile(String imageName);

    void updateImageProfile(UserE userE, MultipartFile file);
}
