package com.threemdroid.habittracker.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.threemdroid.habittracker.core.database.model.SettingsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingsDao {
    @Query(
        """
        SELECT * FROM settings
        WHERE settings_id = 0
        LIMIT 1
        """,
    )
    fun observeSettings(): Flow<SettingsEntity?>

    @Upsert
    suspend fun upsertSettings(settings: SettingsEntity)
}
