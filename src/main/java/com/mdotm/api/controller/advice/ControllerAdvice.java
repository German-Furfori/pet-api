package com.mdotm.api.controller.advice;

import com.mdotm.api.dto.ErrorResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvice {
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDto noSuchElementExceptionHandler(NoSuchElementException exception) {
        log.error("[ControllerAdvice] noSuchElementExceptionHandler, exception: [{}]", exception.getMessage());
        return this.generateError(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    private ErrorResponseDto generateError(HttpStatus httpStatus, String description) {
        return new ErrorResponseDto(httpStatus.toString(), description);
    }
}