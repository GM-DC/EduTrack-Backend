package com.owlcode.microsaap.features.examen.infrastructure.persistence.jpa

import com.owlcode.microsaap.features.examen.infrastructure.persistence.entity.ExamenEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SpringDataExamenRepository : JpaRepository<ExamenEntity, Long> {
    fun findByCourseId(courseId: Long): List<ExamenEntity>
    fun deleteByCourseId(courseId: Long)
}

