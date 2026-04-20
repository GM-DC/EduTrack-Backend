package com.owlcode.microsaap.features.evento.infrastructure.persistence.mapper

import com.owlcode.microsaap.features.evento.domain.model.EventoPersonal
import com.owlcode.microsaap.features.evento.infrastructure.persistence.entity.EventoPersonalEntity
import org.springframework.stereotype.Component

@Component
class EventoPersonalMapper {
    fun toEntity(evento: EventoPersonal): EventoPersonalEntity = EventoPersonalEntity(
        id = evento.id,
        userId = evento.userId,
        titulo = evento.titulo,
        descripcion = evento.descripcion,
        fecha = evento.fecha,
        horaInicio = evento.horaInicio,
        horaFin = evento.horaFin
    )

    fun toDomain(entity: EventoPersonalEntity): EventoPersonal = EventoPersonal(
        id = entity.id,
        userId = entity.userId,
        titulo = entity.titulo,
        descripcion = entity.descripcion ?: "",
        fecha = entity.fecha,
        horaInicio = entity.horaInicio,
        horaFin = entity.horaFin
    )
}
