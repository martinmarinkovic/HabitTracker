package com.threemdroid.habittracker.feature.learn.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import coil.compose.AsyncImage
import com.threemdroid.habittracker.core.designsystem.HabitTrackerDesignSystem
import com.threemdroid.habittracker.core.designsystem.HabitTrackerGradientSurface
import com.threemdroid.habittracker.core.designsystem.HabitTrackerSurfaceTone
import com.threemdroid.habittracker.core.ui.CircularIconButton
import com.threemdroid.habittracker.core.ui.GradientActionButton
import com.threemdroid.habittracker.core.ui.LearnContentCard
import com.threemdroid.habittracker.core.ui.RoundedCardSurface
import com.threemdroid.habittracker.core.ui.SectionHeader
import com.threemdroid.habittracker.feature.learn.contract.LearnArticleUiModel
import com.threemdroid.habittracker.feature.learn.contract.LearnEffect
import com.threemdroid.habittracker.feature.learn.contract.LearnIntent
import com.threemdroid.habittracker.feature.learn.contract.LearnScreenState
import com.threemdroid.habittracker.feature.learn.contract.LearnUiState

@Composable
internal fun LearnRoute(
    onVideoRequested: (String) -> Unit = {},
) {
    val viewModel: LearnViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewModel, lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.effects.collect { effect ->
                when (effect) {
                    is LearnEffect.OpenVideo -> onVideoRequested(effect.videoUrl)
                }
            }
        }
    }

    LearnScreen(
        uiState = uiState,
        snackbarHostState = snackbarHostState,
        onIntent = viewModel::handleIntent,
    )
}

@Composable
internal fun LearnScreen(
    uiState: LearnUiState,
    snackbarHostState: SnackbarHostState,
    onIntent: (LearnIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = HabitTrackerDesignSystem.colors
    val spacing = HabitTrackerDesignSystem.spacing
    val selectedArticle = uiState.selectedArticle

    BackHandler(enabled = selectedArticle != null) {
        onIntent(LearnIntent.DetailBackRequested)
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(colors.surfaceCanvas)
            .testTag(LearnUiTestTags.ROOT),
        containerColor = colors.surfaceCanvas,
        contentWindowInsets = WindowInsets(0.dp),
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.navigationBarsPadding(),
            )
        },
        topBar = {
            LearnTopBar(
                title = if (selectedArticle == null) "Learn" else selectedArticle.title,
                showBack = selectedArticle != null,
                onBackRequested = { onIntent(LearnIntent.DetailBackRequested) },
            )
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            when {
                selectedArticle != null && uiState.screenState == LearnScreenState.Content -> {
                    LearnDetailScreen(
                        article = selectedArticle,
                        onVideoRequested = { onIntent(LearnIntent.VideoPlaybackRequested) },
                    )
                }

                uiState.screenState == LearnScreenState.Loading -> LearnLoadingState()
                uiState.screenState == LearnScreenState.Empty -> LearnEmptyState()
                uiState.screenState is LearnScreenState.Error -> LearnErrorState(
                    message = uiState.screenState.message,
                    onRetry = { onIntent(LearnIntent.RetryLoad) },
                )

                uiState.screenState == LearnScreenState.Content -> LearnGridScreen(
                    articles = uiState.articles,
                    onArticleSelected = { articleId ->
                        onIntent(LearnIntent.ArticleSelected(articleId))
                    },
                )
            }
        }
    }
}

@Composable
private fun LearnTopBar(
    title: String,
    showBack: Boolean,
    onBackRequested: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = HabitTrackerDesignSystem.colors
    val spacing = HabitTrackerDesignSystem.spacing
    val typography = HabitTrackerDesignSystem.typography

    Box(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = spacing.screenEdge, vertical = spacing.md),
    ) {
        if (showBack) {
            CircularIconButton(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .testTag(LearnUiTestTags.DETAIL_BACK_BUTTON),
                onClick = onBackRequested,
            ) {
                Text(
                    text = "<",
                    style = typography.bodyStrong,
                    color = colors.textPrimary,
                )
            }
        }

        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 56.dp),
            text = title,
            style = typography.screenTitle,
            color = colors.textPrimary,
            textAlign = TextAlign.Center,
            maxLines = 2,
        )
    }
}

