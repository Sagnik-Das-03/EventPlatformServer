package com.eventticketsystem.data.requests

import com.eventticketsystem.data.models.Ticket
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId

@Serializable
data class EventRequest(
    val title: String,
    val description: String,
    val venue: String,
    val dateTime: String,
    val tickets: MutableList<Ticket>,
    val eventId:String= ObjectId().toString()
)
