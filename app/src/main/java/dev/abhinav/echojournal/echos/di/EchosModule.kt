package dev.abhinav.echojournal.echos.di

import dev.abhinav.echojournal.echos.data.recording.AndroidVoiceRecorder
import dev.abhinav.echojournal.echos.domain.recording.RecordingStorage
import dev.abhinav.echojournal.echos.domain.recording.VoiceRecorder
import dev.abhinav.echojournal.echos.presentation.echos.EchosViewModel
import dev.abhinav.echojournal.echos.presentation.create_echo.CreateEchoViewModel
import dev.abhinav.echojournal.echos.data.recording.InternalRecordingStorage
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val echoModule = module {
    singleOf(::AndroidVoiceRecorder) bind VoiceRecorder::class
    singleOf(::InternalRecordingStorage) bind RecordingStorage::class

    viewModelOf(::EchosViewModel)
    viewModelOf(::CreateEchoViewModel)
}