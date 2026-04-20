package com.owlcode.microsaap.features.course.application.service

import com.owlcode.microsaap.common.exception.EntityNotFoundException
import com.owlcode.microsaap.features.clase.domain.repository.ClaseRepository
import com.owlcode.microsaap.features.course.application.dto.CreateCourseCommand
import com.owlcode.microsaap.features.course.application.dto.UpdateCourseCommand
import com.owlcode.microsaap.features.course.domain.model.Course
import com.owlcode.microsaap.features.course.domain.repository.CourseRepository
import com.owlcode.microsaap.features.examen.domain.repository.ExamenRepository
import com.owlcode.microsaap.features.tarea.domain.repository.TareaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CourseApplicationService(
    private val courseRepository: CourseRepository,
    private val claseRepository: ClaseRepository,
    private val tareaRepository: TareaRepository,
    private val examenRepository: ExamenRepository
) {

    fun create(command: CreateCourseCommand): Course {
        val course = Course(
            id = null,
            name = command.name.trim(),
            teacher = command.teacher?.trim(),
            description = command.description,
            color = command.color,
            locationOrPlatform = command.locationOrPlatform?.trim(),
            credits = command.credits,
            userId = command.userId
        )
        return courseRepository.save(course)
    }

    fun update(command: UpdateCourseCommand): Course {
        val existing = courseRepository.findById(command.id)
            ?: throw EntityNotFoundException("Curso", command.id)
        val updated = existing.copy(
            name = command.name.trim(),
            teacher = command.teacher?.trim(),
            description = command.description,
            color = command.color,
            locationOrPlatform = command.locationOrPlatform?.trim(),
            credits = command.credits
        )
        return courseRepository.save(updated)
    }

    @Transactional(readOnly = true)
    fun findByUser(userId: Long): List<Course> = courseRepository.findByUserId(userId)

    @Transactional(readOnly = true)
    fun findById(id: Long): Course = courseRepository.findById(id)
        ?: throw EntityNotFoundException("Curso", id)

    fun delete(id: Long) {
        if (!courseRepository.existsById(id)) throw EntityNotFoundException("Curso", id)
        claseRepository.deleteByCourseId(id)
        tareaRepository.deleteByCourseId(id)
        examenRepository.deleteByCourseId(id)
        courseRepository.deleteById(id)
    }
}
