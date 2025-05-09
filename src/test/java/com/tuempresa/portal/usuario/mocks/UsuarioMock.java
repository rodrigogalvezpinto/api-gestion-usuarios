package com.tuempresa.portal.usuario.mocks;

import com.tuempresa.portal.usuario.domain.model.Usuario;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

public class UsuarioMock {
    public static Usuario.UsuarioBuilder mock() {
        return Usuario.builder()
                .id(UUID.fromString("b1a7c2e0-1234-4c56-8a2b-123456789abc"))
                .nombre("Juan Rodriguez")
                .correo("juan@rodriguez.org")
                .contrasena("Hunter2024")
                .creado(LocalDateTime.now())
                .modificado(LocalDateTime.now())
                .ultimoLogin(LocalDateTime.now())
                .token("token")
                .activo(true)
                .telefonos(Collections.emptyList());
    }
} 