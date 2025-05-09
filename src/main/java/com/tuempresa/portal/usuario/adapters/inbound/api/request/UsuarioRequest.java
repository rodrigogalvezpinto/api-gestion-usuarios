package com.tuempresa.portal.usuario.adapters.inbound.api.request;

import java.util.List;

public class UsuarioRequest {
    private String nombre;
    private String correo;
    private String contrasena;
    private List<TelefonoRequest> telefonos;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public List<TelefonoRequest> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<TelefonoRequest> telefonos) {
        this.telefonos = telefonos;
    }
} 