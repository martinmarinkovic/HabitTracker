package com.threemdroid.habittracker.feature.create_habit.presentation

import java.time.DayOfWeek
import java.util.Locale

internal object CreateHabitUiTestTags {
    const val ROOT = "create_habit_root"
    const val NAME_FIELD = "create_habit_name_field"
    const val GOAL_CARD = "create_habit_goal_card"
    const val GOAL_SHEET = "create_habit_goal_sheet"
    const val ICON_CARD = "create_habit_icon_card"
    const val COLOR_CARD = "create_habit_color_card"
    const val ICON_PICKER = "create_habit_icon_picker"
    const val COLOR_PICKER = "create_habit_color_picker"
    const val ICON_SEARCH_FIELD = "create_habit_icon_search_field"
    const val COLOR_SEARCH_FIELD = "create_habit_color_search_field"
    const val ADD_HABIT_BUTTON = "create_habit_add_habit_button"
    const val ADD_REMINDER_BUTTON = "create_habit_add_reminder_button"

    fun iconOption(token: String): String = "create_habit_icon_option_$token"

    fun colorOption(token: String): String = "create_habit_color_option_$token"

    fun weekdayChip(dayOfWeek: DayOfWeek): String = "create_habit_weekday_${dayOfWeek.name.lowercase(Locale.US)}"
}
