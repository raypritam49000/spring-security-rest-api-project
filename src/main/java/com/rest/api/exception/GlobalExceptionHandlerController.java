package com.rest.api.exception;

import com.rest.api.dto.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandlerController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        LOGGER.info("@@@ handleResourceNotFoundException Message : {}", ex.getMessage());
        String path = request.getDescription(false).replace("uri=", "");
        return new ResponseEntity<>(new ApiResponse(path, HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value(), Boolean.TRUE, HttpStatus.NOT_FOUND.getReasonPhrase(), ex.getMessage(), LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<ApiResponse> handleResourceConflictException(ResourceConflictException ex, WebRequest request) {
        LOGGER.info("@@@ handleResourceConflictException Message : {}", ex.getMessage());
        String path = request.getDescription(false).replace("uri=", "");
        return new ResponseEntity<>(new ApiResponse(path, HttpStatus.CONFLICT.name(), HttpStatus.CONFLICT.value(), Boolean.FALSE, HttpStatus.CONFLICT.getReasonPhrase(), ex.getMessage(), LocalDateTime.now()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<ApiResponse> handleUnauthorizedAccessException(UnauthorizedAccessException ex, WebRequest request) {
        LOGGER.info("@@@ handleUnauthorizedAccessException Message : {}", ex.getMessage());
        String path = request.getDescription(false).replace("uri=", "");
        return new ResponseEntity<>(new ApiResponse(path, HttpStatus.UNAUTHORIZED.name(), HttpStatus.UNAUTHORIZED.value(), Boolean.FALSE, HttpStatus.UNAUTHORIZED.getReasonPhrase(), ex.getMessage(), LocalDateTime.now()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
        LOGGER.info("@@@ handleBadCredentialsException Message : {}", ex.getMessage());
        String path = request.getDescription(false).replace("uri=", "");
        return new ResponseEntity<>(new ApiResponse(path, HttpStatus.FORBIDDEN.name(), HttpStatus.FORBIDDEN.value(), Boolean.FALSE, HttpStatus.FORBIDDEN.getReasonPhrase(), ex.getMessage(), LocalDateTime.now()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGlobalExceptionException(Exception ex, WebRequest request) {
        LOGGER.info("@@@ handleGlobalExceptionException Message : {}", ex.getMessage());
        String path = request.getDescription(false).replace("uri=", "");
        return new ResponseEntity<>(new ApiResponse(path, HttpStatus.INTERNAL_SERVER_ERROR.name(), HttpStatus.INTERNAL_SERVER_ERROR.value(), Boolean.FALSE, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage(), LocalDateTime.now()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
