
// fragments/TranslatorFragment.kt
package com.example.kotlinmorse.fragments

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.example.kotlinmorse.databinding.FragmentTranslatorBinding
import com.example.kotlinmorse.utils.MorseCode
import com.example.kotlinmorse.utils.SharedPrefs
import java.util.*

class TranslatorFragment : Fragment(), TextToSpeech.OnInitListener {
    private var _binding: FragmentTranslatorBinding? = null
    private val binding get() = _binding!!
    private lateinit var tts: TextToSpeech
    private var playbackSpeed = 1.0f
    private var isTtsReady = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTranslatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        tts = TextToSpeech(requireContext(), this)
        playbackSpeed = SharedPrefs.getPlaybackSpeed(requireContext())

        setupViews()
    }

    private fun setupViews() {
        binding.inputEditText.doAfterTextChanged {
            binding.translateButton.isEnabled = !it.isNullOrEmpty()
        }

        binding.translateButton.setOnClickListener {
            translate()
        }

        binding.playButton.setOnClickListener {
            playMorseCode()
        }

        updatePlayButton()
    }

    private fun translate() {
        val input = binding.inputEditText.text?.toString() ?: ""
        val translation = MorseCode.translate(input)
        binding.outputEditText.setText(translation)
        updatePlayButton()
    }

    private fun updatePlayButton() {
        val hasTranslation = binding.outputEditText.text?.isNotEmpty() == true
        binding.playButton.isEnabled = hasTranslation && isTtsReady
        if (hasTranslation) {
            binding.playButton.text = "Play Morse Code (${playbackSpeed}x)"
        }
    }

    private fun playMorseCode() {
        val morseText = binding.outputEditText.text?.toString() ?: return
        if (morseText.isEmpty()) return

        val speakText = morseText
            .replace(".", "kropka ")
            .replace("-", "kreska ")
        
        tts.stop()
        tts.setSpeechRate(playbackSpeed * 0.5f)
        tts.speak(speakText, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts.setLanguage(Locale("pl_PL"))
            isTtsReady = result != TextToSpeech.LANG_MISSING_DATA && 
                         result != TextToSpeech.LANG_NOT_SUPPORTED
            updatePlayButton()
        }
    }

    fun updatePlaybackSpeed(speed: Float) {
        playbackSpeed = speed
        updatePlayButton()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        tts.stop()
        tts.shutdown()
        _binding = null
    }
}
