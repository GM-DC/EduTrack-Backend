package com.owlcode.microsaap.features.course.infrastructure.persistence

import com.owlcode.microsaap.features.course.domain.model.Course
import com.owlcode.microsaap.features.course.domain.repository.CourseRepository
import com.owlcode.microsaap.features.course.infrastructure.persistence.jpa.SpringDataCourseRepository
import com.owlcode.microsaap.features.course.infrastructure.persistence.mapper.CourseMapper
import org.springframework.stereotype.Repository

@Repository
class MysqlCourseRepository(
    private val jpaRepository: SpringDataCourseRepository,
    private val mapper: CourseMapper
) : CourseRepository {

    override fun findById(id: Long): Course? =
        jpaRepository.findById(id).map { mapper.toDomain(it) }.orElse(null)

    override fun findByUserId(userId: Long): List<Course> =
        jpaRepository.findByUserId(userId).map { mapper.toDomain(it) }

    override fun save(course: Course): Course =
        mapper.toDomain(jpaRepository.save(mapper.toEntity(course)))

    override fun deleteById(id: Long) = jpaRepository.deleteById(id)

    override fun existsById(id: Long): Boolean = jpaRepository.existsById(id)
}
