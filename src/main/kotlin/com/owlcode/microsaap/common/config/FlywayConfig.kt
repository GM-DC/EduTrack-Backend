package com.owlcode.microsaap.common.config

import org.flywaydb.core.Flyway
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

/**
 * Configuración explícita de Flyway para garantizar la ejecución
 * de migraciones independientemente de la auto-configuración de Spring Boot.
 */
@Configuration
class FlywayConfig {

    private val logger = LoggerFactory.getLogger(FlywayConfig::class.java)

    @Bean(initMethod = "migrate")
    fun flyway(dataSource: DataSource): Flyway {
        logger.info("🚀 Configurando y ejecutando migraciones Flyway...")
        val flyway = Flyway.configure()
            .dataSource(dataSource)
            .locations("classpath:db/migration")
            .baselineOnMigrate(true)
            .baselineVersion("0")
            .validateOnMigrate(false)
            .load()

        // Reparar automáticamente registros fallidos antes de migrar
        logger.info("🔧 Reparando estado de Flyway (limpiando migraciones fallidas)...")
        flyway.repair()

        return flyway
    }
}
