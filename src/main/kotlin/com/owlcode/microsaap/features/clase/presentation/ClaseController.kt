package com.owlcode.microsaap.features.clase.presentation

import com.owlcode.microsaap.common.response.ApiResponse
import com.owlcode.microsaap.features.clase.application.dto.CreateClaseCommand
import com.owlcode.microsaap.features.clase.application.dto.RecurrenceRuleDto
import com.owlcode.microsaap.features.clase.application.dto.UpdateClaseCommand
import com.owlcode.microsaap.features.clase.application.service.ClaseApplicationService
import com.owlcode.microsaap.features.clase.domain.model.Clase
import com.owlcode.microsaap.features.clase.presentation.request.CreateClaseRequest
import com.owlcode.microsaap.features.clase.presentation.request.UpdateClaseRequest
import com.owlcode.microsaap.features.clase.presentation.response.ClaseResponse
import com.owlcode.microsaap.features.clase.presentation.response.RecurrenceRuleResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class ClaseController(
    private val service: ClaseApplicationService
) {

    @GetMapping("/api/courses/{courseId}/clases")
    fun listByCourse(@PathVariable courseId: Long): ApiResponse<List<ClaseResponse>> =
        ApiResponse.success(service.findByCourse(courseId).map { it.toResponse() })

    @GetMapping("/api/clases/{id}")
    fun getById(@PathVariable id: Long): ApiResponse<ClaseResponse> =
        ApiResponse.success(service.findById(id).toResponse())

    @PostMapping("/api/clases")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody request: CreateClaseRequest): ApiResponse<ClaseResponse> {
        val command = CreateClaseCommand(
            courseId = request.courseId,
            titulo = request.titulo,
            modalidad = request.modalidad,
            docente = request.docente,
            aula = request.aula,
            enlace = request.enlace,
            notas = request.notas,
            startDate = request.startDate,
            endDate = request.endDate,
            startTime = request.startTime,
            endTime = request.endTime,
            recurrenceRule = request.recurrenceRule.toDto(),
            daysOfWeek = request.daysOfWeek,
            parentSeriesId = request.parentSeriesId
        )
        return ApiResponse.success(service.create(command).toResponse())
    }

    @PutMapping("/api/clases/{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody request: UpdateClaseRequest
    ): ApiResponse<ClaseResponse> {
        val command = UpdateClaseCommand(
            id = id,
            titulo = request.titulo,
            modalidad = request.modalidad,
            docente = request.docente,
            aula = request.aula,
            enlace = request.enlace,
            notas = request.notas,
            startDate = request.startDate,
            endDate = request.endDate,
            startTime = request.startTime,
            endTime = request.endTime,
            recurrenceRule = request.recurrenceRule.toDto(),
            daysOfWeek = request.daysOfWeek,
            isCancelled = request.isCancelled
        )
        return ApiResponse.success(service.update(command).toResponse())
    }

    @DeleteMapping("/api/clases/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = service.delete(id)

    private fun com.owlcode.microsaap.features.clase.presentation.request.RecurrenceRuleRequest.toDto() =
        RecurrenceRuleDto(type, interval, daysOfWeek, endType, untilDate, occurrenceCount)

    private fun Clase.toResponse() = ClaseResponse(
        id = id!!,
        courseId = courseId,
        titulo = titulo,
        modalidad = modalidad,
        docente = docente,
        aula = aula,
        enlace = enlace,
        notas = notas,
        startDate = startDate,
        endDate = endDate,
        startTime = startTime,
        endTime = endTime,
        recurrenceRule = RecurrenceRuleResponse(
            type = recurrenceRule.type,
            interval = recurrenceRule.interval,
            daysOfWeek = recurrenceRule.daysOfWeek,
            endType = recurrenceRule.endType,
            untilDate = recurrenceRule.untilDate,
            occurrenceCount = recurrenceRule.occurrenceCount
        ),
        daysOfWeek = daysOfWeek,
        isCancelled = isCancelled,
        parentSeriesId = parentSeriesId
    )
}

