package com.owlcode.microsaap.common.config

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import javax.sql.DataSource

@Component
class TableVerifier(private val dataSource: DataSource) : CommandLineRunner {

    private val logger = LoggerFactory.getLogger(TableVerifier::class.java)

    override fun run(vararg args: String) {
        logger.info("🔧 VERIFICANDO TABLA 'users'...")

        try {
            dataSource.connection.use { connection ->
                val stmt = connection.createStatement()

                // Intentar hacer un simple SELECT para verificar si existe
                try {
                    stmt.executeQuery("SELECT 1 FROM users LIMIT 1").use { rs ->
                        logger.info("✅ TABLA 'users' EXISTE Y ES ACCESIBLE")
                    }
                } catch (ex: Exception) {
                    logger.warn("⚠️  TABLA 'users' NO EXISTE. CREANDO...")

                    val createSQL = """
                        CREATE TABLE users (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            email VARCHAR(255) NOT NULL UNIQUE,
                            password_hash VARCHAR(255) NOT NULL,
                            first_name VARCHAR(50) NOT NULL,
                            last_name VARCHAR(50) NOT NULL,
                            is_active BOOLEAN NOT NULL DEFAULT TRUE,
                            created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
                        ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
                    """.trimIndent()

                    stmt.execute(createSQL)
                    logger.info("✅ TABLA 'users' CREADA EXITOSAMENTE")
                }

                stmt.close()
            }
        } catch (ex: Exception) {
            logger.error("❌ ERROR VERIFICANDO TABLA: {}", ex.message)
        }
    }
}
