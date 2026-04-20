package com.owlcode.microsaap.features.examen.infrastructure.persistence.mapper

import com.owlcode.microsaap.features.examen.domain.model.Examen
import com.owlcode.microsaap.features.examen.infrastructure.persistence.entity.ExamenEntity
import org.springframework.stereotype.Component

@Component
class ExamenMapper {
    fun toEntity(examen: Examen): ExamenEntity = ExamenEntity(
        id = examen.id,
        courseId = examen.courseId,
        titulo = examen.titulo,
        tema = examen.tema,
        puntaje = examen.puntaje,
        fecha = examen.fecha,
        horaInicio = examen.horaInicio,
        horaFin = examen.horaFin,
        duracion = examen.duracion,
        estado = examen.estado
    )

    fun toDomain(entity: ExamenEntity): Examen = Examen(
        id = entity.id,
        courseId = entity.courseId,
        titulo = entity.titulo,
        tema = entity.tema,
        puntaje = entity.puntaje,
        fecha = entity.fecha,
        horaInicio = entity.horaInicio,
        horaFin = entity.horaFin,
        duracion = entity.duracion,
        estado = entity.estado
    )
}

