package ru.pet.portal.api.controller.priv;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.pet.portal.api.controller.dto.mapper.UserMapper;
import ru.pet.portal.api.controller.dto.user.UserResponseDto;
import ru.pet.portal.api.service.ImageService;
import ru.pet.portal.store.entity.UserE;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "${exam.portal.front-url}")
@RequiredArgsConstructor
class PrivateUserController {

    private final UserMapper userMapper;
    private final ImageService imageService;

    @GetMapping("/me")
    public UserResponseDto getCurrentUser(UsernamePasswordAuthenticationToken token) {
        final UserE userE = (UserE) token.getPrincipal();
        return userMapper.toDto(userE);
    }

    @GetMapping("/image-profile")
    public byte[] getImageProfile(UsernamePasswordAuthenticationToken token) {
        final UserE userE = (UserE) token.getPrincipal();
        return imageService.getImageProfile(userE.getProfileImage());
    }

    @PutMapping("/image-profile-update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateImageProfile(@RequestParam("image") MultipartFile file, UsernamePasswordAuthenticationToken token) {
        UserE userE = (UserE) token.getPrincipal();
        imageService.updateImageProfile(userE, file);
    }
}
