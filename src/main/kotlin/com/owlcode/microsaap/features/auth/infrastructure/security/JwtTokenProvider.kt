package com.owlcode.microsaap.features.auth.infrastructure.security

import com.owlcode.microsaap.features.auth.domain.service.TokenProvider
import com.owlcode.microsaap.common.security.JwtTokenService
import org.springframework.stereotype.Component

/**
 * Implementación de TokenProvider usando JWT
 */
@Component
class JwtTokenProvider(
    private val jwtTokenService: JwtTokenService
) : TokenProvider {

    override fun generateToken(userId: Long, email: String): String {
        return jwtTokenService.generateToken(userId, email)
    }

    override fun isTokenValid(token: String): Boolean {
        return jwtTokenService.isTokenValid(token)
    }

    override fun extractUserEmail(token: String): String {
        return jwtTokenService.extractSubject(token)
    }

    override fun extractUserId(token: String): Long {
        return jwtTokenService.extractUserId(token)
    }
}
