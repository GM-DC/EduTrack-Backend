package com.owlcode.microsaap.features.tarea.infrastructure.persistence

import com.owlcode.microsaap.features.tarea.domain.model.Tarea
import com.owlcode.microsaap.features.tarea.domain.repository.TareaRepository
import com.owlcode.microsaap.features.tarea.infrastructure.persistence.jpa.SpringDataTareaRepository
import com.owlcode.microsaap.features.tarea.infrastructure.persistence.mapper.TareaMapper
import org.springframework.stereotype.Repository

@Repository
class MysqlTareaRepository(
    private val jpaRepository: SpringDataTareaRepository,
    private val mapper: TareaMapper
) : TareaRepository {

    override fun findById(id: Long): Tarea? =
        jpaRepository.findById(id).map { mapper.toDomain(it) }.orElse(null)

    override fun findByCourseId(courseId: Long): List<Tarea> =
        jpaRepository.findByCourseId(courseId).map { mapper.toDomain(it) }

    override fun save(tarea: Tarea): Tarea =
        mapper.toDomain(jpaRepository.save(mapper.toEntity(tarea)))

    override fun deleteById(id: Long) = jpaRepository.deleteById(id)

    override fun deleteByCourseId(courseId: Long) = jpaRepository.deleteByCourseId(courseId)

    override fun existsById(id: Long): Boolean = jpaRepository.existsById(id)
}
