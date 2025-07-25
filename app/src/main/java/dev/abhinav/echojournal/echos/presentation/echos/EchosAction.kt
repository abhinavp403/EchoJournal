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
    data object OnFabClick: EchosAction
    data object OnFabLongClick: EchosAction
    data object OnSettingsClick: EchosAction
    data object OnPauseClick : EchosAction
    data class OnTrackSizeAvailable(val trackSizeInfo: TrackSizeInfo): EchosAction
    data class OnRemoveFilters(val filterType: EchoFilterChip): EchosAction
    data class OnPlayEchoClick(val echoId: Int): EchosAction
    data object OnAudioPermissionGranted: EchosAction
}