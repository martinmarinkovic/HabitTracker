package com.threemdroid.habittracker.core.database;

import androidx.annotation.NonNull;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenDelegate;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteConnection;
import com.threemdroid.habittracker.core.database.dao.HabitDao;
import com.threemdroid.habittracker.core.database.dao.HabitDao_Impl;
import com.threemdroid.habittracker.core.database.dao.HabitEntryDao;
import com.threemdroid.habittracker.core.database.dao.HabitEntryDao_Impl;
import com.threemdroid.habittracker.core.database.dao.MoodEntryDao;
import com.threemdroid.habittracker.core.database.dao.MoodEntryDao_Impl;
import com.threemdroid.habittracker.core.database.dao.ReminderDao;
import com.threemdroid.habittracker.core.database.dao.ReminderDao_Impl;
import com.threemdroid.habittracker.core.database.dao.SettingsDao;
import com.threemdroid.habittracker.core.database.dao.SettingsDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class HabitTrackerDatabase_Impl extends HabitTrackerDatabase {
  private volatile HabitDao _habitDao;

  private volatile HabitEntryDao _habitEntryDao;

  private volatile MoodEntryDao _moodEntryDao;

  private volatile ReminderDao _reminderDao;

  private volatile SettingsDao _settingsDao;

  @Override
  @NonNull
  protected RoomOpenDelegate createOpenDelegate() {
    final RoomOpenDelegate _openDelegate = new RoomOpenDelegate(1, "891fb8cdc1d21d2ecb91bfba0cbec7cb", "ffd64017675a5ebb29deccdae464de53") {
      @Override
      public void createAllTables(@NonNull final SQLiteConnection connection) {
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `habits` (`habit_id` TEXT NOT NULL, `name` TEXT NOT NULL, `habit_type` TEXT NOT NULL, `target_value` REAL NOT NULL, `default_increment` REAL NOT NULL, `unit_label` TEXT, `allows_multiple_updates_per_day` INTEGER NOT NULL, `selected_icon_token` TEXT NOT NULL, `selected_color_token` TEXT NOT NULL, `selected_days` TEXT NOT NULL, `state` TEXT NOT NULL, `created_at_epoch_millis` INTEGER NOT NULL, `stopped_at_epoch_millis` INTEGER, PRIMARY KEY(`habit_id`))");
        SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_habits_state` ON `habits` (`state`)");
        SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_habits_created_at_epoch_millis` ON `habits` (`created_at_epoch_millis`)");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `habit_entries` (`entry_id` TEXT NOT NULL, `habit_id` TEXT NOT NULL, `entry_date_epoch_day` INTEGER NOT NULL, `logged_at_epoch_millis` INTEGER NOT NULL, `value` REAL NOT NULL, PRIMARY KEY(`entry_id`), FOREIGN KEY(`habit_id`) REFERENCES `habits`(`habit_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_habit_entries_habit_id` ON `habit_entries` (`habit_id`)");
        SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_habit_entries_habit_id_entry_date_epoch_day` ON `habit_entries` (`habit_id`, `entry_date_epoch_day`)");
        SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_habit_entries_logged_at_epoch_millis` ON `habit_entries` (`logged_at_epoch_millis`)");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `mood_entries` (`mood_entry_id` TEXT NOT NULL, `entry_date_epoch_day` INTEGER NOT NULL, `logged_at_epoch_millis` INTEGER NOT NULL, `mood_token` TEXT NOT NULL, PRIMARY KEY(`mood_entry_id`))");
        SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_mood_entries_entry_date_epoch_day` ON `mood_entries` (`entry_date_epoch_day`)");
        SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_mood_entries_logged_at_epoch_millis` ON `mood_entries` (`logged_at_epoch_millis`)");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `reminders` (`reminder_id` TEXT NOT NULL, `habit_id` TEXT NOT NULL, `time_second_of_day` INTEGER NOT NULL, `selected_days` TEXT NOT NULL, `is_enabled` INTEGER NOT NULL, PRIMARY KEY(`reminder_id`), FOREIGN KEY(`habit_id`) REFERENCES `habits`(`habit_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_reminders_habit_id` ON `reminders` (`habit_id`)");
        SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_reminders_habit_id_is_enabled` ON `reminders` (`habit_id`, `is_enabled`)");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `settings` (`settings_id` INTEGER NOT NULL, `reminder_notifications_enabled` INTEGER NOT NULL, PRIMARY KEY(`settings_id`))");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        SQLite.execSQL(connection, "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '891fb8cdc1d21d2ecb91bfba0cbec7cb')");
      }

      @Override
      public void dropAllTables(@NonNull final SQLiteConnection connection) {
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `habits`");
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `habit_entries`");
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `mood_entries`");
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `reminders`");
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `settings`");
      }

      @Override
      public void onCreate(@NonNull final SQLiteConnection connection) {
      }

      @Override
      public void onOpen(@NonNull final SQLiteConnection connection) {
        SQLite.execSQL(connection, "PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(connection);
      }

      @Override
      public void onPreMigrate(@NonNull final SQLiteConnection connection) {
        DBUtil.dropFtsSyncTriggers(connection);
      }

      @Override
      public void onPostMigrate(@NonNull final SQLiteConnection connection) {
      }

      @Override
      @NonNull
      public RoomOpenDelegate.ValidationResult onValidateSchema(
          @NonNull final SQLiteConnection connection) {
        final Map<String, TableInfo.Column> _columnsHabits = new HashMap<String, TableInfo.Column>(13);
        _columnsHabits.put("habit_id", new TableInfo.Column("habit_id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHabits.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHabits.put("habit_type", new TableInfo.Column("habit_type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHabits.put("target_value", new TableInfo.Column("target_value", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHabits.put("default_increment", new TableInfo.Column("default_increment", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHabits.put("unit_label", new TableInfo.Column("unit_label", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHabits.put("allows_multiple_updates_per_day", new TableInfo.Column("allows_multiple_updates_per_day", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHabits.put("selected_icon_token", new TableInfo.Column("selected_icon_token", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHabits.put("selected_color_token", new TableInfo.Column("selected_color_token", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHabits.put("selected_days", new TableInfo.Column("selected_days", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHabits.put("state", new TableInfo.Column("state", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHabits.put("created_at_epoch_millis", new TableInfo.Column("created_at_epoch_millis", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHabits.put("stopped_at_epoch_millis", new TableInfo.Column("stopped_at_epoch_millis", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysHabits = new HashSet<TableInfo.ForeignKey>(0);
        final Set<TableInfo.Index> _indicesHabits = new HashSet<TableInfo.Index>(2);
        _indicesHabits.add(new TableInfo.Index("index_habits_state", false, Arrays.asList("state"), Arrays.asList("ASC")));
        _indicesHabits.add(new TableInfo.Index("index_habits_created_at_epoch_millis", false, Arrays.asList("created_at_epoch_millis"), Arrays.asList("ASC")));
        final TableInfo _infoHabits = new TableInfo("habits", _columnsHabits, _foreignKeysHabits, _indicesHabits);
        final TableInfo _existingHabits = TableInfo.read(connection, "habits");
        if (!_infoHabits.equals(_existingHabits)) {
          return new RoomOpenDelegate.ValidationResult(false, "habits(com.threemdroid.habittracker.core.database.model.HabitEntity).\n"
                  + " Expected:\n" + _infoHabits + "\n"
                  + " Found:\n" + _existingHabits);
        }
        final Map<String, TableInfo.Column> _columnsHabitEntries = new HashMap<String, TableInfo.Column>(5);
        _columnsHabitEntries.put("entry_id", new TableInfo.Column("entry_id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHabitEntries.put("habit_id", new TableInfo.Column("habit_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHabitEntries.put("entry_date_epoch_day", new TableInfo.Column("entry_date_epoch_day", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHabitEntries.put("logged_at_epoch_millis", new TableInfo.Column("logged_at_epoch_millis", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHabitEntries.put("value", new TableInfo.Column("value", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysHabitEntries = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysHabitEntries.add(new TableInfo.ForeignKey("habits", "CASCADE", "NO ACTION", Arrays.asList("habit_id"), Arrays.asList("habit_id")));
        final Set<TableInfo.Index> _indicesHabitEntries = new HashSet<TableInfo.Index>(3);
        _indicesHabitEntries.add(new TableInfo.Index("index_habit_entries_habit_id", false, Arrays.asList("habit_id"), Arrays.asList("ASC")));
        _indicesHabitEntries.add(new TableInfo.Index("index_habit_entries_habit_id_entry_date_epoch_day", false, Arrays.asList("habit_id", "entry_date_epoch_day"), Arrays.asList("ASC", "ASC")));
        _indicesHabitEntries.add(new TableInfo.Index("index_habit_entries_logged_at_epoch_millis", false, Arrays.asList("logged_at_epoch_millis"), Arrays.asList("ASC")));
        final TableInfo _infoHabitEntries = new TableInfo("habit_entries", _columnsHabitEntries, _foreignKeysHabitEntries, _indicesHabitEntries);
        final TableInfo _existingHabitEntries = TableInfo.read(connection, "habit_entries");
        if (!_infoHabitEntries.equals(_existingHabitEntries)) {
          return new RoomOpenDelegate.ValidationResult(false, "habit_entries(com.threemdroid.habittracker.core.database.model.HabitEntryEntity).\n"
                  + " Expected:\n" + _infoHabitEntries + "\n"
                  + " Found:\n" + _existingHabitEntries);
        }
        final Map<String, TableInfo.Column> _columnsMoodEntries = new HashMap<String, TableInfo.Column>(4);
        _columnsMoodEntries.put("mood_entry_id", new TableInfo.Column("mood_entry_id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMoodEntries.put("entry_date_epoch_day", new TableInfo.Column("entry_date_epoch_day", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMoodEntries.put("logged_at_epoch_millis", new TableInfo.Column("logged_at_epoch_millis", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMoodEntries.put("mood_token", new TableInfo.Column("mood_token", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysMoodEntries = new HashSet<TableInfo.ForeignKey>(0);
        final Set<TableInfo.Index> _indicesMoodEntries = new HashSet<TableInfo.Index>(2);
        _indicesMoodEntries.add(new TableInfo.Index("index_mood_entries_entry_date_epoch_day", false, Arrays.asList("entry_date_epoch_day"), Arrays.asList("ASC")));
        _indicesMoodEntries.add(new TableInfo.Index("index_mood_entries_logged_at_epoch_millis", false, Arrays.asList("logged_at_epoch_millis"), Arrays.asList("ASC")));
        final TableInfo _infoMoodEntries = new TableInfo("mood_entries", _columnsMoodEntries, _foreignKeysMoodEntries, _indicesMoodEntries);
        final TableInfo _existingMoodEntries = TableInfo.read(connection, "mood_entries");
        if (!_infoMoodEntries.equals(_existingMoodEntries)) {
          return new RoomOpenDelegate.ValidationResult(false, "mood_entries(com.threemdroid.habittracker.core.database.model.MoodEntryEntity).\n"
                  + " Expected:\n" + _infoMoodEntries + "\n"
                  + " Found:\n" + _existingMoodEntries);
        }
        final Map<String, TableInfo.Column> _columnsReminders = new HashMap<String, TableInfo.Column>(5);
        _columnsReminders.put("reminder_id", new TableInfo.Column("reminder_id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("habit_id", new TableInfo.Column("habit_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("time_second_of_day", new TableInfo.Column("time_second_of_day", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("selected_days", new TableInfo.Column("selected_days", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("is_enabled", new TableInfo.Column("is_enabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysReminders = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysReminders.add(new TableInfo.ForeignKey("habits", "CASCADE", "NO ACTION", Arrays.asList("habit_id"), Arrays.asList("habit_id")));
        final Set<TableInfo.Index> _indicesReminders = new HashSet<TableInfo.Index>(2);
        _indicesReminders.add(new TableInfo.Index("index_reminders_habit_id", false, Arrays.asList("habit_id"), Arrays.asList("ASC")));
        _indicesReminders.add(new TableInfo.Index("index_reminders_habit_id_is_enabled", false, Arrays.asList("habit_id", "is_enabled"), Arrays.asList("ASC", "ASC")));
        final TableInfo _infoReminders = new TableInfo("reminders", _columnsReminders, _foreignKeysReminders, _indicesReminders);
        final TableInfo _existingReminders = TableInfo.read(connection, "reminders");
        if (!_infoReminders.equals(_existingReminders)) {
          return new RoomOpenDelegate.ValidationResult(false, "reminders(com.threemdroid.habittracker.core.database.model.ReminderEntity).\n"
                  + " Expected:\n" + _infoReminders + "\n"
                  + " Found:\n" + _existingReminders);
        }
        final Map<String, TableInfo.Column> _columnsSettings = new HashMap<String, TableInfo.Column>(2);
        _columnsSettings.put("settings_id", new TableInfo.Column("settings_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSettings.put("reminder_notifications_enabled", new TableInfo.Column("reminder_notifications_enabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysSettings = new HashSet<TableInfo.ForeignKey>(0);
        final Set<TableInfo.Index> _indicesSettings = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSettings = new TableInfo("settings", _columnsSettings, _foreignKeysSettings, _indicesSettings);
        final TableInfo _existingSettings = TableInfo.read(connection, "settings");
        if (!_infoSettings.equals(_existingSettings)) {
          return new RoomOpenDelegate.ValidationResult(false, "settings(com.threemdroid.habittracker.core.database.model.SettingsEntity).\n"
                  + " Expected:\n" + _infoSettings + "\n"
                  + " Found:\n" + _existingSettings);
        }
        return new RoomOpenDelegate.ValidationResult(true, null);
      }
    };
    return _openDelegate;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final Map<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final Map<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "habits", "habit_entries", "mood_entries", "reminders", "settings");
  }

  @Override
  public void clearAllTables() {
    super.performClear(true, "habits", "habit_entries", "mood_entries", "reminders", "settings");
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final Map<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(HabitDao.class, HabitDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(HabitEntryDao.class, HabitEntryDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(MoodEntryDao.class, MoodEntryDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ReminderDao.class, ReminderDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(SettingsDao.class, SettingsDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final Set<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public HabitDao habitDao() {
    if (_habitDao != null) {
      return _habitDao;
    } else {
      synchronized(this) {
        if(_habitDao == null) {
          _habitDao = new HabitDao_Impl(this);
        }
        return _habitDao;
      }
    }
  }

  @Override
  public HabitEntryDao habitEntryDao() {
    if (_habitEntryDao != null) {
      return _habitEntryDao;
    } else {
      synchronized(this) {
        if(_habitEntryDao == null) {
          _habitEntryDao = new HabitEntryDao_Impl(this);
        }
        return _habitEntryDao;
      }
    }
  }

  @Override
  public MoodEntryDao moodEntryDao() {
    if (_moodEntryDao != null) {
      return _moodEntryDao;
    } else {
      synchronized(this) {
        if(_moodEntryDao == null) {
          _moodEntryDao = new MoodEntryDao_Impl(this);
        }
        return _moodEntryDao;
      }
    }
  }

  @Override
  public ReminderDao reminderDao() {
    if (_reminderDao != null) {
      return _reminderDao;
    } else {
      synchronized(this) {
        if(_reminderDao == null) {
          _reminderDao = new ReminderDao_Impl(this);
        }
        return _reminderDao;
      }
    }
  }

  @Override
  public SettingsDao settingsDao() {
    if (_settingsDao != null) {
      return _settingsDao;
    } else {
      synchronized(this) {
        if(_settingsDao == null) {
          _settingsDao = new SettingsDao_Impl(this);
        }
        return _settingsDao;
      }
    }
  }
}
