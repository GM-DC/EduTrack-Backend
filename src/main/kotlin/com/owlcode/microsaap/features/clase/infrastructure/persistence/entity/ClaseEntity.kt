package com.owlcode.microsaap.features.clase.infrastructure.persistence.entity

import com.owlcode.microsaap.features.clase.domain.model.ClassMode
import com.owlcode.microsaap.features.clase.domain.model.RecurrenceEndType
import com.owlcode.microsaap.features.clase.domain.model.RecurrenceType
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "clases")
data class ClaseEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "course_id", nullable = false)
    val courseId: Long,

    @Column(nullable = false)
    val titulo: String = "",

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    val modalidad: ClassMode = ClassMode.PRESENTIAL,

    @Column
    val docente: String? = null,

    @Column
    val aula: String? = null,

    @Column
    val enlace: String? = null,

    @Column(columnDefinition = "TEXT")
    val notas: String? = null,

    @Column(name = "start_date", nullable = false)
    val startDate: LocalDate,

    @Column(name = "end_date")
    val endDate: LocalDate? = null,

    @Column(name = "start_time", length = 5)
    val startTime: String? = null,

    @Column(name = "end_time", length = 5)
    val endTime: String? = null,

    // Recurrencia embebida como columnas planas
    @Enumerated(EnumType.STRING)
    @Column(name = "recurrence_type", nullable = false, length = 20)
    val recurrenceType: RecurrenceType = RecurrenceType.NONE,

    @Column(name = "recurrence_interval", nullable = false)
    val recurrenceInterval: Int = 1,

    /** CSV de enteros, ej: "0,2,4" */
    @Column(name = "recurrence_days_of_week", length = 20)
    val recurrenceDaysOfWeek: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "recurrence_end_type", nullable = false, length = 30)
    val recurrenceEndType: RecurrenceEndType = RecurrenceEndType.NEVER,

    @Column(name = "recurrence_until_date")
    val recurrenceUntilDate: LocalDate? = null,

    @Column(name = "recurrence_occurrence_count")
    val recurrenceOccurrenceCount: Int? = null,

    /** CSV de días activos de la clase (para responder daysOfWeek en JSON) */
    @Column(name = "days_of_week", length = 20)
    val daysOfWeek: String? = null,

    @Column(name = "is_cancelled", nullable = false)
    val isCancelled: Boolean = false,

    @Column(name = "parent_series_id")
    val parentSeriesId: Long? = null,

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime? = null,

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    val updatedAt: LocalDateTime? = null
)

