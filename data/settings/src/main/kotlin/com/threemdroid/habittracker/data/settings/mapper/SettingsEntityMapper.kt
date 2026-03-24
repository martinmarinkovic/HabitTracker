package com.threemdroid.habittracker.data.settings.mapper

import com.threemdroid.habittracker.core.database.model.SettingsEntity
import com.threemdroid.habittracker.core.model.settings.SettingsModel

internal fun SettingsEntity.toModel(): SettingsModel = SettingsModel(
    reminderNotificationsEnabled = reminderNotificationsEnabled,
)

internal fun SettingsModel.toEntity(): SettingsEntity = SettingsEntity(
    reminderNotificationsEnabled = reminderNotificationsEnabled,
)
