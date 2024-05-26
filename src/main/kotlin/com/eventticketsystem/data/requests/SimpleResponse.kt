package com.eventticketsystem.data.requests

data class SimpleResponse<T>(
    val status: Boolean,
    val message: String,
    val data: T
)