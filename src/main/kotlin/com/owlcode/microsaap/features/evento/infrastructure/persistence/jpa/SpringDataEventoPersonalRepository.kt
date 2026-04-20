package com.owlcode.microsaap.features.evento.infrastructure.persistence.jpa

import com.owlcode.microsaap.features.evento.infrastructure.persistence.entity.EventoPersonalEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SpringDataEventoPersonalRepository : JpaRepository<EventoPersonalEntity, Long> {
    fun findByUserId(userId: Long): List<EventoPersonalEntity>
}

