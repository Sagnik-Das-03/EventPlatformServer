package com.eventticketsystem.data.models

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class Event(
    val title: String,
    val description: String,
    val venue: String,
    val dateTime: String,
    val tickets: MutableList<Ticket>,
//    val noOfTickets: Int,
    @BsonId
    val eventId: String = ObjectId().toString()
)

