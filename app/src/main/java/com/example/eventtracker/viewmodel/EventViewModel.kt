package com.example.eventtracker.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.eventtracker.data.EventRepository
import com.example.eventtracker.data.Event



class EventViewModel : ViewModel() {

    // The ViewModel now gets its data from the Repository.
    val events = EventRepository.events

    // These functions now just call the Repository's functions.
    fun addEvent(event: Event) {
        EventRepository.addEvent(event)
    }

    fun updateEvent(updatedEvent: Event) {
        EventRepository.updateEvent(updatedEvent)
    }

    fun deleteEvent(event: Event) {
        EventRepository.deleteEvent(event)
    }

    // Add a function to get a single event
    fun getEventById(id: Int): Event? {
        return EventRepository.findEventById(id)
    }
}