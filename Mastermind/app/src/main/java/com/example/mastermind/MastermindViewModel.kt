package com.example.mastermind.viewmodels

import android.graphics.Color
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Justen Lee on 2021-04-24.
 */
class MastermindViewModel : ViewModel(){

    val answers = ArrayList<String>()
    val attemptedAnswers: MutableLiveData<ArrayList<String>> = MutableLiveData()

    init {
        println("ViewModel() ")
    }
    private fun addAttemptedAnswer(answer : String){
        answers.add(answer)
        attemptedAnswers.value = answers
    }

    fun attemptAnswerA() = addAttemptedAnswer("A")
    fun attemptAnswerB() = addAttemptedAnswer("B")
    fun attemptAnswerC() = addAttemptedAnswer("C")
}