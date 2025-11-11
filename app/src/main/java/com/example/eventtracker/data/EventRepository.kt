package com.example.eventtracker.data

import androidx.lifecycle.MutableLiveData
import kotlin.random.Random

object EventRepository {
    private val eventList = mutableListOf<Event>()
    private val _events = MutableLiveData<List<Event>>()
    val events: MutableLiveData<List<Event>> = _events

    init {
        // Add sample data
        addEvent(Event(0, "Tech Conference 2025", "2025-12-10", "Convention Center", "An annual conference for developers."))
        addEvent(Event(0, "Local Music Fest", "2025-11-22", "City Park", "Featuring local bands and artists."))
    }

    fun addEvent(event: Event) {
        val newEvent = event.copy(id = Random.nextInt())
        eventList.add(newEvent)
        _events.postValue(ArrayList(eventList))
    }

    fun updateEvent(updatedEvent: Event) {
        val index = eventList.indexOfFirst { it.id == updatedEvent.id }
        if (index != -1) {
            eventList[index] = updatedEvent
            _events.postValue(ArrayList(eventList))
        }
    }

    fun deleteEvent(event: Event) {
        eventList.removeAll { it.id == event.id }
        _events.postValue(ArrayList(eventList))
    }

    fun findEventById(id: Int): Event? {
        return eventList.find { it.id == id }
    }
}
