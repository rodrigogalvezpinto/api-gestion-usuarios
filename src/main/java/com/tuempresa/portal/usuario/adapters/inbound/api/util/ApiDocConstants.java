package com.tuempresa.portal.usuario.adapters.inbound.api.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ApiDocConstants {
    public static final String API_TITLE = "API de Usuarios";
    public static final String API_DESCRIPTION = "API RESTful para la gestión de usuarios";
    public static final String API_VERSION = "1.0.0";

    public static final String USUARIO_REQUEST = "{\n" +
            "  \"nombre\": \"Juan Rodriguez\",\n" +
            "  \"correo\": \"juan@rodriguez.org\",\n" +
            "  \"contrasena\": \"Hunter2024\",\n" +
            "  \"telefonos\": [\n" +
            "    {\n" +
            "      \"numero\": \"1234567\",\n" +
            "      \"codigoCiudad\": \"1\",\n" +
            "      \"codigoPais\": \"57\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    public static final String USUARIO_RESPONSE = "{\n" +
            "  \"id\": \"b1a7c2e0-1234-4c56-8a2b-123456789abc\",\n" +
            "  \"creado\": \"2024-06-07T12:00:00\",\n" +
            "  \"modificado\": \"2024-06-07T12:00:00\",\n" +
            "  \"ultimoLogin\": \"2024-06-07T12:00:00\",\n" +
            "  \"token\": \"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...\",\n" +
            "  \"activo\": true,\n" +
            "  \"nombre\": \"Juan Rodriguez\",\n" +
            "  \"correo\": \"juan@rodriguez.org\",\n" +
            "  \"telefonos\": [\n" +
            "    {\n" +
            "      \"numero\": \"1234567\",\n" +
            "      \"codigoCiudad\": \"1\",\n" +
            "      \"codigoPais\": \"57\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    public static final String USUARIOS_RESPONSE = "[\n" +
            USUARIO_RESPONSE + ",\n" +
            "{\n" +
            "  \"id\": \"b1a7c2e0-1234-4c56-8a2b-123456789def\",\n" +
            "  \"creado\": \"2024-06-07T13:00:00\",\n" +
            "  \"modificado\": \"2024-06-07T13:00:00\",\n" +
            "  \"ultimoLogin\": \"2024-06-07T13:00:00\",\n" +
            "  \"token\": \"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...\",\n" +
            "  \"activo\": true,\n" +
            "  \"nombre\": \"Maria Perez\",\n" +
            "  \"correo\": \"maria@perez.org\",\n" +
            "  \"telefonos\": [\n" +
            "    {\n" +
            "      \"numero\": \"7654321\",\n" +
            "      \"codigoCiudad\": \"2\",\n" +
            "      \"codigoPais\": \"57\"\n" +
            "    }\n" +
            "  ]\n" +
            "}\n" +
            "]";

    public static final String ERROR_RESPONSE = "{ \"mensaje\": \"El correo ya está registrado\" }";
    public static final String ERROR_NOT_FOUND = "{ \"mensaje\": \"Usuario no encontrado\" }";
} 