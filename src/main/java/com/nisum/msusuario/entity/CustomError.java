package com.nisum.msusuario.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Builder
@Data
public class CustomError {

    private String message;

    private HttpStatus status;
}
