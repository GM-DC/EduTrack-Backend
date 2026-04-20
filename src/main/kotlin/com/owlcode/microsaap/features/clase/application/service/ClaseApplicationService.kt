package com.owlcode.microsaap.features.clase.application.service

import com.owlcode.microsaap.common.exception.EntityNotFoundException
import com.owlcode.microsaap.features.clase.application.dto.CreateClaseCommand
import com.owlcode.microsaap.features.clase.application.dto.UpdateClaseCommand
import com.owlcode.microsaap.features.clase.domain.model.Clase
import com.owlcode.microsaap.features.clase.domain.model.RecurrenceRule
import com.owlcode.microsaap.features.clase.domain.repository.ClaseRepository
import com.owlcode.microsaap.features.course.domain.repository.CourseRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ClaseApplicationService(
    private val claseRepository: ClaseRepository,
    private val courseRepository: CourseRepository
) {

    fun create(command: CreateClaseCommand): Clase {
        if (!courseRepository.existsById(command.courseId))
            throw EntityNotFoundException("Curso", command.courseId)

        val clase = Clase(
            id = null,
            courseId = command.courseId,
            titulo = command.titulo,
            modalidad = command.modalidad,
            docente = command.docente,
            aula = command.aula,
            enlace = command.enlace,
            notas = command.notas,
            startDate = command.startDate,
            endDate = command.endDate,
            startTime = command.startTime,
            endTime = command.endTime,
            recurrenceRule = RecurrenceRule(
                type = command.recurrenceRule.type,
                interval = command.recurrenceRule.interval,
                daysOfWeek = command.recurrenceRule.daysOfWeek,
                endType = command.recurrenceRule.endType,
                untilDate = command.recurrenceRule.untilDate,
                occurrenceCount = command.recurrenceRule.occurrenceCount
            ),
            daysOfWeek = command.daysOfWeek,
            parentSeriesId = command.parentSeriesId
        )
        return claseRepository.save(clase)
    }

    fun update(command: UpdateClaseCommand): Clase {
        val existing = claseRepository.findById(command.id)
            ?: throw EntityNotFoundException("Clase", command.id)
        val updated = existing.copy(
            titulo = command.titulo,
            modalidad = command.modalidad,
            docente = command.docente,
            aula = command.aula,
            enlace = command.enlace,
            notas = command.notas,
            startDate = command.startDate,
            endDate = command.endDate,
            startTime = command.startTime,
            endTime = command.endTime,
            recurrenceRule = RecurrenceRule(
                type = command.recurrenceRule.type,
                interval = command.recurrenceRule.interval,
                daysOfWeek = command.recurrenceRule.daysOfWeek,
                endType = command.recurrenceRule.endType,
                untilDate = command.recurrenceRule.untilDate,
                occurrenceCount = command.recurrenceRule.occurrenceCount
            ),
            daysOfWeek = command.daysOfWeek,
            isCancelled = command.isCancelled
        )
        return claseRepository.save(updated)
    }

    @Transactional(readOnly = true)
    fun findByCourse(courseId: Long): List<Clase> = claseRepository.findByCourseId(courseId)

    @Transactional(readOnly = true)
    fun findById(id: Long): Clase = claseRepository.findById(id)
        ?: throw EntityNotFoundException("Clase", id)

    fun delete(id: Long) {
        if (!claseRepository.existsById(id)) throw EntityNotFoundException("Clase", id)
        claseRepository.deleteById(id)
    }
}
