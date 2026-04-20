package com.owlcode.microsaap.features.examen.domain.model

import java.time.LocalDate

data class Examen(
    val id: Long?,
    val courseId: Long,
    val titulo: String,
    val tema: String = "",
    val puntaje: Double? = null,
    val fecha: LocalDate,
    val horaInicio: String? = null,
    val horaFin: String? = null,
    val duracion: Int? = null,
    val estado: ExamStatus = ExamStatus.PENDING
) {
    init {
        require(titulo.isNotBlank()) { "El título del examen es requerido" }
        require(courseId > 0) { "courseId debe ser válido" }
    }
}

