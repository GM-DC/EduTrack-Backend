package com.owlcode.microsaap.features.auth.application.usecase.impl

import com.owlcode.microsaap.features.auth.application.dto.RegisterCommand
import com.owlcode.microsaap.features.auth.application.dto.AuthResult
import com.owlcode.microsaap.features.auth.application.dto.UserDto
import com.owlcode.microsaap.features.auth.application.usecase.RegisterUserUseCase
import com.owlcode.microsaap.features.auth.domain.exception.UserAlreadyExistsException
import com.owlcode.microsaap.features.auth.domain.model.User
import com.owlcode.microsaap.features.auth.domain.repository.UserRepository
import com.owlcode.microsaap.features.auth.domain.service.PasswordHasher
import com.owlcode.microsaap.features.auth.domain.service.TokenProvider
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Implementación del caso de uso de registro de usuario
 */
@Service
@Transactional
class RegisterUserUseCaseImpl(
    private val userRepository: UserRepository,
    private val passwordHasher: PasswordHasher,
    private val tokenProvider: TokenProvider
) : RegisterUserUseCase {

    override fun execute(command: RegisterCommand): AuthResult {
        // Verificar si el usuario ya existe
        if (userRepository.existsByEmail(command.email.lowercase())) {
            throw UserAlreadyExistsException(command.email)
        }

        // Hash de la contraseña
        val passwordHash = passwordHasher.hash(command.password)

        // Crear el usuario del dominio
        val userToSave = User.create(
            email = command.email,
            passwordHash = passwordHash,
            firstName = command.firstName,
            lastName = command.lastName
        )

        // Guardar en el repositorio
        val savedUser = userRepository.save(userToSave)

        // Generar token
        val token = tokenProvider.generateToken(
            userId = savedUser.id!!,
            email = savedUser.email
        )

        // Mapear a DTO y retornar
        return AuthResult(
            token = token,
            user = UserDto(
                id = savedUser.id,
                email = savedUser.email,
                firstName = savedUser.firstName,
                lastName = savedUser.lastName,
                fullName = savedUser.getFullName(),
                isActive = savedUser.isActive
            )
        )
    }
}
