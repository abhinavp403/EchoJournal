package dev.abhinav.echojournal.echos.presentation.echos.models

import dev.abhinav.echojournal.core.presentation.util.UiText
import dev.abhinav.echojournal.R

data class MoodChipContent(
    val iconsRes: List<Int> = emptyList(),
    val title: UiText = UiText.StringResource(R.string.all_moods)
)