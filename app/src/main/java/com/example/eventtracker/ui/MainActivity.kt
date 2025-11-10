package com.example.eventtracker.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.eventtracker.R
import com.example.eventtracker.data.Event
import com.example.eventtracker.databinding.ActivityMainBinding
import com.example.eventtracker.viewmodel.EventViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: EventViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Create the adapter with an empty list first.
        val adapter = EventAdapter(mutableListOf()) { event ->
            // This is the click listener for an event item
            val intent = Intent(this, EditEventActivity::class.java).apply {
                putExtra("eventId", event.id)
            }
            startActivity(intent)
        }
        binding.recyclerView.adapter = adapter

        // 2. Observe the LiveData for changes
        viewModel.events.observe(this) { eventList ->
            // When the event list changes, update the adapter's data
            adapter.updateEvents(eventList)
        }

        binding.fabAdd.setOnClickListener {
            // For testing, let's add a new event
            val newId = viewModel.events.value?.size ?: 0
            val newEvent = Event(
                id = newId + 1,
                title = "New Event ${newId + 1}",
                date = "2025-01-01",
                location = "Someplace",
                description = "Details about the new event."
            )
            viewModel.addEvent(newEvent)
        }
    }

    override fun onResume() {
        super.onResume()
        // Refresh the adapter when returning to the activity
        // This ensures updates from EditEventActivity are shown
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }
}
