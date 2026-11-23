package com.example.djurvard.data.model

data class CareTask(
    val id: Int,
    val petId: Int,
    val title: String,
    val category: String,
    val isCompleted: Boolean = false
)