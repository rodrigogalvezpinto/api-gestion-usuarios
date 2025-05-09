package com.tuempresa.portal.usuario.mocks;

import com.tuempresa.portal.usuario.domain.model.Telefono;

public class TelefonoMock {
    public static Telefono.TelefonoBuilder mock() {
        return Telefono.builder()
                .numero("1234567")
                .codigoCiudad("1")
                .codigoPais("57");
    }
} 