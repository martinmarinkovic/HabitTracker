package com.threemdroid.habittracker.feature.activity.presentation;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.material3.ExperimentalMaterial3Api;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextOverflow;
import com.threemdroid.habittracker.core.designsystem.HabitTrackerDesignSystem;
import com.threemdroid.habittracker.core.designsystem.HabitTrackerSurfaceTone;
import com.threemdroid.habittracker.core.ui.SegmentedControlOption;
import com.threemdroid.habittracker.feature.activity.contract.ActivityAnalyticsSummary;
import com.threemdroid.habittracker.feature.activity.contract.ActivityChartPoint;
import com.threemdroid.habittracker.feature.activity.contract.ActivityHabitFilterOption;
import com.threemdroid.habittracker.feature.activity.contract.ActivityIntent;
import com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod;
import com.threemdroid.habittracker.feature.activity.contract.ActivityScreenState;
import com.threemdroid.habittracker.feature.activity.contract.ActivityUiState;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@kotlin.Metadata(mv = {2, 2, 0}, k = 2, xi = 48, d1 = {"\u0000z\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0001\u001a.\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u00062\b\b\u0002\u0010\b\u001a\u00020\tH\u0001\u001a\u0012\u0010\n\u001a\u00020\u00012\b\b\u0002\u0010\b\u001a\u00020\tH\u0003\u001a.\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\u00062\b\b\u0002\u0010\b\u001a\u00020\tH\u0003\u001ad\u0010\u000f\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00010\u00182\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00010\u00182\b\b\u0002\u0010\b\u001a\u00020\tH\u0003\u001a\u001a\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\b\u001a\u00020\tH\u0003\u001a\"\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u001f\u001a\u00020\u00142\u0006\u0010 \u001a\u00020\u00142\b\b\u0002\u0010\b\u001a\u00020\tH\u0003\u001a0\u0010!\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00142\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#2\b\b\u0002\u0010\b\u001a\u00020\tH\u0003\u001a\"\u0010%\u001a\u00020\u00012\u0006\u0010&\u001a\u00020$2\u0006\u0010\u001f\u001a\u00020\u00142\b\b\u0002\u0010\b\u001a\u00020\tH\u0003\u001a\u0012\u0010\'\u001a\u00020\u00012\b\b\u0002\u0010\b\u001a\u00020\tH\u0003\u001a\u0012\u0010(\u001a\u00020\u00012\b\b\u0002\u0010\b\u001a\u00020\tH\u0003\u001a \u0010)\u001a\u00020\u00012\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00010\u00182\b\b\u0002\u0010\b\u001a\u00020\tH\u0003\u001aD\u0010+\u001a\u00020\u00012\f\u0010,\u001a\b\u0012\u0004\u0012\u00020-0#2\b\u0010.\u001a\u0004\u0018\u00010\u00142\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00010\u00182\u0014\u00100\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0014\u0012\u0004\u0012\u00020\u00010\u0006H\u0003\u001a0\u00101\u001a\u00020\u00012\u0006\u00102\u001a\u00020-2\u0006\u00103\u001a\u00020\u00162\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u00010\u00182\b\b\u0002\u0010\b\u001a\u00020\tH\u0003\u001a\f\u0010\u001f\u001a\u00020\u0014*\u00020\rH\u0002\u001a\u001c\u00105\u001a\u00020\u0014*\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0002\u001a$\u00106\u001a\u00020\u0014*\u00020$2\u0006\u00107\u001a\u00020\r2\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u000209H\u0002\u001a\f\u0010;\u001a\u00020\u0014*\u00020<H\u0002\u001a\f\u0010=\u001a\u00020\u0014*\u00020<H\u0002\"\u0016\u0010>\u001a\n @*\u0004\u0018\u00010?0?X\u0082\u0004\u00a2\u0006\u0002\n\u0000\"\u0016\u0010A\u001a\n @*\u0004\u0018\u00010?0?X\u0082\u0004\u00a2\u0006\u0002\n\u0000\"\u0016\u0010B\u001a\n @*\u0004\u0018\u00010?0?X\u0082\u0004\u00a2\u0006\u0002\n\u0000\"\u0016\u0010C\u001a\n @*\u0004\u0018\u00010?0?X\u0082\u0004\u00a2\u0006\u0002\n\u0000\"\u0016\u0010D\u001a\n @*\u0004\u0018\u00010?0?X\u0082\u0004\u00a2\u0006\u0002\n\u0000\"\u0016\u0010E\u001a\n @*\u0004\u0018\u00010?0?X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006F"}, d2 = {"ActivityRoute", "", "ActivityScreen", "uiState", "Lcom/threemdroid/habittracker/feature/activity/contract/ActivityUiState;", "onIntent", "Lkotlin/Function1;", "Lcom/threemdroid/habittracker/feature/activity/contract/ActivityIntent;", "modifier", "Landroidx/compose/ui/Modifier;", "ActivityTitle", "ActivityPeriodTabs", "selectedPeriod", "Lcom/threemdroid/habittracker/feature/activity/contract/ActivityPeriod;", "onPeriodSelected", "ActivityPeriodHeaderCard", "periodStartDate", "Ljava/time/LocalDate;", "periodEndDate", "selectedFilterLabel", "", "filterEnabled", "", "onPreviousPeriodRequested", "Lkotlin/Function0;", "onNextPeriodRequested", "onFilterRequested", "ActivityAnalyticsCard", "analyticsSummary", "Lcom/threemdroid/habittracker/feature/activity/contract/ActivityAnalyticsSummary;", "ActivityMetricColumn", "label", "value", "ActivityChartCard", "chartData", "", "Lcom/threemdroid/habittracker/feature/activity/contract/ActivityChartPoint;", "ActivityChartBar", "point", "ActivityLoadingCard", "ActivityEmptyCard", "ActivityErrorCard", "onRetry", "ActivityHabitFilterSheet", "options", "Lcom/threemdroid/habittracker/feature/activity/contract/ActivityHabitFilterOption;", "selectedHabitId", "onDismissRequest", "onOptionSelected", "ActivityFilterOptionCard", "option", "selected", "onClick", "headerText", "axisLabel", "period", "index", "", "totalCount", "toPercentText", "", "toProgressText", "weekdayFormatter", "Ljava/time/format/DateTimeFormatter;", "kotlin.jvm.PlatformType", "monthDayFormatter", "shortDateFormatter", "shortDateFormatterWithYear", "longDateFormatter", "monthYearFormatter", "activity_debug"})
public final class ActivityRouteKt {
    private static final java.time.format.DateTimeFormatter weekdayFormatter = null;
    private static final java.time.format.DateTimeFormatter monthDayFormatter = null;
    private static final java.time.format.DateTimeFormatter shortDateFormatter = null;
    private static final java.time.format.DateTimeFormatter shortDateFormatterWithYear = null;
    private static final java.time.format.DateTimeFormatter longDateFormatter = null;
    private static final java.time.format.DateTimeFormatter monthYearFormatter = null;
    
