package portal.api.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    void update(String email, MultipartFile file);

    byte[] get(String email);
}
