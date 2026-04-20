package com.owlcode.microsaap.features.clase.infrastructure.persistence

import com.owlcode.microsaap.features.clase.domain.model.Clase
import com.owlcode.microsaap.features.clase.domain.repository.ClaseRepository
import com.owlcode.microsaap.features.clase.infrastructure.persistence.jpa.SpringDataClaseRepository
import com.owlcode.microsaap.features.clase.infrastructure.persistence.mapper.ClaseMapper
import org.springframework.stereotype.Repository

@Repository
class MysqlClaseRepository(
    private val jpaRepository: SpringDataClaseRepository,
    private val mapper: ClaseMapper
) : ClaseRepository {

    override fun findById(id: Long): Clase? =
        jpaRepository.findById(id).map { mapper.toDomain(it) }.orElse(null)

    override fun findByCourseId(courseId: Long): List<Clase> =
        jpaRepository.findByCourseId(courseId).map { mapper.toDomain(it) }

    override fun save(clase: Clase): Clase =
        mapper.toDomain(jpaRepository.save(mapper.toEntity(clase)))

    override fun deleteById(id: Long) = jpaRepository.deleteById(id)

    override fun deleteByCourseId(courseId: Long) = jpaRepository.deleteByCourseId(courseId)

    override fun existsById(id: Long): Boolean = jpaRepository.existsById(id)
}
