package com.threemdroid.habittracker.data.learn.remote

import com.threemdroid.habittracker.data.learn.mapper.LearnRemoteBaseUrl
import com.threemdroid.habittracker.data.learn.model.LearnCategoryDto
import com.threemdroid.habittracker.data.learn.model.LearnDetailItemDto
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

internal interface LearnRemoteDataSource {
    suspend fun fetchCategories(): List<LearnCategoryDto>

    suspend fun fetchDetailItems(categoryId: String): List<LearnDetailItemDto>
}

internal class HttpLearnRemoteDataSource @Inject constructor() : LearnRemoteDataSource {
    private val client = OkHttpClient()
    private val json = Json {
        ignoreUnknownKeys = true
    }

    override suspend fun fetchCategories(): List<LearnCategoryDto> = fetch(
        url = "$LearnRemoteBaseUrl/index.json",
    )

    override suspend fun fetchDetailItems(categoryId: String): List<LearnDetailItemDto> = fetch(
        url = "$LearnRemoteBaseUrl/$categoryId.json",
    )

    private suspend inline fun <reified T> fetch(
        url: String,
    ): T = json.decodeFromString(fetchBody(url))

    private suspend fun fetchBody(url: String): String = client.awaitBody(
        Request.Builder()
            .url(url)
            .get()
            .build(),
    )
}

internal class LearnHttpException(
    val code: Int,
    message: String,
) : IOException(message)

private suspend fun OkHttpClient.awaitBody(
    request: Request,
): String = suspendCancellableCoroutine { continuation ->
    val call = newCall(request)

    continuation.invokeOnCancellation {
        call.cancel()
    }

    call.enqueue(
        object : Callback {
            override fun onFailure(
                call: Call,
                e: IOException,
            ) {
                if (continuation.isCancelled) {
                    return
                }
                continuation.resumeWithException(e)
            }

            override fun onResponse(
                call: Call,
                response: Response,
            ) {
                response.use { handledResponse ->
                    if (!handledResponse.isSuccessful) {
                        continuation.resumeWithException(
                            LearnHttpException(
                                code = handledResponse.code,
                                message = handledResponse.message.ifBlank {
                                    "Learn request failed for ${request.url}"
                                },
                            ),
                        )
                        return
                    }

                    val responseBody = handledResponse.body?.string()
                    if (responseBody == null) {
                        continuation.resumeWithException(
                            IOException("Empty Learn response body for ${request.url}"),
                        )
                        return
                    }

                    continuation.resume(responseBody)
                }
            }
        },
    )
}
