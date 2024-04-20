//package portal.api.controller.priv;
//
//import java.security.Principal;
//
//
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import portal.api.controller.dto.UserDto;
//import portal.api.controller.dto.user.UserDtoRequest;
//import portal.api.service.ImageService;
//import portal.api.service.UserService;
//
//@RestController
//@RequestMapping("/api/user")
//@CrossOrigin(origins = "${exam.portal.front-url}")
//@RequiredArgsConstructor
//public class PrivateUserController {
//
//	private final UserService userService;
//	private final ImageService imageService;
//
//	@PostMapping
//	public ResponseEntity<Void> register(@RequestBody @Valid UserDtoRequest user) {
//		userService.register(user);
//		return new ResponseEntity<>(HttpStatus.CREATED);
//	}
//
//	@PutMapping
//	@PreAuthorize("hasAuthority('developers:read')")
//	public ResponseEntity<Void> updateUserProfile(Principal principal, @RequestBody @Valid UserDto user) {
//		userService.update(principal.getName(), user);
//		return new ResponseEntity<>(HttpStatus.ACCEPTED);
//	}
//
//	@DeleteMapping
//	@PreAuthorize("hasAuthority('developers:read')")
//	public ResponseEntity<Void> delete(Principal principal) {
//		userService.delete(principal.getName());
//		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//	}
//
//	@GetMapping("image")
//	@PreAuthorize("hasAuthority('developers:read')")
//	public ResponseEntity<?> getImageProfile(Principal principal){
//		return ResponseEntity.ok(imageService.get(principal.getName()));
//	}
//	@PostMapping("image")
//	@PreAuthorize("hasAuthority('developers:read')")
//	public ResponseEntity<?> addImageProfile(@RequestParam("image") MultipartFile file, Principal principal){
//		imageService.update(principal.getName(), file);
//		return new ResponseEntity<>(HttpStatus.CREATED);
//	}
//
//
//
//}