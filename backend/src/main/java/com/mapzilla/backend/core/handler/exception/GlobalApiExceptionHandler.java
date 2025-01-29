package com.mapzilla.backend.core.handler.exception;

import com.mapzilla.backend.core.handler.exception.dto.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorResponseDto> handleUncaughtException(
            final Exception exception, final ServletWebRequest request) {
        if (log.isErrorEnabled()) {
            log.error(exception.getMessage(), exception);
        }
        final ErrorResponseDto errorResponseDto =
                ErrorResponseBuilder.build(
                        Exception.class.getSimpleName(),
                        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseDto);
    }
}
