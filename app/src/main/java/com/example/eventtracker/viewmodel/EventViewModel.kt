package com.example.eventtracker.viewmodel

import androidx.lifecycle.ViewModel
import com.example.eventtracker.data.Event
import com.example.eventtracker.data.EventRepository

class EventViewModel : ViewModel() {

    val events = EventRepository.events

    fun addEvent(event: Event) {
        EventRepository.addEvent(event)
    }

    fun updateEvent(updatedEvent: Event) {
        EventRepository.updateEvent(updatedEvent)
    }

    fun getEventById(id: Int): Event? {
        return EventRepository.findEventById(id)
    }
}
