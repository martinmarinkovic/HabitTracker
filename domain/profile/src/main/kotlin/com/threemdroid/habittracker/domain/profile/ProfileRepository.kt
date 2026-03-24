package com.threemdroid.habittracker.domain.profile

import com.threemdroid.habittracker.core.common.result.AppResult
import com.threemdroid.habittracker.core.model.profile.ProfileSummary
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun observeProfileSummary(): Flow<AppResult<ProfileSummary>>
}
