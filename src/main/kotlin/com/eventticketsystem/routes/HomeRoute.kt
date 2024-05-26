package com.eventticketsystem.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.home(){
    route("home") {
        get {
            call.respondText("Welcome User")
        }
    }
}