package com.tuempresa.portal.usuario.domain.service;

import com.tuempresa.portal.usuario.adapters.inbound.api.request.UsuarioRequest;
import com.tuempresa.portal.usuario.adapters.inbound.api.response.UsuarioResponse;

import java.util.List;
import java.util.UUID;

public interface UsuarioService {
    UsuarioResponse crearUsuario(UsuarioRequest request);
    UsuarioResponse obtenerUsuario(UUID id);
    List<UsuarioResponse> listarUsuarios();
    UsuarioResponse actualizarUsuario(UUID id, UsuarioRequest request);
    UsuarioResponse actualizarParcialUsuario(UUID id, UsuarioRequest request);
    void eliminarUsuario(UUID id);
} 