
package com.owlcode.microsaap.common.util

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder

/**
 * Utilidades para obtener datos del usuario autenticado desde el SecurityContext
 */
object SecurityUtils {

    fun getCurrentUserId(): Long {
        val auth = SecurityContextHolder.getContext().authentication
            as? UsernamePasswordAuthenticationToken
            ?: error("No hay usuario autenticado")

        @Suppress("UNCHECKED_CAST")
        val details = auth.details as? Map<String, Any>
            ?: error("No se pudo leer los detalles del token")

        return when (val id = details["userId"]) {
            is Long   -> id
            is Int    -> id.toLong()
            is String -> id.toLong()
            else      -> error("userId inválido en el token")
        }
    }
}

