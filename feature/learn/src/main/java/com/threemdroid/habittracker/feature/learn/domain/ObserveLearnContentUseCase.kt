package com.threemdroid.habittracker.feature.learn.domain

import com.threemdroid.habittracker.core.common.result.AppResult
import com.threemdroid.habittracker.core.common.result.map
import com.threemdroid.habittracker.core.model.learn.LearnContent
import com.threemdroid.habittracker.core.model.learn.LearnContentSection
import com.threemdroid.habittracker.domain.learn.LearnRepository
import com.threemdroid.habittracker.feature.learn.contract.LearnArticleUiModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class ObserveLearnContentUseCase @Inject constructor(
    private val learnRepository: LearnRepository,
) {
    operator fun invoke(): Flow<AppResult<List<LearnArticleUiModel>>> =
        learnRepository.observeLearnContent().map { result ->
            result.map { content ->
                content.map(LearnContent::toUiModel)
            }
        }
}

private fun LearnContent.toUiModel(): LearnArticleUiModel = LearnArticleUiModel(
    id = id,
    title = title,
    imageUrl = imageUrl,
    paragraphs = sections.map(LearnContentSection::toParagraph),
    videoUrl = videoUrl,
)

private fun LearnContentSection.toParagraph(): String = when {
    title.isBlank() -> body
    body.isBlank() -> title
    else -> "$title\n\n$body"
}
