package com.owlcode.microsaap.features.auth.infrastructure.spring

import com.owlcode.microsaap.features.auth.domain.usecase.LoginUserUseCase
import com.owlcode.microsaap.features.auth.domain.usecase.RegisterUserUseCase
import com.owlcode.microsaap.features.auth.domain.usecase.impl.LoginUserUseCaseImpl
import com.owlcode.microsaap.features.auth.domain.usecase.impl.RegisterUserUseCaseImpl
import com.owlcode.microsaap.features.auth.domain.repository.UserRepository
import com.owlcode.microsaap.features.auth.domain.service.PasswordHasher
import com.owlcode.microsaap.features.auth.domain.service.TokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Configuración de Spring para los casos de uso del dominio de autenticación
 * Las dependencias de Spring están aisladas en Infrastructure
 */
@Configuration
class AuthSpringConfig {

    /**
     * Bean para caso de uso de login - implementación POJO pura
     */
    @Bean
    fun loginUserUseCase(
        userRepository: UserRepository,
        passwordHasher: PasswordHasher,
        tokenProvider: TokenProvider
    ): LoginUserUseCase {
        return LoginUserUseCaseImpl(
            userRepository = userRepository,
            passwordHasher = passwordHasher,
            tokenProvider = tokenProvider
        )
    }

    /**
     * Bean para caso de uso de registro - implementación POJO pura
     */
    @Bean
    fun registerUserUseCase(
        userRepository: UserRepository,
        passwordHasher: PasswordHasher,
        tokenProvider: TokenProvider
    ): RegisterUserUseCase {
        return RegisterUserUseCaseImpl(
            userRepository = userRepository,
            passwordHasher = passwordHasher,
            tokenProvider = tokenProvider
        )
    }
}
