package ru.pet.portal.api.controller;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.pet.portal.api.controller.dto.ErrorResponse;

import static ru.pet.portal.api.controller.dto.ErrorResponse.create;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(value = {ConstraintViolationException.class, MethodArgumentNotValidException.class})
    public ErrorResponse badRequest(RuntimeException ex) {
        log.info("message {}", ex.getMessage(), ex);
        return create().setMessage(ex.getMessage());
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public ErrorResponse conflict(RuntimeException ex) {
        log.info("message {}", ex.getMessage(), ex);
        return create().setMessage(ex.getMessage());
    }

    @ExceptionHandler
    public ErrorResponse throwable(Throwable ex) {
        log.error("message {}", ex.getMessage(), ex);
        return create().setMessage(ex.getMessage());
    }


}
