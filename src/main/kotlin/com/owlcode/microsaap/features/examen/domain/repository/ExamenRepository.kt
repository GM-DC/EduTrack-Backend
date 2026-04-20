package com.owlcode.microsaap.features.examen.domain.repository

import com.owlcode.microsaap.features.examen.domain.model.Examen

interface ExamenRepository {
    fun findById(id: Long): Examen?
    fun findByCourseId(courseId: Long): List<Examen>
    fun save(examen: Examen): Examen
    fun deleteById(id: Long)
    fun deleteByCourseId(courseId: Long)
    fun existsById(id: Long): Boolean
}

