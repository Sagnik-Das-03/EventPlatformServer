package com.eventticketsystem.data.models

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
@Serializable
data class Ticket(
    val name: String,
    val venue: String,
    val datetime: String = LocalDateTime.now().format(formatter).toString(),
    val qrCode: String,
    val couponCode: String,
    val price: Int,
    @BsonId
    val transactionId: String = ObjectId().toString()
)