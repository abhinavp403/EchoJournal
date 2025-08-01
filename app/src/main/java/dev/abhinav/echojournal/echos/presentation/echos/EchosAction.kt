package dev.abhinav.echojournal.echos.presentation.echos

import dev.abhinav.echojournal.echos.presentation.echos.models.EchoFilterChip
import dev.abhinav.echojournal.echos.presentation.echos.models.TrackSizeInfo
import dev.abhinav.echojournal.echos.presentation.models.MoodUi

sealed interface EchosAction {
    data object OnMoodChipClick: EchosAction
    data object OnDismissMoodDropDown: EchosAction
    data class OnFilterByMoodClick(val moodUi: MoodUi): EchosAction
    data object OnTopicChipClick: EchosAction
    data object OnDismissTopicDropDown: EchosAction
    data class OnFilterByTopicClick(val topic: String): EchosAction
    data object OnRecordFabClick: EchosAction
    data object OnRequestPermissionQuickRecording: EchosAction
    data object OnRecordButtonLongClick: EchosAction
    data object OnSettingsClick: EchosAction
    data object OnPauseClick : EchosAction
    data object OnPauseRecordingClick : EchosAction
    data object OnResumeRecordingClick : EchosAction
    data object OnCompleteRecording : EchosAction
    data object OnPauseAudioClick : EchosAction
    data class OnTrackSizeAvailable(val trackSizeInfo: TrackSizeInfo): EchosAction
    data class OnRemoveFilters(val filterType: EchoFilterChip): EchosAction
    data class OnPlayEchoClick(val echoId: Int): EchosAction
    data object OnAudioPermissionGranted: EchosAction
    data object OnCancelRecording: EchosAction
}