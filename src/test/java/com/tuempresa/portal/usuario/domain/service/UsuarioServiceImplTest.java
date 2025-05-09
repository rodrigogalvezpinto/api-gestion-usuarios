package com.tuempresa.portal.usuario.domain.service;

import com.tuempresa.portal.usuario.adapters.inbound.api.request.UsuarioRequest;
import com.tuempresa.portal.usuario.domain.exception.UsuarioInvalidoException;
import com.tuempresa.portal.usuario.domain.exception.UsuarioNotFoundException;
import com.tuempresa.portal.usuario.domain.model.Usuario;
import com.tuempresa.portal.usuario.domain.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;
    private UsuarioServiceImpl usuarioService;

    @BeforeEach
    void setUp() {
        usuarioService = new UsuarioServiceImpl(usuarioRepository, "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    }

    @Test
    void crearUsuario_CuandoCorreoYaExiste_DebeLanzarExcepcion() {
        // Given
        UsuarioRequest request = new UsuarioRequest();
        request.setCorreo("juan@rodriguez.org");
        request.setNombre("Juan Rodriguez");
        request.setContrasena("Hunter2024@");
        request.setTelefonos(java.util.Collections.emptyList());

        Usuario usuarioExistente = new Usuario();
        usuarioExistente.setId(UUID.randomUUID());
        usuarioExistente.setNombre("Juan Rodriguez");
        usuarioExistente.setCorreo("juan@rodriguez.org");
        usuarioExistente.setContrasena("Hunter2024@");
        usuarioExistente.setCreado(LocalDateTime.now());
        usuarioExistente.setModificado(LocalDateTime.now());
        usuarioExistente.setUltimoLogin(LocalDateTime.now());
        usuarioExistente.setToken("token");
        usuarioExistente.setActivo(true);
        usuarioExistente.setTelefonos(java.util.Collections.emptyList());

        given(usuarioRepository.findByCorreo("juan@rodriguez.org")).willReturn(Optional.of(usuarioExistente));

        // When & Then
        assertThatThrownBy(() -> usuarioService.crearUsuario(request))
                .isInstanceOf(UsuarioInvalidoException.class)
                .hasMessageContaining("El correo ya está registrado");
    }

    @Test
    void obtenerUsuario_CuandoNoExiste_DebeLanzarExcepcion() {
        // Given
        UUID id = UUID.randomUUID();
        given(usuarioRepository.findById(id)).willReturn(Optional.empty());
        // When & Then
        assertThatThrownBy(() -> usuarioService.obtenerUsuario(id))
                .isInstanceOf(UsuarioNotFoundException.class)
                .hasMessageContaining("Usuario no encontrado");
    }
} 