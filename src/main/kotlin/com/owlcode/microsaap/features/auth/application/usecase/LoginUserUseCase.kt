package com.owlcode.microsaap.features.auth.application.usecase

import com.owlcode.microsaap.features.auth.application.dto.LoginCommand
import com.owlcode.microsaap.features.auth.application.dto.AuthResult

/**
 * Caso de uso para login de usuario
 */
interface LoginUserUseCase {

    /**
     * Autentica un usuario y genera token de acceso
     * @param command Credenciales del usuario
     * @return Resultado con token y datos del usuario
     * @throws UserNotFoundException si el usuario no existe
     * @throws InvalidPasswordException si la contraseña es incorrecta
     * @throws UserInactiveException si el usuario está inactivo
     */
    fun execute(command: LoginCommand): AuthResult
}
