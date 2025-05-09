INSERT INTO USUARIO (id, nombre, correo, contrasena, creado, modificado, ultimo_login, token, activo)
VALUES
  ('b1a7c2e0-1234-4c56-8a2b-123456789abc', 'Juan Rodriguez', 'juan@rodriguez.org', '$2a$10$7QJ8QwQwQwQwQwQwQwQwQeQwQwQwQwQwQwQwQwQwQwQwQwQwQw', '2024-06-07T12:00:00', '2024-06-07T12:00:00', '2024-06-07T12:00:00', 'token1', true),
  ('b1a7c2e0-1234-4c56-8a2b-123456789def', 'Maria Perez', 'maria@perez.org', '$2a$10$7QJ8QwQwQwQwQwQwQwQwQeQwQwQwQwQwQwQwQwQwQwQwQwQwQw', '2024-06-07T13:00:00', '2024-06-07T13:00:00', '2024-06-07T13:00:00', 'token2', true);

INSERT INTO TELEFONO (numero, codigo_ciudad, codigo_pais, usuario_id)
VALUES
  ('1234567', '1', '57', 'b1a7c2e0-1234-4c56-8a2b-123456789abc'),
  ('7654321', '2', '57', 'b1a7c2e0-1234-4c56-8a2b-123456789def'); 