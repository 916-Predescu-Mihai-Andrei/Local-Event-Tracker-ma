package com.example.eventtracker.data

import androidx.lifecycle.MutableLiveData

object EventRepository {

    private val eventList = mutableListOf<Event>()

    val events = MutableLiveData<List<Event>>(eventList)

    fun addEvent(event: Event) {
        eventList.add(event)
        // Post the updated list to any observers.
        events.value = eventList
    }

    fun updateEvent(updatedEvent: Event) {
        val index = eventList.indexOfFirst { it.id == updatedEvent.id }
        if (index != -1) {
            eventList[index] = updatedEvent
            events.value = eventList
        }
    }

    fun deleteEvent(event: Event) {
        eventList.remove(event)
        events.value = eventList
    }

    fun findEventById(id: Int): Event? {
        return eventList.find { it.id == id }
    }
}
