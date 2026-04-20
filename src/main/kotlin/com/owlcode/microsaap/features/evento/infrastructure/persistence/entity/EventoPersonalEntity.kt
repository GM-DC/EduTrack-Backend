package com.owlcode.microsaap.features.evento.infrastructure.persistence.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "eventos_personales")
data class EventoPersonalEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "user_id", nullable = false)
    val userId: Long,

    @Column(nullable = false)
    val titulo: String,

    @Column(columnDefinition = "TEXT")
    val descripcion: String? = null,

    @Column(nullable = false)
    val fecha: LocalDate,

    @Column(name = "hora_inicio", length = 5)
    val horaInicio: String? = null,

    @Column(name = "hora_fin", length = 5)
    val horaFin: String? = null,

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime? = null,

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    val updatedAt: LocalDateTime? = null
)

