package com.eventticketsystem.data.requests

import kotlinx.serialization.Serializable

@Serializable
data class TicketRequest(
    val userId: String,
    val eventId: String,
    val price: Int
)
