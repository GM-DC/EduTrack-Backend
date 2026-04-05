package com.owlcode.microsaap.features.auth.application.usecase

import com.owlcode.microsaap.features.auth.application.dto.RegisterCommand
import com.owlcode.microsaap.features.auth.application.dto.AuthResult

/**
 * Caso de uso para registro de usuario
 */
interface RegisterUserUseCase {

    /**
     * Registra un nuevo usuario en el sistema
     * @param command Datos del usuario a registrar
     * @return Resultado con token y datos del usuario
     * @throws UserAlreadyExistsException si el email ya está en uso
     * @throws BusinessValidationException si los datos no son válidos
     */
    fun execute(command: RegisterCommand): AuthResult
}
