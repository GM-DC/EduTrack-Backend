package com.owlcode.microsaap.features.tarea.infrastructure.persistence.entity

import com.owlcode.microsaap.features.tarea.domain.model.TaskPriority
import com.owlcode.microsaap.features.tarea.domain.model.TaskStatus
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "tareas")
data class TareaEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "course_id", nullable = false)
    val courseId: Long,

    @Column(nullable = false)
    val titulo: String,

    @Column(columnDefinition = "TEXT")
    val descripcion: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    val prioridad: TaskPriority = TaskPriority.MEDIUM,

    @Column(name = "start_date")
    val startDate: LocalDate? = null,

    @Column(name = "due_date", nullable = false)
    val dueDate: LocalDate,

    @Column(name = "due_time", length = 5)
    val dueTime: String? = null,

    @Column(name = "end_date")
    val endDate: LocalDate? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    val estado: TaskStatus = TaskStatus.PENDING,

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime? = null,

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    val updatedAt: LocalDateTime? = null
)

