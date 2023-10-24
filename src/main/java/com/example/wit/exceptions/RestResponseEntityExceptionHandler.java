package com.example.wit.exceptions;

import jakarta.annotation.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
//    @ExceptionHandler(value = ElementNotFoundException.class)
//    protected ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request) {
//        String body = "something";
//        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
//    }

    @ExceptionHandler(value = ElementNotFoundException.class)
    protected ResponseEntity<ErrorBody> handlePlayerNotFoundException(ElementNotFoundException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, new ErrorBody(errors), new HttpHeaders(), status, request);
    }

    @ExceptionHandler(value = ElementAlreadyExistsException.class)
    protected ResponseEntity<ErrorBody> handleElementAlreadyExistsException(ElementAlreadyExistsException ex, WebRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, new ErrorBody(errors), new HttpHeaders(), status, request);
    }

    protected ResponseEntity<ErrorBody> handleExceptionInternal(Exception ex, @Nullable ErrorBody body,
                                                               HttpHeaders headers, HttpStatus status,
                                                               WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        return new ResponseEntity<>(body, headers, status);
    }
}
