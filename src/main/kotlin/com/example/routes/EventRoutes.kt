package com.example.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.eventRoutes(){
    route("/get-events"){
        get {
            call.respondText("In get event route")
        }
    }
    route("/add-event"){
        get {
            call.respondText("In add event route")
        }
    }
    route("/delete-event"){
        get {
            call.respondText("In delete event route")
        }
    }
}