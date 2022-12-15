package portal.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import portal.api.exception.NotFoundException;
import portal.api.service.ImageService;
import portal.api.util.ImageUtil;
import portal.store.entity.User;
import portal.store.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ImageServiceImpl implements ImageService {

    private final UserRepository userRepository;
    private final ImageUtil imageUtil;

    @Override
    @Transactional
    public void update(String email, MultipartFile file) {
        User user = findByEmail(email);
        String oldImage = user.getProfileImage();
        user.setProfileImage(imageUtil.upload(file));
        imageUtil.delete(oldImage);
    }

    @Override
    public byte[] get(String email) {
        User user = findByEmail(email);
        return imageUtil.get(user.getProfileImage());
    }

    private User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new NotFoundException("Пользователь с почтой " + email + " не найден"));
    }
}
