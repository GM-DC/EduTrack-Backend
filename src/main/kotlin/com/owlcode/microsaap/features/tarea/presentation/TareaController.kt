package com.owlcode.microsaap.features.tarea.presentation

import com.owlcode.microsaap.common.response.ApiResponse
import com.owlcode.microsaap.features.tarea.application.service.CreateTareaCommand
import com.owlcode.microsaap.features.tarea.application.service.TareaApplicationService
import com.owlcode.microsaap.features.tarea.application.service.UpdateTareaCommand
import com.owlcode.microsaap.features.tarea.domain.model.Tarea
import com.owlcode.microsaap.features.tarea.domain.model.TaskPriority
import com.owlcode.microsaap.features.tarea.domain.model.TaskStatus
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

// ─── Request / Response (en mismo archivo por brevedad) ──────────────────────

data class CreateTareaRequest(
    @field:NotNull val courseId: Long,
    @field:NotBlank val titulo: String,
    val descripcion: String = "",
    val prioridad: TaskPriority = TaskPriority.MEDIUM,
    val startDate: LocalDate? = null,
    @field:NotNull val dueDate: LocalDate,
    val dueTime: String? = null,
    val endDate: LocalDate? = null
)

data class UpdateTareaRequest(
    @field:NotBlank val titulo: String,
    val descripcion: String = "",
    val prioridad: TaskPriority = TaskPriority.MEDIUM,
    val startDate: LocalDate? = null,
    @field:NotNull val dueDate: LocalDate,
    val dueTime: String? = null,
    val endDate: LocalDate? = null,
    val estado: TaskStatus = TaskStatus.PENDING
)

data class TareaResponse(
    val id: Long,
    val courseId: Long,
    val titulo: String,
    val descripcion: String,
    val prioridad: TaskPriority,
    val startDate: LocalDate?,
    val dueDate: LocalDate,
    val dueTime: String?,
    val endDate: LocalDate?,
    val estado: TaskStatus
)

// ─── Controller ──────────────────────────────────────────────────────────────

@RestController
class TareaController(private val service: TareaApplicationService) {

    @GetMapping("/api/courses/{courseId}/tareas")
    fun listByCourse(@PathVariable courseId: Long): ApiResponse<List<TareaResponse>> =
        ApiResponse.success(service.findByCourse(courseId).map { it.toResponse() })

    @GetMapping("/api/tareas/{id}")
    fun getById(@PathVariable id: Long): ApiResponse<TareaResponse> =
        ApiResponse.success(service.findById(id).toResponse())

    @PostMapping("/api/tareas")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody request: CreateTareaRequest): ApiResponse<TareaResponse> =
        ApiResponse.success(
            service.create(
                CreateTareaCommand(
                    courseId = request.courseId,
                    titulo = request.titulo,
                    descripcion = request.descripcion,
                    prioridad = request.prioridad,
                    startDate = request.startDate,
                    dueDate = request.dueDate,
                    dueTime = request.dueTime,
                    endDate = request.endDate
                )
            ).toResponse()
        )

    @PutMapping("/api/tareas/{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody request: UpdateTareaRequest
    ): ApiResponse<TareaResponse> =
        ApiResponse.success(
            service.update(
                UpdateTareaCommand(
                    id = id,
                    titulo = request.titulo,
                    descripcion = request.descripcion,
                    prioridad = request.prioridad,
                    startDate = request.startDate,
                    dueDate = request.dueDate,
                    dueTime = request.dueTime,
                    endDate = request.endDate,
                    estado = request.estado
                )
            ).toResponse()
        )

    @DeleteMapping("/api/tareas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = service.delete(id)

    private fun Tarea.toResponse() = TareaResponse(
        id = id!!, courseId = courseId, titulo = titulo, descripcion = descripcion,
        prioridad = prioridad, startDate = startDate, dueDate = dueDate,
        dueTime = dueTime, endDate = endDate, estado = estado
    )
}

