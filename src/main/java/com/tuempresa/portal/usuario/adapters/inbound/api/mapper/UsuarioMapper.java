package com.tuempresa.portal.usuario.adapters.inbound.api.mapper;

import com.tuempresa.portal.usuario.adapters.inbound.api.request.UsuarioRequest;
import com.tuempresa.portal.usuario.adapters.inbound.api.response.TelefonoResponse;
import com.tuempresa.portal.usuario.adapters.inbound.api.response.UsuarioResponse;
import com.tuempresa.portal.usuario.domain.model.Telefono;
import com.tuempresa.portal.usuario.domain.model.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {
    public static Usuario toEntity(UsuarioRequest request) {
        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setCorreo(request.getCorreo());
        usuario.setContrasena(request.getContrasena());
        if (request.getTelefonos() != null) {
            List<Telefono> telefonos = request.getTelefonos().stream().map(t -> {
                Telefono tel = new Telefono();
                tel.setNumero(t.getNumero());
                tel.setCodigoCiudad(t.getCodigoCiudad());
                tel.setCodigoPais(t.getCodigoPais());
                tel.setUsuario(usuario);
                return tel;
            }).collect(Collectors.toList());
            usuario.setTelefonos(telefonos);
        }
        return usuario;
    }

    public static UsuarioResponse toResponse(Usuario usuario) {
        UsuarioResponse response = new UsuarioResponse();
        response.setId(usuario.getId());
        response.setNombre(usuario.getNombre());
        response.setCorreo(usuario.getCorreo());
        response.setCreado(usuario.getCreado());
        response.setModificado(usuario.getModificado());
        response.setUltimoLogin(usuario.getUltimoLogin());
        response.setToken(usuario.getToken());
        response.setActivo(usuario.isActivo());
        if (usuario.getTelefonos() != null) {
            List<TelefonoResponse> telefonos = usuario.getTelefonos().stream().map(t -> {
                TelefonoResponse tel = new TelefonoResponse();
                tel.setNumero(t.getNumero());
                tel.setCodigoCiudad(t.getCodigoCiudad());
                tel.setCodigoPais(t.getCodigoPais());
                return tel;
            }).collect(Collectors.toList());
            response.setTelefonos(telefonos);
        }
        return response;
    }
} 