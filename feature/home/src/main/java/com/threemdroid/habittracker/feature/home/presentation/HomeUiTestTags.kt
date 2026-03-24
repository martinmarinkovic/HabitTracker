package com.threemdroid.habittracker.feature.home.presentation

internal object HomeUiTestTags {
    const val ROOT = "home_root"
    const val MOOD_BUTTON = "home_mood_button"
    const val RETRY_BUTTON = "home_retry_button"
    const val EMPTY_CREATE_HABIT_BUTTON = "home_empty_create_habit_button"
    const val FLOATING_CREATE_HABIT_BUTTON = "home_floating_create_habit_button"
    const val QUICK_ADD_SHEET = "home_quick_add_sheet"
    const val CONTEXT_SHEET = "home_context_sheet"
    const val LOADING_STATE = "home_loading_state"
    const val EMPTY_STATE = "home_empty_state"
    const val ERROR_STATE = "home_error_state"

    fun dateSelector(dateKey: String): String = "home_date_selector_$dateKey"

    fun habitCard(habitId: String): String = "home_habit_card_$habitId"

    fun habitPrimaryAction(habitId: String): String = "home_habit_primary_action_$habitId"

    fun habitManageAction(habitId: String): String = "home_habit_manage_action_$habitId"
}
