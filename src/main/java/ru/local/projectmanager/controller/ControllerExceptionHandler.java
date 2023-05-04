package ru.local.projectmanager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.local.projectmanager.router.dto.ErrorDto;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> exception(Exception ex) {
        var error = new ErrorDto();
        error.setMessage(ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
}