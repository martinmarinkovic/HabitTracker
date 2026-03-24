package com.threemdroid.habittracker.feature.activity.contract;

import java.time.LocalDate;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0005\u0002\u0003\u0004\u0005\u0006\u0082\u0001\u0005\u0007\b\t\n\u000b\u00a8\u0006\f\u00c0\u0006\u0003"}, d2 = {"Lcom/threemdroid/habittracker/feature/activity/contract/ActivityIntent;", "", "PeriodSelected", "PreviousPeriodRequested", "NextPeriodRequested", "HabitFilterSelected", "RetryLoad", "Lcom/threemdroid/habittracker/feature/activity/contract/ActivityIntent$HabitFilterSelected;", "Lcom/threemdroid/habittracker/feature/activity/contract/ActivityIntent$NextPeriodRequested;", "Lcom/threemdroid/habittracker/feature/activity/contract/ActivityIntent$PeriodSelected;", "Lcom/threemdroid/habittracker/feature/activity/contract/ActivityIntent$PreviousPeriodRequested;", "Lcom/threemdroid/habittracker/feature/activity/contract/ActivityIntent$RetryLoad;", "activity_debug"})
public abstract interface ActivityIntent {
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u00d6\u0003J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001J\t\u0010\u0010\u001a\u00020\u0003H\u00d6\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0011"}, d2 = {"Lcom/threemdroid/habittracker/feature/activity/contract/ActivityIntent$HabitFilterSelected;", "Lcom/threemdroid/habittracker/feature/activity/contract/ActivityIntent;", "habitId", "", "<init>", "(Ljava/lang/String;)V", "getHabitId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "activity_debug"})
    public static final class HabitFilterSelected implements com.threemdroid.habittracker.feature.activity.contract.ActivityIntent {
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String habitId = null;
        
        public HabitFilterSelected(@org.jetbrains.annotations.Nullable()
        java.lang.String habitId) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getHabitId() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.threemdroid.habittracker.feature.activity.contract.ActivityIntent.HabitFilterSelected copy(@org.jetbrains.annotations.Nullable()
        java.lang.String habitId) {
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
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u00c6\n\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u00d6\u0003J\t\u0010\b\u001a\u00020\tH\u00d6\u0001J\t\u0010\n\u001a\u00020\u000bH\u00d6\u0001\u00a8\u0006\f"}, d2 = {"Lcom/threemdroid/habittracker/feature/activity/contract/ActivityIntent$NextPeriodRequested;", "Lcom/threemdroid/habittracker/feature/activity/contract/ActivityIntent;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "activity_debug"})
    public static final class NextPeriodRequested implements com.threemdroid.habittracker.feature.activity.contract.ActivityIntent {
        @org.jetbrains.annotations.NotNull()
        public static final com.threemdroid.habittracker.feature.activity.contract.ActivityIntent.NextPeriodRequested INSTANCE = null;
        
        private NextPeriodRequested() {
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
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u00d6\u0003J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0012"}, d2 = {"Lcom/threemdroid/habittracker/feature/activity/contract/ActivityIntent$PeriodSelected;", "Lcom/threemdroid/habittracker/feature/activity/contract/ActivityIntent;", "period", "Lcom/threemdroid/habittracker/feature/activity/contract/ActivityPeriod;", "<init>", "(Lcom/threemdroid/habittracker/feature/activity/contract/ActivityPeriod;)V", "getPeriod", "()Lcom/threemdroid/habittracker/feature/activity/contract/ActivityPeriod;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "activity_debug"})
    public static final class PeriodSelected implements com.threemdroid.habittracker.feature.activity.contract.ActivityIntent {
        @org.jetbrains.annotations.NotNull()
        private final com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod period = null;
        
        public PeriodSelected(@org.jetbrains.annotations.NotNull()
        com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod period) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod getPeriod() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.threemdroid.habittracker.feature.activity.contract.ActivityIntent.PeriodSelected copy(@org.jetbrains.annotations.NotNull()
        com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod period) {
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
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u00c6\n\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u00d6\u0003J\t\u0010\b\u001a\u00020\tH\u00d6\u0001J\t\u0010\n\u001a\u00020\u000bH\u00d6\u0001\u00a8\u0006\f"}, d2 = {"Lcom/threemdroid/habittracker/feature/activity/contract/ActivityIntent$PreviousPeriodRequested;", "Lcom/threemdroid/habittracker/feature/activity/contract/ActivityIntent;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "activity_debug"})
    public static final class PreviousPeriodRequested implements com.threemdroid.habittracker.feature.activity.contract.ActivityIntent {
        @org.jetbrains.annotations.NotNull()
        public static final com.threemdroid.habittracker.feature.activity.contract.ActivityIntent.PreviousPeriodRequested INSTANCE = null;
        
        private PreviousPeriodRequested() {
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
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u00c6\n\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u00d6\u0003J\t\u0010\b\u001a\u00020\tH\u00d6\u0001J\t\u0010\n\u001a\u00020\u000bH\u00d6\u0001\u00a8\u0006\f"}, d2 = {"Lcom/threemdroid/habittracker/feature/activity/contract/ActivityIntent$RetryLoad;", "Lcom/threemdroid/habittracker/feature/activity/contract/ActivityIntent;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "activity_debug"})
    public static final class RetryLoad implements com.threemdroid.habittracker.feature.activity.contract.ActivityIntent {
        @org.jetbrains.annotations.NotNull()
        public static final com.threemdroid.habittracker.feature.activity.contract.ActivityIntent.RetryLoad INSTANCE = null;
        
        private RetryLoad() {
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
}