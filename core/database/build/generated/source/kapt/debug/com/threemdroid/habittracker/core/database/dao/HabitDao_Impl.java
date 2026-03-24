package com.threemdroid.habittracker.core.database.dao;

import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.EntityUpsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.RelationUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import com.threemdroid.habittracker.core.database.DatabaseConverters;
import com.threemdroid.habittracker.core.database.model.HabitEntity;
import com.threemdroid.habittracker.core.database.model.ReminderEntity;
import com.threemdroid.habittracker.core.database.relation.HabitWithReminders;
import com.threemdroid.habittracker.core.model.habits.HabitState;
import com.threemdroid.habittracker.core.model.habits.HabitType;
import java.lang.Class;
import java.lang.Integer;
import java.lang.Long;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class HabitDao_Impl implements HabitDao {
  private final RoomDatabase __db;

  private final EntityUpsertAdapter<HabitEntity> __upsertAdapterOfHabitEntity;

  private final DatabaseConverters __databaseConverters = new DatabaseConverters();

  public HabitDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__upsertAdapterOfHabitEntity = new EntityUpsertAdapter<HabitEntity>(new EntityInsertAdapter<HabitEntity>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT INTO `habits` (`habit_id`,`name`,`habit_type`,`target_value`,`default_increment`,`unit_label`,`allows_multiple_updates_per_day`,`selected_icon_token`,`selected_color_token`,`selected_days`,`state`,`created_at_epoch_millis`,`stopped_at_epoch_millis`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final HabitEntity entity) {
        if (entity.getHabitId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getHabitId());
        }
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getName());
        }
        final String _tmp = __databaseConverters.habitTypeToString(entity.getHabitType());
        if (_tmp == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, _tmp);
        }
        statement.bindDouble(4, entity.getTargetValue());
        statement.bindDouble(5, entity.getDefaultIncrement());
        if (entity.getUnitLabel() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getUnitLabel());
        }
        final int _tmp_1 = entity.getAllowsMultipleUpdatesPerDay() ? 1 : 0;
        statement.bindLong(7, _tmp_1);
        if (entity.getSelectedIconToken() == null) {
          statement.bindNull(8);
        } else {
          statement.bindText(8, entity.getSelectedIconToken());
        }
        if (entity.getSelectedColorToken() == null) {
          statement.bindNull(9);
        } else {
          statement.bindText(9, entity.getSelectedColorToken());
        }
        final String _tmp_2 = __databaseConverters.dayOfWeekSetToString(entity.getSelectedDays());
        if (_tmp_2 == null) {
          statement.bindNull(10);
        } else {
          statement.bindText(10, _tmp_2);
        }
        final String _tmp_3 = __databaseConverters.habitStateToString(entity.getState());
        if (_tmp_3 == null) {
          statement.bindNull(11);
        } else {
          statement.bindText(11, _tmp_3);
        }
        final Long _tmp_4 = __databaseConverters.instantToEpochMillis(entity.getCreatedAt());
        if (_tmp_4 == null) {
          statement.bindNull(12);
        } else {
          statement.bindLong(12, _tmp_4);
        }
        final Long _tmp_5 = __databaseConverters.instantToEpochMillis(entity.getStoppedAt());
        if (_tmp_5 == null) {
          statement.bindNull(13);
        } else {
          statement.bindLong(13, _tmp_5);
        }
      }
    }, new EntityDeleteOrUpdateAdapter<HabitEntity>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE `habits` SET `habit_id` = ?,`name` = ?,`habit_type` = ?,`target_value` = ?,`default_increment` = ?,`unit_label` = ?,`allows_multiple_updates_per_day` = ?,`selected_icon_token` = ?,`selected_color_token` = ?,`selected_days` = ?,`state` = ?,`created_at_epoch_millis` = ?,`stopped_at_epoch_millis` = ? WHERE `habit_id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final HabitEntity entity) {
        if (entity.getHabitId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getHabitId());
        }
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getName());
        }
        final String _tmp = __databaseConverters.habitTypeToString(entity.getHabitType());
        if (_tmp == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, _tmp);
        }
        statement.bindDouble(4, entity.getTargetValue());
        statement.bindDouble(5, entity.getDefaultIncrement());
        if (entity.getUnitLabel() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getUnitLabel());
        }
        final int _tmp_1 = entity.getAllowsMultipleUpdatesPerDay() ? 1 : 0;
        statement.bindLong(7, _tmp_1);
        if (entity.getSelectedIconToken() == null) {
          statement.bindNull(8);
        } else {
          statement.bindText(8, entity.getSelectedIconToken());
        }
        if (entity.getSelectedColorToken() == null) {
          statement.bindNull(9);
        } else {
          statement.bindText(9, entity.getSelectedColorToken());
        }
        final String _tmp_2 = __databaseConverters.dayOfWeekSetToString(entity.getSelectedDays());
        if (_tmp_2 == null) {
          statement.bindNull(10);
        } else {
          statement.bindText(10, _tmp_2);
        }
        final String _tmp_3 = __databaseConverters.habitStateToString(entity.getState());
        if (_tmp_3 == null) {
          statement.bindNull(11);
        } else {
          statement.bindText(11, _tmp_3);
        }
        final Long _tmp_4 = __databaseConverters.instantToEpochMillis(entity.getCreatedAt());
        if (_tmp_4 == null) {
          statement.bindNull(12);
        } else {
          statement.bindLong(12, _tmp_4);
        }
        final Long _tmp_5 = __databaseConverters.instantToEpochMillis(entity.getStoppedAt());
        if (_tmp_5 == null) {
          statement.bindNull(13);
        } else {
          statement.bindLong(13, _tmp_5);
        }
        if (entity.getHabitId() == null) {
          statement.bindNull(14);
        } else {
          statement.bindText(14, entity.getHabitId());
        }
      }
    });
  }

  @Override
  public Object upsertHabit(final HabitEntity habit, final Continuation<? super Unit> $completion) {
    if (habit == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __upsertAdapterOfHabitEntity.upsert(_connection, habit);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Flow<List<HabitWithReminders>> observeHabitsWithReminders() {
    final String _sql = "SELECT * FROM habits ORDER BY created_at_epoch_millis DESC";
    return FlowUtil.createFlow(__db, true, new String[] {"reminders", "habits"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfHabitId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "habit_id");
        final int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
        final int _columnIndexOfHabitType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "habit_type");
        final int _columnIndexOfTargetValue = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "target_value");
        final int _columnIndexOfDefaultIncrement = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "default_increment");
        final int _columnIndexOfUnitLabel = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "unit_label");
        final int _columnIndexOfAllowsMultipleUpdatesPerDay = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "allows_multiple_updates_per_day");
        final int _columnIndexOfSelectedIconToken = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "selected_icon_token");
        final int _columnIndexOfSelectedColorToken = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "selected_color_token");
        final int _columnIndexOfSelectedDays = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "selected_days");
        final int _columnIndexOfState = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "state");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "created_at_epoch_millis");
        final int _columnIndexOfStoppedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "stopped_at_epoch_millis");
        final ArrayMap<String, ArrayList<ReminderEntity>> _collectionReminders = new ArrayMap<String, ArrayList<ReminderEntity>>();
        while (_stmt.step()) {
          final String _tmpKey;
          if (_stmt.isNull(_columnIndexOfHabitId)) {
            _tmpKey = null;
          } else {
            _tmpKey = _stmt.getText(_columnIndexOfHabitId);
          }
          if (_tmpKey != null) {
            if (!_collectionReminders.containsKey(_tmpKey)) {
              _collectionReminders.put(_tmpKey, new ArrayList<ReminderEntity>());
            }
          }
        }
        _stmt.reset();
        __fetchRelationshipremindersAscomThreemdroidHabittrackerCoreDatabaseModelReminderEntity(_connection, _collectionReminders);
        final List<HabitWithReminders> _result = new ArrayList<HabitWithReminders>();
        while (_stmt.step()) {
          final HabitWithReminders _item;
          final HabitEntity _tmpHabit;
          final String _tmpHabitId;
          if (_stmt.isNull(_columnIndexOfHabitId)) {
            _tmpHabitId = null;
          } else {
            _tmpHabitId = _stmt.getText(_columnIndexOfHabitId);
          }
          final String _tmpName;
          if (_stmt.isNull(_columnIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = _stmt.getText(_columnIndexOfName);
          }
          final HabitType _tmpHabitType;
          final String _tmp;
          if (_stmt.isNull(_columnIndexOfHabitType)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getText(_columnIndexOfHabitType);
          }
          _tmpHabitType = __databaseConverters.stringToHabitType(_tmp);
          final double _tmpTargetValue;
          _tmpTargetValue = _stmt.getDouble(_columnIndexOfTargetValue);
          final double _tmpDefaultIncrement;
          _tmpDefaultIncrement = _stmt.getDouble(_columnIndexOfDefaultIncrement);
          final String _tmpUnitLabel;
          if (_stmt.isNull(_columnIndexOfUnitLabel)) {
            _tmpUnitLabel = null;
          } else {
            _tmpUnitLabel = _stmt.getText(_columnIndexOfUnitLabel);
          }
          final boolean _tmpAllowsMultipleUpdatesPerDay;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfAllowsMultipleUpdatesPerDay));
          _tmpAllowsMultipleUpdatesPerDay = _tmp_1 != 0;
          final String _tmpSelectedIconToken;
          if (_stmt.isNull(_columnIndexOfSelectedIconToken)) {
            _tmpSelectedIconToken = null;
          } else {
            _tmpSelectedIconToken = _stmt.getText(_columnIndexOfSelectedIconToken);
          }
          final String _tmpSelectedColorToken;
          if (_stmt.isNull(_columnIndexOfSelectedColorToken)) {
            _tmpSelectedColorToken = null;
          } else {
            _tmpSelectedColorToken = _stmt.getText(_columnIndexOfSelectedColorToken);
          }
          final Set<DayOfWeek> _tmpSelectedDays;
          final String _tmp_2;
          if (_stmt.isNull(_columnIndexOfSelectedDays)) {
            _tmp_2 = null;
          } else {
            _tmp_2 = _stmt.getText(_columnIndexOfSelectedDays);
          }
          _tmpSelectedDays = __databaseConverters.stringToDayOfWeekSet(_tmp_2);
          final HabitState _tmpState;
          final String _tmp_3;
          if (_stmt.isNull(_columnIndexOfState)) {
            _tmp_3 = null;
          } else {
            _tmp_3 = _stmt.getText(_columnIndexOfState);
          }
          _tmpState = __databaseConverters.stringToHabitState(_tmp_3);
          final Instant _tmpCreatedAt;
          final Long _tmp_4;
          if (_stmt.isNull(_columnIndexOfCreatedAt)) {
            _tmp_4 = null;
          } else {
            _tmp_4 = _stmt.getLong(_columnIndexOfCreatedAt);
          }
          _tmpCreatedAt = __databaseConverters.epochMillisToInstant(_tmp_4);
          final Instant _tmpStoppedAt;
          final Long _tmp_5;
          if (_stmt.isNull(_columnIndexOfStoppedAt)) {
            _tmp_5 = null;
          } else {
            _tmp_5 = _stmt.getLong(_columnIndexOfStoppedAt);
          }
          _tmpStoppedAt = __databaseConverters.epochMillisToInstant(_tmp_5);
          _tmpHabit = new HabitEntity(_tmpHabitId,_tmpName,_tmpHabitType,_tmpTargetValue,_tmpDefaultIncrement,_tmpUnitLabel,_tmpAllowsMultipleUpdatesPerDay,_tmpSelectedIconToken,_tmpSelectedColorToken,_tmpSelectedDays,_tmpState,_tmpCreatedAt,_tmpStoppedAt);
          final ArrayList<ReminderEntity> _tmpRemindersCollection;
          final String _tmpKey_1;
          if (_stmt.isNull(_columnIndexOfHabitId)) {
            _tmpKey_1 = null;
          } else {
            _tmpKey_1 = _stmt.getText(_columnIndexOfHabitId);
          }
          if (_tmpKey_1 != null) {
            _tmpRemindersCollection = _collectionReminders.get(_tmpKey_1);
          } else {
            _tmpRemindersCollection = new ArrayList<ReminderEntity>();
          }
          _item = new HabitWithReminders(_tmpHabit,_tmpRemindersCollection);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<HabitWithReminders> observeHabitWithReminders(final String habitId) {
    final String _sql = "SELECT * FROM habits WHERE habit_id = ? LIMIT 1";
    return FlowUtil.createFlow(__db, true, new String[] {"reminders", "habits"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (habitId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, habitId);
        }
        final int _columnIndexOfHabitId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "habit_id");
        final int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
        final int _columnIndexOfHabitType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "habit_type");
        final int _columnIndexOfTargetValue = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "target_value");
        final int _columnIndexOfDefaultIncrement = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "default_increment");
        final int _columnIndexOfUnitLabel = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "unit_label");
        final int _columnIndexOfAllowsMultipleUpdatesPerDay = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "allows_multiple_updates_per_day");
        final int _columnIndexOfSelectedIconToken = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "selected_icon_token");
        final int _columnIndexOfSelectedColorToken = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "selected_color_token");
        final int _columnIndexOfSelectedDays = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "selected_days");
        final int _columnIndexOfState = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "state");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "created_at_epoch_millis");
        final int _columnIndexOfStoppedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "stopped_at_epoch_millis");
        final ArrayMap<String, ArrayList<ReminderEntity>> _collectionReminders = new ArrayMap<String, ArrayList<ReminderEntity>>();
        while (_stmt.step()) {
          final String _tmpKey;
          if (_stmt.isNull(_columnIndexOfHabitId)) {
            _tmpKey = null;
          } else {
            _tmpKey = _stmt.getText(_columnIndexOfHabitId);
          }
          if (_tmpKey != null) {
            if (!_collectionReminders.containsKey(_tmpKey)) {
              _collectionReminders.put(_tmpKey, new ArrayList<ReminderEntity>());
            }
          }
        }
        _stmt.reset();
        __fetchRelationshipremindersAscomThreemdroidHabittrackerCoreDatabaseModelReminderEntity(_connection, _collectionReminders);
        final HabitWithReminders _result;
        if (_stmt.step()) {
          final HabitEntity _tmpHabit;
          final String _tmpHabitId;
          if (_stmt.isNull(_columnIndexOfHabitId)) {
            _tmpHabitId = null;
          } else {
            _tmpHabitId = _stmt.getText(_columnIndexOfHabitId);
          }
          final String _tmpName;
          if (_stmt.isNull(_columnIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = _stmt.getText(_columnIndexOfName);
          }
          final HabitType _tmpHabitType;
          final String _tmp;
          if (_stmt.isNull(_columnIndexOfHabitType)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getText(_columnIndexOfHabitType);
          }
          _tmpHabitType = __databaseConverters.stringToHabitType(_tmp);
          final double _tmpTargetValue;
          _tmpTargetValue = _stmt.getDouble(_columnIndexOfTargetValue);
          final double _tmpDefaultIncrement;
          _tmpDefaultIncrement = _stmt.getDouble(_columnIndexOfDefaultIncrement);
          final String _tmpUnitLabel;
          if (_stmt.isNull(_columnIndexOfUnitLabel)) {
            _tmpUnitLabel = null;
          } else {
            _tmpUnitLabel = _stmt.getText(_columnIndexOfUnitLabel);
          }
          final boolean _tmpAllowsMultipleUpdatesPerDay;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfAllowsMultipleUpdatesPerDay));
          _tmpAllowsMultipleUpdatesPerDay = _tmp_1 != 0;
          final String _tmpSelectedIconToken;
          if (_stmt.isNull(_columnIndexOfSelectedIconToken)) {
            _tmpSelectedIconToken = null;
          } else {
            _tmpSelectedIconToken = _stmt.getText(_columnIndexOfSelectedIconToken);
          }
          final String _tmpSelectedColorToken;
          if (_stmt.isNull(_columnIndexOfSelectedColorToken)) {
            _tmpSelectedColorToken = null;
          } else {
            _tmpSelectedColorToken = _stmt.getText(_columnIndexOfSelectedColorToken);
          }
          final Set<DayOfWeek> _tmpSelectedDays;
          final String _tmp_2;
          if (_stmt.isNull(_columnIndexOfSelectedDays)) {
            _tmp_2 = null;
          } else {
            _tmp_2 = _stmt.getText(_columnIndexOfSelectedDays);
          }
          _tmpSelectedDays = __databaseConverters.stringToDayOfWeekSet(_tmp_2);
          final HabitState _tmpState;
          final String _tmp_3;
          if (_stmt.isNull(_columnIndexOfState)) {
            _tmp_3 = null;
          } else {
            _tmp_3 = _stmt.getText(_columnIndexOfState);
          }
          _tmpState = __databaseConverters.stringToHabitState(_tmp_3);
          final Instant _tmpCreatedAt;
          final Long _tmp_4;
          if (_stmt.isNull(_columnIndexOfCreatedAt)) {
            _tmp_4 = null;
          } else {
            _tmp_4 = _stmt.getLong(_columnIndexOfCreatedAt);
          }
          _tmpCreatedAt = __databaseConverters.epochMillisToInstant(_tmp_4);
          final Instant _tmpStoppedAt;
          final Long _tmp_5;
          if (_stmt.isNull(_columnIndexOfStoppedAt)) {
            _tmp_5 = null;
          } else {
            _tmp_5 = _stmt.getLong(_columnIndexOfStoppedAt);
          }
          _tmpStoppedAt = __databaseConverters.epochMillisToInstant(_tmp_5);
          _tmpHabit = new HabitEntity(_tmpHabitId,_tmpName,_tmpHabitType,_tmpTargetValue,_tmpDefaultIncrement,_tmpUnitLabel,_tmpAllowsMultipleUpdatesPerDay,_tmpSelectedIconToken,_tmpSelectedColorToken,_tmpSelectedDays,_tmpState,_tmpCreatedAt,_tmpStoppedAt);
          final ArrayList<ReminderEntity> _tmpRemindersCollection;
          final String _tmpKey_1;
          if (_stmt.isNull(_columnIndexOfHabitId)) {
            _tmpKey_1 = null;
          } else {
            _tmpKey_1 = _stmt.getText(_columnIndexOfHabitId);
          }
          if (_tmpKey_1 != null) {
            _tmpRemindersCollection = _collectionReminders.get(_tmpKey_1);
          } else {
            _tmpRemindersCollection = new ArrayList<ReminderEntity>();
          }
          _result = new HabitWithReminders(_tmpHabit,_tmpRemindersCollection);
        } else {
          _result = null;
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<Integer> observeTotalHabitCount() {
    final String _sql = "SELECT COUNT(*) FROM habits";
    return FlowUtil.createFlow(__db, false, new String[] {"habits"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final Integer _result;
        if (_stmt.step()) {
          final Integer _tmp;
          if (_stmt.isNull(0)) {
            _tmp = null;
          } else {
            _tmp = (int) (_stmt.getLong(0));
          }
          _result = _tmp;
        } else {
          _result = null;
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<Integer> observeActiveHabitCount() {
    final String _sql = "SELECT COUNT(*) FROM habits WHERE state = 'ACTIVE'";
    return FlowUtil.createFlow(__db, false, new String[] {"habits"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final Integer _result;
        if (_stmt.step()) {
          final Integer _tmp;
          if (_stmt.isNull(0)) {
            _tmp = null;
          } else {
            _tmp = (int) (_stmt.getLong(0));
          }
          _result = _tmp;
        } else {
          _result = null;
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<Integer> observeStoppedHabitCount() {
    final String _sql = "SELECT COUNT(*) FROM habits WHERE state = 'STOPPED'";
    return FlowUtil.createFlow(__db, false, new String[] {"habits"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final Integer _result;
        if (_stmt.step()) {
          final Integer _tmp;
          if (_stmt.isNull(0)) {
            _tmp = null;
          } else {
            _tmp = (int) (_stmt.getLong(0));
          }
          _result = _tmp;
        } else {
          _result = null;
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Object deleteHabit(final String habitId, final Continuation<? super Unit> $completion) {
    final String _sql = "DELETE FROM habits WHERE habit_id = ?";
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (habitId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, habitId);
        }
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private void __fetchRelationshipremindersAscomThreemdroidHabittrackerCoreDatabaseModelReminderEntity(
      @NonNull final SQLiteConnection _connection,
      @NonNull final ArrayMap<String, ArrayList<ReminderEntity>> _map) {
    final Set<String> __mapKeySet = _map.keySet();
    if (__mapKeySet.isEmpty()) {
      return;
    }
    if (_map.size() > 999) {
      RelationUtil.recursiveFetchArrayMap(_map, true, (_tmpMap) -> {
        __fetchRelationshipremindersAscomThreemdroidHabittrackerCoreDatabaseModelReminderEntity(_connection, _tmpMap);
        return Unit.INSTANCE;
      });
      return;
    }
    final StringBuilder _stringBuilder = new StringBuilder();
    _stringBuilder.append("SELECT `reminder_id`,`habit_id`,`time_second_of_day`,`selected_days`,`is_enabled` FROM `reminders` WHERE `habit_id` IN (");
    final int _inputSize = __mapKeySet == null ? 1 : __mapKeySet.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final SQLiteStatement _stmt = _connection.prepare(_sql);
    int _argIndex = 1;
    if (__mapKeySet == null) {
      _stmt.bindNull(_argIndex);
    } else {
      for (String _item : __mapKeySet) {
        if (_item == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, _item);
        }
        _argIndex++;
      }
    }
    try {
      final int _itemKeyIndex = SQLiteStatementUtil.getColumnIndex(_stmt, "habit_id");
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _columnIndexOfReminderId = 0;
      final int _columnIndexOfHabitId = 1;
      final int _columnIndexOfTime = 2;
      final int _columnIndexOfSelectedDays = 3;
      final int _columnIndexOfIsEnabled = 4;
      while (_stmt.step()) {
        final String _tmpKey;
        if (_stmt.isNull(_itemKeyIndex)) {
          _tmpKey = null;
        } else {
          _tmpKey = _stmt.getText(_itemKeyIndex);
        }
        if (_tmpKey != null) {
          final ArrayList<ReminderEntity> _tmpRelation = _map.get(_tmpKey);
          if (_tmpRelation != null) {
            final ReminderEntity _item_1;
            final String _tmpReminderId;
            if (_stmt.isNull(_columnIndexOfReminderId)) {
              _tmpReminderId = null;
            } else {
              _tmpReminderId = _stmt.getText(_columnIndexOfReminderId);
            }
            final String _tmpHabitId;
            if (_stmt.isNull(_columnIndexOfHabitId)) {
              _tmpHabitId = null;
            } else {
              _tmpHabitId = _stmt.getText(_columnIndexOfHabitId);
            }
            final LocalTime _tmpTime;
            final Integer _tmp;
            if (_stmt.isNull(_columnIndexOfTime)) {
              _tmp = null;
            } else {
              _tmp = (int) (_stmt.getLong(_columnIndexOfTime));
            }
            _tmpTime = __databaseConverters.secondOfDayToLocalTime(_tmp);
            final Set<DayOfWeek> _tmpSelectedDays;
            final String _tmp_1;
            if (_stmt.isNull(_columnIndexOfSelectedDays)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _stmt.getText(_columnIndexOfSelectedDays);
            }
            _tmpSelectedDays = __databaseConverters.stringToDayOfWeekSet(_tmp_1);
            final boolean _tmpIsEnabled;
            final int _tmp_2;
            _tmp_2 = (int) (_stmt.getLong(_columnIndexOfIsEnabled));
            _tmpIsEnabled = _tmp_2 != 0;
            _item_1 = new ReminderEntity(_tmpReminderId,_tmpHabitId,_tmpTime,_tmpSelectedDays,_tmpIsEnabled);
            _tmpRelation.add(_item_1);
          }
        }
      }
    } finally {
      _stmt.close();
    }
  }
}
