package com.example.lista1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity(){

    private lateinit var btnAnswer1: RadioButton
    private lateinit var btnAnswer2: RadioButton
    private lateinit var btnAnswer3: RadioButton
    private lateinit var btnAnswer4: RadioButton
    private lateinit var btnNext: Button
    private lateinit var questionNum: TextView
    private lateinit var questionTxt: TextView
    private lateinit var qNumDisplay: ProgressBar

    private val questions = arrayOf("Od którego wieku uprawniony jest Polak do głosowania w wyborach do Parlamentu Europejskiego?","Jaki rok oznacza początek XX wieku?",
        "W jakim roku miała miejsce bitwa pod Grunwaldem?","W jakim kraju się znajduje Garnisonsmarch?","Kto jest autorem utworu Pan Tadeusz?",
        "W którym roku został podpisany Traktat Wersalski?","Jak nazywa się kreska w geografii oddzielająca kontynenty?","Jaki jest najwyższy szczyt na świecie?",
        "W jakim roku miał miejsce holokaust?","Jaki jest najdłuższy rzeka na świecie")

    private val options = arrayOf(arrayOf("18 lat","21 lat","16 lat","25 lat"),arrayOf("1800","1900","1801","1899"),arrayOf("1410","1910","1431","1520"),arrayOf("Dania","Niemcy","Szwecja","Finlandia")
    ,arrayOf("Juliusz Słowacki","Adam Mickiewicz","Henryk Sienkiewicz","Władysław Reymont"),arrayOf("1919","1945","1920","1946"),arrayOf("Ocean","Granica Kontynentalna","Kreska Czapekina","Rów Nero"),
        arrayOf("K2","Kanczendzong","Mount Everest","Kangchenjung"),arrayOf("1933-1945","1941-1945","1938-1945","1939-1945"),arrayOf("Nigru","Amazonka","Jangcy","Len"))

    private val correctAnswers = arrayOf(0,1,0,1,1,1,1,2,3,1)

    private var currentQuestionIndex = 0
    private var score = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAnswer1 = findViewById(R.id.btnAnswer1)
        btnAnswer2 = findViewById(R.id.btnAnswer2)
        btnAnswer3 = findViewById(R.id.btnAnswer3)
        btnAnswer4 = findViewById(R.id.btnAnswer4)
        btnNext = findViewById(R.id.btnConfirm)
        questionNum = findViewById(R.id.QuestionNum)
        questionTxt = findViewById(R.id.QuestionTxt)
        qNumDisplay = findViewById(R.id.progressBar)
        qNumDisplay.progress = currentQuestionIndex+1

        displayQuestion()
        btnAnswer1.setOnClickListener {
            btnNext.setOnClickListener{
                checkAnswer(0)
            }
        }
        btnAnswer2.setOnClickListener{
            btnNext.setOnClickListener{
                checkAnswer(1)
            }
        }
        btnAnswer3.setOnClickListener{
            btnNext.setOnClickListener{
                checkAnswer(2)
            }
        }
        btnAnswer4.setOnClickListener{
            btnNext.setOnClickListener{
                checkAnswer(3)
            }
        }
    }

   private fun displayQuestion(){
       questionNum.text="Pytanie ${currentQuestionIndex+1}/${questions.size}"
       questionTxt.text=questions[currentQuestionIndex]
       btnAnswer1.text=options[currentQuestionIndex][0]
       btnAnswer2.text=options[currentQuestionIndex][1]
       btnAnswer3.text=options[currentQuestionIndex][2]
       btnAnswer4.text=options[currentQuestionIndex][3]

   }

    private fun showResults(){
        Toast.makeText(this,"Zdobyłeś $score punktów", Toast.LENGTH_LONG).show()
    }

    private fun checkAnswer(selectedAnswerIndex:Int) {
        val correctAnswerIndex= correctAnswers[currentQuestionIndex]

        if(selectedAnswerIndex == correctAnswerIndex){
            score+=10
        }
        if(currentQuestionIndex<questions.size-1) {
            currentQuestionIndex++
            displayQuestion()
            qNumDisplay.progress = currentQuestionIndex+1
            btnAnswer1.isChecked=false
            btnAnswer2.isChecked=false
            btnAnswer3.isChecked=false
            btnAnswer4.isChecked=false
        }
        else {
            showResults()
        }
    }
}