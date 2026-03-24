package com.threemdroid.habittracker.feature.activity.presentation;

import androidx.activity.ComponentActivity;
import com.threemdroid.habittracker.feature.activity.contract.ActivityAnalyticsSummary;
import com.threemdroid.habittracker.feature.activity.contract.ActivityChartPoint;
import com.threemdroid.habittracker.feature.activity.contract.ActivityHabitFilterOption;
import com.threemdroid.habittracker.feature.activity.contract.ActivityIntent;
import com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod;
import com.threemdroid.habittracker.feature.activity.contract.ActivityScreenState;
import com.threemdroid.habittracker.feature.activity.contract.ActivityUiState;
import java.time.LocalDate;
import org.junit.Rule;
import org.junit.Test;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0007J\b\u0010\f\u001a\u00020\u000bH\u0007J\b\u0010\r\u001a\u00020\u000bH\u0007J\b\u0010\u000e\u001a\u00020\u000bH\u0007R%\u0010\u0004\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0004\u0012\u00020\u00070\u00058\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\u000f"}, d2 = {"Lcom/threemdroid/habittracker/feature/activity/presentation/ActivityScreenTest;", "", "<init>", "()V", "composeRule", "Landroidx/compose/ui/test/junit4/AndroidComposeTestRule;", "Landroidx/test/ext/junit/rules/ActivityScenarioRule;", "Landroidx/activity/ComponentActivity;", "getComposeRule", "()Landroidx/compose/ui/test/junit4/AndroidComposeTestRule;", "contentState_rendersAnalyticsAndChart", "", "periodTabsAndNavigationControls_emitIntents", "filterSheet_openAndSelectHabit_emitsFilterIntent", "errorState_retryEmitsIntent", "activity_debugAndroidTest"})
public final class ActivityScreenTest {
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.ui.test.junit4.AndroidComposeTestRule<androidx.test.ext.junit.rules.ActivityScenarioRule<androidx.activity.ComponentActivity>, androidx.activity.ComponentActivity> composeRule = null;
    
    public ActivityScreenTest() {
        super();
    }
    
    @org.junit.Rule()
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.ui.test.junit4.AndroidComposeTestRule<androidx.test.ext.junit.rules.ActivityScenarioRule<androidx.activity.ComponentActivity>, androidx.activity.ComponentActivity> getComposeRule() {
        return null;
    }
    
    @org.junit.Test()
    public final void contentState_rendersAnalyticsAndChart() {
    }
    
    @org.junit.Test()
    public final void periodTabsAndNavigationControls_emitIntents() {
    }
    
    @org.junit.Test()
    public final void filterSheet_openAndSelectHabit_emitsFilterIntent() {
    }
    
    @org.junit.Test()
    public final void errorState_retryEmitsIntent() {
    }
}