    @androidx.compose.runtime.Composable()
    public static final void ActivityRoute() {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void ActivityScreen(@org.jetbrains.annotations.NotNull()
    com.threemdroid.habittracker.feature.activity.contract.ActivityUiState uiState, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.threemdroid.habittracker.feature.activity.contract.ActivityIntent, kotlin.Unit> onIntent, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ActivityTitle(androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ActivityPeriodTabs(com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod selectedPeriod, kotlin.jvm.functions.Function1<? super com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod, kotlin.Unit> onPeriodSelected, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ActivityPeriodHeaderCard(com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod selectedPeriod, java.time.LocalDate periodStartDate, java.time.LocalDate periodEndDate, java.lang.String selectedFilterLabel, boolean filterEnabled, kotlin.jvm.functions.Function0<kotlin.Unit> onPreviousPeriodRequested, kotlin.jvm.functions.Function0<kotlin.Unit> onNextPeriodRequested, kotlin.jvm.functions.Function0<kotlin.Unit> onFilterRequested, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ActivityAnalyticsCard(com.threemdroid.habittracker.feature.activity.contract.ActivityAnalyticsSummary analyticsSummary, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ActivityMetricColumn(java.lang.String label, java.lang.String value, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ActivityChartCard(com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod selectedPeriod, java.lang.String selectedFilterLabel, java.util.List<com.threemdroid.habittracker.feature.activity.contract.ActivityChartPoint> chartData, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ActivityChartBar(com.threemdroid.habittracker.feature.activity.contract.ActivityChartPoint point, java.lang.String label, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ActivityLoadingCard(androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ActivityEmptyCard(androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ActivityErrorCard(kotlin.jvm.functions.Function0<kotlin.Unit> onRetry, androidx.compose.ui.Modifier modifier) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    private static final void ActivityHabitFilterSheet(java.util.List<com.threemdroid.habittracker.feature.activity.contract.ActivityHabitFilterOption> options, java.lang.String selectedHabitId, kotlin.jvm.functions.Function0<kotlin.Unit> onDismissRequest, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onOptionSelected) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ActivityFilterOptionCard(com.threemdroid.habittracker.feature.activity.contract.ActivityHabitFilterOption option, boolean selected, kotlin.jvm.functions.Function0<kotlin.Unit> onClick, androidx.compose.ui.Modifier modifier) {
    }
    
    private static final java.lang.String label(com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod $this$label) {
        return null;
    }
    
    private static final java.lang.String headerText(com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod $this$headerText, java.time.LocalDate periodStartDate, java.time.LocalDate periodEndDate) {
        return null;
    }
    
    private static final java.lang.String axisLabel(com.threemdroid.habittracker.feature.activity.contract.ActivityChartPoint $this$axisLabel, com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod period, int index, int totalCount) {
        return null;
    }
    
    private static final java.lang.String toPercentText(double $this$toPercentText) {
        return null;
    }
    
    private static final java.lang.String toProgressText(double $this$toProgressText) {
        return null;
    }
}