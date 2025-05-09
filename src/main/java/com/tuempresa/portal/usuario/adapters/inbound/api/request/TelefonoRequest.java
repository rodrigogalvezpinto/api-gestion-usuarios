package com.tuempresa.portal.usuario.adapters.inbound.api.request;

import lombok.Data;

@Data
public class TelefonoRequest {
    private String numero;
    private String codigoCiudad;
    private String codigoPais;

} 