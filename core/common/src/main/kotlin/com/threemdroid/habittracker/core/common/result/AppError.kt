package com.threemdroid.habittracker.core.common.result

sealed interface AppError {
    val message: String?

    data class Validation(
        override val message: String,
    ) : AppError

    data class NotFound(
        override val message: String,
    ) : AppError

    data class Storage(
        val source: String,
        override val message: String? = null,
        val cause: Throwable? = null,
    ) : AppError

    data class Network(
        val code: Int? = null,
        override val message: String? = null,
        val cause: Throwable? = null,
    ) : AppError

    data class Unavailable(
        override val message: String,
    ) : AppError

    data class Unknown(
        override val message: String? = null,
        val cause: Throwable? = null,
    ) : AppError
}
