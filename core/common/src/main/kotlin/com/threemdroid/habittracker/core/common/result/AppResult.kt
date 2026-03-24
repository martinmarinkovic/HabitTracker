package com.threemdroid.habittracker.core.common.result

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

sealed interface AppResult<out T> {
    data class Success<T>(
        val value: T,
    ) : AppResult<T>

    data class Failure(
        val error: AppError,
    ) : AppResult<Nothing>
}

inline fun <T, R> AppResult<T>.fold(
    onSuccess: (T) -> R,
    onFailure: (AppError) -> R,
): R = when (this) {
    is AppResult.Success -> onSuccess(value)
    is AppResult.Failure -> onFailure(error)
}

inline fun <T, R> AppResult<T>.map(
    transform: (T) -> R,
): AppResult<R> = when (this) {
    is AppResult.Success -> AppResult.Success(transform(value))
    is AppResult.Failure -> this
}

fun <T> AppResult<T>.getOrNull(): T? = when (this) {
    is AppResult.Success -> value
    is AppResult.Failure -> null
}

fun <T> Flow<T>.asAppResult(
    mapError: (Throwable) -> AppError = { throwable ->
        AppError.Unknown(
            message = throwable.message,
            cause = throwable,
        )
    },
): Flow<AppResult<T>> = map<T, AppResult<T>> { value ->
    AppResult.Success(value)
}.catch { throwable ->
    emit(AppResult.Failure(mapError(throwable)))
}

suspend fun <T> suspendAppResultOf(
    mapError: (Throwable) -> AppError = { throwable ->
        AppError.Unknown(
            message = throwable.message,
            cause = throwable,
        )
    },
    block: suspend () -> T,
): AppResult<T> = try {
    AppResult.Success(block())
} catch (throwable: Throwable) {
    AppResult.Failure(mapError(throwable))
}
