
// fragments/SettingsFragment.kt
package com.example.kotlinmorse.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlinmorse.databinding.FragmentSettingsBinding
import com.example.kotlinmorse.utils.SharedPrefs
import com.google.android.material.slider.Slider

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val currentSpeed = SharedPrefs.getPlaybackSpeed(requireContext())
        binding.speedSlider.value = currentSpeed
        updateSpeedText(currentSpeed)

        binding.speedSlider.addOnChangeListener { _, value, _ ->
            updateSpeedText(value)
            SharedPrefs.setPlaybackSpeed(requireContext(), value)
            (parentFragmentManager.fragments.find { it is TranslatorFragment } as? TranslatorFragment)
                ?.updatePlaybackSpeed(value)
        }
    }

    private fun updateSpeedText(speed: Float) {
        binding.speedTextView.text = String.format("Playback Speed: %.1fx", speed)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
