package com.owlcode.microsaap.common.config

import org.slf4j.LoggerFactory
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.core.annotation.Order
import javax.sql.DataSource
import java.sql.DatabaseMetaData

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
                val metaData: DatabaseMetaData = connection.metaData

                logger.info("=== VERIFICACIÓN DE BASE DE DATOS ===")
                logger.info("Database URL: {}", metaData.url)
                logger.info("Database Product: {}", metaData.databaseProductName)
                logger.info("Database Version: {}", metaData.databaseProductVersion)

                // Verificar si la tabla users existe
                val tables = metaData.getTables(null, null, "USERS", arrayOf("TABLE"))

                if (tables.next()) {
                    logger.info("✅ TABLA 'USERS' ENCONTRADA Y VERIFICADA EXITOSAMENTE")

                    // Obtener información de columnas
                    val columns = metaData.getColumns(null, null, "USERS", null)
                    logger.info("📋 COLUMNAS DE LA TABLA 'USERS':")

                    while (columns.next()) {
                        val columnName = columns.getString("COLUMN_NAME")
                        val columnType = columns.getString("TYPE_NAME")
                        val columnSize = columns.getInt("COLUMN_SIZE")
                        val nullable = columns.getString("IS_NULLABLE")

                        logger.info("   - {}: {} ({}) - Nullable: {}",
                            columnName, columnType, columnSize, nullable)
                    }
                    columns.close()

                    // Verificar índices
                    val indexes = metaData.getIndexInfo(null, null, "USERS", false, true)
                    logger.info("📋 ÍNDICES DE LA TABLA 'USERS':")

                    while (indexes.next()) {
                        val indexName = indexes.getString("INDEX_NAME")
                        val columnName = indexes.getString("COLUMN_NAME")
                        val unique = !indexes.getBoolean("NON_UNIQUE")

                        if (indexName != null) {
                            logger.info("   - Índice '{}' en columna '{}' - Único: {}",
                                indexName, columnName, unique)
                        }
                    }
                    indexes.close()

                } else {
                    logger.error("❌ TABLA 'USERS' NO ENCONTRADA - VERIFICAR CONFIGURACIÓN DDL")
                }
                tables.close()

                logger.info("=== FIN VERIFICACIÓN DE BASE DE DATOS ===")
            }
        } catch (exception: Exception) {
            logger.error("❌ ERROR AL VERIFICAR BASE DE DATOS: {}", exception.message, exception)
        }
    }
}
