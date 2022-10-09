package com.example.lesson3

import androidx.lifecycle.ViewModel

private const val TAG = "Quiz"

class QuizViewModel: ViewModel() {
    private val questionBank = listOf(
        Question(R.string.question_1, false),
        Question(R.string.question_2, true),
        Question(R.string.question_3, false),
        Question(R.string.question_4,true),
        Question(R.string.question_5, true),
        Question(R.string.question_6, false),
    )
    var currentIndex = 0
    var isCheater = false

    val currentQuestionAnswer : Boolean
        get() = questionBank[currentIndex].answer
    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    fun moveToNext() {
        currentIndex = (currentIndex + 1 )% questionBank.size
    }

    fun moveToPrev() {
        currentIndex = (currentIndex - 1) % questionBank.size
    }
}