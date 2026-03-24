package com.threemdroid.habittracker.feature.learn.presentation;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.lazy.grid.GridCells;
import androidx.compose.material3.SnackbarHostState;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.style.TextAlign;
import androidx.lifecycle.Lifecycle;
import com.threemdroid.habittracker.core.designsystem.HabitTrackerDesignSystem;
import com.threemdroid.habittracker.core.designsystem.HabitTrackerSurfaceTone;
import com.threemdroid.habittracker.feature.learn.contract.LearnArticleUiModel;
import com.threemdroid.habittracker.feature.learn.contract.LearnEffect;
import com.threemdroid.habittracker.feature.learn.contract.LearnIntent;
import com.threemdroid.habittracker.feature.learn.contract.LearnScreenState;
import com.threemdroid.habittracker.feature.learn.contract.LearnUiState;

@kotlin.Metadata(mv = {2, 2, 0}, k = 2, xi = 48, d1 = {"\u0000H\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000e\u001a\u001e\u0010\u0000\u001a\u00020\u00012\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003H\u0001\u001a6\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\f\u001a\u00020\rH\u0001\u001a0\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00010\u00132\b\b\u0002\u0010\f\u001a\u00020\rH\u0003\u001a4\u0010\u0014\u001a\u00020\u00012\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\f\u001a\u00020\rH\u0003\u001a(\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u00172\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00132\b\b\u0002\u0010\f\u001a\u00020\rH\u0003\u001a\u0012\u0010\u001b\u001a\u00020\u00012\b\b\u0002\u0010\f\u001a\u00020\rH\u0003\u001a\u0012\u0010\u001c\u001a\u00020\u00012\b\b\u0002\u0010\f\u001a\u00020\rH\u0003\u001a(\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u001e\u001a\u00020\u00042\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00010\u00132\b\b\u0002\u0010\f\u001a\u00020\rH\u0003\u001a\b\u0010 \u001a\u00020\u0001H\u0003\u001a\b\u0010!\u001a\u00020\u0001H\u0003\u001a\b\u0010\"\u001a\u00020\u0001H\u0003\u001a\b\u0010#\u001a\u00020\u0001H\u0003\u001a\b\u0010$\u001a\u00020\u0001H\u0003\u00a8\u0006%"}, d2 = {"LearnRoute", "", "onVideoRequested", "Lkotlin/Function1;", "", "LearnScreen", "uiState", "Lcom/threemdroid/habittracker/feature/learn/contract/LearnUiState;", "snackbarHostState", "Landroidx/compose/material3/SnackbarHostState;", "onIntent", "Lcom/threemdroid/habittracker/feature/learn/contract/LearnIntent;", "modifier", "Landroidx/compose/ui/Modifier;", "LearnTopBar", "title", "showBack", "", "onBackRequested", "Lkotlin/Function0;", "LearnGridScreen", "articles", "", "Lcom/threemdroid/habittracker/feature/learn/contract/LearnArticleUiModel;", "onArticleSelected", "LearnDetailScreen", "article", "LearnLoadingState", "LearnEmptyState", "LearnErrorState", "message", "onRetry", "LearnGridPreview", "LearnDetailPreview", "LearnLoadingPreview", "LearnEmptyPreview", "LearnErrorPreview", "learn_debug"})
public final class LearnRouteKt {
    
    @androidx.compose.runtime.Composable()
    public static final void LearnRoute(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onVideoRequested) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void LearnScreen(@org.jetbrains.annotations.NotNull()
    com.threemdroid.habittracker.feature.learn.contract.LearnUiState uiState, @org.jetbrains.annotations.NotNull()
    androidx.compose.material3.SnackbarHostState snackbarHostState, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.threemdroid.habittracker.feature.learn.contract.LearnIntent, kotlin.Unit> onIntent, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void LearnTopBar(java.lang.String title, boolean showBack, kotlin.jvm.functions.Function0<kotlin.Unit> onBackRequested, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void LearnGridScreen(java.util.List<com.threemdroid.habittracker.feature.learn.contract.LearnArticleUiModel> articles, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onArticleSelected, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void LearnDetailScreen(com.threemdroid.habittracker.feature.learn.contract.LearnArticleUiModel article, kotlin.jvm.functions.Function0<kotlin.Unit> onVideoRequested, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void LearnLoadingState(androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void LearnEmptyState(androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void LearnErrorState(java.lang.String message, kotlin.jvm.functions.Function0<kotlin.Unit> onRetry, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.ui.tooling.preview.Preview(showBackground = true)
    @androidx.compose.runtime.Composable()
    private static final void LearnGridPreview() {
    }
    
    @androidx.compose.ui.tooling.preview.Preview(showBackground = true)
    @androidx.compose.runtime.Composable()
    private static final void LearnDetailPreview() {
    }
    
    @androidx.compose.ui.tooling.preview.Preview(showBackground = true)
    @androidx.compose.runtime.Composable()
    private static final void LearnLoadingPreview() {
    }
    
    @androidx.compose.ui.tooling.preview.Preview(showBackground = true)
    @androidx.compose.runtime.Composable()
    private static final void LearnEmptyPreview() {
    }
    
    @androidx.compose.ui.tooling.preview.Preview(showBackground = true)
    @androidx.compose.runtime.Composable()
    private static final void LearnErrorPreview() {
    }
}