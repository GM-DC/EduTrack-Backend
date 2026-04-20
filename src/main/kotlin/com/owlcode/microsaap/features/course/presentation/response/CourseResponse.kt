package com.owlcode.microsaap.features.course.presentation.response

data class CourseResponse(
    val id: Long,
    val name: String,
    val teacher: String?,
    val description: String,
    val color: String,
    val locationOrPlatform: String?,
    val credits: Int?
)

