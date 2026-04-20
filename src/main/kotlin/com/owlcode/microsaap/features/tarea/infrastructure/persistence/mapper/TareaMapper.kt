package com.owlcode.microsaap.features.tarea.infrastructure.persistence.mapper

import com.owlcode.microsaap.features.tarea.domain.model.Tarea
import com.owlcode.microsaap.features.tarea.infrastructure.persistence.entity.TareaEntity
import org.springframework.stereotype.Component

@Component
class TareaMapper {
    fun toEntity(tarea: Tarea): TareaEntity = TareaEntity(
        id = tarea.id,
        courseId = tarea.courseId,
        titulo = tarea.titulo,
        descripcion = tarea.descripcion,
        prioridad = tarea.prioridad,
        startDate = tarea.startDate,
        dueDate = tarea.dueDate,
        dueTime = tarea.dueTime,
        endDate = tarea.endDate,
        estado = tarea.estado
    )

    fun toDomain(entity: TareaEntity): Tarea = Tarea(
        id = entity.id,
        courseId = entity.courseId,
        titulo = entity.titulo,
        descripcion = entity.descripcion ?: "",
        prioridad = entity.prioridad,
        startDate = entity.startDate,
        dueDate = entity.dueDate,
        dueTime = entity.dueTime,
        endDate = entity.endDate,
        estado = entity.estado
    )
}

