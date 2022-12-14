package portal.api.exception;

import javax.security.sasl.AuthenticationException;
import javax.validation.ConstraintViolationException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionController {

	@ExceptionHandler({BadRequestException.class, MethodArgumentNotValidException.class,
			ConstraintViolationException.class})
	public ResponseEntity<String> badRequestException(RuntimeException ex){
		log.info(ex.getMessage());
		return new ResponseEntity<>(ex.getMessage() ,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<String> userFoundException(UserFoundException ex){
		log.info(ex.getMessage());
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
	}
	@ExceptionHandler
	public ResponseEntity<String> userNotFoundException(UserNotFoundException ex){
		log.info(ex.getMessage());
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<String> authenticationException(AuthenticationException ex){
		log.info(ex.getMessage());
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
	}
	@ExceptionHandler
	public ResponseEntity<String> notPermissionException(NotPermissionException ex){
		log.info(ex.getMessage());
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler
	public ResponseEntity<String> invalidFileException(InvalidFileException ex){
		log.info(ex.getMessage());
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler
	public ResponseEntity<String> throwable(Throwable ex) {
		log.error(ex.getMessage());
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
