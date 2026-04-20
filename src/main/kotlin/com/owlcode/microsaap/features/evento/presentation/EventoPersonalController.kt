package com.owlcode.microsaap.features.evento.presentation

import com.owlcode.microsaap.common.exception.EntityNotFoundException
import com.owlcode.microsaap.common.response.ApiResponse
import com.owlcode.microsaap.common.util.SecurityUtils
import com.owlcode.microsaap.features.evento.domain.model.EventoPersonal
import com.owlcode.microsaap.features.evento.domain.repository.EventoPersonalRepository
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

// ─── Request / Response ───────────────────────────────────────────────────────

data class CreateEventoPersonalRequest(
    @field:NotBlank val titulo: String,
    val descripcion: String = "",
    @field:NotNull val fecha: LocalDate,
    val horaInicio: String? = null,
    val horaFin: String? = null
)

data class UpdateEventoPersonalRequest(
    @field:NotBlank val titulo: String,
    val descripcion: String = "",
    @field:NotNull val fecha: LocalDate,
    val horaInicio: String? = null,
    val horaFin: String? = null
)

/** El userId no se expone al cliente según el plan */
data class EventoPersonalResponse(
    val id: Long,
    val titulo: String,
    val descripcion: String,
    val fecha: LocalDate,
    val horaInicio: String?,
    val horaFin: String?
)

// ─── Service ─────────────────────────────────────────────────────────────────

@Service
@Transactional
class EventoPersonalApplicationService(
    private val eventoRepository: EventoPersonalRepository
) {
    fun create(userId: Long, request: CreateEventoPersonalRequest): EventoPersonal =
        eventoRepository.save(
            EventoPersonal(
                id = null, userId = userId, titulo = request.titulo.trim(),
                descripcion = request.descripcion, fecha = request.fecha,
                horaInicio = request.horaInicio, horaFin = request.horaFin
            )
        )

    fun update(id: Long, request: UpdateEventoPersonalRequest): EventoPersonal {
        val existing = eventoRepository.findById(id)
            ?: throw EntityNotFoundException("EventoPersonal", id)
        return eventoRepository.save(
            existing.copy(
                titulo = request.titulo.trim(), descripcion = request.descripcion,
                fecha = request.fecha, horaInicio = request.horaInicio, horaFin = request.horaFin
            )
        )
    }

    @Transactional(readOnly = true)
    fun findByUser(userId: Long): List<EventoPersonal> = eventoRepository.findByUserId(userId)

    @Transactional(readOnly = true)
    fun findById(id: Long): EventoPersonal = eventoRepository.findById(id)
        ?: throw EntityNotFoundException("EventoPersonal", id)

    fun delete(id: Long) {
        if (!eventoRepository.existsById(id)) throw EntityNotFoundException("EventoPersonal", id)
        eventoRepository.deleteById(id)
    }
}

// ─── Controller ──────────────────────────────────────────────────────────────

@RestController
@RequestMapping("/api/eventos-personales")
class EventoPersonalController(private val service: EventoPersonalApplicationService) {

    @GetMapping
    fun list(): ApiResponse<List<EventoPersonalResponse>> {
        val userId = SecurityUtils.getCurrentUserId()
        val items = service.findByUser(userId).map { it.toResponse() }
        return ApiResponse.success(items)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ApiResponse<EventoPersonalResponse> =
        ApiResponse.success(service.findById(id).toResponse())

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody request: CreateEventoPersonalRequest): ApiResponse<EventoPersonalResponse> {
        val userId = SecurityUtils.getCurrentUserId()
        return ApiResponse.success(service.create(userId, request).toResponse())
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody request: UpdateEventoPersonalRequest
    ): ApiResponse<EventoPersonalResponse> =
        ApiResponse.success(service.update(id, request).toResponse())

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = service.delete(id)

    private fun EventoPersonal.toResponse() = EventoPersonalResponse(
        id = this.id!!,
        titulo = this.titulo,
        descripcion = this.descripcion,
        fecha = this.fecha,
        horaInicio = this.horaInicio,
        horaFin = this.horaFin
    )
}
