package com.example.eventtracker.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.eventtracker.databinding.ActivityEventDetailBinding
import com.example.eventtracker.viewmodel.EventViewModel

class EventDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventDetailBinding
    private val viewModel: EventViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra("eventId", -1)
        val event = viewModel.events.value?.find { it.id == id }

        event?.let {
            binding.tvTitle.text = it.title
            binding.tvDate.text = it.date
            binding.tvLocation.text = it.location
            binding.tvDescription.text = it.description
        }
    }
}
