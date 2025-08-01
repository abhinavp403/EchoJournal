package dev.abhinav.echojournal.echos.presentation.models

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import dev.abhinav.echojournal.core.presentation.util.UiText
import dev.abhinav.echojournal.R
import dev.abhinav.echojournal.core.presentation.designsystem.theme.*

enum class MoodUi(
    val iconSet: MoodIconSet,
    val colorSet: MoodColorSet,
    val title: UiText
) {
    STRESSED(
        iconSet = MoodIconSet(
            fill = R.drawable.emoji_stressed,
            outline = R.drawable.emoji_stressed_outline
        ),
        colorSet = MoodColorSet(
            vivid = Stressed80,
            desaturated = Stressed35,
            faded = Stressed25
        ),
        title = UiText.StringResource(R.string.stressed)
    ),
    SAD(
        iconSet = MoodIconSet(
            fill = R.drawable.emoji_sad,
            outline = R.drawable.emoji_sad_outline
        ),
        colorSet = MoodColorSet(
            vivid = Sad80,
            desaturated = Sad35,
            faded = Sad25
        ),
        title = UiText.StringResource(R.string.sad)
    ),
    NEUTRAL(
        iconSet = MoodIconSet(
            fill = R.drawable.emoji_neutral,
            outline = R.drawable.emoji_neutral_outline
        ),
        colorSet = MoodColorSet(
            vivid = Neutral80,
            desaturated = Neutral35,
            faded = Neutral25
        ),
        title = UiText.StringResource(R.string.neutral)
    ),
    PEACEFUL(
        iconSet = MoodIconSet(
            fill = R.drawable.emoji_peaceful,
            outline = R.drawable.emoji_peaceful_outline
        ),
        colorSet = MoodColorSet(
            vivid = Peaceful80,
            desaturated = Peaceful35,
            faded = Peaceful25
        ),
        title = UiText.StringResource(R.string.peaceful)
    ),
    EXCITED(
        iconSet = MoodIconSet(
            fill = R.drawable.emoji_excited,
            outline = R.drawable.emoji_excited_outline
        ),
        colorSet = MoodColorSet(
            vivid = Excited80,
            desaturated = Excited35,
            faded = Excited25
        ),
        title = UiText.StringResource(R.string.excited)
    )
}

data class MoodIconSet(
    @DrawableRes val fill: Int,
    @DrawableRes val outline: Int
)

data class MoodColorSet(
    val vivid: Color,
    val desaturated: Color,
    val faded: Color
)