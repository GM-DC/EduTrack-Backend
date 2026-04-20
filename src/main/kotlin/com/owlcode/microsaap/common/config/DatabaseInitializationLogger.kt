package com.owlcode.microsaap.common.config

import org.slf4j.LoggerFactory
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.core.annotation.Order
import javax.sql.DataSource

/**
 * Componente para verificar y loggear la creación exitosa de tablas
 */
@Component
@Order(200) // Ejecutar al final para verificar resultados
class DatabaseInitializationLogger(
    private val dataSource: DataSource
) {

    private val logger = LoggerFactory.getLogger(DatabaseInitializationLogger::class.java)

    @EventListener(ApplicationReadyEvent::class)
    fun logDatabaseStatus() {
        try {
            dataSource.connection.use { connection ->
                val catalog = connection.catalog   // = "microsaap_db"
                logger.info("=== VERIFICACIÓN DE BASE DE DATOS (schema: $catalog) ===")

                // Listar TODAS las tablas del schema de la app
                val tables = connection.metaData.getTables(catalog, null, "%", arrayOf("TABLE"))
                val tableNames = mutableListOf<String>()
                while (tables.next()) {
                    tableNames += tables.getString("TABLE_NAME")
                }
                tables.close()

                if (tableNames.isEmpty()) {
                    logger.error("❌ NO SE ENCONTRARON TABLAS en '$catalog'. Flyway no ejecutó las migraciones.")
                } else {
                    logger.info("✅ Tablas encontradas en '$catalog': $tableNames")

                    val expected = listOf("users", "courses", "clases", "tareas", "examenes", "eventos_personales")
                    val missing = expected.filter { it !in tableNames }
                    if (missing.isEmpty()) {
                        logger.info("✅ Todas las tablas esperadas existen.")
                    } else {
                        logger.warn("⚠️  Tablas faltantes: $missing")
                    }
                }

                // Verificar flyway_schema_history
                if ("flyway_schema_history" in tableNames) {
                    connection.createStatement().use { stmt ->
                        stmt.executeQuery("SELECT version, description, success FROM flyway_schema_history ORDER BY installed_rank")
                            .use { rs ->
                                logger.info("📋 Estado de migraciones Flyway:")
                                while (rs.next()) {
                                    logger.info("   V${rs.getString("version")} - ${rs.getString("description")} - OK: ${rs.getBoolean("success")}")
                                }
                            }
                    }
                } else {
                    logger.warn("⚠️  Tabla 'flyway_schema_history' no encontrada — Flyway nunca ejecutó.")
                }

                logger.info("=== FIN VERIFICACIÓN ===")
            }
        } catch (e: Exception) {
            logger.error("❌ Error al verificar base de datos: ${e.message}", e)
        }
    }
}
