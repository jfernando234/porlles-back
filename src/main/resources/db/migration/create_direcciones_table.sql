-- Crear tabla de direcciones
CREATE TABLE IF NOT EXISTS direcciones (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    direccion VARCHAR(500) NOT NULL,
    departamento VARCHAR(100),
    provincia VARCHAR(100),
    distrito VARCHAR(100),
    avenida_calle_jiron VARCHAR(200),
    numero VARCHAR(20),
    codigo_postal VARCHAR(20),
    referencia VARCHAR(300),
    es_principal BOOLEAN DEFAULT FALSE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    CONSTRAINT fk_direcciones_usuario 
        FOREIGN KEY (usuario_id) 
        REFERENCES usuarios(id) 
        ON DELETE CASCADE,
        
    -- Asegurar que solo haya una dirección principal por usuario
    CONSTRAINT unique_principal_por_usuario 
        UNIQUE KEY idx_usuario_principal (usuario_id, es_principal)
);

-- Índices para mejorar el rendimiento
CREATE INDEX idx_direcciones_usuario_id ON direcciones(usuario_id);
CREATE INDEX idx_direcciones_principal ON direcciones(es_principal);
CREATE INDEX idx_direcciones_fecha_creacion ON direcciones(fecha_creacion);
