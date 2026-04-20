package com.owlcode.microsaap.features.tarea.application.service

import com.owlcode.microsaap.common.exception.EntityNotFoundException
import com.owlcode.microsaap.features.course.domain.repository.CourseRepository
import com.owlcode.microsaap.features.tarea.domain.model.Tarea
import com.owlcode.microsaap.features.tarea.domain.model.TaskPriority
import com.owlcode.microsaap.features.tarea.domain.model.TaskStatus
import com.owlcode.microsaap.features.tarea.domain.repository.TareaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

data class CreateTareaCommand(
    val courseId: Long,
    val titulo: String,
    val descripcion: String = "",
    val prioridad: TaskPriority = TaskPriority.MEDIUM,
    val startDate: LocalDate? = null,
    val dueDate: LocalDate,
    val dueTime: String? = null,
    val endDate: LocalDate? = null
)

data class UpdateTareaCommand(
    val id: Long,
    val titulo: String,
    val descripcion: String = "",
    val prioridad: TaskPriority = TaskPriority.MEDIUM,
    val startDate: LocalDate? = null,
    val dueDate: LocalDate,
    val dueTime: String? = null,
    val endDate: LocalDate? = null,
    val estado: TaskStatus = TaskStatus.PENDING
)

@Service
@Transactional
class TareaApplicationService(
    private val tareaRepository: TareaRepository,
    private val courseRepository: CourseRepository
) {

    fun create(command: CreateTareaCommand): Tarea {
        if (!courseRepository.existsById(command.courseId))
            throw EntityNotFoundException("Curso", command.courseId)
        return tareaRepository.save(
            Tarea(
                id = null,
                courseId = command.courseId,
                titulo = command.titulo.trim(),
                descripcion = command.descripcion,
                prioridad = command.prioridad,
                startDate = command.startDate,
                dueDate = command.dueDate,
                dueTime = command.dueTime,
                endDate = command.endDate
            )
        )
    }

    fun update(command: UpdateTareaCommand): Tarea {
        val existing = tareaRepository.findById(command.id)
            ?: throw EntityNotFoundException("Tarea", command.id)
        return tareaRepository.save(
            existing.copy(
                titulo = command.titulo.trim(),
                descripcion = command.descripcion,
                prioridad = command.prioridad,
                startDate = command.startDate,
                dueDate = command.dueDate,
                dueTime = command.dueTime,
                endDate = command.endDate,
                estado = command.estado
            )
        )
    }

    @Transactional(readOnly = true)
    fun findByCourse(courseId: Long): List<Tarea> = tareaRepository.findByCourseId(courseId)

    @Transactional(readOnly = true)
    fun findById(id: Long): Tarea = tareaRepository.findById(id)
        ?: throw EntityNotFoundException("Tarea", id)

    fun delete(id: Long) {
        if (!tareaRepository.existsById(id)) throw EntityNotFoundException("Tarea", id)
        tareaRepository.deleteById(id)
    }
}

