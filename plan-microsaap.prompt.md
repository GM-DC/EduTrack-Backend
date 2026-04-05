# Plan: Arquitectura Limpia con MySQL y Validaciones Personalizadas

Implementación completa de Clean Architecture para el proyecto Microsaap usando MySQL como base de datos y validaciones personalizadas del dominio, siguiendo principios SOLID y separación clara de responsabilidades por capas.

## Pasos

### 1. Configurar dependencias y base de datos
Agregar Spring Security, JWT, BCrypt al [build.gradle.kts](C:\Users\gmflo\Documents\ProyectoAndroid\microsaap\build.gradle.kts) y configurar MySQL en [application.yaml](C:\Users\gmflo\Documents\ProyectoAndroid\microsaap\src\main\resources\application.yaml)

### 2. Crear infraestructura común
Implementar `common/config`, `common/exception`, `common/response` y `common/util` con manejo global de errores y configuraciones

### 3. Desarrollar módulo de dominio
Crear entidad `User`, repository interface `UserRepository`, validaciones personalizadas y servicios de dominio `PasswordHasher` y `TokenProvider`

### 4. Implementar capa de aplicación
Definir commands (`RegisterCommand`, `LoginCommand`), result (`AuthResult`) y casos de uso con validaciones de negocio

### 5. Construir infraestructura de persistencia
Crear `UserEntity` JPA, `SpringDataUserRepository`, `UserMapper` y `MysqlUserRepository` con implementaciones de seguridad

### 6. Desarrollar API REST
Implementar `AuthController` con requests, responses y manejo de endpoints siguiendo principios REST

## Consideraciones Adicionales

### Configuración de base de datos
MySQL como base principal con configuración de conexión, pool y migraciones usando Flyway

### Validaciones personalizadas
Validadores específicos del dominio para email único, contraseña segura y reglas de negocio

### Manejo de errores
Sistema centralizado de excepciones con códigos de error específicos y respuestas consistentes

## Estructura de directorios objetivo

```
src/
└── main/
    ├── kotlin/
    │   └── com/owlcode/microsaap/
    │       ├── MicrosaapApplication.kt
    │       │
    │       ├── common/
    │       │   ├── config/
    │       │   ├── exception/
    │       │   ├── response/
    │       │   ├── security/
    │       │   └── util/
    │       │
    │       └── features/
    │           └── auth/
    │               ├── domain/
    │               │   ├── model/
    │               │   │   └── User.kt
    │               │   ├── repository/
    │               │   │   └── UserRepository.kt
    │               │   ├── service/
    │               │   │   ├── PasswordHasher.kt
    │               │   │   └── TokenProvider.kt
    │               │   └── exception/
    │               │
    │               ├── application/
    │               │   ├── dto/
    │               │   │   ├── RegisterCommand.kt
    │               │   │   ├── LoginCommand.kt
    │               │   │   └── AuthResult.kt
    │               │   └── usecase/
    │               │       ├── RegisterUserUseCase.kt
    │               │       ├── LoginUserUseCase.kt
    │               │       └── impl/
    │               │           ├── RegisterUserUseCaseImpl.kt
    │               │           └── LoginUserUseCaseImpl.kt
    │               │
    │               ├── infrastructure/
    │               │   ├── persistence/
    │               │   │   ├── entity/
    │               │   │   │   └── UserEntity.kt
    │               │   │   ├── jpa/
    │               │   │   │   └── SpringDataUserRepository.kt
    │               │   │   ├── mapper/
    │               │   │   │   └── UserMapper.kt
    │               │   │   └── MysqlUserRepository.kt
    │               │   └── security/
    │               │       ├── BCryptPasswordHasher.kt
    │               │       └── JwtTokenProvider.kt
    │               │
    │               └── presentation/
    │                   ├── request/
    │                   │   ├── LoginRequest.kt
    │                   │   └── RegisterRequest.kt
    │                   ├── response/
    │                   │   └── AuthResponse.kt
    │                   └── AuthController.kt
    │
    └── resources/
        ├── application.properties
        └── db/
            └── migration/
```
