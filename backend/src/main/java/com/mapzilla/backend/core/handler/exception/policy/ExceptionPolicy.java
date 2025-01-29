package com.mapzilla.backend.core.handler.exception.policy;

public interface ExceptionPolicy {
    String getCode();

    String getMessage();
}