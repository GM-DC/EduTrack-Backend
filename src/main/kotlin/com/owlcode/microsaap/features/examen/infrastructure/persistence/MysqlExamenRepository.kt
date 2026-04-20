package com.owlcode.microsaap.features.examen.infrastructure.persistence

import com.owlcode.microsaap.features.examen.domain.model.Examen
import com.owlcode.microsaap.features.examen.domain.repository.ExamenRepository
import com.owlcode.microsaap.features.examen.infrastructure.persistence.jpa.SpringDataExamenRepository
import com.owlcode.microsaap.features.examen.infrastructure.persistence.mapper.ExamenMapper
import org.springframework.stereotype.Repository

@Repository
class MysqlExamenRepository(
    private val jpaRepository: SpringDataExamenRepository,
    private val mapper: ExamenMapper
) : ExamenRepository {
    override fun findById(id: Long): Examen? =
        jpaRepository.findById(id).map { mapper.toDomain(it) }.orElse(null)

    override fun findByCourseId(courseId: Long): List<Examen> =
        jpaRepository.findByCourseId(courseId).map { mapper.toDomain(it) }

    override fun save(examen: Examen): Examen =
        mapper.toDomain(jpaRepository.save(mapper.toEntity(examen)))

    override fun deleteById(id: Long) = jpaRepository.deleteById(id)

    override fun deleteByCourseId(courseId: Long) = jpaRepository.deleteByCourseId(courseId)

    override fun existsById(id: Long): Boolean = jpaRepository.existsById(id)
}

