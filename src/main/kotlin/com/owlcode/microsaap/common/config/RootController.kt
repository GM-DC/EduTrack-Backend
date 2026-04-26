package com.owlcode.microsaap.common.config

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Endpoint raíz para healthcheck de Railway.
 * Responde 200 OK inmediatamente sin depender de la base de datos.
 */
@RestController
class RootController {

    @GetMapping("/")
    fun health(): ResponseEntity<Map<String, String>> =
        ResponseEntity.ok(mapOf("status" to "UP", "app" to "microsaap"))
}

