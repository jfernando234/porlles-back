-- Script para eliminar la columna nombre_completo de la tabla Usuarios
-- Ejecutar este script en MySQL para asegurar que la columna sea eliminada

USE ImportPorllesDB;

-- Verificar si la columna existe antes de eliminarla
SET @column_exists = (
    SELECT COUNT(*)
    FROM INFORMATION_SCHEMA.COLUMNS 
    WHERE TABLE_SCHEMA = 'ImportPorllesDB' 
    AND TABLE_NAME = 'Usuarios' 
    AND COLUMN_NAME = 'nombre_completo'
);

-- Eliminar la columna si existe
SET @sql = IF(@column_exists > 0, 
    'ALTER TABLE Usuarios DROP COLUMN nombre_completo;', 
    'SELECT "La columna nombre_completo no existe en la tabla Usuarios" as mensaje;'
);

PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

-- Verificar la estructura actual de la tabla
DESCRIBE Usuarios;
