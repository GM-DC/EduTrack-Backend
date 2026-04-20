package com.owlcode.microsaap.features.course.domain.model

import java.time.LocalDateTime

data class Course(
    val id: Long?,
    val name: String,
    val teacher: String?,
    val description: String = "",
    val color: String = "#4A90D9",
    val locationOrPlatform: String?,
    val credits: Int?,
    val userId: Long,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null
) {
    init {
        require(name.isNotBlank()) { "El nombre del curso es requerido" }
        require(userId > 0) { "El userId debe ser válido" }
    }
}

