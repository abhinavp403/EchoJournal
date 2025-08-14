package dev.abhinav.echojournal.core.database.echo

import androidx.room.TypeConverter
import dev.abhinav.echojournal.echos.presentation.models.MoodUi

class MoodTypeConverter {

    @TypeConverter
    fun fromMood(moodUi: MoodUi): String {
        return moodUi.name
    }

    @TypeConverter
    fun toMood(moodName: String): MoodUi {
        return MoodUi.valueOf(moodName)
    }
}