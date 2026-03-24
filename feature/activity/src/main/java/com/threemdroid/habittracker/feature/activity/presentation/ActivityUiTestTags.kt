package com.threemdroid.habittracker.feature.activity.presentation

internal object ActivityUiTestTags {
    const val ROOT = "activity_root"
    const val PERIOD_HEADER = "activity_period_header"
    const val PREVIOUS_PERIOD_BUTTON = "activity_previous_period_button"
    const val NEXT_PERIOD_BUTTON = "activity_next_period_button"
    const val FILTER_BUTTON = "activity_filter_button"
    const val FILTER_SHEET = "activity_filter_sheet"
    const val ANALYTICS_CARD = "activity_analytics_card"
    const val CHART_CARD = "activity_chart_card"
    const val LOADING_CARD = "activity_loading_card"
    const val EMPTY_CARD = "activity_empty_card"
    const val ERROR_CARD = "activity_error_card"
    const val RETRY_BUTTON = "activity_retry_button"

    fun filterOption(habitId: String?): String = "activity_filter_option_${habitId ?: "all"}"
}
