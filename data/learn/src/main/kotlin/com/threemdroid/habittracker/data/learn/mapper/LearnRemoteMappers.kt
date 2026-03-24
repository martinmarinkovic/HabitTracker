package com.threemdroid.habittracker.data.learn.mapper

import com.threemdroid.habittracker.core.model.learn.LearnContent
import com.threemdroid.habittracker.core.model.learn.LearnContentSection
import com.threemdroid.habittracker.data.learn.model.LearnCategoryDto
import com.threemdroid.habittracker.data.learn.model.LearnDetailItemDto

internal const val LearnRemoteBaseUrl = "https://iomc.rs/rutinna/db"

internal fun LearnCategoryDto.toModel(
    detailItems: List<LearnDetailItemDto>,
): LearnContent = LearnContent(
    id = id,
    title = title,
    imageUrl = "$LearnRemoteBaseUrl/$id.jpg",
    sections = detailItems.map(LearnDetailItemDto::toModel),
    videoUrl = "$LearnRemoteBaseUrl/$id.mp4",
)

private fun LearnDetailItemDto.toModel(): LearnContentSection = LearnContentSection(
    id = id,
    title = title,
    body = body,
)
