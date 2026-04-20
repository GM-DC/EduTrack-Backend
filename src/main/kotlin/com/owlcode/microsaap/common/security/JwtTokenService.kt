package com.owlcode.microsaap.common.security

import com.owlcode.microsaap.common.config.JwtProperties
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import java.time.Instant
import java.util.*
import javax.crypto.SecretKey
import org.slf4j.LoggerFactory

/**
 * Servicio para manejo de tokens JWT
 */
@Service
class JwtTokenService(
    private val jwtProperties: JwtProperties
) {
    private val logger = LoggerFactory.getLogger(JwtTokenService::class.java)
    private val secretKey: SecretKey by lazy {
        logger.info("[DEBUG] Clave secreta JWT usada por backend: ${jwtProperties.secret}")
        Keys.hmacShaKeyFor(jwtProperties.secret.toByteArray(StandardCharsets.UTF_8))
    }

    fun generateToken(userId: Long, email: String): String {
        val now = Instant.now()
        val expiration = now.plusMillis(jwtProperties.expiration)

        return Jwts.builder()
            .subject(email)
            .claim("userId", userId)
            .issuedAt(Date.from(now))
            .expiration(Date.from(expiration))
            .signWith(secretKey)
            .compact()
    }

    fun extractSubject(token: String): String {
        return extractClaims(token).subject
    }

    fun extractUserId(token: String): Long {
        val value = extractClaims(token)["userId"]
        return when (value) {
            is Long -> value
            is Int -> value.toLong()
            is Number -> value.toLong()
            else -> throw IllegalArgumentException("userId no es un número válido en el token")
        }
    }

    fun extractExpiration(token: String): Date {
        return extractClaims(token).expiration
    }

    fun isTokenValid(token: String): Boolean {
        return try {
            val claims = extractClaims(token)
            !claims.expiration.before(Date())
        } catch (e: Exception) {
            false
        }
    }

    private fun extractClaims(token: String): Claims {
        return Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .payload
    }
}
