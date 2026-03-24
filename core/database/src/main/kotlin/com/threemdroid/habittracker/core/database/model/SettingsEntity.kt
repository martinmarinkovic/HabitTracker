package com.threemdroid.habittracker.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
data class SettingsEntity(
    @PrimaryKey
    @ColumnInfo(name = "settings_id")
    val settingsId: Int = SETTINGS_SINGLETON_ID,
    @ColumnInfo(name = "reminder_notifications_enabled")
    val reminderNotificationsEnabled: Boolean,
) {
    companion object {
        const val SETTINGS_SINGLETON_ID: Int = 0
    }
}
