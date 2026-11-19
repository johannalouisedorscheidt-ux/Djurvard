package com.example.djurvard.data.model

data class DjurUppgift(
    val id: Int,
    val namn: String,
    val kategori: String,
    val djurIds: List<Int> = emptyList(),
    val klar: Boolean = false
)