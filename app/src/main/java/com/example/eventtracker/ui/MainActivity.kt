package com.example.eventtracker.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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

        val adapter = EventAdapter(mutableListOf()) { event ->
            val intent = Intent(this, EditEventActivity::class.java).apply {
                putExtra("eventId", event.id)
            }
            startActivity(intent)
        }
        binding.recyclerView.adapter = adapter

        viewModel.events.observe(this) { eventList ->
            adapter.updateEvents(eventList.toList())
        }

        binding.fabAdd.setOnClickListener {
            val newId = viewModel.events.value?.size ?: 0
            val newEvent = Event(
                id = newId + 1,
                title = "New Event ${newId + 1}",
                date = "2025-01-01",
                location = "Someplace",
                description = "Details about this new event."
            )
            viewModel.addEvent(newEvent)
        }
    }
}
