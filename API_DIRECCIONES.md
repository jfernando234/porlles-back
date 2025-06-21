# API de Direcciones - Documentación Actualizada

## Descripción
Sistema para gestionar direcciones de usuarios en el backend. Permite que cada usuario tenga múltiples direcciones y una dirección principal. **Ahora incluye manejo específico para usuarios sin direcciones**.

## Estados de Usuario

### 🔍 **Detección de Usuarios Sin Direcciones**
El sistema ahora detecta automáticamente cuando un usuario no tiene direcciones registradas y proporciona respuestas específicas.

### 📍 **Campos Actualizados**
Compatible con el formulario del frontend que incluye:
- Departamento, Provincia, Distrito
- Avenida/Calle/Jirón y Número
- Referencia y Código Postal

## Endpoints Disponibles

### 1. Verificar si tiene direcciones
```
GET /api/direcciones/verificar
```
**Respuesta cuando NO tiene direcciones:**
```json
{
  "success": true,
  "tieneDirecciones": false,
  "cantidadDirecciones": 0,
  "message": "El usuario aún no cuenta con direcciones registradas"
}
```

### 2. Obtener dirección principal
```
GET /api/direcciones/principal
```
**Respuesta cuando NO tiene dirección:**
```json
{
  "success": true,
  "data": null,
  "message": "Aún no cuentas con una dirección principal. Agrega tu primera dirección.",
  "hasPrincipalAddress": false
}
```

### 3. Obtener todas las direcciones
```
GET /api/direcciones
```
**Respuesta cuando NO tiene direcciones:**
```json
{
  "success": true,
  "data": [],
  "message": "No tienes direcciones registradas aún",
  "hasAddresses": false,
  "totalAddresses": 0
}
```

### 4. Crear dirección (Compatible con formulario frontend)
```
POST /api/direcciones
```
**Body:**
```json
{
  "departamento": "Ancash",
  "provincia": "Huaraz", 
  "distrito": "Colcabamba",
  "avenidaCalleJiron": "123",
  "numero": "123",
  "referencia": "123",
  "esPrincipal": false
}
```

## Integración Frontend

### Servicio Angular Incluido
```typescript
import { DireccionesService } from './shared/services/direcciones.service';

// Verificar estado de direcciones
verificarDirecciones().subscribe(response => {
  if (!response.tieneDirecciones) {
    // Mostrar: "Aún no cuentas con dirección"
    this.mostrarFormularioAgregarDireccion();
  }
});
```

### Casos de Uso
1. **Usuario Sin Direcciones**: Mostrar mensaje "Aún no cuentas con dirección"
2. **Primera Dirección**: Se marca automáticamente como principal
3. **Múltiples Direcciones**: Usuario puede elegir cual es principal

## Base de Datos Actualizada
```sql
-- Tabla con campos compatibles con formulario frontend
CREATE TABLE direcciones (
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
    fecha_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

El sistema está completamente integrado y listo para manejar todos los casos: usuarios sin direcciones, con una dirección, o con múltiples direcciones.

## Descripción
Sistema para gestionar direcciones de usuarios en el backend. Permite que cada usuario tenga múltiples direcciones y una dirección principal.

## Endpoints Disponibles

### 1. Obtener todas las direcciones del usuario
```
GET /api/direcciones
```
**Respuesta:**
```json
{
  "success": true,
  "data": [
    {
      "id": 1,
      "direccion": "Av. Principal 123",
      "ciudad": "Lima",
      "departamento": "Lima",
      "codigoPostal": "15001",
      "referencia": "Cerca al parque principal",
      "esPrincipal": true
    }
  ],
  "message": "Direcciones obtenidas exitosamente"
}
```

### 2. Obtener dirección principal
```
GET /api/direcciones/principal
```
**Respuesta:**
```json
{
  "success": true,
  "data": {
    "id": 1,
    "direccion": "Av. Principal 123",
    "ciudad": "Lima",
    "departamento": "Lima",
    "codigoPostal": "15001",
    "referencia": "Cerca al parque principal",
    "esPrincipal": true
  },
  "message": "Dirección principal obtenida exitosamente"
}
```

### 3. Crear nueva dirección
```
POST /api/direcciones
```
**Body:**
```json
{
  "direccion": "Calle Secundaria 456",
  "ciudad": "Arequipa",
  "departamento": "Arequipa",
  "codigoPostal": "04001",
  "referencia": "Al lado del mercado",
  "esPrincipal": false
}
```

### 4. Actualizar dirección
```
PUT /api/direcciones/{direccionId}
```
**Body:**
```json
{
  "direccion": "Calle Actualizada 789",
  "ciudad": "Cusco",
  "departamento": "Cusco",
  "codigoPostal": "08001",
  "referencia": "Nueva referencia",
  "esPrincipal": true
}
```

### 5. Eliminar dirección
```
DELETE /api/direcciones/{direccionId}
```

### 6. Establecer dirección como principal
```
PUT /api/direcciones/{direccionId}/principal
```

## Características del Sistema

### Reglas de Negocio
1. **Dirección Principal Automática**: Si es la primera dirección del usuario, se marca automáticamente como principal.
2. **Una Sola Principal**: Solo puede haber una dirección principal por usuario.
3. **Seguridad**: Los usuarios solo pueden acceder a sus propias direcciones.
4. **Eliminación Inteligente**: Si se elimina la dirección principal, otra dirección se convierte automáticamente en principal.

### Validaciones
- El usuario debe estar autenticado
- La dirección es un campo obligatorio
- Solo el propietario puede modificar sus direcciones
- Al establecer una dirección como principal, las demás se desmarcan automáticamente

### Base de Datos
La tabla `direcciones` se crea automáticamente con la siguiente estructura:
- `id`: Identificador único
- `usuario_id`: Referencia al usuario propietario
- `direccion`: Dirección completa (obligatorio)
- `ciudad`: Ciudad
- `departamento`: Departamento/Estado
- `codigo_postal`: Código postal
- `referencia`: Punto de referencia
- `es_principal`: Si es la dirección principal del usuario
- `fecha_creacion`: Fecha de creación
- `fecha_modificacion`: Última modificación

### Autenticación
Todos los endpoints requieren autenticación. El sistema obtiene automáticamente el ID del usuario desde el token de autenticación.

## Ejemplos de Uso

### Frontend - Obtener direcciones
```javascript
// Obtener todas las direcciones del usuario autenticado
fetch('/api/direcciones', {
  method: 'GET',
  headers: {
    'Authorization': 'Bearer ' + token,
    'Content-Type': 'application/json'
  }
})
.then(response => response.json())
.then(data => {
  if (data.success) {
    console.log('Direcciones:', data.data);
  }
});
```

### Frontend - Crear dirección
```javascript
// Crear nueva dirección
const nuevaDireccion = {
  direccion: "Av. Los Libertadores 123",
  ciudad: "Lima",
  departamento: "Lima",
  codigoPostal: "15001",
  referencia: "Frente al centro comercial",
  esPrincipal: false
};

fetch('/api/direcciones', {
  method: 'POST',
  headers: {
    'Authorization': 'Bearer ' + token,
    'Content-Type': 'application/json'
  },
  body: JSON.stringify(nuevaDireccion)
})
.then(response => response.json())
.then(data => {
  if (data.success) {
    console.log('Dirección creada:', data.data);
  }
});
```
