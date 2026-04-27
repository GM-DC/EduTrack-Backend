package com.owlcode.microsaap.common.config

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

/**
 * Filtro que desactiva el caché del navegador en todas las respuestas de la API.
 * Evita que el frontend quede "pegado" con datos viejos después de un nuevo despliegue.
 */
@Component
@Order(1)
class NoCacheFilter : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0")
        response.setHeader("Pragma", "no-cache")
        response.setHeader("Expires", "0")
        filterChain.doFilter(request, response)
    }

    // Solo aplica a rutas /api/** y /auth/**
    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        val path = request.requestURI
        return !path.startsWith("/api") && !path.startsWith("/auth")
    }
}

