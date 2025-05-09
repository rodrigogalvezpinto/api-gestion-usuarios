package com.tuempresa.portal.usuario.domain.service;

import com.tuempresa.portal.usuario.adapters.inbound.api.mapper.UsuarioMapper;
import com.tuempresa.portal.usuario.adapters.inbound.api.request.UsuarioRequest;
import com.tuempresa.portal.usuario.adapters.inbound.api.response.UsuarioResponse;
import com.tuempresa.portal.usuario.adapters.inbound.api.util.JwtUtil;
import com.tuempresa.portal.usuario.domain.exception.UsuarioInvalidoException;
import com.tuempresa.portal.usuario.domain.exception.UsuarioNotFoundException;
import com.tuempresa.portal.usuario.domain.model.Usuario;
import com.tuempresa.portal.usuario.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final Pattern correoPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
    private final Pattern passwordPattern;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, @Value("${usuario.password-regex}") String passwordRegex) {
        this.usuarioRepository = usuarioRepository;
        this.passwordPattern = Pattern.compile(passwordRegex);
    }

    @Override
    @Transactional
    public UsuarioResponse crearUsuario(UsuarioRequest request) {
        if (!correoPattern.matcher(request.getCorreo()).matches()) {
            throw new UsuarioInvalidoException("El formato del correo es inválido");
        }
        if (!passwordPattern.matcher(request.getContrasena()).matches()) {
            throw new UsuarioInvalidoException("El formato de la contraseña es inválido");
        }
        if (usuarioRepository.findByCorreo(request.getCorreo()).isPresent()) {
            throw new UsuarioInvalidoException("El correo ya está registrado");
        }
        Usuario usuario = UsuarioMapper.toEntity(request);
        usuario.setContrasena(passwordEncoder.encode(request.getContrasena()));
        usuario.setCreado(LocalDateTime.now());
        usuario.setModificado(LocalDateTime.now());
        usuario.setUltimoLogin(LocalDateTime.now());
        usuario.setActivo(true);
        String jwt = JwtUtil.generateToken(usuario.getId() != null ? usuario.getId() : UUID.randomUUID(), usuario.getCorreo());
        usuario.setToken(jwt);
        usuarioRepository.save(usuario);
        return UsuarioMapper.toResponse(usuario);
    }

    @Override
    public UsuarioResponse obtenerUsuario(UUID id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado"));
        return UsuarioMapper.toResponse(usuario);
    }

    @Override
    public List<UsuarioResponse> listarUsuarios() {
        return usuarioRepository.findAll().stream().map(UsuarioMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UsuarioResponse actualizarUsuario(UUID id, UsuarioRequest request) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado"));
        usuario.setNombre(request.getNombre());
        usuario.setCorreo(request.getCorreo());
        usuario.setContrasena(passwordEncoder.encode(request.getContrasena()));
        usuario.setModificado(LocalDateTime.now());
        usuarioRepository.save(usuario);
        return UsuarioMapper.toResponse(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponse actualizarParcialUsuario(UUID id, UsuarioRequest request) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado"));
        if (request.getNombre() != null) usuario.setNombre(request.getNombre());
        if (request.getCorreo() != null) usuario.setCorreo(request.getCorreo());
        if (request.getContrasena() != null) usuario.setContrasena(passwordEncoder.encode(request.getContrasena()));
        usuario.setModificado(LocalDateTime.now());
        usuarioRepository.save(usuario);
        return UsuarioMapper.toResponse(usuario);
    }

    @Override
    @Transactional
    public void eliminarUsuario(UUID id) {
        if (!usuarioRepository.existsById(id)) {
            throw new UsuarioNotFoundException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
    }
} 