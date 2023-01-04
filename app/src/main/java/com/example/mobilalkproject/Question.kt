package com.example.mobilalkproject

import android.media.Image

data class Question(
    val id: Int,
    val flagImage: Int,
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val optionFour: String,
    val correctAnswer: Int
)
