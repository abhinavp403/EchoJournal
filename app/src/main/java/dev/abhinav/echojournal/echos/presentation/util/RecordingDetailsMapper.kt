package dev.abhinav.echojournal.echos.presentation.util

import dev.abhinav.echojournal.echos.domain.recording.RecordingDetails
import dev.abhinav.echojournal.navigation.NavigationRoute
import kotlin.time.Duration.Companion.milliseconds

fun RecordingDetails.toCreateEchoRoute(): NavigationRoute.CreateEcho {
    return NavigationRoute.CreateEcho(
        recordingPath = this.filePath ?: throw IllegalArgumentException(
            "Recording path can't be null."
        ),
        duration = this.duration.inWholeMilliseconds,
        amplitudes = this.amplitudes.joinToString(";")
    )
}

fun NavigationRoute.CreateEcho.toRecordingDetails(): RecordingDetails {
    return RecordingDetails(
        duration = this.duration.milliseconds,
        amplitudes = this.amplitudes.split(";").map { it.toFloat() },
        filePath = recordingPath
    )
}