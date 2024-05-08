package com.example.routes

import com.example.data.local.RepositoryEvent
import com.example.data.local.RepositoryUser
import com.example.data.models.Ticket
import com.example.data.requests.TicketRequest
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.ticketRoute(repositoryEvent: RepositoryEvent,  repositoryUser: RepositoryUser){
    route("/ticket/buy"){
        post {
            val ticketRequest = call.receive<TicketRequest>()
            val events = repositoryEvent.getAllEvent()
            val users  = repositoryUser.getAllUsers()

            val event = events.find {
                it.eventId == ticketRequest.eventId
            }
            if (event != null) {
                println(event.eventId)
            }
            if(event != null){
                val purchasedTicket = event.tickets.firstOrNull { it.price == ticketRequest.price }
                if (purchasedTicket != null) {
                    val user = users.find { it.userId == ticketRequest.userId }
                    if (user != null) {
                        val bookedTicket = Ticket(
                            name = "${event.title}(${purchasedTicket.name})",
                            venue = purchasedTicket.venue,
                            qrCode = event.eventId,
                            couponCode = purchasedTicket.couponCode,
                            datetime = purchasedTicket.datetime,
                            price = purchasedTicket.price,
                            transactionId = purchasedTicket.transactionId
                        )
                        // Transfer the ticket from the event to the user
                        user.tickets.add(bookedTicket)
                        repositoryUser.addOrUpdateUser(user)
                        call.respond(HttpStatusCode.OK, bookedTicket)
                    } else {
                        call.respond(HttpStatusCode.BadRequest, "User not found")
                    }
                } else {
                    call.respond(HttpStatusCode.BadRequest, "Ticket not found")
                }
            } else {
                call.respond(HttpStatusCode.BadRequest, "Event not found")
            }
        }
    }

    route("/ticket-get/{userId}"){
        get {
            val users = repositoryUser.getAllUsers()
            val userId = call.parameters["userId"]
            val user = users.find { it.userId == userId }

            if (user != null) {
                call.respond(HttpStatusCode.OK, user.tickets)
            } else {
                call.respond(HttpStatusCode.BadRequest, "User not found")
            }
        }
    }
}
