package dev.abhinav.echojournal.echos.presentation.echos.models

import dev.abhinav.echojournal.core.presentation.util.UiText
import dev.abhinav.echojournal.echos.presentation.models.EchoUi

data class EchoDaySection(
    val dateHeader: UiText,
    val echos: List<EchoUi>
)
