package com.owlcode.microsaap.features.clase.infrastructure.persistence.jpa

import com.owlcode.microsaap.features.clase.infrastructure.persistence.entity.ClaseEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SpringDataClaseRepository : JpaRepository<ClaseEntity, Long> {
    fun findByCourseId(courseId: Long): List<ClaseEntity>
    fun deleteByCourseId(courseId: Long)
}
