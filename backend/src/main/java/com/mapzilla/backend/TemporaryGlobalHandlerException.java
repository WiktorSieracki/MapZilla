//package com.mapzilla.backend;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//
//@ControllerAdvice
//public class TemporaryGlobalHandlerException {
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(
//            MethodArgumentNotValidException ex, WebRequest request) {
//
//        Map<String, Object> body = new HashMap<>();
//        body.put("timestamp", System.currentTimeMillis());
//        body.put("status", HttpStatus.BAD_REQUEST.value());
//
//        List<String> errors = ex.getBindingResult()
//                .getFieldErrors()
//                .stream()
//                .map(x -> x.getDefaultMessage())
//                .collect(Collectors.toList());
//
//        body.put("errors", errors);
//
//        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
//    }
//}
