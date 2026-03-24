package com.threemdroid.habittracker.data.learn.model

import kotlinx.serialization.Serializable

@Serializable
internal data class LearnCategoryDto(
    val title: String,
    val id: String,
)

@Serializable
internal data class LearnDetailItemDto(
    val id: String,
    val title: String,
    val body: String,
)
