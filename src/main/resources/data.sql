-- Script para inicializar la base de datos con datos básicos

-- Crear roles básicos
INSERT INTO roles (id, nombre) VALUES (1, 'USER') ON DUPLICATE KEY UPDATE nombre = 'USER';
INSERT INTO roles (id, nombre) VALUES (2, 'ADMIN') ON DUPLICATE KEY UPDATE nombre = 'ADMIN';

-- Verificar que las tablas se crearon correctamente
SELECT 'Tabla roles inicializada' as mensaje;
