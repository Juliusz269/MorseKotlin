
// adapters/DictionaryAdapter.kt
package com.example.kotlinmorse.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinmorse.databinding.ItemDictionaryBinding

class DictionaryAdapter(
    private val phrases: List<Pair<String, String>>
) : RecyclerView.Adapter<DictionaryAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemDictionaryBinding) : 
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDictionaryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (phrase, morse) = phrases[position]
        holder.binding.phraseTextView.text = phrase
        holder.binding.morseTextView.text = morse
    }

    override fun getItemCount() = phrases.size
}