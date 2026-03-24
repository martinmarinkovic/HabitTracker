package com.threemdroid.habittracker.feature.learn.presentation

import com.threemdroid.habittracker.feature.learn.contract.LearnArticleUiModel
import com.threemdroid.habittracker.feature.learn.contract.LearnScreenState
import com.threemdroid.habittracker.feature.learn.contract.LearnUiState

internal object LearnPreviewData {
    private val sampleArticles = listOf(
        LearnArticleUiModel(
            id = "learn-habits",
            title = "Why Tiny Habits Compound",
            imageUrl = "https://example.com/learn-habits.jpg",
            paragraphs = listOf(
                "Small repeated actions become easier to sustain than large one-off efforts.",
                "Consistency matters more than intensity when the goal is long-term habit formation.",
            ),
        ),
        LearnArticleUiModel(
            id = "learn-mood",
            title = "Tracking Mood Without Overthinking It",
            imageUrl = "https://example.com/learn-mood.jpg",
            paragraphs = listOf(
                "A lightweight daily check-in is often enough to reveal useful patterns over time.",
            ),
            videoUrl = "https://example.com/learn-mood.mp4",
        ),
    )

    fun gridState(): LearnUiState = LearnUiState(
        screenState = LearnScreenState.Content,
        articles = sampleArticles,
    )

    fun loadingState(): LearnUiState = LearnUiState(
        screenState = LearnScreenState.Loading,
    )

    fun emptyState(): LearnUiState = LearnUiState(
        screenState = LearnScreenState.Empty,
    )

    fun errorState(): LearnUiState = LearnUiState(
        screenState = LearnScreenState.Error(
            message = "Learn content is blocked until a verified Learn contract exists in the repository.",
        ),
    )

    fun detailState(): LearnUiState = LearnUiState(
        screenState = LearnScreenState.Content,
        articles = sampleArticles,
        selectedArticleId = sampleArticles.first().id,
    )
}
