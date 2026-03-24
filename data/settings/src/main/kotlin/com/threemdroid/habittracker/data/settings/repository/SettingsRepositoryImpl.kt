package com.threemdroid.habittracker.data.settings.repository

import com.threemdroid.habittracker.core.common.result.AppError
import com.threemdroid.habittracker.core.common.result.AppResult
import com.threemdroid.habittracker.core.common.result.asAppResult
import com.threemdroid.habittracker.core.common.result.suspendAppResultOf
import com.threemdroid.habittracker.core.database.dao.SettingsDao
import com.threemdroid.habittracker.core.model.settings.SettingsModel
import com.threemdroid.habittracker.data.settings.mapper.toEntity
import com.threemdroid.habittracker.data.settings.mapper.toModel
import com.threemdroid.habittracker.domain.settings.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsRepositoryImpl(
    private val settingsDao: SettingsDao,
) : SettingsRepository {
    override fun observeSettings(): Flow<AppResult<SettingsModel>> =
        settingsDao.observeSettings()
            .map { settingsEntity ->
                settingsEntity?.toModel() ?: SettingsModel(
                    reminderNotificationsEnabled = false,
                )
            }
            .asAppResult { throwable ->
                AppError.Storage(
                    source = "SettingsDao.observeSettings",
                    message = throwable.message,
                    cause = throwable,
                )
            }

    override suspend fun updateSettings(settings: SettingsModel): AppResult<Unit> = suspendAppResultOf(
        mapError = { throwable ->
            AppError.Storage(
                source = "SettingsDao.upsertSettings",
                message = throwable.message,
                cause = throwable,
            )
        },
    ) {
        settingsDao.upsertSettings(settings.toEntity())
    }
}
