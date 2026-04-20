package com.owlcode.microsaap.features.course.infrastructure.persistence.jpa

import com.owlcode.microsaap.features.course.infrastructure.persistence.entity.CourseEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SpringDataCourseRepository : JpaRepository<CourseEntity, Long> {
    fun findByUserId(userId: Long): List<CourseEntity>
}
