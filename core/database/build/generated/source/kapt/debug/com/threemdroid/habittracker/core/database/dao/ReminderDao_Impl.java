package com.threemdroid.habittracker.core.database.dao;

import androidx.annotation.NonNull;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.EntityUpsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import com.threemdroid.habittracker.core.database.DatabaseConverters;
import com.threemdroid.habittracker.core.database.model.ReminderEntity;
import java.lang.Class;
import java.lang.Integer;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.time.DayOfWeek;
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
public final class ReminderDao_Impl implements ReminderDao {
  private final RoomDatabase __db;

  private final EntityUpsertAdapter<ReminderEntity> __upsertAdapterOfReminderEntity;

  private final DatabaseConverters __databaseConverters = new DatabaseConverters();

  public ReminderDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__upsertAdapterOfReminderEntity = new EntityUpsertAdapter<ReminderEntity>(new EntityInsertAdapter<ReminderEntity>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT INTO `reminders` (`reminder_id`,`habit_id`,`time_second_of_day`,`selected_days`,`is_enabled`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final ReminderEntity entity) {
        if (entity.getReminderId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getReminderId());
        }
        if (entity.getHabitId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getHabitId());
        }
        final Integer _tmp = __databaseConverters.localTimeToSecondOfDay(entity.getTime());
        if (_tmp == null) {
          statement.bindNull(3);
        } else {
          statement.bindLong(3, _tmp);
        }
        final String _tmp_1 = __databaseConverters.dayOfWeekSetToString(entity.getSelectedDays());
        if (_tmp_1 == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, _tmp_1);
        }
        final int _tmp_2 = entity.isEnabled() ? 1 : 0;
        statement.bindLong(5, _tmp_2);
      }
    }, new EntityDeleteOrUpdateAdapter<ReminderEntity>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE `reminders` SET `reminder_id` = ?,`habit_id` = ?,`time_second_of_day` = ?,`selected_days` = ?,`is_enabled` = ? WHERE `reminder_id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final ReminderEntity entity) {
        if (entity.getReminderId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getReminderId());
        }
        if (entity.getHabitId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getHabitId());
        }
        final Integer _tmp = __databaseConverters.localTimeToSecondOfDay(entity.getTime());
        if (_tmp == null) {
          statement.bindNull(3);
        } else {
          statement.bindLong(3, _tmp);
        }
        final String _tmp_1 = __databaseConverters.dayOfWeekSetToString(entity.getSelectedDays());
        if (_tmp_1 == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, _tmp_1);
        }
        final int _tmp_2 = entity.isEnabled() ? 1 : 0;
        statement.bindLong(5, _tmp_2);
        if (entity.getReminderId() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getReminderId());
        }
      }
    });
  }

  @Override
  public Object upsertReminder(final ReminderEntity reminder,
      final Continuation<? super Unit> $completion) {
    if (reminder == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __upsertAdapterOfReminderEntity.upsert(_connection, reminder);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Flow<List<ReminderEntity>> observeRemindersForHabit(final String habitId) {
    final String _sql = "\n"
            + "        SELECT * FROM reminders\n"
            + "        WHERE habit_id = ?\n"
            + "        ORDER BY time_second_of_day ASC\n"
            + "        ";
    return FlowUtil.createFlow(__db, false, new String[] {"reminders"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (habitId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, habitId);
        }
        final int _columnIndexOfReminderId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "reminder_id");
        final int _columnIndexOfHabitId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "habit_id");
        final int _columnIndexOfTime = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "time_second_of_day");
        final int _columnIndexOfSelectedDays = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "selected_days");
        final int _columnIndexOfIsEnabled = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "is_enabled");
        final List<ReminderEntity> _result = new ArrayList<ReminderEntity>();
        while (_stmt.step()) {
          final ReminderEntity _item;
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
          _item = new ReminderEntity(_tmpReminderId,_tmpHabitId,_tmpTime,_tmpSelectedDays,_tmpIsEnabled);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<Integer> observeEnabledReminderCount() {
    final String _sql = "SELECT COUNT(*) FROM reminders WHERE is_enabled = 1";
    return FlowUtil.createFlow(__db, false, new String[] {"reminders"}, (_connection) -> {
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
  public Object deleteReminder(final String reminderId,
      final Continuation<? super Unit> $completion) {
    final String _sql = "DELETE FROM reminders WHERE reminder_id = ?";
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (reminderId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, reminderId);
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
}
