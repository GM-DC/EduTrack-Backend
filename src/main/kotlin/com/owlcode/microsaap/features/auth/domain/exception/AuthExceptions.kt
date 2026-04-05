package com.owlcode.microsaap.features.auth.domain.exception

import com.owlcode.microsaap.common.exception.DomainException

/**
 * Excepción cuando un usuario ya existe con el mismo email
 */
class UserAlreadyExistsException(
    email: String
) : DomainException("Ya existe un usuario con el email '$email'")

/**
 * Excepción cuando no se encuentra un usuario
 */
class UserNotFoundException(
    identifier: Any
) : DomainException("Usuario con identificador '$identifier' no fue encontrado")

/**
 * Excepción para contraseña inválida durante login
 */
class InvalidPasswordException : DomainException("Contraseña incorrecta")

/**
 * Excepción para usuario inactivo
 */
class UserInactiveException(
    email: String
) : DomainException("El usuario '$email' está inactivo")

/**
 * Excepción para validación de contraseña débil
 */
class WeakPasswordException(
    message: String
) : DomainException("Contraseña débil: $message")
