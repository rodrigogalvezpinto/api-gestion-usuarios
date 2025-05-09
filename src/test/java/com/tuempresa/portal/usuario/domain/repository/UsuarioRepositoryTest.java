package com.tuempresa.portal.usuario.domain.repository;

import com.tuempresa.portal.usuario.domain.model.Usuario;
import com.tuempresa.portal.usuario.mocks.UsuarioMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void findByCorreo_CuandoExiste_DebeRetornarUsuario() {
        // Given
        Usuario usuario = UsuarioMock.mock().correo("test@correo.com").build();
        usuarioRepository.save(usuario);
        // When
        Optional<Usuario> result = usuarioRepository.findByCorreo("test@correo.com");
        // Then
        then(result).isPresent();
        then(result.get().getCorreo()).isEqualTo("test@correo.com");
    }

    @Test
    void findById_CuandoNoExiste_DebeRetornarVacio() {
        // Given
        UUID id = UUID.randomUUID();
        // When
        Optional<Usuario> result = usuarioRepository.findById(id);
        // Then
        then(result).isEmpty();
    }
} 