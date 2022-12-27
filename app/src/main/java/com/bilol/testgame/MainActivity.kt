package com.bilol.testgame

import android.annotation.SuppressLint
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.Random

class MainActivity : AppCompatActivity() {

    private var answer = 0
    private var trueResult = -1
    var radBtnCheck = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findRandomQuestion()

        btn_check.setOnClickListener {

            if (rad_a.isChecked) radBtnCheck = 0
            if (rad_b.isChecked) radBtnCheck = 1
            if (rad_c.isChecked) radBtnCheck = 2
            if (rad_d.isChecked) radBtnCheck = 3

            if (radBtnCheck == trueResult) {
                val mediaPlayer = MediaPlayer.create(this, R.raw.correct)
                mediaPlayer.start()
                Toast.makeText(this, "To'g'ri javob", Toast.LENGTH_SHORT).show()
            } else {
                val mediaPlayer = MediaPlayer.create(this, R.raw.wrong)
                mediaPlayer.start()
                Toast.makeText(this, "Nato'g'ri javob", Toast.LENGTH_SHORT).show()
            }

            rad_group.clearCheck()
            radBtnCheck = -1
            findRandomQuestion()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun findRandomQuestion() {

        val number1 = Random().nextInt(20)
        val number2 = Random().nextInt(20)

        when (Random().nextInt(4)) {
            0 -> {
                text_question.text = "$number1 + $number2 ="
                answer = number1 + number2
            }
            1 -> {
                if (number1 > number2) {
                    text_question.text = "$number1 - $number2 ="

                    answer = number1 - number2
                } else {
                    findRandomQuestion()
                }
            }
            2 -> {
                text_question.text = "$number1 * $number2 ="
                answer = number1 * number2
            }
            3 -> {
                if (number1 > number2 && number1 % number2 == 0) {
                    text_question.text = "$number1 / $number2 ="
                    answer = number1 / number2
                } else {
                    findRandomQuestion()
                }
            }
        }

        showAnswer()
    }

    private fun showAnswer() {
        val randomNumber1 = Random().nextInt(100)
        val randomNumber2 = Random().nextInt(100)
        val randomNumber3 = Random().nextInt(500)

        trueResult = Random().nextInt(4)
        if (randomNumber1 != randomNumber2 || randomNumber1 != randomNumber3 || randomNumber1 != answer) {
            when (trueResult) {
                0 -> {
                    rad_a.text = answer.toString()
                    rad_b.text = randomNumber3.toString()
                    rad_c.text = randomNumber2.toString()
                    rad_d.text = randomNumber1.toString()

                }
                1 -> {
                    rad_b.text = answer.toString()
                    rad_a.text = randomNumber1.toString()
                    rad_c.text = randomNumber2.toString()
                    rad_d.text = randomNumber3.toString()
                }
                2 -> {
                    rad_c.text = answer.toString()
                    rad_b.text = randomNumber1.toString()
                    rad_a.text = randomNumber3.toString()
                    rad_d.text = randomNumber2.toString()
                }
                3 -> {
                    rad_d.text = answer.toString()
                    rad_b.text = randomNumber2.toString()
                    rad_c.text = randomNumber3.toString()
                    rad_a.text = randomNumber1.toString()
                }
            }
        } else {
            showAnswer()
        }
    }
}