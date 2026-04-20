package com.owlcode.microsaap.features.course.infrastructure.persistence.mapper

import com.owlcode.microsaap.features.course.domain.model.Course
import com.owlcode.microsaap.features.course.infrastructure.persistence.entity.CourseEntity
import org.springframework.stereotype.Component

@Component
class CourseMapper {
    fun toEntity(course: Course): CourseEntity = CourseEntity(
        id = course.id,
        name = course.name,
        teacher = course.teacher,
        description = course.description,
        color = course.color,
        locationOrPlatform = course.locationOrPlatform,
        credits = course.credits,
        userId = course.userId
    )

    fun toDomain(entity: CourseEntity): Course = Course(
        id = entity.id,
        name = entity.name,
        teacher = entity.teacher,
        description = entity.description ?: "",
        color = entity.color,
        locationOrPlatform = entity.locationOrPlatform,
        credits = entity.credits,
        userId = entity.userId,
        createdAt = entity.createdAt,
        updatedAt = entity.updatedAt
    )
}

