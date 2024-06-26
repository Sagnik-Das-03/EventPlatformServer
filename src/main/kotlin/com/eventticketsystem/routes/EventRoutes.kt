package com.eventticketsystem.routes

import com.eventticketsystem.data.local.RepositoryEvent
import com.eventticketsystem.data.models.Event
import com.eventticketsystem.data.requests.SimpleResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.eventRoutes(repositoryEvent: RepositoryEvent){
    route("/events"){
        get {
            val events = repositoryEvent.getAllEvent()
            call.respond(HttpStatusCode.OK, events)
        }
    }
    route("/add-event"){
        post {
            val events = repositoryEvent.getAllEvent()
            val request = try {
                call.receive<Event>()
            } catch (e: ContentTransformationException) {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

            if (repositoryEvent.addEvent(request)) {
                call.respond(
                    HttpStatusCode.OK,
                    SimpleResponse(true, "Event successfully created", Unit)

                )
            } else {
                call.respond(HttpStatusCode.Conflict)
            }
        }
    }
    route("/events-delete"){
        delete("/{eventId}") {
            val events = repositoryEvent.getAllEvent()
            val eventId = call.parameters["eventId"]
            if (eventId != null) {
                repositoryEvent.deleteEvent(eventId)
                call.respond(HttpStatusCode.OK, "Event deleted successfully")
            } else {
                call.respond(HttpStatusCode.NotFound, "Event not found")
            }
        }
    }
}