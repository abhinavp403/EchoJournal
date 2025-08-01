package dev.abhinav.echojournal.echos.presentation.echos

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.abhinav.echojournal.R
import dev.abhinav.echojournal.core.presentation.designsystem.theme.bgGradient
import dev.abhinav.echojournal.core.presentation.util.ObserveAsEvents
import dev.abhinav.echojournal.core.presentation.util.isAppInForeground
import dev.abhinav.echojournal.echos.domain.recording.RecordingDetails
import dev.abhinav.echojournal.echos.presentation.echos.components.EchoFilterRow
import dev.abhinav.echojournal.echos.presentation.echos.components.EchoList
import dev.abhinav.echojournal.echos.presentation.echos.components.EchoQuickRecordFloatingActionButton
import dev.abhinav.echojournal.echos.presentation.echos.components.EchoRecordingSheet
import dev.abhinav.echojournal.echos.presentation.echos.components.EchosEmptyBackground
import dev.abhinav.echojournal.echos.presentation.echos.components.EchosTopBar
import dev.abhinav.echojournal.echos.presentation.echos.models.AudioCaptureMethod
import dev.abhinav.echojournal.echos.presentation.echos.models.RecordingState
import org.koin.androidx.compose.koinViewModel

@Composable
fun EchosRoot(
    onNavigateToCreateEcho: (RecordingDetails) -> Unit,
    viewModel: EchosViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val isAppInForeground by isAppInForeground()

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if(isGranted && state.currentCaptureMethod == AudioCaptureMethod.STANDARD) {
            viewModel.onAction(EchosAction.OnAudioPermissionGranted)
        }
    }
    ObserveAsEvents(viewModel.events) { event ->
        when(event) {
            is EchosEvent.RequestAudioPermission -> {
                permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
            }
            is EchosEvent.RecordingTooShort -> {
                Toast.makeText(context, context.getString(R.string.audio_recording_was_too_short), Toast.LENGTH_LONG).show()
            }
            is EchosEvent.OnDoneRecording -> {
                onNavigateToCreateEcho(event.details)
            }
        }
    }

    LaunchedEffect(isAppInForeground, state.recordingState) {
        if(state.recordingState == RecordingState.NORMAL_CAPTURE && !isAppInForeground) {
            viewModel.onAction(EchosAction.OnPauseRecordingClick)
        }
    }

    EchosScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun EchosScreen(
    state: EchosState,
    onAction: (EchosAction) -> Unit,
) {
    val context = LocalContext.current
    Scaffold(
        floatingActionButton = {
            EchoQuickRecordFloatingActionButton(
                onClick = {
                    onAction(EchosAction.OnRecordFabClick)
                },
                isQuickRecording = state.recordingState == RecordingState.QUICK_CAPTURE,
                onLongPressStart = {
                    val hasPermission = ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.RECORD_AUDIO
                    ) == PackageManager.PERMISSION_GRANTED
                    if(hasPermission) {
                        onAction(EchosAction.OnRecordButtonLongClick)
                    } else {
                        onAction(EchosAction.OnRequestPermissionQuickRecording)
                    }
                },
                onLongPressEnd = { cancelledRecording->
                    if(cancelledRecording) {
                        onAction(EchosAction.OnCancelRecording)
                    } else {
                        onAction(EchosAction.OnCompleteRecording)
                    }
                },
            )
        },
        topBar = {
            EchosTopBar(
                onSettingsClick = {
                    onAction(EchosAction.OnSettingsClick)
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = MaterialTheme.colorScheme.bgGradient
                )
                .padding(innerPadding)
        ) {
            EchoFilterRow(
                moodChipContent = state.moodChipContent,
                hasActiveMoodFilters = state.hasActiveMoodFilters,
                selectedEchoFilterChip = state.selectedEchoFilterChip,
                moods = state.moods,
                topicChipTitle = state.topicChipTitle,
                hasActiveTopicFilters = state.hasActiveTopicFilters,
                topics = state.topics,
                onAction = onAction,
                modifier = Modifier
                    .fillMaxWidth()
            )
            when {
                state.isLoadingData -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .wrapContentSize(),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                !state.hasEchosRecorded -> {
                    EchosEmptyBackground(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    )
                }
                else -> {
                    EchoList(
                        sections = state.echoDaySections,
                        onPlayClick = {
                            onAction(EchosAction.OnPlayEchoClick(it))
                        },
                        onPauseClick = {
                            onAction(EchosAction.OnPauseRecordingClick)
                        },
                        onTrackSizeAvailable = { trackSize ->
                            onAction(EchosAction.OnTrackSizeAvailable(trackSize))
                        }
                    )
                }
            }
        }

        if(state.recordingState in listOf(RecordingState.NORMAL_CAPTURE, RecordingState.PAUSED)) {
            EchoRecordingSheet(
                formattedRecordDuration = state.formattedRecordDuration,
                isRecording = state.recordingState == RecordingState.NORMAL_CAPTURE,
                onDismiss = { onAction(EchosAction.OnCancelRecording) },
                onPauseClick = { onAction(EchosAction.OnPauseRecordingClick) },
                onResumeClick = { onAction(EchosAction.OnResumeRecordingClick) },
                onCompleteRecording = { onAction(EchosAction.OnCompleteRecording) },
            )
        }
    }
}