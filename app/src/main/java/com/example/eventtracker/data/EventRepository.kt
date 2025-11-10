package com.example.eventtracker.data

import androidx.compose.animation.core.copy

import androidx.lifecycle.MutableLiveData
import kotlin.random.Random

object EventRepository {

    private val eventList = mutableListOf<Event>()
    private val _events = MutableLiveData<List<Event>>()
    val events: MutableLiveData<List<Event>> = _events

    fun addEvent(event: Event) {
        // Assign a truly unique random ID to prevent conflicts
        val newEvent = event.copy(id = Random.nextInt())
        eventList.add(newEvent)
        _events.value = ArrayList(eventList) // Post a copy of the list
    }

    fun updateEvent(updatedEvent: Event) {
        val index = eventList.indexOfFirst { it.id == updatedEvent.id }
        if (index != -1) {
            eventList[index] = updatedEvent
            _events.value = ArrayList(eventList)
        }
    }

    fun findEventById(id: Int): Event? {
        return eventList.find { it.id == id }
    }
}
