package com.fresher.fresherserivce.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.fresher.fresherserivce.vo.ExceptionVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.fresher.fresherserivce.vo.response.ResponseEntity.responseToClient;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class HandleException {

    private static final Logger logger = LoggerFactory.getLogger(HandleException.class);

    @ExceptionHandler(value = { IllegalStateException.class })
    public ResponseEntity<ExceptionVO> handleIllegalStateException(IllegalStateException ex, WebRequest request) {
        logger.error("IllegalStateException occurred: ", ex);

        return new ResponseEntity<>(
                ExceptionVO.builder()
                        .statusCode(HttpStatus.FORBIDDEN.value())
                        .message(ex.getMessage())
                        .build(),
                new HttpHeaders(),
                HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<Object> handleGeneralException(Exception ex, WebRequest request) {
        logger.error("Exception occurred: ", ex);

        if (ex instanceof AccessDeniedException
                || (ex.getMessage() != null && ex.getMessage().contains("Access is denied"))) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", HttpStatus.FORBIDDEN.value());
            response.put("error", "Access Denied");
            response.put("message", "You do not have the necessary permissions to access this resource.");
            response.put("details", ex.getMessage());
            response.put("timestamp", System.currentTimeMillis());
            return new ResponseEntity<>(responseToClient(response), HttpStatus.FORBIDDEN);
        }

        if (ex.getMessage() != null && ex.getMessage().contains("Bad credentials")) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", HttpStatus.UNAUTHORIZED.value());
            response.put("error", "Bad credentials");
            response.put("message", "The provided credentials are incorrect.");
            response.put("details", ex.getMessage());
            response.put("timestamp", System.currentTimeMillis());
            return new ResponseEntity<>(responseToClient(response), HttpStatus.UNAUTHORIZED);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put("message", "An unexpected error occurred");

        return new ResponseEntity<>(responseToClient(response), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public Object handleAccessDeniedException(AccessDeniedException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", "Access Denied");
        response.put("message", "You do not have the necessary permissions to access this resource.");
        response.put("details", ex.getMessage());
        response.put("timestamp", System.currentTimeMillis());
        return new ResponseEntity<>(responseToClient(response), HttpStatus.FORBIDDEN);
    }

}
