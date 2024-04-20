package ru.pet.portal.api.controller.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ErrorResponse {
    private String message;

    public static ErrorResponse create() {
        return new ErrorResponse();
    }
}
