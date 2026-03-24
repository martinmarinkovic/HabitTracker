package com.threemdroid.habittracker.domain.settings

import com.threemdroid.habittracker.core.common.result.AppResult
import com.threemdroid.habittracker.core.model.settings.SettingsModel
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun observeSettings(): Flow<AppResult<SettingsModel>>

    suspend fun updateSettings(settings: SettingsModel): AppResult<Unit>
}
