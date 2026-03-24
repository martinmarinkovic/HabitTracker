package com.threemdroid.habittracker.feature.learn.presentation

import androidx.activity.ComponentActivity
import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.threemdroid.habittracker.core.designsystem.HabitTrackerTheme
import com.threemdroid.habittracker.feature.learn.contract.LearnArticleUiModel
import com.threemdroid.habittracker.feature.learn.contract.LearnIntent
import com.threemdroid.habittracker.feature.learn.contract.LearnScreenState
import com.threemdroid.habittracker.feature.learn.contract.LearnUiState
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class LearnScreenTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun loadingState_rendersLoadingCard() {
        composeRule.setContent {
            HabitTrackerTheme {
                LearnScreen(
                    uiState = LearnUiState(screenState = LearnScreenState.Loading),
                    snackbarHostState = SnackbarHostState(),
                    onIntent = {},
                )
            }
        }

        composeRule.onNodeWithTag(LearnUiTestTags.LOADING_STATE).assertIsDisplayed()
    }

    @Test
    fun emptyState_rendersEmptyCard() {
        composeRule.setContent {
            HabitTrackerTheme {
                LearnScreen(
                    uiState = LearnUiState(screenState = LearnScreenState.Empty),
                    snackbarHostState = SnackbarHostState(),
                    onIntent = {},
                )
            }
        }

        composeRule.onNodeWithTag(LearnUiTestTags.EMPTY_STATE).assertIsDisplayed()
    }

    @Test
    fun errorState_retryEmitsIntent() {
        var lastIntent: LearnIntent? = null

        composeRule.setContent {
            HabitTrackerTheme {
                LearnScreen(
                    uiState = LearnUiState(
                        screenState = LearnScreenState.Error("Learn is currently unavailable."),
                    ),
                    snackbarHostState = SnackbarHostState(),
                    onIntent = { intent -> lastIntent = intent },
                )
            }
        }

        composeRule.onNodeWithTag(LearnUiTestTags.ERROR_STATE).assertIsDisplayed()
        composeRule.onNodeWithTag(LearnUiTestTags.RETRY_BUTTON).performClick()

        assertEquals(LearnIntent.RetryLoad, lastIntent)
    }

    @Test
    fun contentState_rendersGridAndArticleCards() {
        composeRule.setContent {
            HabitTrackerTheme {
                LearnScreen(
                    uiState = gridState(),
                    snackbarHostState = SnackbarHostState(),
                    onIntent = {},
                )
            }
        }

        composeRule.onNodeWithTag(LearnUiTestTags.GRID_SCREEN).assertIsDisplayed()
        composeRule.onNodeWithTag(LearnUiTestTags.articleCard("learn-1")).assertIsDisplayed()
        composeRule.onNodeWithTag(LearnUiTestTags.articleCard("learn-2")).assertIsDisplayed()
    }

    @Test
    fun clickingArticleCard_emitsSelectionIntent() {
        var lastIntent: LearnIntent? = null

        composeRule.setContent {
            HabitTrackerTheme {
                LearnScreen(
                    uiState = gridState(),
                    snackbarHostState = SnackbarHostState(),
                    onIntent = { intent -> lastIntent = intent },
                )
            }
        }

        composeRule.onNodeWithTag(LearnUiTestTags.articleCard("learn-2")).performClick()

        assertEquals(LearnIntent.ArticleSelected("learn-2"), lastIntent)
    }

    @Test
    fun detailState_rendersParagraphsAndBackIntent() {
        var lastIntent: LearnIntent? = null

        composeRule.setContent {
            HabitTrackerTheme {
                LearnScreen(
                    uiState = detailState(),
                    snackbarHostState = SnackbarHostState(),
                    onIntent = { intent -> lastIntent = intent },
                )
            }
        }

        composeRule.onNodeWithTag(LearnUiTestTags.DETAIL_SCREEN).assertIsDisplayed()
        composeRule.onNodeWithTag(LearnUiTestTags.paragraph(0)).assertIsDisplayed()
        composeRule.onNodeWithText("A lightweight daily check-in can still reveal meaningful patterns.").assertIsDisplayed()

        composeRule.onNodeWithTag(LearnUiTestTags.DETAIL_BACK_BUTTON).performClick()

        assertEquals(LearnIntent.DetailBackRequested, lastIntent)
    }

    @Test
    fun detailState_withVideo_rendersButtonAndEmitsIntent() {
        var lastIntent: LearnIntent? = null

        composeRule.setContent {
            HabitTrackerTheme {
                LearnScreen(
                    uiState = detailState(videoUrl = "https://example.com/learn.mp4"),
                    snackbarHostState = SnackbarHostState(),
                    onIntent = { intent -> lastIntent = intent },
                )
            }
        }

        composeRule.onNodeWithTag(LearnUiTestTags.DETAIL_VIDEO_BUTTON).assertIsDisplayed()
        composeRule.onNodeWithTag(LearnUiTestTags.DETAIL_VIDEO_BUTTON).performClick()

        assertEquals(LearnIntent.VideoPlaybackRequested, lastIntent)
    }
}

private fun gridState(): LearnUiState = LearnUiState(
    screenState = LearnScreenState.Content,
    articles = listOf(
        LearnArticleUiModel(
            id = "learn-1",
            title = "Why Habits Stick",
        ),
        LearnArticleUiModel(
            id = "learn-2",
            title = "Mood Patterns",
        ),
    ),
)

private fun detailState(
    videoUrl: String? = null,
): LearnUiState = LearnUiState(
    screenState = LearnScreenState.Content,
    articles = listOf(
        LearnArticleUiModel(
            id = "learn-2",
            title = "Mood Patterns",
            paragraphs = listOf(
                "A lightweight daily check-in can still reveal meaningful patterns.",
                "Repeated observations are more useful than an occasional perfect entry.",
            ),
            videoUrl = videoUrl,
        ),
    ),
    selectedArticleId = "learn-2",
)
