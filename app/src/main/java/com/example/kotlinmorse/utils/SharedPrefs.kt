
// utils/SharedPrefs.kt
package com.example.kotlinmorse.utils

import android.content.Context

object SharedPrefs {
    private const val PREFS_NAME = "MorseCodePrefs"
    private const val KEY_PLAYBACK_SPEED = "playback_speed"

    fun getPlaybackSpeed(context: Context): Float {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getFloat(KEY_PLAYBACK_SPEED, 1.0f)
    }

    fun setPlaybackSpeed(context: Context, speed: Float) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putFloat(KEY_PLAYBACK_SPEED, speed)
            .apply()
    }
}