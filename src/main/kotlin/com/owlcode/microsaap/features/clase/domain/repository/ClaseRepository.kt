package com.owlcode.microsaap.features.clase.domain.repository

import com.owlcode.microsaap.features.clase.domain.model.Clase

interface ClaseRepository {
    fun findById(id: Long): Clase?
    fun findByCourseId(courseId: Long): List<Clase>
    fun save(clase: Clase): Clase
    fun deleteById(id: Long)
    fun deleteByCourseId(courseId: Long)
    fun existsById(id: Long): Boolean
}
