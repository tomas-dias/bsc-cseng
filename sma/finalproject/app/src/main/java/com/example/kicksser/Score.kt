package com.example.kicksser

import java.util.*

data class Score (
    val email: String ?= null,
    val username: String ?= null,
    val score: Int ?= null,
    val date: Date ?= null
)