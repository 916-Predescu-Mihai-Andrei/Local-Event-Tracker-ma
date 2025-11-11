package com.example.eventtracker.ui

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
// Import the CORRECT binding class for the edit screen
import com.example.eventtracker.databinding.ActivityEditEventBinding
import com.example.eventtracker.viewmodel.EventViewModel

class EditEventActivity : AppCompatActivity() {

    // Use the CORRECT binding class
    private lateinit var binding: ActivityEditEventBinding
    private val viewModel: EventViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate the CORRECT layout
        binding = ActivityEditEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val eventId = intent.getIntExtra("eventId", -1)
        val eventToEdit = viewModel.getEventById(eventId)

        if (eventToEdit == null) {
            Toast.makeText(this, "Cannot edit event", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Now the binding will correctly find all the views from activity_edit_event.xml
        binding.etTitle.setText(eventToEdit.title)
        binding.etDate.setText(eventToEdit.date)
        binding.etLocation.setText(eventToEdit.location)
        binding.etDescription.setText(eventToEdit.description)

        // The binding for btnUpdate is correct
        binding.btnUpdate.setOnClickListener {
            val updatedEvent = eventToEdit.copy(
                title = binding.etTitle.text.toString(),
                date = binding.etDate.text.toString(),
                location = binding.etLocation.text.toString(),
                description = binding.etDescription.text.toString()
            )
            viewModel.updateEvent(updatedEvent)
            setResult(Activity.RESULT_OK)
            finish()
        }

        // The binding for btnDelete will now work because it exists in this layout
        binding.btnDelete.setOnClickListener {
            viewModel.deleteEvent(eventToEdit)
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}
