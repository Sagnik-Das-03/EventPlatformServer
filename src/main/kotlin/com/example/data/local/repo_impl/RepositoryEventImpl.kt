package com.example.data.local.repo_impl

import com.example.data.local.RepositoryEvent
import com.example.data.models.Event
import com.example.data.models.User
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class RepositoryEventImpl(
    private val db: CoroutineDatabase
): RepositoryEvent {

    private val events = db.getCollection<Event>()
    override suspend fun getAllEvent(): List<Event> {
        return events.find().ascendingSort(Event::title).toList()
    }

    override suspend fun addEvent(event: Event): Boolean {
        val eventExists = events.findOneById(event.eventId) != null
        return if (eventExists) {
            events.updateOneById(event.eventId, event).wasAcknowledged()
        } else {
            events.insertOne(event).wasAcknowledged()
        }
    }

    override suspend fun deleteEvent(id: String): Boolean {
        val event = events.findOne(Event::eventId eq id)
        event?.let { ev ->
            return events.deleteOneById(ev.eventId).wasAcknowledged()
        } ?: return false
    }

    override suspend fun getEventForId(id: String): Event? {
        return events.findOneById(id)
    }

}