package com.example.eventtracker.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eventtracker.data.Event
import com.example.eventtracker.databinding.ItemEventBinding

class EventAdapter(
    private val onClick: (Event) -> Unit,
    private val onEdit: (Event) -> Unit,
    private val onDelete: (Event) -> Unit
) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    private var events = listOf<Event>()

    inner class EventViewHolder(val binding: ItemEventBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]
        holder.binding.tvTitle.text = event.title
        holder.binding.tvDate.text = event.date

        holder.binding.root.setOnClickListener { onClick(event) }
        holder.binding.btnEdit.setOnClickListener { onEdit(event) }
        holder.binding.btnDelete.setOnClickListener { onDelete(event) }
    }

    override fun getItemCount(): Int = events.size

    fun submitList(list: List<Event>) {
        events = list
        notifyDataSetChanged()
    }
}
