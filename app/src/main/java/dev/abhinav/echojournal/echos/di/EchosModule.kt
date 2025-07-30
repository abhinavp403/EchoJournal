package dev.abhinav.echojournal.echos.di

import dev.abhinav.echojournal.echos.data.recording.AndroidVoiceRecorder
import dev.abhinav.echojournal.echos.domain.recording.VoiceRecorder
import dev.abhinav.echojournal.echos.presentation.echos.EchosViewModel
import dev.abhinav.echojournal.echos.presentation.create_echo.CreateEchoViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val echoModule = module {
    single {
        AndroidVoiceRecorder(
            context = androidApplication(),
            applicationScope = get()
        )
    } bind VoiceRecorder::class

    viewModelOf(::EchosViewModel)
    viewModelOf(::CreateEchoViewModel)
}