package com.tuempresa.portal.usuario.adapters.inbound.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TelefonoResponse {
    private String numero;
    private String codigoCiudad;
    private String codigoPais;

} 