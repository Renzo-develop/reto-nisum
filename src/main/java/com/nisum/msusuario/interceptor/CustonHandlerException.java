package com.nisum.msusuario.interceptor;

import com.nisum.msusuario.entity.CustomError;
import com.nisum.msusuario.exception.NotUniqueEmailException;
import com.nisum.msusuario.exception.NotUniqueUserSecurityException;
import com.nisum.msusuario.exception.ResourceNotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class CustonHandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleFormatError(ConstraintViolationException ex) {
        CustomError error = CustomError.builder()
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .build();
        log.info("Error message={} status={}", error.getMessage(), error.getStatus());
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler(NotUniqueEmailException.class)
    public ResponseEntity<Object> handleNotUniqueEmailException() {
        CustomError error = CustomError.builder()
                .message("El correo ya fue registrado, intentar con uno nuevo")
                .status(HttpStatus.BAD_REQUEST)
                .build();
        log.info("Error message={} status={}", error.getMessage(), error.getStatus());
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler(NotUniqueUserSecurityException.class)
    public ResponseEntity<Object> handleNotUserSecurityException() {
        CustomError error = CustomError.builder()
                .message("El nombre de usuario ya fue registrado")
                .status(HttpStatus.BAD_REQUEST)
                .build();
        log.info("Error message={} status={}", error.getMessage(), error.getStatus());
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<Object> handleExpiredJwtException(JwtException ex) {
        CustomError error = CustomError.builder()
                .message("El token expiro, intente generar uno nuevo")
                .status(HttpStatus.FORBIDDEN)
                .build();
        log.info("Error message={} status={}", error.getMessage(), error.getStatus());
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleExpiredJwtException(ResourceNotFoundException ex) {
        CustomError error = CustomError.builder()
                .message("El usuario no fue encontrado")
                .status(HttpStatus.NO_CONTENT)
                .build();
        log.info("Error message={} status={}", error.getMessage(), error.getStatus());
        return new ResponseEntity<>(error, error.getStatus());
    }



}
