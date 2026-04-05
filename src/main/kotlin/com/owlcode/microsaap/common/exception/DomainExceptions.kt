package com.owlcode.microsaap.common.exception

/**
 * Clase base para todas las excepciones del dominio
 */
abstract class DomainException(
    message: String,
    cause: Throwable? = null
) : RuntimeException(message, cause)

/**
 * Excepción cuando una entidad no se encuentra
 */
class EntityNotFoundException(
    entityName: String,
    identifier: Any
) : DomainException("$entityName con identificador '$identifier' no fue encontrado")

/**
 * Excepción cuando hay un conflicto con datos existentes
 */
class EntityConflictException(
    message: String
) : DomainException(message)

/**
 * Excepción para validaciones de negocio
 */
class BusinessValidationException(
    message: String
) : DomainException(message)

/**
 * Excepción para autenticación fallida
 */
class AuthenticationException(
    message: String = "Credenciales inválidas"
) : DomainException(message)

/**
 * Excepción para autorización fallida
 */
class AuthorizationException(
    message: String = "Acceso denegado"
) : DomainException(message)
