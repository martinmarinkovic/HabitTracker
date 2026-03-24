package com.threemdroid.habittracker.data.learn.repository

import com.threemdroid.habittracker.core.common.result.AppError
import com.threemdroid.habittracker.core.common.result.AppResult
import com.threemdroid.habittracker.core.common.result.asAppResult
import com.threemdroid.habittracker.core.model.learn.LearnContent
import com.threemdroid.habittracker.data.learn.mapper.toModel
import com.threemdroid.habittracker.data.learn.remote.LearnHttpException
import com.threemdroid.habittracker.data.learn.remote.LearnRemoteDataSource
import com.threemdroid.habittracker.domain.learn.LearnRepository
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.SerializationException

internal class LearnRepositoryImpl @Inject constructor(
    private val remoteDataSource: LearnRemoteDataSource,
) : LearnRepository {
    override fun observeLearnContent(): Flow<AppResult<List<LearnContent>>> = flow {
        val categories = remoteDataSource.fetchCategories()
        val content = categories.map { category ->
            category.toModel(
                detailItems = remoteDataSource.fetchDetailItems(category.id),
            )
        }

        emit(content)
    }.asAppResult(::toLearnAppError)
}

private fun toLearnAppError(
    throwable: Throwable,
): AppError = when (throwable) {
    is LearnHttpException -> AppError.Network(
        code = throwable.code,
        message = throwable.message,
        cause = throwable,
    )

    is IOException -> AppError.Network(
        message = throwable.message,
        cause = throwable,
    )

    is SerializationException -> AppError.Unknown(
        message = throwable.message,
        cause = throwable,
    )

    else -> AppError.Unknown(
        message = throwable.message,
        cause = throwable,
    )
}
