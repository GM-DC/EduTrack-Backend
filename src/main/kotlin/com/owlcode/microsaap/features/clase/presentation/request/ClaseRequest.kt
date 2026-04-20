package com.owlcode.microsaap.features.clase.presentation.request

import com.owlcode.microsaap.features.clase.domain.model.ClassMode
import com.owlcode.microsaap.features.clase.domain.model.RecurrenceEndType
import com.owlcode.microsaap.features.clase.domain.model.RecurrenceType
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

data class RecurrenceRuleRequest(
    val type: RecurrenceType = RecurrenceType.NONE,
    val interval: Int = 1,
    val daysOfWeek: List<Int> = emptyList(),
    val endType: RecurrenceEndType = RecurrenceEndType.NEVER,
    val untilDate: LocalDate? = null,
    val occurrenceCount: Int? = null
)

data class CreateClaseRequest(
    @field:NotNull val courseId: Long,
    val titulo: String = "",
    val modalidad: ClassMode = ClassMode.PRESENTIAL,
    val docente: String? = null,
    val aula: String? = null,
    val enlace: String? = null,
    val notas: String? = null,
    @field:NotNull val startDate: LocalDate,
    val endDate: LocalDate? = null,
    val startTime: String? = null,
    val endTime: String? = null,
    val recurrenceRule: RecurrenceRuleRequest = RecurrenceRuleRequest(),
    val daysOfWeek: List<Int> = emptyList(),
    val parentSeriesId: Long? = null
)

data class UpdateClaseRequest(
    val titulo: String = "",
    val modalidad: ClassMode = ClassMode.PRESENTIAL,
    val docente: String? = null,
    val aula: String? = null,
    val enlace: String? = null,
    val notas: String? = null,
    @field:NotNull val startDate: LocalDate,
    val endDate: LocalDate? = null,
    val startTime: String? = null,
    val endTime: String? = null,
    val recurrenceRule: RecurrenceRuleRequest = RecurrenceRuleRequest(),
    val daysOfWeek: List<Int> = emptyList(),
    val isCancelled: Boolean = false
)

