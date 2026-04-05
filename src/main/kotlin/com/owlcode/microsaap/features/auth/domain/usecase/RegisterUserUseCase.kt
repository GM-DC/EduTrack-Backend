package com.owlcode.microsaap.features.auth.domain.usecase

import com.owlcode.microsaap.features.auth.application.dto.RegisterCommand
import com.owlcode.microsaap.features.auth.application.dto.AuthResult

/**
 * Caso de uso para registro de usuario
 * CORREGIDO: Ahora ubicado en domain donde corresponde
 */
interface RegisterUserUseCase {
    /**
     * Ejecuta el caso de uso de registro
     */
    fun execute(command: RegisterCommand): AuthResult
}
