package com.tuempresa.portal.usuario.adapters.inbound.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuempresa.portal.usuario.adapters.inbound.api.mapper.UsuarioMapper;
import com.tuempresa.portal.usuario.adapters.inbound.api.request.UsuarioRequest;
import com.tuempresa.portal.usuario.adapters.inbound.api.response.UsuarioResponse;
import com.tuempresa.portal.usuario.domain.model.Usuario;
import com.tuempresa.portal.usuario.domain.service.UsuarioService;
import com.tuempresa.portal.usuario.mocks.UsuarioMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;


@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
@WebMvcTest(UsuarioController.class)
@ContextConfiguration(classes = {UsuarioController.class})
@AutoConfigureMockMvc(addFilters = false)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UsuarioService usuarioService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void crearUsuario_CuandoDatosValidos_DebeCrearUsuario() throws Exception {
        // Given
        UsuarioRequest request = new UsuarioRequest();
        request.setNombre("Juan Rodriguez");
        request.setCorreo("nuevo@correo.com");
        request.setContrasena("Hunter2024");
        request.setTelefonos(Collections.emptyList());
        Usuario usuario = UsuarioMock.mock().correo("nuevo@correo.com").build();
        UsuarioResponse response = UsuarioMapper.toResponse(usuario);
        given(usuarioService.crearUsuario(any(UsuarioRequest.class))).willReturn(response);
        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.correo").value("nuevo@correo.com"));
    }

    @Test
    void obtenerUsuario_CuandoExiste_DebeRetornarUsuario() throws Exception {
        // Given
        UUID id = UUID.fromString("b1a7c2e0-1234-4c56-8a2b-123456789abc");
        Usuario usuario = UsuarioMock.mock().build();
        UsuarioResponse response = UsuarioMapper.toResponse(usuario);
        given(usuarioService.obtenerUsuario(eq(id))).willReturn(response);
        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/usuarios/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id.toString()));
    }
} 