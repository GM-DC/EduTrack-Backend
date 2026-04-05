package com.owlcode.microsaap.common.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 * Propiedades de configuración para JWT
 */
@Component
@ConfigurationProperties(prefix = "app.jwt")
data class JwtProperties(
    var secret: String = "",
    var expiration: Long = 86400000 // 24 horas por defecto
)
