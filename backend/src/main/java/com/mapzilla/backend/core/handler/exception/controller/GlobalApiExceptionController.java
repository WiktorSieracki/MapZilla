package com.mapzilla.backend.core.handler.exception.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception/")
public class GlobalApiExceptionController {
    @GetMapping("http-message-not-readable")
    ResponseEntity<FieldValidationErrorsDto> handleHttpMessageNotReadableException(
            @RequestBody FieldValidationErrorsDto fieldValidationErrorsDto) {
        return ResponseEntity.ok(fieldValidationErrorsDto);
    }
}
