package com.owlcode.microsaap.features.course.application.dto

data class CreateCourseCommand(
    val name: String,
    val teacher: String?,
    val description: String = "",
    val color: String = "#4A90D9",
    val locationOrPlatform: String?,
    val credits: Int?,
    val userId: Long
)

data class UpdateCourseCommand(
    val id: Long,
    val name: String,
    val teacher: String?,
    val description: String,
    val color: String,
    val locationOrPlatform: String?,
    val credits: Int?
)

