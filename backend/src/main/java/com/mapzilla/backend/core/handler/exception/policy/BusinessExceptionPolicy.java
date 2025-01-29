package com.mapzilla.backend.core.handler.exception.policy;

import com.mapzilla.backend.core.handler.exception.dto.FieldValidationErrorsDto;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface BusinessExceptionPolicy extends ExceptionPolicy {
    HttpStatus getHttpStatus();

    List<FieldValidationErrorsDto> getErrors();
}

