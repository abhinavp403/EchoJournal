package dev.abhinav.echojournal.echos.presentation.create_echo

import dev.abhinav.echojournal.echos.presentation.echos.models.PlaybackState
import dev.abhinav.echojournal.echos.presentation.models.MoodUi
import kotlin.time.Duration

data class CreateEchoState(
    val titleText: String = "",
    val addTopicText: String = "",
    val noteText: String = "",
    val showMoodSelector: Boolean = true,
    val selectedMood: MoodUi = MoodUi.NEUTRAL,
    val showTopicSuggestions: Boolean = false,
    val mood: MoodUi? = null,
    val searchResults: List<String> = emptyList(),
    val showCreateTopicOption: Boolean = false,
    val canSaveEcho: Boolean = false,
    val playbackAmplitudes: List<Float> = List(32) { 0.3f},
    val playbackTotalDuration: Duration = Duration.ZERO,
    val playbackState: PlaybackState = PlaybackState.STOPPED,
    val durationPlayed: Duration = Duration.ZERO
) {
    val durationPlayedRatio = (durationPlayed / playbackTotalDuration).toFloat()
}
