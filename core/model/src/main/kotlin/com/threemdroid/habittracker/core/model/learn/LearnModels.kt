package com.threemdroid.habittracker.core.model.learn

data class LearnContent(
    val id: String,
    val title: String,
    val imageUrl: String? = null,
    val sections: List<LearnContentSection> = emptyList(),
    val videoUrl: String? = null,
)

data class LearnContentSection(
    val id: String,
    val title: String,
    val body: String,
)
