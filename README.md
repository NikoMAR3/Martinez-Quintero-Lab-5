# 🔐 BluePrints API - Seguridad JWT

### Laboratorio 5 - Parte 2: REST API con OAuth 2.0

**Escuela Colombiana de Ingeniería Julio Garavito**  
*Arquitectura de Software (ARSW)*

---

## 📋 Descripción

API REST de gestión de planos (blueprints) protegida con **JWT (JSON Web Tokens)** y **OAuth 2.0**. Este proyecto implementa un **Resource Server** seguro con autenticación basada en tokens, control de acceso basado en roles y scopes, e integración con PostgreSQL (realizada en laboratorios pasados).

---

## 🛠️ Requisitos previos

| Herramienta | Versión mínima |
|-------------|----------------|
| JDK         | 21             |
| Maven       | 3.9+           |
| PostgreSQL  | 12+            |
| Git         | 2.0+           |

---

## 🚀 Inicio rápido

### 1️⃣ Clonar el repositorio

```bash
git clone <repository-url>
cd Martinez-Quintero-Lab-5
```

### 2️⃣ Configurar base de datos

Asegúrate de tener PostgreSQL ejecutándose y crear la base de datos:

```sql
CREATE DATABASE blueprintsdb;
CREATE USER blueprints WITH PASSWORD 'blueprints123';
GRANT ALL PRIVILEGES ON DATABASE blueprintsdb TO blueprints;
```

### 3️⃣ Ejecutar con Maven

```bash
mvn clean install
mvn -q -DskipTests spring-boot:run
```

### 4️⃣ Verificar que la aplicación esté corriendo

Accede a: **http://localhost:8080**

---

## 📡 Endpoints principales

### 🔑 Autenticación

#### Obtener token JWT

```http
POST http://localhost:8080/auth/login
Content-Type: application/json

{
  "username": "student",
  "password": "student123"
}
```

**Respuesta exitosa:**

```json
{
  "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9...",
  "token_type": "Bearer",
  "expires_in": 3600
}
```

---

### 📘 Blueprints

#### Listar todos los blueprints `🔓 blueprints.read`

```http
GET http://localhost:8080/api/blueprints
Authorization: Bearer <ACCESS_TOKEN>
```

#### Obtener blueprint por autor `🔓 blueprints.read`

```http
GET http://localhost:8080/api/blueprints/{author}
Authorization: Bearer <ACCESS_TOKEN>
```

#### Crear un nuevo blueprint `🔒 blueprints.write`

```http
POST http://localhost:8080/api/blueprints
Authorization: Bearer <ACCESS_TOKEN>
Content-Type: application/json

{
  "author": "john_doe",
  "name": "Mi Plano",
  "points": [
    {"x": 10, "y": 20},
    {"x": 30, "y": 40}
  ]
}
```

#### Actualizar blueprint `🔒 blueprints.write`

```http
PUT http://localhost:8080/api/blueprints/{author}/{name}
Authorization: Bearer <ACCESS_TOKEN>
Content-Type: application/json
```

---

## 📖 Documentación interactiva

### Swagger UI

Accede a la documentación interactiva en:  
**🌐 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)**

#### 🔐 Autenticación en Swagger

1. Haz clic en el botón **"Authorize"** 🔓
2. Ingresa el token en el formato: `Bearer eyJhbGciOi...`
3. Haz clic en **"Authorize"** y luego **"Close"**
4. Probar los endpoints protegidos

---

## 📁 Estructura del proyecto

```
src/main/java/co/edu/eci/blueprints/
│
├── 📱 api/
│   └── BlueprintController.java          # Endpoints REST protegidos
│
├── 🔐 auth/
│   └── AuthController.java               # Login y emisión de tokens
│
├── ⚙️ config/
│   └── OpenApiConfig.java                # Configuración Swagger + JWT
│
├── 🎛️ controllers/
│   ├── ApiResponseS.java                 # Respuestas estandarizadas
│   └── BlueprintsAPIController.java      # Controlador principal
│
├── 🔍 filters/
│   ├── BlueprintsFilter.java             # Interface de filtros
│   ├── IdentityFilter.java               # Filtro identidad
│   ├── RedundancyFilter.java             # Elimina puntos duplicados
│   └── UndersamplingFilter.java          # Reduce densidad de puntos
│
├── 📦 model/
│   ├── Blueprint.java                    # Entidad Blueprint
│   └── Point.java                        # Entidad Point
│
├── 💾 persistence/
│   ├── BlueprintNotFoundException.java
│   ├── BlueprintPersistence.java         # Interface de persistencia
│   ├── BlueprintPersistenceException.java
│   ├── BlueprintRepository.java          # Repository JPA
│   ├── InMemoryBlueprintPersistence.java # Implementación en memoria
│   └── PostgresBlueprintPersistence.java # Implementación PostgreSQL
│
├── 🛡️ security/
│   ├── InMemoryUserService.java          # Servicio de usuarios
│   ├── JwtKeyProvider.java               # Proveedor de claves RSA
│   ├── MethodSecurityConfig.java         # Seguridad a nivel de método
│   ├── RsaKeyProperties.java             # Propiedades de claves
│   └── SecurityConfig.java               # Configuración principal
│
├── 🔧 services/
│   └── BlueprintsServices.java           # Lógica de negocio
│
└── BlueprintsApiApplication.java         # Clase principal
```

---

## � Informe del laboratorio

El documento del laboratorio en formato PDF se encuentra en la carpeta **`docs/`**.

---

## 🐳 Docker

### Ejecutar con Docker Compose

```bash
docker-compose up -d
```

Esto levantará:
- 🐘 PostgreSQL en el puerto `5432`
- ☕ API de Blueprints en el puerto `8080`

---

## 🧪 Testing

### Probar con curl

```bash
# Login
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"student","password":"student123"}'

# Listar blueprints (reemplaza TOKEN)
curl -X GET http://localhost:8080/api/blueprints \
  -H "Authorization: Bearer TOKEN"
```

---

## �👥 Autores

| Nombre | GitHub |
|--------|--------|
| **María Belén Quintero** | [@mariaquintero](https://github.com/mariaquintero) |
| **Nikolas Martínez Rivera** | [@nikolasmartinez](https://github.com/nikolasmartinez) |

---

## 📄 Licencia

Este es un proyecto educativo con fines académicos.  
**Escuela Colombiana de Ingeniería Julio Garavito** - 2026

