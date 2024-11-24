
// utils/PlaybackSpeedManager.kt
package com.example.morsecodetranslator.utils

class PlaybackSpeedManager(private val context: Context) {
    private var listeners = mutableListOf<(Float) -> Unit>()
    
    fun getCurrentSpeed(): Float {
        return SharedPrefs.getPlaybackSpeed(context)
    }
    
    fun setPlaybackSpeed(speed: Float) {
        SharedPrefs.setPlaybackSpeed(context, speed)
        notifyListeners(speed)
    }
    
    fun addListener(listener: (Float) -> Unit) {
        listeners.add(listener)
    }
    
    fun removeListener(listener: (Float) -> Unit) {
        listeners.remove(listener)
    }
    
    private fun notifyListeners(speed: Float) {
        listeners.forEach { it(speed) }
    }
}