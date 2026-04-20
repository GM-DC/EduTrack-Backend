package com.owlcode.microsaap.features.clase.infrastructure.persistence.mapper

import com.owlcode.microsaap.features.clase.domain.model.Clase
import com.owlcode.microsaap.features.clase.domain.model.RecurrenceRule
import com.owlcode.microsaap.features.clase.infrastructure.persistence.entity.ClaseEntity
import org.springframework.stereotype.Component

@Component
class ClaseMapper {

    fun toEntity(clase: Clase): ClaseEntity = ClaseEntity(
        id = clase.id,
        courseId = clase.courseId,
        titulo = clase.titulo,
        modalidad = clase.modalidad,
        docente = clase.docente,
        aula = clase.aula,
        enlace = clase.enlace,
        notas = clase.notas,
        startDate = clase.startDate,
        endDate = clase.endDate,
        startTime = clase.startTime,
        endTime = clase.endTime,
        recurrenceType = clase.recurrenceRule.type,
        recurrenceInterval = clase.recurrenceRule.interval,
        recurrenceDaysOfWeek = clase.recurrenceRule.daysOfWeek.joinToString(",").ifEmpty { null },
        recurrenceEndType = clase.recurrenceRule.endType,
        recurrenceUntilDate = clase.recurrenceRule.untilDate,
        recurrenceOccurrenceCount = clase.recurrenceRule.occurrenceCount,
        daysOfWeek = clase.daysOfWeek.joinToString(",").ifEmpty { null },
        isCancelled = clase.isCancelled,
        parentSeriesId = clase.parentSeriesId
    )

    fun toDomain(entity: ClaseEntity): Clase = Clase(
        id = entity.id,
        courseId = entity.courseId,
        titulo = entity.titulo,
        modalidad = entity.modalidad,
        docente = entity.docente,
        aula = entity.aula,
        enlace = entity.enlace,
        notas = entity.notas,
        startDate = entity.startDate,
        endDate = entity.endDate,
        startTime = entity.startTime,
        endTime = entity.endTime,
        recurrenceRule = RecurrenceRule(
            type = entity.recurrenceType,
            interval = entity.recurrenceInterval,
            daysOfWeek = entity.recurrenceDaysOfWeek
                ?.split(",")?.filter { it.isNotBlank() }?.map { it.toInt() } ?: emptyList(),
            endType = entity.recurrenceEndType,
            untilDate = entity.recurrenceUntilDate,
            occurrenceCount = entity.recurrenceOccurrenceCount
        ),
        daysOfWeek = entity.daysOfWeek
            ?.split(",")?.filter { it.isNotBlank() }?.map { it.toInt() } ?: emptyList(),
        isCancelled = entity.isCancelled,
        parentSeriesId = entity.parentSeriesId
    )
}

