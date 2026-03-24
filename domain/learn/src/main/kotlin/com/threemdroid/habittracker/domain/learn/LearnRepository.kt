package com.threemdroid.habittracker.domain.learn

import com.threemdroid.habittracker.core.common.result.AppResult
import com.threemdroid.habittracker.core.model.learn.LearnContent
import kotlinx.coroutines.flow.Flow

interface LearnRepository {
    fun observeLearnContent(): Flow<AppResult<List<LearnContent>>>
}
