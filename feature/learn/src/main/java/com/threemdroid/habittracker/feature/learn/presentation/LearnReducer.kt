package com.threemdroid.habittracker.feature.learn.presentation

import com.threemdroid.habittracker.feature.learn.contract.LearnArticleUiModel
import com.threemdroid.habittracker.feature.learn.contract.LearnScreenState
import com.threemdroid.habittracker.feature.learn.contract.LearnUiState

internal object LearnReducer {
    fun reduce(
        currentState: LearnUiState,
        result: LearnResult,
    ): LearnUiState = when (result) {
        LearnResult.Loading -> LearnUiState(
            screenState = LearnScreenState.Loading,
        )

        is LearnResult.ContentLoaded -> {
            val selectedArticleId = currentState.selectedArticleId.takeIf { currentSelectedArticleId ->
                result.articles.any { article -> article.id == currentSelectedArticleId }
            }
            LearnUiState(
                screenState = if (result.articles.isEmpty()) {
                    LearnScreenState.Empty
                } else {
                    LearnScreenState.Content
                },
                articles = result.articles,
                selectedArticleId = selectedArticleId,
            )
        }

        is LearnResult.LoadFailed -> LearnUiState(
            screenState = LearnScreenState.Error(result.message),
        )

        is LearnResult.ArticleSelectionChanged -> currentState.copy(
            selectedArticleId = result.articleId,
        )
    }
}

internal sealed interface LearnResult {
    data object Loading : LearnResult

    data class ContentLoaded(
        val articles: List<LearnArticleUiModel>,
    ) : LearnResult

    data class LoadFailed(
        val message: String,
    ) : LearnResult

    data class ArticleSelectionChanged(
        val articleId: String?,
    ) : LearnResult
}
