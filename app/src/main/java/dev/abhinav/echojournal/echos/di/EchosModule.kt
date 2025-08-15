package dev.abhinav.echojournal.echos.di

import dev.abhinav.echojournal.echos.data.recording.AndroidVoiceRecorder
import dev.abhinav.echojournal.echos.domain.recording.RecordingStorage
import dev.abhinav.echojournal.echos.domain.recording.VoiceRecorder
import dev.abhinav.echojournal.echos.presentation.echos.EchosViewModel
import dev.abhinav.echojournal.echos.presentation.create_echo.CreateEchoViewModel
import dev.abhinav.echojournal.echos.data.recording.InternalRecordingStorage
import dev.abhinav.echojournal.echos.domain.audio.AudioPlayer
import dev.abhinav.echojournal.echos.data.audio.AndroidAudioPlayer
import dev.abhinav.echojournal.echos.domain.echo.EchoDataSource
import dev.abhinav.echojournal.echos.data.echo.RoomEchoDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val echoModule = module {
    singleOf(::AndroidVoiceRecorder) bind VoiceRecorder::class
    singleOf(::InternalRecordingStorage) bind RecordingStorage::class
    singleOf(::AndroidAudioPlayer) bind AudioPlayer::class
    singleOf(::RoomEchoDataSource) bind EchoDataSource::class

    viewModelOf(::EchosViewModel)
    viewModelOf(::CreateEchoViewModel)
}