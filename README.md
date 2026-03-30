# OneMillionCopyLEADS API

## 📌 Descripción
API REST para gestión de leads (ciclo CRUD mínimo) con arquitectura hexagonal (clean architecture) usando Spring Boot.

## 🛠 Tecnologías usadas y por qué
- Java 17: estabilidad y compatibilidad con Spring Boot 3.
- Spring Boot 3: arranque rápido, inyección de dependencias y servidor embebido.
- Spring Data JPA + Hibernate: acceso de datos y mapeo ORM a base MySQL.
- MySQL: base de datos relacional común en producción.
- Maven: build, gestión de dependencias y pruebas.
- Jakarta Validation: validación de entradas (e.g. @Valid).
- Estrategia hexagonal: separa dominio, puerto/adapter e infraestructura para testabilidad y mantenimiento.

## 🚀 Requisitos previos
- Java 17 instalado (`java -version`).
- Maven instalado (`mvn -v`).
- MySQL accesible en `localhost:3306` o configuración personalizada.

## 🧩 Configuración de la DB
Archivo: `src/main/resources/application.properties`
- `spring.datasource.url=jdbc:mysql://localhost:3306/OneMillionCopyPruebaDB?useSSL=false&serverTimezone=UTC`
- `spring.datasource.username=root`
- `spring.datasource.password=OneMillion`

> Ajusta estos valores con tus credenciales antes de ejecutar.

## 📦 Instalación y ejecución
1. Clona el repositorio:
   ```bash
   git clone <repo-url>
   cd OneMillionCopyLEADS
   ```
2. Crea la base de datos MySQL (según `QuerysDB/2CrearTablaLeads.sql`):
   ```sql
   CREATE DATABASE IF NOT EXISTS OneMillionCopyPruebaDB;
   USE OneMillionCopyPruebaDB;
   -- Copia el contenido de QuerysDB/2CrearTablaLeads.sql
   ```
3. Ejecuta el proyecto:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
4. Verifica que levanta en `http://localhost:8080`.

## 🌱 Seed inicial
No existe seed automático en código, pero puedes sembrar manualmente en MySQL:
- `QuerysDB/1CrearBaseDatos.sql` y `QuerysDB/2CrearTablaLeads.sql` contienen scripts.
- Inserta datos de ejemplo:
   ```sql
   INSERT INTO leads (nombre, email, telefono, fuente, fecha_creacion) VALUES
   ('Juan Pérez','juan@ejemplo.com','555-1234','INSTAGRAM', NOW()),
   ('Ana Ruiz','ana@ejemplo.com','555-5678','FACEBOOK', NOW());
   ```

## 🔍 Endpoints
Base: `http://localhost:8080/leads`

1. Crear lead
   - POST `/leads`
   - Body JSON:
     {
       "nombre": "Sofía",
       "email": "sofia@domain.com",
       "telefono": "555000111",
       "fuente": "LANDING_PAGE"
     }
   - Respuesta: `201 CREATED`, objeto Lead.

2. Listar leads paginados + filtros
   - GET `/leads?page=0&limit=10`
   - Filtros opcionales:
     - `fuente=INSTAGRAM|FACEBOOK|LANDING_PAGE|REFERIDO|OTRO`
     - `fecha_creacion_inicio=2025-03-01T00:00:00`
     - `fecha_creacion_fin=2025-03-30T23:59:59`

3. Obtener lead por ID
   - GET `/leads/{id}`

4. Eliminar lead
   - DELETE `/leads/{id}`
   - Respuesta: `204 NO_CONTENT`

## 🧪 Ejemplos con curl
Crear:
```bash
curl -X POST http://localhost:8080/leads \
  -H 'Content-Type: application/json' \
  -d '{"nombre":"Sergio","email":"sergio@x.com","telefono":"555123456","fuente":"INSTAGRAM"}'
```
Listar:
```bash
curl 'http://localhost:8080/leads?page=0&limit=5'
```
Obtener uno:
```bash
curl http://localhost:8080/leads/1
```
Borrar:
```bash
curl -X DELETE http://localhost:8080/leads/1
```

## ⚠️ Manejo de errores consistentes
GlobalExceptionHandler ofrece:
- `409 CONFLICT` para `DuplicateEmailException`.
- `400 BAD_REQUEST` para JSON inválido / valores fuera de enums (`FUENTE_INVALIDA`, `JSON_INVALIDO`).
- `400 BAD_REQUEST` para `IllegalArgumentException` (`VALIDACION_NEGOCIO`).
- `400 BAD_REQUEST` para `MethodArgumentNotValidException` (`VALIDATION_ERROR` con lista de campos).
- `404 NOT_FOUND` para `LeadNotFoundException`.

Ejemplo:
```json
{
  "error":"NOT_FOUND",
  "message":"Lead con id 99 no encontrado"
}
```

## 🧰 Pruebas
Ejecuta:
```bash
mvn test
```

## 📌 Buenas prácticas
- Usa DTOs de request/response y mapeo con ensambladores si el schema crece.
- Externaliza credenciales y perfiles de Spring Boot para entornos.
- Maneja excepciones de forma consistente y devuelve JSON estructurado.
