package com.owlcode.microsaap.features.tarea.domain.repository

import com.owlcode.microsaap.features.tarea.domain.model.Tarea

interface TareaRepository {
    fun findById(id: Long): Tarea?
    fun findByCourseId(courseId: Long): List<Tarea>
    fun save(tarea: Tarea): Tarea
    fun deleteById(id: Long)
    fun deleteByCourseId(courseId: Long)
    fun existsById(id: Long): Boolean
}
