package com.owlcode.microsaap.features.auth.presentation

import com.owlcode.microsaap.common.response.ApiResponse
import com.owlcode.microsaap.features.auth.application.dto.LoginCommand
import com.owlcode.microsaap.features.auth.application.dto.RegisterCommand
import com.owlcode.microsaap.features.auth.application.service.AuthApplicationService
import com.owlcode.microsaap.features.auth.presentation.request.LoginRequest
import com.owlcode.microsaap.features.auth.presentation.request.RegisterRequest
import com.owlcode.microsaap.features.auth.presentation.response.AuthResponse
import com.owlcode.microsaap.features.auth.presentation.response.UserResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * Controlador REST para autenticación
 * CORREGIDO: Ahora depende del Application Service, no de casos de uso directamente
 */
@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authApplicationService: AuthApplicationService
) {

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun register(@Valid @RequestBody request: RegisterRequest): ApiResponse<AuthResponse> {
        val command = RegisterCommand(
            email = request.email,
            password = request.password,
            firstName = request.firstName,
            lastName = request.lastName
        )

        val result = authApplicationService.register(command)

        val response = AuthResponse(
            token = result.token,
            user = UserResponse(
                id = result.user.id,
                email = result.user.email,
                firstName = result.user.firstName,
                lastName = result.user.lastName,
                fullName = result.user.fullName,
                isActive = result.user.isActive
            )
        )

        return ApiResponse.success(response)
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    fun login(@Valid @RequestBody request: LoginRequest): ApiResponse<AuthResponse> {
        val command = LoginCommand(
            email = request.email,
            password = request.password
        )

        val result = authApplicationService.login(command)

        val response = AuthResponse(
            token = result.token,
            user = UserResponse(
                id = result.user.id,
                email = result.user.email,
                firstName = result.user.firstName,
                lastName = result.user.lastName,
                fullName = result.user.fullName,
                isActive = result.user.isActive
            )
        )

        return ApiResponse.success(response)
    }
}
