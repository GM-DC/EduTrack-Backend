package com.owlcode.microsaap.features.examen.infrastructure.persistence.entity

import com.owlcode.microsaap.features.examen.domain.model.ExamStatus
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "examenes")
data class ExamenEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "course_id", nullable = false)
    val courseId: Long,

    @Column(nullable = false)
    val titulo: String,

    @Column
    val tema: String = "",

    @Column
    val puntaje: Double? = null,

    @Column(nullable = false)
    val fecha: LocalDate,

    @Column(name = "hora_inicio", length = 5)
    val horaInicio: String? = null,

    @Column(name = "hora_fin", length = 5)
    val horaFin: String? = null,

    @Column
    val duracion: Int? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    val estado: ExamStatus = ExamStatus.PENDING,

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime? = null,

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    val updatedAt: LocalDateTime? = null
)

