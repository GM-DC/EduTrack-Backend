package com.owlcode.microsaap.common.exception

import com.owlcode.microsaap.common.response.ApiResponse
import com.owlcode.microsaap.common.response.ErrorResponse
import com.owlcode.microsaap.features.auth.domain.exception.*
import org.slf4j.LoggerFactory
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.resource.NoResourceFoundException
import java.time.LocalDateTime

/**
 * Manejador global de excepciones para toda la aplicación
 */
@RestControllerAdvice
class GlobalExceptionHandler {

    private val log = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFound(
        ex: EntityNotFoundException,
        request: WebRequest
    ): ResponseEntity<ApiResponse<Nothing>> {
        val errorResponse = ErrorResponse(
            timestamp = LocalDateTime.now(),
            status = HttpStatus.NOT_FOUND.value(),
            error = "Entity Not Found",
            message = ex.message ?: "Entidad no encontrada",
            path = request.getDescription(false)
        )

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ApiResponse.error(errorResponse))
    }

    @ExceptionHandler(EntityConflictException::class)
    fun handleEntityConflict(
        ex: EntityConflictException,
        request: WebRequest
    ): ResponseEntity<ApiResponse<Nothing>> {
        val errorResponse = ErrorResponse(
            timestamp = LocalDateTime.now(),
            status = HttpStatus.CONFLICT.value(),
            error = "Entity Conflict",
            message = ex.message ?: "Conflicto con datos existentes",
            path = request.getDescription(false)
        )

        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(ApiResponse.error(errorResponse))
    }

    @ExceptionHandler(BusinessValidationException::class)
    fun handleBusinessValidation(
        ex: BusinessValidationException,
        request: WebRequest
    ): ResponseEntity<ApiResponse<Nothing>> {
        val errorResponse = ErrorResponse(
            timestamp = LocalDateTime.now(),
            status = HttpStatus.BAD_REQUEST.value(),
            error = "Business Validation Error",
            message = ex.message ?: "Error de validación de negocio",
            path = request.getDescription(false)
        )

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ApiResponse.error(errorResponse))
    }

    @ExceptionHandler(AuthenticationException::class)
    fun handleAuthentication(
        ex: AuthenticationException,
        request: WebRequest
    ): ResponseEntity<ApiResponse<Nothing>> {
        val errorResponse = ErrorResponse(
            timestamp = LocalDateTime.now(),
            status = HttpStatus.UNAUTHORIZED.value(),
            error = "Authentication Error",
            message = ex.message ?: "Error de autenticación",
            path = request.getDescription(false)
        )

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(ApiResponse.error(errorResponse))
    }

    @ExceptionHandler(AuthorizationException::class)
    fun handleAuthorization(
        ex: AuthorizationException,
        request: WebRequest
    ): ResponseEntity<ApiResponse<Nothing>> {
        val errorResponse = ErrorResponse(
            timestamp = LocalDateTime.now(),
            status = HttpStatus.FORBIDDEN.value(),
            error = "Authorization Error",
            message = ex.message ?: "Error de autorización",
            path = request.getDescription(false)
        )

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
            .body(ApiResponse.error(errorResponse))
    }

    @ExceptionHandler(UserAlreadyExistsException::class)
    fun handleUserAlreadyExists(
        ex: UserAlreadyExistsException,
        request: WebRequest
    ): ResponseEntity<ApiResponse<Nothing>> {
        val errorResponse = ErrorResponse(
            timestamp = LocalDateTime.now(),
            status = HttpStatus.CONFLICT.value(),
            error = "User Already Exists",
            message = ex.message ?: "El usuario ya existe",
            path = request.getDescription(false)
        )

        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(ApiResponse.error(errorResponse))
    }

    @ExceptionHandler(UserNotFoundException::class)
    fun handleUserNotFound(
        ex: UserNotFoundException,
        request: WebRequest
    ): ResponseEntity<ApiResponse<Nothing>> {
        val errorResponse = ErrorResponse(
            timestamp = LocalDateTime.now(),
            status = HttpStatus.NOT_FOUND.value(),
            error = "User Not Found",
            message = ex.message ?: "Usuario no encontrado",
            path = request.getDescription(false)
        )

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ApiResponse.error(errorResponse))
    }

    @ExceptionHandler(InvalidPasswordException::class)
    fun handleInvalidPassword(
        ex: InvalidPasswordException,
        request: WebRequest
    ): ResponseEntity<ApiResponse<Nothing>> {
        val errorResponse = ErrorResponse(
            timestamp = LocalDateTime.now(),
            status = HttpStatus.UNAUTHORIZED.value(),
            error = "Invalid Password",
            message = ex.message ?: "Contraseña inválida",
            path = request.getDescription(false)
        )

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(ApiResponse.error(errorResponse))
    }

    @ExceptionHandler(UserInactiveException::class)
    fun handleUserInactive(
        ex: UserInactiveException,
        request: WebRequest
    ): ResponseEntity<ApiResponse<Nothing>> {
        val errorResponse = ErrorResponse(
            timestamp = LocalDateTime.now(),
            status = HttpStatus.FORBIDDEN.value(),
            error = "User Inactive",
            message = ex.message ?: "Usuario inactivo",
            path = request.getDescription(false)
        )

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
            .body(ApiResponse.error(errorResponse))
    }

    @ExceptionHandler(WeakPasswordException::class)
    fun handleWeakPassword(
        ex: WeakPasswordException,
        request: WebRequest
    ): ResponseEntity<ApiResponse<Nothing>> {
        val errorResponse = ErrorResponse(
            timestamp = LocalDateTime.now(),
            status = HttpStatus.BAD_REQUEST.value(),
            error = "Weak Password",
            message = ex.message ?: "Contraseña débil",
            path = request.getDescription(false)
        )

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ApiResponse.error(errorResponse))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidation(
        ex: MethodArgumentNotValidException,
        request: WebRequest
    ): ResponseEntity<ApiResponse<Nothing>> {
        val errors = ex.bindingResult.allErrors.map { error ->
            when (error) {
                is FieldError -> "${error.field}: ${error.defaultMessage}"
                else -> error.defaultMessage ?: "Error de validación"
            }
        }

        val errorResponse = ErrorResponse(
            timestamp = LocalDateTime.now(),
            status = HttpStatus.BAD_REQUEST.value(),
            error = "Validation Error",
            message = "Errores de validación: ${errors.joinToString(", ")}",
            path = request.getDescription(false)
        )

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ApiResponse.error(errorResponse))
    }

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleDataIntegrity(
        ex: DataIntegrityViolationException,
        request: WebRequest
    ): ResponseEntity<ApiResponse<Nothing>> {
        log.error("DataIntegrityViolationException: ${ex.message}", ex)
        val errorResponse = ErrorResponse(
            timestamp = LocalDateTime.now(),
            status = HttpStatus.CONFLICT.value(),
            error = "Data Integrity Violation",
            message = "Violación de integridad de datos: verifique que el recurso referenciado exista (ej. courseId)",
            path = request.getDescription(false)
        )
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(ApiResponse.error(errorResponse))
    }

    @ExceptionHandler(NoResourceFoundException::class)
    fun handleNoResourceFound(
        ex: NoResourceFoundException,
        request: WebRequest
    ): ResponseEntity<ApiResponse<Nothing>> {
        log.warn("NoResourceFoundException: ${ex.message}")
        val errorResponse = ErrorResponse(
            timestamp = LocalDateTime.now(),
            status = HttpStatus.NOT_FOUND.value(),
            error = "Not Found",
            message = "El endpoint '${ex.resourcePath}' no existe. Revisa la URL y el método HTTP.",
            path = request.getDescription(false)
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ApiResponse.error(errorResponse))
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun handleMethodNotSupported(
        ex: HttpRequestMethodNotSupportedException,
        request: WebRequest
    ): ResponseEntity<ApiResponse<Nothing>> {
        log.warn("HttpRequestMethodNotSupportedException: ${ex.message}")
        val allowed = ex.supportedHttpMethods?.joinToString(", ") ?: "desconocido"
        val errorResponse = ErrorResponse(
            timestamp = LocalDateTime.now(),
            status = HttpStatus.METHOD_NOT_ALLOWED.value(),
            error = "Method Not Allowed",
            message = "El método '${ex.method}' no está permitido para esta ruta. Métodos permitidos: $allowed",
            path = request.getDescription(false)
        )
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
            .body(ApiResponse.error(errorResponse))
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleNotReadable(
        ex: HttpMessageNotReadableException,
        request: WebRequest
    ): ResponseEntity<ApiResponse<Nothing>> {
        log.warn("HttpMessageNotReadableException: ${ex.message}")
        val errorResponse = ErrorResponse(
            timestamp = LocalDateTime.now(),
            status = HttpStatus.BAD_REQUEST.value(),
            error = "Bad Request",
            message = "JSON inválido o valor de enum no reconocido. Verifica los valores permitidos.",
            path = request.getDescription(false)
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ApiResponse.error(errorResponse))
    }

    @ExceptionHandler(IllegalStateException::class)
    fun handleIllegalState(
        ex: IllegalStateException,
        request: WebRequest
    ): ResponseEntity<ApiResponse<Nothing>> {
        log.warn("IllegalStateException: ${ex.message}")
        val errorResponse = ErrorResponse(
            timestamp = LocalDateTime.now(),
            status = HttpStatus.NOT_FOUND.value(),
            error = "Not Found",
            message = ex.message ?: "Recurso no encontrado",
            path = request.getDescription(false)
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ApiResponse.error(errorResponse))
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgument(
        ex: IllegalArgumentException,
        request: WebRequest
    ): ResponseEntity<ApiResponse<Nothing>> {
        log.warn("IllegalArgumentException: ${ex.message}")
        val errorResponse = ErrorResponse(
            timestamp = LocalDateTime.now(),
            status = HttpStatus.BAD_REQUEST.value(),
            error = "Bad Request",
            message = ex.message ?: "Argumento inválido",
            path = request.getDescription(false)
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ApiResponse.error(errorResponse))
    }

    @ExceptionHandler(Exception::class)
    fun handleGeneral(
        ex: Exception,
        request: WebRequest
    ): ResponseEntity<ApiResponse<Nothing>> {
        log.error("Unhandled exception [${ex::class.simpleName}]: ${ex.message}", ex)
        val errorResponse = ErrorResponse(
            timestamp = LocalDateTime.now(),
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error = "Internal Server Error",
            message = "Error interno del servidor",
            path = request.getDescription(false)
        )
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ApiResponse.error(errorResponse))
    }
}
