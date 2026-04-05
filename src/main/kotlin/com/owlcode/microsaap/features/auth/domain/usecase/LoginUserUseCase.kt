package com.owlcode.microsaap.features.auth.domain.usecase

import com.owlcode.microsaap.features.auth.application.dto.LoginCommand
import com.owlcode.microsaap.features.auth.application.dto.AuthResult

/**
 * Caso de uso para login de usuario
 * CORREGIDO: Ahora ubicado en domain donde corresponde
 */
interface LoginUserUseCase {
    /**
     * Ejecuta el caso de uso de login
     */
    fun execute(command: LoginCommand): AuthResult
}
