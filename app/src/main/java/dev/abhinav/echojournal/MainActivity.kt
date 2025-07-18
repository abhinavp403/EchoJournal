package dev.abhinav.echojournal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.abhinav.echojournal.core.presentation.designsystem.theme.EchoJournalTheme
import dev.abhinav.echojournal.echos.presentation.echos.EchosRoot
import dev.abhinav.echojournal.echos.presentation.echos.components.EchoExpandableText

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EchoJournalTheme {
                EchoExpandableText(
                    text = buildString{
                        repeat(200) {
                            append("Hello")
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EchoJournalTheme {
        Greeting("Android")
    }
}