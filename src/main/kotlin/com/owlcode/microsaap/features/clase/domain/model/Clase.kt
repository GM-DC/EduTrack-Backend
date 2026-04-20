package com.owlcode.microsaap.features.clase.domain.model

import java.time.LocalDate

data class RecurrenceRule(
    val type: RecurrenceType = RecurrenceType.NONE,
    val interval: Int = 1,
    val daysOfWeek: List<Int> = emptyList(),
    val endType: RecurrenceEndType = RecurrenceEndType.NEVER,
    val untilDate: LocalDate? = null,
    val occurrenceCount: Int? = null
)

data class Clase(
    val id: Long?,
    val courseId: Long,
    val titulo: String = "",
    val modalidad: ClassMode = ClassMode.PRESENTIAL,
    val docente: String? = null,
    val aula: String? = null,
    val enlace: String? = null,
    val notas: String? = null,
    val startDate: LocalDate,
    val endDate: LocalDate? = null,
    val startTime: String? = null,
    val endTime: String? = null,
    val recurrenceRule: RecurrenceRule = RecurrenceRule(),
    val daysOfWeek: List<Int> = emptyList(),
    val isCancelled: Boolean = false,
    val parentSeriesId: Long? = null
) {
    init {
        require(courseId > 0) { "courseId debe ser válido" }
    }
}

