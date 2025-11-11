package com.example.eventtracker.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.eventtracker.data.Event
import com.example.eventtracker.databinding.ActivityEventDetailBinding
import com.example.eventtracker.viewmodel.EventViewModel

class EventDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventDetailBinding
    private val viewModel: EventViewModel by viewModels()
    private var currentEvent: Event? = null

    private val editEventResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            loadEventData()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadEventData()

        binding.btnEdit.setOnClickListener {
            currentEvent?.let {
                val intent = Intent(this, EditEventActivity::class.java).apply {
                    putExtra("eventId", it.id)
                }
                editEventResultLauncher.launch(intent)
            }
        }

        binding.btnDelete.setOnClickListener {
            currentEvent?.let {
                viewModel.deleteEvent(it)
                finish()
            }
        }
    }

    private fun loadEventData() {
        val eventId = intent.getIntExtra("eventId", -1)
        currentEvent = viewModel.getEventById(eventId)

        if (currentEvent == null) {
            Toast.makeText(this, "Event not found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        currentEvent?.let {
            binding.tvDetailTitle.text = it.title
            binding.tvDetailDate.text = it.date
            binding.tvDetailLocation.text = it.location
            binding.tvDetailDescription.text = it.description
        }
    }
}
