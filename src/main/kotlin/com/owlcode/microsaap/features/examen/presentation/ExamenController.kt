package com.owlcode.microsaap.features.examen.presentation

import com.owlcode.microsaap.common.exception.EntityNotFoundException
import com.owlcode.microsaap.common.response.ApiResponse
import com.owlcode.microsaap.features.course.domain.repository.CourseRepository
import com.owlcode.microsaap.features.examen.domain.model.Examen
import com.owlcode.microsaap.features.examen.domain.model.ExamStatus
import com.owlcode.microsaap.features.examen.domain.repository.ExamenRepository
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

// ─── Commands / Responses ────────────────────────────────────────────────────

data class CreateExamenRequest(
    @field:NotNull val courseId: Long,
    @field:NotBlank val titulo: String,
    val tema: String = "",
    val puntaje: Double? = null,
    @field:NotNull val fecha: LocalDate,
    val horaInicio: String? = null,
    val horaFin: String? = null,
    val duracion: Int? = null
)

data class UpdateExamenRequest(
    @field:NotBlank val titulo: String,
    val tema: String = "",
    val puntaje: Double? = null,
    @field:NotNull val fecha: LocalDate,
    val horaInicio: String? = null,
    val horaFin: String? = null,
    val duracion: Int? = null,
    val estado: ExamStatus = ExamStatus.PENDING
)

data class ExamenResponse(
    val id: Long,
    val courseId: Long,
    val titulo: String,
    val tema: String,
    val puntaje: Double?,
    val fecha: LocalDate,
    val horaInicio: String?,
    val horaFin: String?,
    val duracion: Int?,
    val estado: ExamStatus
)

// ─── Service ─────────────────────────────────────────────────────────────────

@Service
@Transactional
class ExamenApplicationService(
    private val examenRepository: ExamenRepository,
    private val courseRepository: CourseRepository
) {

    fun create(request: CreateExamenRequest): Examen {
        if (!courseRepository.existsById(request.courseId))
            throw EntityNotFoundException("Curso", request.courseId)
        return examenRepository.save(
            Examen(
                id = null, courseId = request.courseId, titulo = request.titulo.trim(),
                tema = request.tema, puntaje = request.puntaje, fecha = request.fecha,
                horaInicio = request.horaInicio, horaFin = request.horaFin, duracion = request.duracion
            )
        )
    }

    fun update(id: Long, request: UpdateExamenRequest): Examen {
        val existing = examenRepository.findById(id) ?: throw EntityNotFoundException("Examen", id)
        return examenRepository.save(
            existing.copy(
                titulo = request.titulo.trim(), tema = request.tema, puntaje = request.puntaje,
                fecha = request.fecha, horaInicio = request.horaInicio, horaFin = request.horaFin,
                duracion = request.duracion, estado = request.estado
            )
        )
    }

    @Transactional(readOnly = true)
    fun findByCourse(courseId: Long): List<Examen> = examenRepository.findByCourseId(courseId)

    @Transactional(readOnly = true)
    fun findById(id: Long): Examen = examenRepository.findById(id)
        ?: throw EntityNotFoundException("Examen", id)

    fun delete(id: Long) {
        if (!examenRepository.existsById(id)) throw EntityNotFoundException("Examen", id)
        examenRepository.deleteById(id)
    }
}

// ─── Controller ──────────────────────────────────────────────────────────────

@RestController
class ExamenController(private val service: ExamenApplicationService) {

    @GetMapping("/api/courses/{courseId}/examenes")
    fun listByCourse(@PathVariable courseId: Long): ApiResponse<List<ExamenResponse>> =
        ApiResponse.success(service.findByCourse(courseId).map { it.toResponse() })

    @GetMapping("/api/examenes/{id}")
    fun getById(@PathVariable id: Long): ApiResponse<ExamenResponse> =
        ApiResponse.success(service.findById(id).toResponse())

    @PostMapping("/api/examenes")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody request: CreateExamenRequest): ApiResponse<ExamenResponse> =
        ApiResponse.success(service.create(request).toResponse())

    @PutMapping("/api/examenes/{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody request: UpdateExamenRequest
    ): ApiResponse<ExamenResponse> =
        ApiResponse.success(service.update(id, request).toResponse())

    @DeleteMapping("/api/examenes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = service.delete(id)

    private fun Examen.toResponse() = ExamenResponse(
        id = id!!, courseId = courseId, titulo = titulo, tema = tema, puntaje = puntaje,
        fecha = fecha, horaInicio = horaInicio, horaFin = horaFin, duracion = duracion, estado = estado
    )
}

