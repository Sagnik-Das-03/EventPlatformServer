package com.example.data.requests

data class SimpleResponse<T>(
    val status: Boolean,
    val message: String,
    val data: T
)