package com.threemdroid.habittracker.feature.activity.presentation;

import com.threemdroid.habittracker.feature.activity.contract.ActivityAnalyticsSummary;
import com.threemdroid.habittracker.feature.activity.contract.ActivityLoadError;
import com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod;
import com.threemdroid.habittracker.feature.activity.contract.ActivityScreenState;
import com.threemdroid.habittracker.feature.activity.contract.ActivityUiState;
import com.threemdroid.habittracker.feature.activity.domain.ActivitySnapshot;
import java.time.LocalDate;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bp\u0018\u00002\u00020\u0001:\u0004\u0002\u0003\u0004\u0005\u0082\u0001\u0004\u0006\u0007\b\t\u00a8\u0006\n\u00c0\u0006\u0003"}, d2 = {"Lcom/threemdroid/habittracker/feature/activity/presentation/ActivityResult;", "", "ConfigurationChanged", "ActivityDataLoading", "ActivityDataLoaded", "ActivityDataLoadFailed", "Lcom/threemdroid/habittracker/feature/activity/presentation/ActivityResult$ActivityDataLoadFailed;", "Lcom/threemdroid/habittracker/feature/activity/presentation/ActivityResult$ActivityDataLoaded;", "Lcom/threemdroid/habittracker/feature/activity/presentation/ActivityResult$ActivityDataLoading;", "Lcom/threemdroid/habittracker/feature/activity/presentation/ActivityResult$ConfigurationChanged;", "activity_debug"})
public abstract interface ActivityResult {
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u00c6\n\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u00d6\u0003J\t\u0010\b\u001a\u00020\tH\u00d6\u0001J\t\u0010\n\u001a\u00020\u000bH\u00d6\u0001\u00a8\u0006\f"}, d2 = {"Lcom/threemdroid/habittracker/feature/activity/presentation/ActivityResult$ActivityDataLoadFailed;", "Lcom/threemdroid/habittracker/feature/activity/presentation/ActivityResult;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "activity_debug"})
    public static final class ActivityDataLoadFailed implements com.threemdroid.habittracker.feature.activity.presentation.ActivityResult {
        @org.jetbrains.annotations.NotNull()
        public static final com.threemdroid.habittracker.feature.activity.presentation.ActivityResult.ActivityDataLoadFailed INSTANCE = null;
        
        private ActivityDataLoadFailed() {
            super();
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u00d6\u0003J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0012"}, d2 = {"Lcom/threemdroid/habittracker/feature/activity/presentation/ActivityResult$ActivityDataLoaded;", "Lcom/threemdroid/habittracker/feature/activity/presentation/ActivityResult;", "snapshot", "Lcom/threemdroid/habittracker/feature/activity/domain/ActivitySnapshot;", "<init>", "(Lcom/threemdroid/habittracker/feature/activity/domain/ActivitySnapshot;)V", "getSnapshot", "()Lcom/threemdroid/habittracker/feature/activity/domain/ActivitySnapshot;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "activity_debug"})
    public static final class ActivityDataLoaded implements com.threemdroid.habittracker.feature.activity.presentation.ActivityResult {
        @org.jetbrains.annotations.NotNull()
        private final com.threemdroid.habittracker.feature.activity.domain.ActivitySnapshot snapshot = null;
        
        public ActivityDataLoaded(@org.jetbrains.annotations.NotNull()
        com.threemdroid.habittracker.feature.activity.domain.ActivitySnapshot snapshot) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.threemdroid.habittracker.feature.activity.domain.ActivitySnapshot getSnapshot() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.threemdroid.habittracker.feature.activity.domain.ActivitySnapshot component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.threemdroid.habittracker.feature.activity.presentation.ActivityResult.ActivityDataLoaded copy(@org.jetbrains.annotations.NotNull()
        com.threemdroid.habittracker.feature.activity.domain.ActivitySnapshot snapshot) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u00c6\n\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u00d6\u0003J\t\u0010\b\u001a\u00020\tH\u00d6\u0001J\t\u0010\n\u001a\u00020\u000bH\u00d6\u0001\u00a8\u0006\f"}, d2 = {"Lcom/threemdroid/habittracker/feature/activity/presentation/ActivityResult$ActivityDataLoading;", "Lcom/threemdroid/habittracker/feature/activity/presentation/ActivityResult;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "activity_debug"})
    public static final class ActivityDataLoading implements com.threemdroid.habittracker.feature.activity.presentation.ActivityResult {
        @org.jetbrains.annotations.NotNull()
        public static final com.threemdroid.habittracker.feature.activity.presentation.ActivityResult.ActivityDataLoading INSTANCE = null;
        
        private ActivityDataLoading() {
            super();
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u00a2\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0005H\u00c6\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\tH\u00c6\u0003J=\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tH\u00c6\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u00d6\u0003J\t\u0010\u001e\u001a\u00020\u001fH\u00d6\u0001J\t\u0010 \u001a\u00020\tH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0013\u0010\b\u001a\u0004\u0018\u00010\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006!"}, d2 = {"Lcom/threemdroid/habittracker/feature/activity/presentation/ActivityResult$ConfigurationChanged;", "Lcom/threemdroid/habittracker/feature/activity/presentation/ActivityResult;", "period", "Lcom/threemdroid/habittracker/feature/activity/contract/ActivityPeriod;", "periodAnchorDate", "Ljava/time/LocalDate;", "periodStartDate", "periodEndDate", "selectedHabitId", "", "<init>", "(Lcom/threemdroid/habittracker/feature/activity/contract/ActivityPeriod;Ljava/time/LocalDate;Ljava/time/LocalDate;Ljava/time/LocalDate;Ljava/lang/String;)V", "getPeriod", "()Lcom/threemdroid/habittracker/feature/activity/contract/ActivityPeriod;", "getPeriodAnchorDate", "()Ljava/time/LocalDate;", "getPeriodStartDate", "getPeriodEndDate", "getSelectedHabitId", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "", "toString", "activity_debug"})
    public static final class ConfigurationChanged implements com.threemdroid.habittracker.feature.activity.presentation.ActivityResult {
        @org.jetbrains.annotations.NotNull()
        private final com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod period = null;
        @org.jetbrains.annotations.NotNull()
        private final java.time.LocalDate periodAnchorDate = null;
        @org.jetbrains.annotations.NotNull()
        private final java.time.LocalDate periodStartDate = null;
        @org.jetbrains.annotations.NotNull()
        private final java.time.LocalDate periodEndDate = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String selectedHabitId = null;
        
        public ConfigurationChanged(@org.jetbrains.annotations.NotNull()
        com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod period, @org.jetbrains.annotations.NotNull()
        java.time.LocalDate periodAnchorDate, @org.jetbrains.annotations.NotNull()
        java.time.LocalDate periodStartDate, @org.jetbrains.annotations.NotNull()
        java.time.LocalDate periodEndDate, @org.jetbrains.annotations.Nullable()
        java.lang.String selectedHabitId) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod getPeriod() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.time.LocalDate getPeriodAnchorDate() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.time.LocalDate getPeriodStartDate() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.time.LocalDate getPeriodEndDate() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getSelectedHabitId() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.time.LocalDate component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.time.LocalDate component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.time.LocalDate component4() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.threemdroid.habittracker.feature.activity.presentation.ActivityResult.ConfigurationChanged copy(@org.jetbrains.annotations.NotNull()
        com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod period, @org.jetbrains.annotations.NotNull()
        java.time.LocalDate periodAnchorDate, @org.jetbrains.annotations.NotNull()
        java.time.LocalDate periodStartDate, @org.jetbrains.annotations.NotNull()
        java.time.LocalDate periodEndDate, @org.jetbrains.annotations.Nullable()
        java.lang.String selectedHabitId) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
}