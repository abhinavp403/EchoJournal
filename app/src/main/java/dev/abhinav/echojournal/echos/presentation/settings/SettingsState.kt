package dev.abhinav.echojournal.echos.presentation.settings

import dev.abhinav.echojournal.echos.presentation.models.MoodUi

data class SettingsState(
    val topics: List<String> = emptyList(),
    val selectedMood: MoodUi? = null,
    val searchText: String = "",
    val suggestedTopics: List<String> = emptyList(),
    val isTopicSuggestionsVisible: Boolean = false,
    val showCreateTopicOption: Boolean = false,
    val isTopicTextInputVisible: Boolean = false
)
