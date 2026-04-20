package com.owlcode.microsaap.features.evento.domain.repository

import com.owlcode.microsaap.features.evento.domain.model.EventoPersonal

interface EventoPersonalRepository {
    fun findById(id: Long): EventoPersonal?
    fun findByUserId(userId: Long): List<EventoPersonal>
    fun save(evento: EventoPersonal): EventoPersonal
    fun deleteById(id: Long)
    fun existsById(id: Long): Boolean
}
