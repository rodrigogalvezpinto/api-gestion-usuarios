package com.tuempresa.portal.usuario.adapters.inbound.api.exception;

import com.tuempresa.portal.usuario.adapters.inbound.api.response.ErrorResponse;
import com.tuempresa.portal.usuario.domain.exception.UsuarioInvalidoException;
import com.tuempresa.portal.usuario.domain.exception.UsuarioNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder().mensaje(e.getMessage()).build());
    }

    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUsuarioNotFoundException(UsuarioNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builder().mensaje(e.getMessage()).build());
    }

    @ExceptionHandler(UsuarioInvalidoException.class)
    public ResponseEntity<ErrorResponse> handleUsuarioInvalidoException(UsuarioInvalidoException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder().mensaje(e.getMessage()).build());
    }
} 
