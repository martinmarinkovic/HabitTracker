package com.threemdroid.habittracker.feature.learn.contract

internal data class LearnArticleUiModel(
    val id: String,
    val title: String,
    val imageUrl: String? = null,
    val paragraphs: List<String> = emptyList(),
    val videoUrl: String? = null,
)

internal data class LearnUiState(
    val screenState: LearnScreenState = LearnScreenState.Loading,
    val articles: List<LearnArticleUiModel> = emptyList(),
    val selectedArticleId: String? = null,
) {
    val selectedArticle: LearnArticleUiModel?
        get() = articles.firstOrNull { article -> article.id == selectedArticleId }

    companion object {
        fun initial(): LearnUiState = LearnUiState()
    }
}

internal sealed interface LearnScreenState {
    data object Loading : LearnScreenState

    data object Empty : LearnScreenState

    data object Content : LearnScreenState

    data class Error(
        val message: String,
    ) : LearnScreenState
}

internal sealed interface LearnIntent {
    data class ArticleSelected(
        val articleId: String,
    ) : LearnIntent

    data object DetailBackRequested : LearnIntent

    data object RetryLoad : LearnIntent

    data object VideoPlaybackRequested : LearnIntent
}

internal sealed interface LearnEffect {
    data class OpenVideo(
        val videoUrl: String,
    ) : LearnEffect
}
