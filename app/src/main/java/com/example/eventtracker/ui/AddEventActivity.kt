package com.example.eventtracker.ui

import android.os.Bundle

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.eventtracker.data.Event
import com.example.eventtracker.databinding.ActivityAddEventBinding
import com.example.eventtracker.viewmodel.EventViewModel

class AddEventActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEventBinding
    private val viewModel: EventViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            val newEvent = Event(
                id = 0,
                title = binding.etTitle.text.toString(),
                date = binding.etDate.text.toString(),
                location = binding.etLocation.text.toString(),
                description = binding.etDescription.text.toString()
            )
            viewModel.addEvent(newEvent)
            finish()
        }
    }
}
