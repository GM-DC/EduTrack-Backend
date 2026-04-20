package com.owlcode.microsaap.features.clase.presentation.response
import com.owlcode.microsaap.features.clase.domain.model.ClassMode
import com.owlcode.microsaap.features.clase.domain.model.RecurrenceEndType
import com.owlcode.microsaap.features.clase.domain.model.RecurrenceType
import java.time.LocalDate
data class RecurrenceRuleResponse(
    val type: RecurrenceType,
    val interval: Int,
    val daysOfWeek: List<Int>,
    val endType: RecurrenceEndType,
    val untilDate: LocalDate?,
    val occurrenceCount: Int?
)
data class ClaseResponse(
    val id: Long,
    val courseId: Long,
    val titulo: String,
    val modalidad: ClassMode,
    val docente: String?,
    val aula: String?,
    val enlace: String?,
    val notas: String?,
    val startDate: LocalDate,
    val endDate: LocalDate?,
    val startTime: String?,
    val endTime: String?,
    val recurrenceRule: RecurrenceRuleResponse,
    val daysOfWeek: List<Int>,
    val isCancelled: Boolean,
    val parentSeriesId: Long?
)
