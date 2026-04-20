package com.owlcode.microsaap.features.evento.domain.model

import java.time.LocalDate

data class EventoPersonal(
    val id: Long?,
    val userId: Long,
    val titulo: String,
    val descripcion: String = "",
    val fecha: LocalDate,
    val horaInicio: String? = null,
    val horaFin: String? = null
) {
    init {
        require(titulo.isNotBlank()) { "El título del evento es requerido" }
        require(userId > 0) { "userId debe ser válido" }
    }
}
