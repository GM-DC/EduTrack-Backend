package com.owlcode.microsaap.common.config

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

/**
 * Verificador desactivado - Flyway gestiona el esquema.
 */
@Component
class TableVerifier : CommandLineRunner {

    private val logger = LoggerFactory.getLogger(TableVerifier::class.java)

    override fun run(vararg args: String) {
        logger.debug("TableVerifier: Flyway gestiona el esquema de base de datos.")
    }
}
