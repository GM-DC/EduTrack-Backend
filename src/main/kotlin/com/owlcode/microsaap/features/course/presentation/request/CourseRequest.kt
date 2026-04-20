package com.owlcode.microsaap.features.course.presentation.request

import jakarta.validation.constraints.NotBlank

data class CreateCourseRequest(
    @field:NotBlank(message = "El nombre del curso es requerido")
    val name: String,
    val teacher: String? = null,
    val description: String = "",
    val color: String = "#4A90D9",
    val locationOrPlatform: String? = null,
    val credits: Int? = null
)

data class UpdateCourseRequest(
    @field:NotBlank(message = "El nombre del curso es requerido")
    val name: String,
    val teacher: String? = null,
    val description: String = "",
    val color: String = "#4A90D9",
    val locationOrPlatform: String? = null,
    val credits: Int? = null
)

