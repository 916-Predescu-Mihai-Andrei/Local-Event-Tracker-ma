package com.example.eventtracker.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
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
            val intent = Intent(this, EventDetailActivity::class.java).apply {
                putExtra("eventId", event.id)
            }
            startActivity(intent)
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.events.observe(this) { eventList ->
            adapter.updateEvents(eventList.toList())
        }

        binding.fabAdd.setOnClickListener {
            startActivity(Intent(this, AddEventActivity::class.java))
        }
    }
}
