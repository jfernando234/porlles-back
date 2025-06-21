-- Script para eliminar la columna nombre_completo de la tabla Usuarios
-- Ejecutar este script manualmente en la base de datos

ALTER TABLE Usuarios DROP COLUMN IF EXISTS nombre_completo;

-- Verificar que las nuevas columnas existan
ALTER TABLE Usuarios ADD COLUMN IF NOT EXISTS nombre VARCHAR(100);
ALTER TABLE Usuarios ADD COLUMN IF NOT EXISTS apellidos VARCHAR(100);
ALTER TABLE Usuarios ADD COLUMN IF NOT EXISTS tipo_documento VARCHAR(20);
ALTER TABLE Usuarios ADD COLUMN IF NOT EXISTS numero_documento VARCHAR(50);
ALTER TABLE Usuarios ADD COLUMN IF NOT EXISTS celular VARCHAR(20);
