package com.owlcode.microsaap.features.tarea.infrastructure.persistence.jpa

import com.owlcode.microsaap.features.tarea.infrastructure.persistence.entity.TareaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SpringDataTareaRepository : JpaRepository<TareaEntity, Long> {
    fun findByCourseId(courseId: Long): List<TareaEntity>
    fun deleteByCourseId(courseId: Long)
}
