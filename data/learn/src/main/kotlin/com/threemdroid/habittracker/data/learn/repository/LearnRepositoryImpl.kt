package com.threemdroid.habittracker.data.learn.repository

import com.threemdroid.habittracker.core.common.result.AppError
import com.threemdroid.habittracker.core.common.result.AppResult
import com.threemdroid.habittracker.core.model.learn.LearnContent
import com.threemdroid.habittracker.domain.learn.LearnRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class LearnRepositoryImpl : LearnRepository {
    override fun observeLearnContent(): Flow<AppResult<List<LearnContent>>> = flowOf(
        AppResult.Failure(
            AppError.Unavailable(
                message = "Learn content is blocked until a verified Learn contract exists in the repository.",
            ),
        ),
    )
}
