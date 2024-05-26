package com.eventticketsystem.data.models

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
@Serializable
data class User(
    val name: String,
    val mail: String,
    val datetime: String = LocalDateTime.now().format(formatter).toString(),
    val tickets: MutableList<Ticket>,
//    val quantity: Int,
    @BsonId
    val userId: String = ObjectId().toString()
)