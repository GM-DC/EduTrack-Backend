package com.owlcode.microsaap.features.auth.application.service

import com.owlcode.microsaap.features.auth.application.dto.LoginCommand
import com.owlcode.microsaap.features.auth.application.dto.RegisterCommand
import com.owlcode.microsaap.features.auth.application.dto.AuthResult
import com.owlcode.microsaap.features.auth.domain.usecase.LoginUserUseCase
import com.owlcode.microsaap.features.auth.domain.usecase.RegisterUserUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Servicio de aplicación para orquestación de casos de uso de autenticación
 * Aquí SÍ van las anotaciones de Spring para manejo de transacciones
 */
@Service
class AuthApplicationService(
    private val loginUserUseCase: LoginUserUseCase,
    private val registerUserUseCase: RegisterUserUseCase
) {

    /**
     * Orquesta el caso de uso de login con transacciones
     */
    @Transactional(readOnly = true)
    fun login(command: LoginCommand): AuthResult {
        return loginUserUseCase.execute(command)
    }

    /**
     * Orquesta el caso de uso de registro con transacciones
     */
    @Transactional
    fun register(command: RegisterCommand): AuthResult {
        return registerUserUseCase.execute(command)
    }
}
