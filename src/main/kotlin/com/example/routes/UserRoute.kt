package com.example.routes

import com.example.data.local.RepositoryUser
import com.example.data.models.User
import com.example.data.requests.SimpleResponse
import com.example.data.requests.UserRequest
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
fun Route.userRoute(repositoryUser: RepositoryUser){
    route("/get-users"){
        get {
           val users = repositoryUser.getAllUsers()
            call.respond(HttpStatusCode.OK, users)
        }
    }
    route("/add-or-update-user"){
        post {
            val request = try {
                call.receive<User>()
            } catch (e: ContentTransformationException) {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }
            if (repositoryUser.addOrUpdateUser(request)) {
                call.respond(
                    HttpStatusCode.OK,
                    SimpleResponse(true, "User successfully created/ updated", Unit)

                )
            } else {
                call.respond(HttpStatusCode.Conflict)
            }
        }
    }
    route("/delete-user"){
        delete("{userId}") {
            val events = repositoryUser.getAllUsers()
            val eventId = call.parameters["eventId"]
            if (eventId != null) {
                repositoryUser.deleteUser(eventId)
                call.respond(HttpStatusCode.OK, "Event deleted successfully")
            } else {
                call.respond(HttpStatusCode.NotFound, "Event not found")
            }
        }
    }
}