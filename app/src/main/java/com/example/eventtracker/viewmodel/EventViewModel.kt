package com.example.eventtracker.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.eventtracker.data.Event

class EventViewModel : ViewModel() {

    // Initialize with an empty list to avoid nulls
    val events = MutableLiveData<MutableList<Event>>(mutableListOf())

    fun addEvent(event: Event) {
        events.value?.add(event)
        // You must re-assign the value to trigger the LiveData observer
        events.value = events.value
    }

    fun updateEvent(updatedEvent: Event) {
        val list = events.value
        list?.let { eventList ->
            val index = eventList.indexOfFirst { it.id == updatedEvent.id }
            if (index != -1) {
                eventList[index] = updatedEvent
                events.value = eventList
            }
        }
    }

    fun deleteEvent(event: Event) {
        events.value?.remove(event)
        events.value = events.value
    }

    fun getEventById(id: Int): Event? {
        return events.value?.find { it.id == id }
    }
}
