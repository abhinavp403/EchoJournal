package dev.abhinav.echojournal.echos.presentation.create_echo.components

sealed interface CreateEchoEvent {
    data object FailedToSaveFile: CreateEchoEvent
}