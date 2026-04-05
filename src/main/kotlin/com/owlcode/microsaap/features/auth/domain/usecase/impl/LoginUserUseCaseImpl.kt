package com.owlcode.microsaap.features.auth.domain.usecase.impl

import com.owlcode.microsaap.features.auth.application.dto.LoginCommand
import com.owlcode.microsaap.features.auth.application.dto.AuthResult
import com.owlcode.microsaap.features.auth.application.dto.UserDto
import com.owlcode.microsaap.features.auth.domain.usecase.LoginUserUseCase
import com.owlcode.microsaap.features.auth.domain.exception.InvalidPasswordException
import com.owlcode.microsaap.features.auth.domain.exception.UserInactiveException
import com.owlcode.microsaap.features.auth.domain.exception.UserNotFoundException
import com.owlcode.microsaap.features.auth.domain.repository.UserRepository
import com.owlcode.microsaap.features.auth.domain.service.PasswordHasher
import com.owlcode.microsaap.features.auth.domain.service.TokenProvider

/**
 * Implementación del caso de uso de login de usuario
 * SIN dependencias de frameworks - POJO puro
 */
class LoginUserUseCaseImpl(
    private val userRepository: UserRepository,
    private val passwordHasher: PasswordHasher,
    private val tokenProvider: TokenProvider
) : LoginUserUseCase {

    override fun execute(command: LoginCommand): AuthResult {
        // Buscar usuario por email
        val user = userRepository.findByEmail(command.email.lowercase())
            ?: throw UserNotFoundException(command.email)

        // Verificar si el usuario está activo
        if (!user.isEnabled()) {
            throw UserInactiveException(user.email)
        }

        // Verificar contraseña
        if (!passwordHasher.verify(command.password, user.passwordHash)) {
            throw InvalidPasswordException()
        }

        // Generar token
        val token = tokenProvider.generateToken(
            userId = user.id!!,
            email = user.email
        )

        // Mapear a DTO y retornar
        return AuthResult(
            token = token,
            user = UserDto(
                id = user.id,
                email = user.email,
                firstName = user.firstName,
                lastName = user.lastName,
                fullName = user.getFullName(),
                isActive = user.isActive
            )
        )
    }
}
