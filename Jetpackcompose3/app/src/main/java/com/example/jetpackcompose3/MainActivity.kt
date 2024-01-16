package com.example.jetpackcompose3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose3.ui.theme.JetpackCompose3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackCompose3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Quiz()
                }
            }
        }
    }
}

data class Question(val text: String, val options: List<String>, val correctOption: Int)

@Composable
fun Quiz() {
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var pointsCollected by remember{ mutableStateOf(0)}
    var selectedOptionIndex by remember { mutableStateOf<Int?>(null) }
    var progress by remember { mutableStateOf(0.0f) }
    val questions = listOf(
        Question(
            "Od którego wieku uprawniony jest Polak do głosowania w wyborach do Parlamentu Europejskiego?",
            listOf("18 lat","21 lat","16 lat","25 lat"),
            0
        ),
        Question(
            "Jaki rok oznacza początek XX wieku?",
            listOf("1800","1900","1801","1899"),
            1
        ),
        Question(
            "W jakim roku miała miejsce bitwa pod Grunwaldem?",
            listOf("1410","1910","1431","1520"),
            0
        ),
        Question(
            "W jakim kraju się znajduje Garnisonsmarch?",
            listOf("Dania","Niemcy","Szwecja","Finlandia"),
            1
        ),
        Question(
            "Kto jest autorem utworu Pan Tadeusz?",
            listOf("Juliusz Słowacki","Adam Mickiewicz","Henryk Sienkiewicz","Władysław Reymont"),
            1
        ),
        Question(
            "W którym roku został podpisany Traktat Wersalski?",
            listOf("1919","1945","1920","1946"),
            1
        ),
        Question(
            "Jak nazywa się kreska w geografii oddzielająca kontynenty?",
            listOf("Ocean","Granica Kontynentalna","Kreska Czapekina","Rów Nero"),
            1
        ),
        Question(
            "Jaki jest najwyższy szczyt na świecie?",
            listOf("K2","Kanczendzong","Mount Everest","Kangchenjung"),
            2
        ),
        Question(
            "W jakim roku miał miejsce holokaust?",
            listOf("1933-1945","1941-1945","1938-1945","1939-1945"),
            3
        ),
        Question(
            "Jaki jest najdłuższy rzeka na świecie",
            listOf("Nigru","Amazonka","Jangcy","Len"),
            1
        )
    )

    if (currentQuestionIndex < questions.size) {
        val currentQuestion = questions[currentQuestionIndex]
        Bottom
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Question ${currentQuestionIndex + 1}/${questions.size}",
                fontSize = 18.sp
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        text = currentQuestion.text,
                        fontSize = 18.sp
                    )
                }
            }
            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    currentQuestion.options.forEachIndexed { index, option ->
                        QuizOption(
                            text = option,
                            selected = selectedOptionIndex == index,
                            onOptionSelected = {
                                selectedOptionIndex = index
                            }
                        )
                    }
                }
            }

            Button(
                onClick = {
                    if (selectedOptionIndex != null && selectedOptionIndex == currentQuestion.correctOption) {
                        pointsCollected += 10
                    }
                    selectedOptionIndex = null
                    currentQuestionIndex++
                    progress = (currentQuestionIndex.toFloat() / questions.size.toFloat())
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Next Question")
            }
        }
    } else {
        Text("Gratulacje! Uzyskałeś $pointsCollected punktów")
    }
}

@Composable
fun QuizOption(text: String, selected: Boolean, onOptionSelected: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = { onOptionSelected() },
            modifier = Modifier
                .size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .clickable { onOptionSelected() }
        ) {
            Text(
                text = text,
                fontSize = 16.sp
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun QuizPreview() {
    JetpackCompose3Theme {
        Quiz()
    }
}
