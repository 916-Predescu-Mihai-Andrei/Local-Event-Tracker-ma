package com.example.eventtracker.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.eventtracker.data.Event
import com.example.eventtracker.databinding.ActivityAddEventBinding
import com.example.eventtracker.viewmodel.EventViewModel
import kotlin.random.Random

class AddEventActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEventBinding
    private val viewModel: EventViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val date = binding.etDate.text.toString()
            val location = binding.etLocation.text.toString()
            val desc = binding.etDescription.text.toString()

            if (title.isEmpty() || date.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                val event = Event(Random.nextInt(), title, date, location, desc)
                viewModel.addEvent(event)
                finish()
            }
        }
    }
}
