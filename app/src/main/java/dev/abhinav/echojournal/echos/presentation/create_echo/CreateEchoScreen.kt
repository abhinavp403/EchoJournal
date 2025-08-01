@file:OptIn(ExperimentalMaterial3Api::class)

package dev.abhinav.echojournal.echos.presentation.create_echo

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.abhinav.echojournal.R
import dev.abhinav.echojournal.core.presentation.designsystem.buttons.PrimaryButton
import dev.abhinav.echojournal.core.presentation.designsystem.buttons.SecondaryButton
import dev.abhinav.echojournal.core.presentation.designsystem.text_fields.TransparentHintTextField
import dev.abhinav.echojournal.core.presentation.designsystem.theme.secondary70
import dev.abhinav.echojournal.core.presentation.designsystem.theme.secondary95
import dev.abhinav.echojournal.echos.presentation.components.EchoMoodPlayer
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateEchoRoot(
    viewModel: CreateEchoViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    CreateEchoScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun CreateEchoScreen(
    state: CreateEchoState,
    onAction: (CreateEchoAction) -> Unit,
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.new_entry),
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onAction(CreateEchoAction.OnNavigateBackClick)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = stringResource(R.string.navigate_back)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        val descriptionFocusRequester = remember {
            FocusRequester()
        }
        val focusManager = LocalFocusManager.current
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if(state.mood == null) {
                    FilledIconButton(
                        onClick = {
                            onAction(CreateEchoAction.OnSelectMoodClick)
                        },
                        colors = IconButtonDefaults.filledIconButtonColors(
                            containerColor = MaterialTheme.colorScheme.secondary95,
                            contentColor = MaterialTheme.colorScheme.secondary70
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = stringResource(R.string.add_mood)
                        )
                    }
                } else {
                    Image(
                        imageVector = ImageVector.vectorResource(state.mood.iconSet.fill),
                        contentDescription = state.mood.title.asString(),
                        modifier = Modifier
                            .height(32.dp)
                            .clickable {
                                onAction(CreateEchoAction.OnSelectMoodClick)
                            },
                        contentScale = ContentScale.FillHeight
                    )
                }

                TransparentHintTextField(
                    text = state.titleText,
                    onValueChange = {
                        onAction(CreateEchoAction.OnTitleTextChange(it))
                    },
                    modifier = Modifier
                        .weight(1f),
                    hintText = stringResource(R.string.add_title),
                    textStyle = MaterialTheme.typography.headlineLarge.copy(
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            descriptionFocusRequester.requestFocus()
                        }
                    )
                )
            }

            EchoMoodPlayer(
                moodUi = state.mood,
                playbackState = state.playbackState,
                playerProgress = { state.durationPlayedRatio },
                durationPlayed = state.durationPlayed,
                totalPlaybackDuration = state.playbackTotalDuration,
                powerRatios = state.playbackAmplitudes,
                onPlayClick = {
                    onAction(CreateEchoAction.OnPlayAudioClick)
                },
                onPauseClick = {
                    onAction(CreateEchoAction.OnPauseAudioClick)
                },
                onTrackSizeAvailable = {
                    onAction(CreateEchoAction.OnTrackSizeAvailable(it))
                }
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Create,
                    contentDescription = stringResource(R.string.add_description),
                    tint = MaterialTheme.colorScheme.outlineVariant,
                    modifier = Modifier.size(16.dp)
                )
                TransparentHintTextField(
                    text = state.noteText,
                    onValueChange = {
                        onAction(CreateEchoAction.OnNoteTextChange(it))
                    },
                    modifier = Modifier
                        .weight(1f)
                        .focusRequester(descriptionFocusRequester),
                    hintText = stringResource(R.string.add_description),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    )
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SecondaryButton(
                    text = stringResource(R.string.cancel),
                    onClick = {
                        onAction(CreateEchoAction.OnCancelClick)
                    },
                    modifier = Modifier
                        .fillMaxHeight()
                )
                PrimaryButton(
                    text = stringResource(R.string.save),
                    onClick = {
                        onAction(CreateEchoAction.OnSaveClick)
                    },
                    modifier = Modifier.weight(1f),
                    enabled = state.canSaveEcho,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = stringResource(R.string.save),
                            modifier = Modifier
                                .size(16.dp)
                        )
                    }
                )
            }
        }
    }
}