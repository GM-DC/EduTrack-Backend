package com.owlcode.microsaap.common.security

import com.owlcode.microsaap.common.config.JwtProperties
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.time.Instant
import java.util.*
import javax.crypto.SecretKey
import org.slf4j.LoggerFactory

/**
 * Servicio para manejo de tokens JWT.
 * El secret se normaliza con SHA-256 → siempre 256 bits, evita WeakKeyException.
 */
@Service
class JwtTokenService(
    private val jwtProperties: JwtProperties
) {
    private val logger = LoggerFactory.getLogger(JwtTokenService::class.java)

    private val secretKey: SecretKey by lazy {
        val rawSecret = jwtProperties.secret
        if (rawSecret.isBlank()) {
            logger.error("❌ JWT_SECRET no está configurado. Agrégalo como variable en Railway.")
            throw IllegalStateException("JWT_SECRET no está configurado")
        }
        // SHA-256 normaliza a exactamente 256 bits sin importar la longitud del secret
        val keyBytes = MessageDigest.getInstance("SHA-256")
            .digest(rawSecret.toByteArray(StandardCharsets.UTF_8))
        logger.info("✅ JWT SecretKey inicializada (${keyBytes.size * 8} bits)")
        Keys.hmacShaKeyFor(keyBytes)
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
