package com.example.eventtracker.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.eventtracker.data.Event
import com.example.eventtracker.databinding.ActivityEditEventBinding
import com.example.eventtracker.viewmodel.EventViewModel

class EditEventActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditEventBinding

    private val viewModel: EventViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra("eventId", -1)
        val event = viewModel.getEventById(id)

        if (event == null) {
            finish()
            return
        }

        binding.etTitle.setText(event.title)
        binding.etDate.setText(event.date)
        binding.etLocation.setText(event.location)
        binding.etDescription.setText(event.description)

        binding.btnUpdate.setOnClickListener {
            event.title = binding.etTitle.text.toString()
            event.date = binding.etDate.text.toString()
            event.location = binding.etLocation.text.toString()
            event.description = binding.etDescription.text.toString()

            viewModel.updateEvent(event)
            finish()
        }
    }
}
