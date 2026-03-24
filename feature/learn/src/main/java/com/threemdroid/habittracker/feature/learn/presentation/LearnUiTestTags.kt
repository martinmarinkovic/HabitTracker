package com.threemdroid.habittracker.feature.learn.presentation

internal object LearnUiTestTags {
    const val ROOT = "learn_root"
    const val GRID_SCREEN = "learn_grid_screen"
    const val DETAIL_SCREEN = "learn_detail_screen"
    const val LOADING_STATE = "learn_loading_state"
    const val EMPTY_STATE = "learn_empty_state"
    const val ERROR_STATE = "learn_error_state"
    const val RETRY_BUTTON = "learn_retry_button"
    const val DETAIL_BACK_BUTTON = "learn_detail_back_button"
    const val DETAIL_VIDEO_BUTTON = "learn_detail_video_button"

    fun articleCard(articleId: String): String = "learn_article_card_$articleId"
    fun paragraph(paragraphIndex: Int): String = "learn_paragraph_$paragraphIndex"
}
