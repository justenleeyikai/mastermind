package com.example.mastermind

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.mastermind.databinding.ActivityMainBinding
import com.example.mastermind.viewmodels.MastermindViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mastermindViewModel: MastermindViewModel by viewModels()

    private var answer = "ABA"
    private var attemptedAnswers = ArrayList<String>()
    private var lastThreeAnswers = listOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        answer = intent.extras?.getString(EXTRA_ANSWER) ?: getRandomAnswerString()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.lifecycleOwner = this
        binding.viewModel = mastermindViewModel

        binding.answerTextView.text = answer

        mastermindViewModel.attemptedAnswers.observe(this, Observer {
            refreshGameBoard()
        })

        refreshGameBoard()
    }

    private fun refreshGameBoard() {
        println(" refreshGameBoard()")
        println(" lastThreeAnswers : $lastThreeAnswers")

        binding.attemptsTextView.setText(attemptedAnswers.toString())

        mastermindViewModel.attemptedAnswers.value?.takeLast(3)?.toList()?.let {
            lastThreeAnswers = it
        }

        lastThreeAnswers.let {
            binding.lED1.setBackgroundColor(
                getColorForAnswer(
                    if (lastThreeAnswers.size < 3) Answer.NONE
                    else getColorForAnswerAtPosition(
                        attemptedAnswer = it.getOrElse(0) { NOTHING },
                        answerAtPosition = answer[0].toString()
                    )
                )
            )

            binding.lED2.setBackgroundColor(
                getColorForAnswer(
                    getColorForAnswerAtPosition(
                        attemptedAnswer = it.getOrElse(1) { NOTHING },
                        answerAtPosition = answer[1].toString()
                    )
                )
            )
            binding.lED3.setBackgroundColor(
                getColorForAnswer(
                    getColorForAnswerAtPosition(
                        attemptedAnswer = it.getOrElse(if (lastThreeAnswers.size < 3) 0 else 2) { NOTHING },
                        answerAtPosition = answer[2].toString()
                    )
                )
            )
        }
    }

    private fun getColorForAnswer(answer: Answer): Int {
        return when (answer) {
            Answer.WRONG -> Color.RED
            Answer.WRONGPOSITION -> resources.getColor(R.color.orange)
            Answer.CORRECT -> Color.GREEN
            else -> Color.GRAY
        }
    }

    private fun getColorForAnswerAtPosition(
        attemptedAnswer: String,
        answerAtPosition: String
    ): Answer {
        return when {
            attemptedAnswer == NOTHING -> Answer.NONE
            attemptedAnswer == answerAtPosition -> Answer.CORRECT
            answer.contains(attemptedAnswer) -> Answer.WRONGPOSITION
            !answer.contains(attemptedAnswer) -> Answer.WRONG
            else -> Answer.NONE
        }
    }

    private fun getRandomAnswerString(): String {
        val allowedChars = ('A'..'C')

        return List(3) { allowedChars.random() }.joinToString("")
    }

    companion object {
        private const val NOTHING = "NOTHING"
        private const val A = "A"
        private const val B = "B"
        private const val C = "C"

        private const val EXTRA_ANSWER = "EXTRA_ANSWER"
    }

    enum class Answer {
        WRONG, WRONGPOSITION, CORRECT, NONE
    }
}