package com.threemdroid.habittracker.feature.learn.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.threemdroid.habittracker.core.common.result.AppError
import com.threemdroid.habittracker.core.common.result.AppResult
import com.threemdroid.habittracker.feature.learn.contract.LearnEffect
import com.threemdroid.habittracker.feature.learn.contract.LearnIntent
import com.threemdroid.habittracker.feature.learn.contract.LearnUiState
import com.threemdroid.habittracker.feature.learn.domain.ObserveLearnContentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
internal class LearnViewModel @Inject constructor(
    private val observeLearnContentUseCase: ObserveLearnContentUseCase,
) : ViewModel() {
    private val mutableUiState = MutableStateFlow(LearnUiState.initial())
    val uiState: StateFlow<LearnUiState> = mutableUiState.asStateFlow()

    private val effectsChannel = Channel<LearnEffect>(capacity = Channel.BUFFERED)
    val effects = effectsChannel.receiveAsFlow()

    private var observeJob: Job? = null

    init {
        loadContent()
    }

    fun handleIntent(intent: LearnIntent) {
        when (intent) {
            is LearnIntent.ArticleSelected -> selectArticle(intent.articleId)
            LearnIntent.DetailBackRequested -> dismissDetail()
            LearnIntent.RetryLoad -> loadContent()
            LearnIntent.VideoPlaybackRequested -> emitVideoEffectIfAvailable()
        }
    }

    private fun selectArticle(articleId: String) {
        if (mutableUiState.value.articles.none { article -> article.id == articleId }) {
            return
        }
        reduce(LearnResult.ArticleSelectionChanged(articleId))
    }

    private fun dismissDetail() {
        if (mutableUiState.value.selectedArticleId == null) {
            return
        }
        reduce(LearnResult.ArticleSelectionChanged(null))
    }

    private fun emitVideoEffectIfAvailable() {
        val videoUrl = mutableUiState.value.selectedArticle?.videoUrl ?: return
        viewModelScope.launch {
            effectsChannel.send(LearnEffect.OpenVideo(videoUrl))
        }
    }

    private fun loadContent() {
        observeJob?.cancel()
        reduce(LearnResult.Loading)
        observeJob = viewModelScope.launch {
            observeLearnContentUseCase().collect { result ->
                when (result) {
                    is AppResult.Success -> reduce(LearnResult.ContentLoaded(result.value))
                    is AppResult.Failure -> reduce(
                        LearnResult.LoadFailed(result.error.message()),
                    )
                }
            }
        }
    }

    private fun reduce(result: LearnResult) {
        mutableUiState.value = LearnReducer.reduce(
            currentState = mutableUiState.value,
            result = result,
        )
    }
}

private fun AppError.message(): String = message ?: when (this) {
    is AppError.Network -> "Learn is unavailable right now."
    is AppError.NotFound -> "Learn content could not be found."
    is AppError.Storage -> "Learn content could not be loaded."
    is AppError.Unavailable -> "Learn is currently unavailable."
    is AppError.Unknown -> "Something went wrong while loading Learn."
    is AppError.Validation -> this.message
}
