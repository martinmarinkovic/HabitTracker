package com.threemdroid.habittracker.data.learn.repository

import com.threemdroid.habittracker.core.common.result.AppError
import com.threemdroid.habittracker.core.common.result.AppResult
import com.threemdroid.habittracker.core.model.learn.LearnContentSection
import com.threemdroid.habittracker.data.learn.model.LearnCategoryDto
import com.threemdroid.habittracker.data.learn.model.LearnDetailItemDto
import com.threemdroid.habittracker.data.learn.remote.LearnRemoteDataSource
import java.io.IOException
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class LearnRepositoryImplTest {
    @Test
    fun observeLearnContent_mapsCategoriesDetailContentAndDerivedMedia() = runBlocking {
        val repository = LearnRepositoryImpl(
            remoteDataSource = FakeLearnRemoteDataSource(
                categories = listOf(
                    LearnCategoryDto(
                        title = "Routine / Habit Building",
                        id = "routine",
                    ),
                ),
                detailItemsByCategoryId = mapOf(
                    "routine" to listOf(
                        LearnDetailItemDto(
                            id = "routine-1",
                            title = "Automates Good Behavior",
                            body = "Habits reduce the need for constant decision-making.",
                        ),
                        LearnDetailItemDto(
                            id = "routine-2",
                            title = "Improves Efficiency",
                            body = "Having a structured routine removes wasted time.",
                        ),
                    ),
                ),
            ),
        )

        val result = repository.observeLearnContent().first()

        assertTrue(result is AppResult.Success)
        result as AppResult.Success
        assertEquals(
            listOf(
                com.threemdroid.habittracker.core.model.learn.LearnContent(
                    id = "routine",
                    title = "Routine / Habit Building",
                    imageUrl = "https://iomc.rs/rutinna/db/routine.jpg",
                    sections = listOf(
                        LearnContentSection(
                            id = "routine-1",
                            title = "Automates Good Behavior",
                            body = "Habits reduce the need for constant decision-making.",
                        ),
                        LearnContentSection(
                            id = "routine-2",
                            title = "Improves Efficiency",
                            body = "Having a structured routine removes wasted time.",
                        ),
                    ),
                    videoUrl = "https://iomc.rs/rutinna/db/routine.mp4",
                ),
            ),
            result.value,
        )
    }

    @Test
    fun observeLearnContent_whenRemoteFetchFails_returnsNetworkFailure() = runBlocking {
        val repository = LearnRepositoryImpl(
            remoteDataSource = FakeLearnRemoteDataSource(
                categoriesError = IOException("offline"),
            ),
        )

        val result = repository.observeLearnContent().first()

        assertTrue(result is AppResult.Failure)
        result as AppResult.Failure
        assertTrue(result.error is AppError.Network)
        assertEquals("offline", result.error.message)
    }
}

private class FakeLearnRemoteDataSource(
    private val categories: List<LearnCategoryDto> = emptyList(),
    private val detailItemsByCategoryId: Map<String, List<LearnDetailItemDto>> = emptyMap(),
    private val categoriesError: Throwable? = null,
    private val detailErrorByCategoryId: Map<String, Throwable> = emptyMap(),
) : LearnRemoteDataSource {
    override suspend fun fetchCategories(): List<LearnCategoryDto> {
        categoriesError?.let { throw it }
        return categories
    }

    override suspend fun fetchDetailItems(categoryId: String): List<LearnDetailItemDto> {
        detailErrorByCategoryId[categoryId]?.let { throw it }
        return detailItemsByCategoryId[categoryId].orEmpty()
    }
}
