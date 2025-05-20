package com.mapzilla.backend.feature.util.dto;

import com.mapzilla.backend.core.handler.exception.dto.FieldValidationErrorsDto;
import com.mapzilla.backend.feature.util.enums.SuccessCode;
import jakarta.annotation.Nullable;
import lombok.Data;

import java.util.List;

@Data
public class ApiResponse<T> {
    SuccessCode code;
    String message;
    T data;
    @Nullable
    List<FieldValidationErrorsDto> errors;

    public ApiResponse(SuccessCode code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(SuccessCode code, String message, List<FieldValidationErrorsDto> errors) {
        this.code = code;
        this.message = message;
        this.errors = errors;
    }
}
