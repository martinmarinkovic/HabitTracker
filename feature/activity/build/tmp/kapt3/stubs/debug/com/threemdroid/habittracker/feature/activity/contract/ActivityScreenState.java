package com.threemdroid.habittracker.feature.activity.contract;

import java.time.LocalDate;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0004\u0002\u0003\u0004\u0005\u0082\u0001\u0004\u0006\u0007\b\t\u00a8\u0006\n\u00c0\u0006\u0003"}, d2 = {"Lcom/threemdroid/habittracker/feature/activity/contract/ActivityScreenState;", "", "Loading", "Content", "Empty", "Error", "Lcom/threemdroid/habittracker/feature/activity/contract/ActivityScreenState$Content;", "Lcom/threemdroid/habittracker/feature/activity/contract/ActivityScreenState$Empty;", "Lcom/threemdroid/habittracker/feature/activity/contract/ActivityScreenState$Error;", "Lcom/threemdroid/habittracker/feature/activity/contract/ActivityScreenState$Loading;", "activity_debug"})
public abstract interface ActivityScreenState {
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u00c6\n\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u00d6\u0003J\t\u0010\b\u001a\u00020\tH\u00d6\u0001J\t\u0010\n\u001a\u00020\u000bH\u00d6\u0001\u00a8\u0006\f"}, d2 = {"Lcom/threemdroid/habittracker/feature/activity/contract/ActivityScreenState$Content;", "Lcom/threemdroid/habittracker/feature/activity/contract/ActivityScreenState;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "activity_debug"})
    public static final class Content implements com.threemdroid.habittracker.feature.activity.contract.ActivityScreenState {
        @org.jetbrains.annotations.NotNull()
        public static final com.threemdroid.habittracker.feature.activity.contract.ActivityScreenState.Content INSTANCE = null;
        
        private Content() {
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
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u00c6\n\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u00d6\u0003J\t\u0010\b\u001a\u00020\tH\u00d6\u0001J\t\u0010\n\u001a\u00020\u000bH\u00d6\u0001\u00a8\u0006\f"}, d2 = {"Lcom/threemdroid/habittracker/feature/activity/contract/ActivityScreenState$Empty;", "Lcom/threemdroid/habittracker/feature/activity/contract/ActivityScreenState;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "activity_debug"})
    public static final class Empty implements com.threemdroid.habittracker.feature.activity.contract.ActivityScreenState {
        @org.jetbrains.annotations.NotNull()
        public static final com.threemdroid.habittracker.feature.activity.contract.ActivityScreenState.Empty INSTANCE = null;
        
        private Empty() {
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
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u00d6\u0003J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0012"}, d2 = {"Lcom/threemdroid/habittracker/feature/activity/contract/ActivityScreenState$Error;", "Lcom/threemdroid/habittracker/feature/activity/contract/ActivityScreenState;", "error", "Lcom/threemdroid/habittracker/feature/activity/contract/ActivityLoadError;", "<init>", "(Lcom/threemdroid/habittracker/feature/activity/contract/ActivityLoadError;)V", "getError", "()Lcom/threemdroid/habittracker/feature/activity/contract/ActivityLoadError;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "activity_debug"})
    public static final class Error implements com.threemdroid.habittracker.feature.activity.contract.ActivityScreenState {
        @org.jetbrains.annotations.NotNull()
        private final com.threemdroid.habittracker.feature.activity.contract.ActivityLoadError error = null;
        
        public Error(@org.jetbrains.annotations.NotNull()
        com.threemdroid.habittracker.feature.activity.contract.ActivityLoadError error) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.threemdroid.habittracker.feature.activity.contract.ActivityLoadError getError() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.threemdroid.habittracker.feature.activity.contract.ActivityLoadError component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.threemdroid.habittracker.feature.activity.contract.ActivityScreenState.Error copy(@org.jetbrains.annotations.NotNull()
        com.threemdroid.habittracker.feature.activity.contract.ActivityLoadError error) {
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
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u00c6\n\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u00d6\u0003J\t\u0010\b\u001a\u00020\tH\u00d6\u0001J\t\u0010\n\u001a\u00020\u000bH\u00d6\u0001\u00a8\u0006\f"}, d2 = {"Lcom/threemdroid/habittracker/feature/activity/contract/ActivityScreenState$Loading;", "Lcom/threemdroid/habittracker/feature/activity/contract/ActivityScreenState;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "activity_debug"})
    public static final class Loading implements com.threemdroid.habittracker.feature.activity.contract.ActivityScreenState {
        @org.jetbrains.annotations.NotNull()
        public static final com.threemdroid.habittracker.feature.activity.contract.ActivityScreenState.Loading INSTANCE = null;
        
        private Loading() {
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