@Composable
private fun LearnGridScreen(
    articles: List<LearnArticleUiModel>,
    onArticleSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val spacing = HabitTrackerDesignSystem.spacing

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag(LearnUiTestTags.GRID_SCREEN)
            .padding(horizontal = spacing.screenEdge),
        verticalArrangement = Arrangement.spacedBy(spacing.lg),
    ) {
        SectionHeader(
            modifier = Modifier.padding(top = spacing.sm),
            title = "Learn",
            supportingText = "Browse articles and open details when available.",
        )

        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Adaptive(minSize = 160.dp),
            contentPadding = PaddingValues(bottom = 32.dp),
            horizontalArrangement = Arrangement.spacedBy(spacing.md),
            verticalArrangement = Arrangement.spacedBy(spacing.md),
        ) {
            items(
                items = articles,
                key = LearnArticleUiModel::id,
            ) { article ->
                LearnContentCard(
                    modifier = Modifier.testTag(LearnUiTestTags.articleCard(article.id)),
                    title = article.title,
                    onClick = { onArticleSelected(article.id) },
                    media = {
                        if (article.imageUrl != null) {
                            AsyncImage(
                                model = article.imageUrl,
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                            )
                        }
                    },
                )
            }
        }
    }
}

@Composable
private fun LearnDetailScreen(
    article: LearnArticleUiModel,
    onVideoRequested: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = HabitTrackerDesignSystem.colors
    val spacing = HabitTrackerDesignSystem.spacing
    val typography = HabitTrackerDesignSystem.typography

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag(LearnUiTestTags.DETAIL_SCREEN)
            .padding(horizontal = spacing.screenEdge),
        contentPadding = PaddingValues(
            top = spacing.sm,
            bottom = 32.dp,
        ),
        verticalArrangement = Arrangement.spacedBy(spacing.lg),
    ) {
        item {
            if (article.imageUrl != null) {
                RoundedCardSurface(
                    modifier = Modifier.fillMaxWidth(),
                    tone = HabitTrackerSurfaceTone.Raised,
                ) {
                    AsyncImage(
                        model = article.imageUrl,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp),
                    )
                }
            } else {
                HabitTrackerGradientSurface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp),
                    brush = HabitTrackerDesignSystem.gradients.raisedSurface,
                    contentPadding = PaddingValues(spacing.lg),
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.BottomStart),
                        text = article.title,
                        style = typography.sectionTitle,
                        color = colors.textPrimary,
                    )
                }
            }
        }

        item {
            SectionHeader(
                title = article.title,
                supportingText = if (article.paragraphs.isEmpty()) {
                    "No additional article copy is available in the current repository content."
                } else {
                    null
                },
            )
        }

        items(article.paragraphs.size) { paragraphIndex ->
            RoundedCardSurface(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(LearnUiTestTags.paragraph(paragraphIndex)),
                tone = HabitTrackerSurfaceTone.Raised,
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = article.paragraphs[paragraphIndex],
                    style = typography.body,
                    color = colors.textPrimary,
                )
            }
        }

        if (article.videoUrl != null) {
            item {
                GradientActionButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag(LearnUiTestTags.DETAIL_VIDEO_BUTTON),
                    text = "Play Video",
                    onClick = onVideoRequested,
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun LearnLoadingState(
    modifier: Modifier = Modifier,
) {
    val colors = HabitTrackerDesignSystem.colors
    val spacing = HabitTrackerDesignSystem.spacing
    val typography = HabitTrackerDesignSystem.typography

    Box(
        modifier = modifier
            .fillMaxSize()
            .testTag(LearnUiTestTags.LOADING_STATE)
            .padding(horizontal = spacing.screenEdge),
        contentAlignment = Alignment.Center,
    ) {
        RoundedCardSurface(
            modifier = Modifier.fillMaxWidth(),
            tone = HabitTrackerSurfaceTone.Raised,
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(spacing.md),
            ) {
                CircularProgressIndicator(color = colors.accentPrimary)
                Text(
                    text = "Loading Learn...",
                    style = typography.body,
                    color = colors.textSecondary,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Composable
private fun LearnEmptyState(
    modifier: Modifier = Modifier,
) {
    val colors = HabitTrackerDesignSystem.colors
    val spacing = HabitTrackerDesignSystem.spacing
    val typography = HabitTrackerDesignSystem.typography

    Box(
        modifier = modifier
            .fillMaxSize()
            .testTag(LearnUiTestTags.EMPTY_STATE)
            .padding(horizontal = spacing.screenEdge),
        contentAlignment = Alignment.Center,
    ) {
        RoundedCardSurface(
            modifier = Modifier.fillMaxWidth(),
            tone = HabitTrackerSurfaceTone.Raised,
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(spacing.sm),
            ) {
                Text(
                    text = "No Learn content is available.",
                    style = typography.sectionTitle,
                    color = colors.textPrimary,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = "This state is ready for real Learn content when it exists in the repository.",
                    style = typography.callout,
                    color = colors.textSecondary,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Composable
private fun LearnErrorState(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = HabitTrackerDesignSystem.colors
    val spacing = HabitTrackerDesignSystem.spacing
    val typography = HabitTrackerDesignSystem.typography

    Box(
        modifier = modifier
            .fillMaxSize()
            .testTag(LearnUiTestTags.ERROR_STATE)
            .padding(horizontal = spacing.screenEdge),
        contentAlignment = Alignment.Center,
    ) {
        RoundedCardSurface(
            modifier = Modifier.fillMaxWidth(),
            tone = HabitTrackerSurfaceTone.Raised,
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(spacing.md),
            ) {
                Text(
                    text = "Learn Unavailable",
                    style = typography.sectionTitle,
                    color = colors.textPrimary,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = message,
                    style = typography.callout,
                    color = colors.textSecondary,
                    textAlign = TextAlign.Center,
                )
                GradientActionButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .safeDrawingPadding()
                        .testTag(LearnUiTestTags.RETRY_BUTTON),
                    text = "Retry",
                    onClick = onRetry,
                )
            }
        }
    }
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
private fun LearnGridPreview() {
    com.threemdroid.habittracker.core.designsystem.HabitTrackerTheme {
        LearnScreen(
            uiState = LearnPreviewData.gridState(),
            snackbarHostState = SnackbarHostState(),
            onIntent = {},
        )
    }
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
private fun LearnDetailPreview() {
    com.threemdroid.habittracker.core.designsystem.HabitTrackerTheme {
        LearnScreen(
            uiState = LearnPreviewData.detailState(),
            snackbarHostState = SnackbarHostState(),
            onIntent = {},
        )
    }
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
private fun LearnLoadingPreview() {
    com.threemdroid.habittracker.core.designsystem.HabitTrackerTheme {
        LearnScreen(
            uiState = LearnPreviewData.loadingState(),
            snackbarHostState = SnackbarHostState(),
            onIntent = {},
        )
    }
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
private fun LearnEmptyPreview() {
    com.threemdroid.habittracker.core.designsystem.HabitTrackerTheme {
        LearnScreen(
            uiState = LearnPreviewData.emptyState(),
            snackbarHostState = SnackbarHostState(),
            onIntent = {},
        )
    }
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
private fun LearnErrorPreview() {
    com.threemdroid.habittracker.core.designsystem.HabitTrackerTheme {
        LearnScreen(
            uiState = LearnPreviewData.errorState(),
            snackbarHostState = SnackbarHostState(),
            onIntent = {},
        )
    }
}
