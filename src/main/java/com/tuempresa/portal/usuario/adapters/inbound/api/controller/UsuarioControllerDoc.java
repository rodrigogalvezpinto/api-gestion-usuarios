package com.tuempresa.portal.usuario.adapters.inbound.api.controller;

import com.tuempresa.portal.usuario.adapters.inbound.api.request.UsuarioRequest;
import com.tuempresa.portal.usuario.adapters.inbound.api.response.ErrorResponse;
import com.tuempresa.portal.usuario.adapters.inbound.api.response.UsuarioResponse;
import com.tuempresa.portal.usuario.adapters.inbound.api.util.ApiDocConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Tag(name = "Usuarios", description = "Operaciones sobre usuarios")
public interface UsuarioControllerDoc {

    @Operation(summary = "Crear un usuario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = UsuarioResponse.class),
                examples = @ExampleObject(value = ApiDocConstants.USUARIO_RESPONSE)
            )),
        @ApiResponse(responseCode = "400", description = "Error de validación",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                examples = @ExampleObject(value = ApiDocConstants.ERROR_RESPONSE)))
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Ejemplo de request para crear usuario",
        required = true,
        content = @Content(
            schema = @Schema(implementation = UsuarioRequest.class),
            examples = @ExampleObject(value = ApiDocConstants.USUARIO_REQUEST)
        )
    )
    @PostMapping
    ResponseEntity<UsuarioResponse> crearUsuario(@RequestBody UsuarioRequest request);

    @Operation(summary = "Obtener usuario por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario encontrado",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = UsuarioResponse.class),
                examples = @ExampleObject(value = ApiDocConstants.USUARIO_RESPONSE)
            )),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                examples = @ExampleObject(value = ApiDocConstants.ERROR_NOT_FOUND)))
    })
    @GetMapping("/{id}")
    ResponseEntity<UsuarioResponse> obtenerUsuario(@Parameter(description = "ID del usuario", example = "b1a7c2e0-1234-4c56-8a2b-123456789abc") @PathVariable UUID id);

    @Operation(summary = "Listar todos los usuarios")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de usuarios",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = UsuarioResponse.class),
                examples = @ExampleObject(value = ApiDocConstants.USUARIOS_RESPONSE)
            ))
    })
    @GetMapping
    ResponseEntity<List<UsuarioResponse>> listarUsuarios();

    @Operation(summary = "Actualizar usuario completamente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario actualizado",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = UsuarioResponse.class),
                examples = @ExampleObject(value = ApiDocConstants.USUARIO_RESPONSE)
            )),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                examples = @ExampleObject(value = ApiDocConstants.ERROR_NOT_FOUND)))
    })
    @PutMapping("/{id}")
    ResponseEntity<UsuarioResponse> actualizarUsuario(@PathVariable UUID id, @RequestBody UsuarioRequest request);

    @Operation(summary = "Actualizar usuario parcialmente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario actualizado parcialmente",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = UsuarioResponse.class),
                examples = @ExampleObject(value = ApiDocConstants.USUARIO_RESPONSE)
            )),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                examples = @ExampleObject(value = ApiDocConstants.ERROR_NOT_FOUND)))
    })
    @PatchMapping("/{id}")
    ResponseEntity<UsuarioResponse> actualizarParcialUsuario(@PathVariable UUID id, @RequestBody UsuarioRequest request);

    @Operation(summary = "Eliminar usuario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Usuario eliminado"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                examples = @ExampleObject(value = ApiDocConstants.ERROR_NOT_FOUND)))
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> eliminarUsuario(@PathVariable UUID id);
} 