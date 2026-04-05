package com.owlcode.microsaap.common.security

import com.owlcode.microsaap.features.auth.domain.service.TokenProvider
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

/**
 * Filtro para procesar tokens JWT en cada request
 */
@Component
class JwtRequestFilter(
    private val tokenProvider: TokenProvider
) : OncePerRequestFilter() {

    private val logger = LoggerFactory.getLogger(JwtRequestFilter::class.java)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = request.getHeader("Authorization")

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            val token = authHeader.substring(7)

            try {
                if (tokenProvider.isTokenValid(token)) {
                    val userId = tokenProvider.extractUserId(token)
                    val email = tokenProvider.extractUserEmail(token)

                    // Crear authorities básicas - en el futuro se pueden extraer del token o BD
                    val authorities = listOf(SimpleGrantedAuthority("ROLE_USER"))

                    val authentication = UsernamePasswordAuthenticationToken(
                        email, null, authorities
                    )

                    // Agregar información adicional del usuario
                    authentication.details = mapOf("userId" to userId)

                    SecurityContextHolder.getContext().authentication = authentication
                    logger.debug("Usuario autenticado: $email")
                }
            } catch (e: Exception) {
                logger.warn("Error validando JWT token: ${e.message}")
                SecurityContextHolder.clearContext()
            }
        }

        filterChain.doFilter(request, response)
    }
}
