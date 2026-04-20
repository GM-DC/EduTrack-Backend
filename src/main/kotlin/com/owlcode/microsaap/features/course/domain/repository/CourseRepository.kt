package com.owlcode.microsaap.features.course.domain.repository

import com.owlcode.microsaap.features.course.domain.model.Course

interface CourseRepository {
    fun findById(id: Long): Course?
    fun findByUserId(userId: Long): List<Course>
    fun save(course: Course): Course
    fun deleteById(id: Long)
    fun existsById(id: Long): Boolean
}
