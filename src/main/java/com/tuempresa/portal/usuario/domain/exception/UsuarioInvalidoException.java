package com.tuempresa.portal.usuario.domain.exception;

public class UsuarioInvalidoException extends RuntimeException {
    public UsuarioInvalidoException(String mensaje) {
        super(mensaje);
    }
} 