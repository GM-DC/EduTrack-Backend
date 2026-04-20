package com.owlcode.microsaap.features.tarea.domain.model

import java.time.LocalDate

data class Tarea(
    val id: Long?,
    val courseId: Long,
    val titulo: String,
    val descripcion: String = "",
    val prioridad: TaskPriority = TaskPriority.MEDIUM,
    val startDate: LocalDate? = null,
    val dueDate: LocalDate,
    val dueTime: String? = null,
    val endDate: LocalDate? = null,
    val estado: TaskStatus = TaskStatus.PENDING
) {
    init {
        require(titulo.isNotBlank()) { "El título de la tarea es requerido" }
        require(courseId > 0) { "courseId debe ser válido" }
    }
}

