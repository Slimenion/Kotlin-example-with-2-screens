package com.example.lesson3

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.TextView

private const val EXTRA_ANSWER_IS_TRUE = "com.example.lesson3.answer_is_true"
const val EXTRA_ANSWER_SHOWN = "com.example.lesson3.answer_shown"

class CheatActivity : AppCompatActivity() {
    private var answerIsTrue = false
    private  lateinit var  showAnswerButton: Button
    private lateinit var answerTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        answerIsTrue = intent?.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)?:false
        setContentView(R.layout.activity_cheat)
        answerTextView = findViewById(R.id.text_answer)
        showAnswerButton = findViewById(R.id.show_answer_button)

        val answerText = when {
            answerIsTrue -> R.string.true_button
            else -> R.string.false_button
        }

        answerTextView.setText(answerText)
        showAnswerButton.setOnClickListener {
            answerTextView.visibility = View.VISIBLE
            setAnswerShownResult(true)
        }

        if (savedInstanceState!=null) {
            answerIsTrue = savedInstanceState.getBoolean(EXTRA_ANSWER_IS_TRUE)
            val sab=savedInstanceState.getBoolean(EXTRA_ANSWER_SHOWN)
            if (sab) {
                showAnswerButton.callOnClick()
            }
        }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putBoolean(EXTRA_ANSWER_SHOWN, answerTextView.visibility == View.VISIBLE)
        savedInstanceState.putBoolean(EXTRA_ANSWER_IS_TRUE, answerIsTrue)

    }

    private fun setAnswerShownResult(isAnswerShown: Boolean) {
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        }
        setResult(Activity.RESULT_OK, data)
    }
    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }
}