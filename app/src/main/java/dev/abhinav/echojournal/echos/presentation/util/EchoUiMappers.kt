package dev.abhinav.echojournal.echos.presentation.util

import dev.abhinav.echojournal.echos.domain.echo.Echo
import dev.abhinav.echojournal.echos.presentation.echos.models.PlaybackState
import dev.abhinav.echojournal.echos.presentation.models.EchoUi
import dev.abhinav.echojournal.echos.presentation.models.MoodUi
import kotlin.time.Duration

fun Echo.toEchoUi(
    currentPlaybackDuration: Duration = Duration.ZERO,
    playbackState: PlaybackState = PlaybackState.STOPPED
): EchoUi {
    return EchoUi(
        id = id!!,
        title = title,
        mood = MoodUi.valueOf(mood.name),
        recordedAt = recordedAt,
        note = note,
        topics = topics,
        amplitudes = audioAmplitudes,
        playbackTotalDuration = audioPlaybackLength,
        audioFilePath = audioFilePath,
        playbackCurrentDuration = currentPlaybackDuration,
        playbackState = playbackState
    )
}