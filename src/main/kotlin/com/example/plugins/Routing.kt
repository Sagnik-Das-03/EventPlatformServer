package com.example.plugins

import com.example.routes.eventRoutes
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        eventRoutes()
        get("/") {
            call.respondText("Hello from Event-Server!")
        }
    }
}

