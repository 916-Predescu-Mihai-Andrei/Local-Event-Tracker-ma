package com.example.eventtracker.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eventtracker.data.Event
import com.example.eventtracker.databinding.ItemEventBinding

class EventAdapter(
    private var events: MutableList<Event>,
    private val onItemClick: (Event) -> Unit // Lambda for handling clicks
) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    inner class EventViewHolder(val binding: ItemEventBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]
        holder.binding.tvTitle.text = event.title
        holder.binding.tvDate.text = event.date

        // Set the click listener for the whole item view
        holder.itemView.setOnClickListener {
            onItemClick(event)
        }
    }

    override fun getItemCount(): Int {
        return events.size
    }

    // Add this function to update the list and refresh the RecyclerView
    fun updateEvents(newEvents: List<Event>) {
        events.clear()
        events.addAll(newEvents)
        notifyDataSetChanged() // This tells the adapter to redraw the list
    }
}
