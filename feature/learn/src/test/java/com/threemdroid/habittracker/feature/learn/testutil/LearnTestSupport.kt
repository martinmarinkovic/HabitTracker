package com.threemdroid.habittracker.feature.learn.testutil

import com.threemdroid.habittracker.core.common.result.AppError
import com.threemdroid.habittracker.core.common.result.AppResult
import com.threemdroid.habittracker.core.model.learn.LearnContent
import com.threemdroid.habittracker.core.model.learn.LearnContentSection
import com.threemdroid.habittracker.domain.learn.LearnRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

internal fun learnContent(
    id: String,
    title: String,
    imageUrl: String? = null,
    sections: List<LearnContentSection> = emptyList(),
    videoUrl: String? = null,
): LearnContent = LearnContent(
    id = id,
    title = title,
    imageUrl = imageUrl,
    sections = sections,
    videoUrl = videoUrl,
)

internal fun learnSection(
    id: String,
    title: String,
    body: String,
): LearnContentSection = LearnContentSection(
    id = id,
    title = title,
    body = body,
)

internal class TestLearnRepository : LearnRepository {
    private val contentFlow = MutableStateFlow<AppResult<List<LearnContent>>>(
        AppResult.Success(emptyList()),
    )

    override fun observeLearnContent(): Flow<AppResult<List<LearnContent>>> = contentFlow

    fun setContent(content: List<LearnContent>) {
        contentFlow.value = AppResult.Success(content)
    }

    fun setFailure(error: AppError) {
        contentFlow.value = AppResult.Failure(error)
    }
}
