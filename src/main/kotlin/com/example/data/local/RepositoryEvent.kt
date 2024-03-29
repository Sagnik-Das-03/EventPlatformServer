package com.example.data.local

import com.example.data.models.Event

interface RepositoryEvent {
    suspend fun getAllEvent(): List<Event>
    suspend fun addEvent(event: Event): Boolean
    suspend fun deleteEvent(id: String) : Boolean
    suspend fun getEventForId(id: String) : Event?

}