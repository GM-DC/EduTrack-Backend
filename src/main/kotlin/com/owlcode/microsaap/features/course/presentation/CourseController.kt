package com.owlcode.microsaap.features.course.presentation

import com.owlcode.microsaap.common.response.ApiResponse
import com.owlcode.microsaap.common.util.SecurityUtils
import com.owlcode.microsaap.features.course.application.dto.CreateCourseCommand
import com.owlcode.microsaap.features.course.application.dto.UpdateCourseCommand
import com.owlcode.microsaap.features.course.application.service.CourseApplicationService
import com.owlcode.microsaap.features.course.domain.model.Course
import com.owlcode.microsaap.features.course.presentation.request.CreateCourseRequest
import com.owlcode.microsaap.features.course.presentation.request.UpdateCourseRequest
import com.owlcode.microsaap.features.course.presentation.response.CourseResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/courses")
class CourseController(
    private val service: CourseApplicationService
) {

    @GetMapping
    fun listByUser(): ApiResponse<List<CourseResponse>> {
        val userId = SecurityUtils.getCurrentUserId()
        return ApiResponse.success(service.findByUser(userId).map { it.toResponse() })
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ApiResponse<CourseResponse> =
        ApiResponse.success(service.findById(id).toResponse())

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody request: CreateCourseRequest): ApiResponse<CourseResponse> {
        val userId = SecurityUtils.getCurrentUserId()
        val command = CreateCourseCommand(
            name = request.name,
            teacher = request.teacher,
            description = request.description,
            color = request.color,
            locationOrPlatform = request.locationOrPlatform,
            credits = request.credits,
            userId = userId
        )
        return ApiResponse.success(service.create(command).toResponse())
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody request: UpdateCourseRequest
    ): ApiResponse<CourseResponse> {
        val command = UpdateCourseCommand(
            id = id,
            name = request.name,
            teacher = request.teacher,
            description = request.description,
            color = request.color,
            locationOrPlatform = request.locationOrPlatform,
            credits = request.credits
        )
        return ApiResponse.success(service.update(command).toResponse())
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = service.delete(id)

    private fun Course.toResponse() = CourseResponse(
        id = id!!,
        name = name,
        teacher = teacher,
        description = description,
        color = color,
        locationOrPlatform = locationOrPlatform,
        credits = credits
    )
}

