package ru.pet.portal.api.controller.priv;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pet.portal.api.controller.dto.user.UserResponseDto;
import ru.pet.portal.store.entity.User;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "${exam.portal.front-url}")
@RequiredArgsConstructor
class PrivateUserController {

    @GetMapping("/current-user")
    public UserResponseDto getCurrentUser(Principal principal) {
        User user = (User)((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        return new UserResponseDto().setRole(user.getRole().name()).setEmail(user.getEmail());
    }



}
