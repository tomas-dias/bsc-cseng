package com.example.kicksser

data class Question (
    val id: Int,
    val question: String,
    val image: Int,
    val image2: Int,
    val image3: Int,
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val optionFour: String,
    val correctAnswer: Int
)