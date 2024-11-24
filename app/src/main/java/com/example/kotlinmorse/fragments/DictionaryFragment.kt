
// fragments/DictionaryFragment.kt
package com.example.kotlinmorse.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinmorse.adapters.DictionaryAdapter
import com.example.kotlinmorse.databinding.FragmentDictionaryBinding
import com.example.kotlinmorse.utils.MorseCode

class DictionaryFragment : Fragment() {
    private var _binding: FragmentDictionaryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDictionaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = DictionaryAdapter(MorseCode.COMMON_PHRASES)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}