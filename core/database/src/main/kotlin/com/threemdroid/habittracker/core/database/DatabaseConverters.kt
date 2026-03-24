package com.threemdroid.habittracker.core.database

import androidx.room.TypeConverter
import com.threemdroid.habittracker.core.model.habits.HabitState
import com.threemdroid.habittracker.core.model.habits.HabitType
import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime

class DatabaseConverters {
    @TypeConverter
    fun instantToEpochMillis(value: Instant?): Long? = value?.toEpochMilli()

    @TypeConverter
    fun epochMillisToInstant(value: Long?): Instant? = value?.let(Instant::ofEpochMilli)

    @TypeConverter
    fun localDateToEpochDay(value: LocalDate?): Long? = value?.toEpochDay()

    @TypeConverter
    fun epochDayToLocalDate(value: Long?): LocalDate? = value?.let(LocalDate::ofEpochDay)

    @TypeConverter
    fun localTimeToSecondOfDay(value: LocalTime?): Int? = value?.toSecondOfDay()

    @TypeConverter
    fun secondOfDayToLocalTime(value: Int?): LocalTime? = value?.let { secondOfDay ->
        LocalTime.ofSecondOfDay(secondOfDay.toLong())
    }

    @TypeConverter
    fun habitTypeToString(value: HabitType?): String? = value?.name

    @TypeConverter
    fun stringToHabitType(value: String?): HabitType? = value?.let(HabitType::valueOf)

    @TypeConverter
    fun habitStateToString(value: HabitState?): String? = value?.name

    @TypeConverter
    fun stringToHabitState(value: String?): HabitState? = value?.let(HabitState::valueOf)

    @TypeConverter
    fun dayOfWeekSetToString(value: Set<DayOfWeek>?): String? =
        value
            ?.sortedBy { dayOfWeek -> dayOfWeek.value }
            ?.joinToString(separator = ",") { dayOfWeek -> dayOfWeek.name }

    @TypeConverter
    fun stringToDayOfWeekSet(value: String?): Set<DayOfWeek> =
        value
            ?.takeIf(String::isNotBlank)
            ?.split(",")
            ?.map(String::trim)
            ?.filter(String::isNotBlank)
            ?.map(DayOfWeek::valueOf)
            ?.toSet()
            ?: emptySet()
}
