package com.owlcode.microsaap.features.evento.infrastructure.persistence

import com.owlcode.microsaap.features.evento.domain.model.EventoPersonal
import com.owlcode.microsaap.features.evento.domain.repository.EventoPersonalRepository
import com.owlcode.microsaap.features.evento.infrastructure.persistence.jpa.SpringDataEventoPersonalRepository
import com.owlcode.microsaap.features.evento.infrastructure.persistence.mapper.EventoPersonalMapper
import org.springframework.stereotype.Repository

@Repository
class MysqlEventoPersonalRepository(
    private val jpaRepository: SpringDataEventoPersonalRepository,
    private val mapper: EventoPersonalMapper
) : EventoPersonalRepository {

    override fun findById(id: Long): EventoPersonal? =
        jpaRepository.findById(id).map { mapper.toDomain(it) }.orElse(null)

    override fun findByUserId(userId: Long): List<EventoPersonal> =
        jpaRepository.findByUserId(userId).map { mapper.toDomain(it) }

    override fun save(evento: EventoPersonal): EventoPersonal =
        mapper.toDomain(jpaRepository.save(mapper.toEntity(evento)))

    override fun deleteById(id: Long) = jpaRepository.deleteById(id)

    override fun existsById(id: Long): Boolean = jpaRepository.existsById(id)
}
