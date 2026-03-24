package com.threemdroid.habittracker.core.database.dao;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003H\'J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004H\u00a7@\u00a2\u0006\u0002\u0010\b\u00a8\u0006\t\u00c0\u0006\u0003"}, d2 = {"Lcom/threemdroid/habittracker/core/database/dao/SettingsDao;", "", "observeSettings", "Lkotlinx/coroutines/flow/Flow;", "Lcom/threemdroid/habittracker/core/database/model/SettingsEntity;", "upsertSettings", "", "settings", "(Lcom/threemdroid/habittracker/core/database/model/SettingsEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "database_debug"})
@androidx.room.Dao()
public abstract interface SettingsDao {
    
    @androidx.room.Query(value = "\n        SELECT * FROM settings\n        WHERE settings_id = 0\n        LIMIT 1\n        ")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.threemdroid.habittracker.core.database.model.SettingsEntity> observeSettings();
    
    @androidx.room.Upsert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object upsertSettings(@org.jetbrains.annotations.NotNull()
    com.threemdroid.habittracker.core.database.model.SettingsEntity settings, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}