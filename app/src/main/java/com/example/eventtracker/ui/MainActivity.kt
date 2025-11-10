package com.example.eventtracker.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eventtracker.data.Event
import com.example.eventtracker.databinding.ActivityMainBinding
import com.example.eventtracker.viewmodel.EventViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: EventViewModel by viewModels()
    private lateinit var adapter: EventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = EventAdapter(
            onClick = { event ->
                val intent = Intent(this, EventDetailActivity::class.java)
                intent.putExtra("eventId", event.id)
                startActivity(intent)
            },
            onEdit = { event ->
                val intent = Intent(this, EditEventActivity::class.java)
                intent.putExtra("eventId", event.id)
                startActivity(intent)
            },
            onDelete = { event ->
                viewModel.deleteEvent(event)
            }
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.events.observe(this) { adapter.submitList(it.toList()) }

        binding.fabAdd.setOnClickListener {
            startActivity(Intent(this, AddEventActivity::class.java))
        }
    }
}
