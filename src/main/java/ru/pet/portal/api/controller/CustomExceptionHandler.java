package ru.pet.portal.api.controller;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.pet.portal.api.controller.dto.ErrorResponse;

import static ru.pet.portal.api.controller.dto.ErrorResponse.create;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(value = {ConstraintViolationException.class, MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse badRequest(Exception ex) {
        log.info("message {}", ex.getMessage(), ex);
        return create().setMessage(ex.getMessage());
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse conflict(RuntimeException ex) {
        log.info("message {}", ex.getMessage(), ex);
        return create().setMessage(ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse throwable(Throwable ex) {
        log.error("message {}", ex.getMessage(), ex);
        return create().setMessage(ex.getMessage());
    }


}
