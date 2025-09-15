package com.mdotm.api.controller.advice;

import com.mdotm.api.dto.ApiErrorResponseDto;
import com.mdotm.api.dto.ErrorResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvice {
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponseDto noSuchElementExceptionHandler(NoSuchElementException exception) {
        log.error("[ControllerAdvice] noSuchElementExceptionHandler, exception: [{}]", exception.getMessage());
        return this.generateError(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponseDto methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {
        log.error("[ControllerAdvice] methodArgumentNotValidExceptionHandler, exception: [{}]", exception.getMessage());
        List<ErrorResponseDto> errorList = exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .map(message -> new ErrorResponseDto(HttpStatus.BAD_REQUEST.toString(), message))
                .toList();
        return new ApiErrorResponseDto(errorList);
    }

    private ApiErrorResponseDto generateError(HttpStatus httpStatus, String description) {
        ErrorResponseDto errorResponse = new ErrorResponseDto(httpStatus.toString(), description);
        return new ApiErrorResponseDto(List.of(errorResponse));
    }
}