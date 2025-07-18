package dev.abhinav.echojournal.echos.presentation.echos

import dev.abhinav.echojournal.R
import dev.abhinav.echojournal.core.presentation.designsystem.dropdowns.Selectable
import dev.abhinav.echojournal.core.presentation.designsystem.dropdowns.Selectable.Companion.asUnselectedItems
import dev.abhinav.echojournal.core.presentation.util.UiText
import dev.abhinav.echojournal.echos.presentation.echos.models.EchoFilterChip
import dev.abhinav.echojournal.echos.presentation.echos.models.MoodChipContent
import dev.abhinav.echojournal.echos.presentation.models.MoodUi

data class EchosState(
    val hasEchosRecorded: Boolean = false,
    val hasActiveTopicFilters: Boolean = false,
    val hasActiveMoodFilters: Boolean = false,
    val isLoadingData: Boolean = false,
    val moods: List<Selectable<MoodUi>> = emptyList(),
    val topics: List<Selectable<String>> = listOf("Love", "Happy", "Work").asUnselectedItems(),
    val moodChipContent: MoodChipContent = MoodChipContent(),
    val selectedEchoFilterChip: EchoFilterChip? = null,
    val topicChipTitle: UiText = UiText.StringResource(R.string.all_topics)
)
