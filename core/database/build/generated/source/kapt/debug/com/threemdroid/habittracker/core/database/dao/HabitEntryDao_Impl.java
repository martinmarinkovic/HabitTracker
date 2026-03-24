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
import com.threemdroid.habittracker.core.database.model.HabitEntryEntity;
import java.lang.Class;
import java.lang.Integer;
import java.lang.Long;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class HabitEntryDao_Impl implements HabitEntryDao {
  private final RoomDatabase __db;

  private final EntityUpsertAdapter<HabitEntryEntity> __upsertAdapterOfHabitEntryEntity;

  private final DatabaseConverters __databaseConverters = new DatabaseConverters();

  public HabitEntryDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__upsertAdapterOfHabitEntryEntity = new EntityUpsertAdapter<HabitEntryEntity>(new EntityInsertAdapter<HabitEntryEntity>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT INTO `habit_entries` (`entry_id`,`habit_id`,`entry_date_epoch_day`,`logged_at_epoch_millis`,`value`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final HabitEntryEntity entity) {
        if (entity.getEntryId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getEntryId());
        }
        if (entity.getHabitId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getHabitId());
        }
        final Long _tmp = __databaseConverters.localDateToEpochDay(entity.getEntryDate());
        if (_tmp == null) {
          statement.bindNull(3);
        } else {
          statement.bindLong(3, _tmp);
        }
        final Long _tmp_1 = __databaseConverters.instantToEpochMillis(entity.getLoggedAt());
        if (_tmp_1 == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, _tmp_1);
        }
        statement.bindDouble(5, entity.getValue());
      }
    }, new EntityDeleteOrUpdateAdapter<HabitEntryEntity>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE `habit_entries` SET `entry_id` = ?,`habit_id` = ?,`entry_date_epoch_day` = ?,`logged_at_epoch_millis` = ?,`value` = ? WHERE `entry_id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final HabitEntryEntity entity) {
        if (entity.getEntryId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getEntryId());
        }
        if (entity.getHabitId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getHabitId());
        }
        final Long _tmp = __databaseConverters.localDateToEpochDay(entity.getEntryDate());
        if (_tmp == null) {
          statement.bindNull(3);
        } else {
          statement.bindLong(3, _tmp);
        }
        final Long _tmp_1 = __databaseConverters.instantToEpochMillis(entity.getLoggedAt());
        if (_tmp_1 == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, _tmp_1);
        }
        statement.bindDouble(5, entity.getValue());
        if (entity.getEntryId() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getEntryId());
        }
      }
    });
  }

  @Override
  public Object upsertEntry(final HabitEntryEntity habitEntry,
      final Continuation<? super Unit> $completion) {
    if (habitEntry == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __upsertAdapterOfHabitEntryEntity.upsert(_connection, habitEntry);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Flow<List<HabitEntryEntity>> observeEntriesForHabit(final String habitId) {
    final String _sql = "\n"
            + "        SELECT * FROM habit_entries\n"
            + "        WHERE habit_id = ?\n"
            + "        ORDER BY logged_at_epoch_millis DESC\n"
            + "        ";
    return FlowUtil.createFlow(__db, false, new String[] {"habit_entries"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (habitId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, habitId);
        }
        final int _columnIndexOfEntryId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "entry_id");
        final int _columnIndexOfHabitId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "habit_id");
        final int _columnIndexOfEntryDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "entry_date_epoch_day");
        final int _columnIndexOfLoggedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "logged_at_epoch_millis");
        final int _columnIndexOfValue = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "value");
        final List<HabitEntryEntity> _result = new ArrayList<HabitEntryEntity>();
        while (_stmt.step()) {
          final HabitEntryEntity _item;
          final String _tmpEntryId;
          if (_stmt.isNull(_columnIndexOfEntryId)) {
            _tmpEntryId = null;
          } else {
            _tmpEntryId = _stmt.getText(_columnIndexOfEntryId);
          }
          final String _tmpHabitId;
          if (_stmt.isNull(_columnIndexOfHabitId)) {
            _tmpHabitId = null;
          } else {
            _tmpHabitId = _stmt.getText(_columnIndexOfHabitId);
          }
          final LocalDate _tmpEntryDate;
          final Long _tmp;
          if (_stmt.isNull(_columnIndexOfEntryDate)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getLong(_columnIndexOfEntryDate);
          }
          _tmpEntryDate = __databaseConverters.epochDayToLocalDate(_tmp);
          final Instant _tmpLoggedAt;
          final Long _tmp_1;
          if (_stmt.isNull(_columnIndexOfLoggedAt)) {
            _tmp_1 = null;
          } else {
            _tmp_1 = _stmt.getLong(_columnIndexOfLoggedAt);
          }
          _tmpLoggedAt = __databaseConverters.epochMillisToInstant(_tmp_1);
          final double _tmpValue;
          _tmpValue = _stmt.getDouble(_columnIndexOfValue);
          _item = new HabitEntryEntity(_tmpEntryId,_tmpHabitId,_tmpEntryDate,_tmpLoggedAt,_tmpValue);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<List<HabitEntryEntity>> observeEntriesForHabit(final String habitId,
      final LocalDate startDate, final LocalDate endDate) {
    final String _sql = "\n"
            + "        SELECT * FROM habit_entries\n"
            + "        WHERE habit_id = ?\n"
            + "        AND entry_date_epoch_day BETWEEN ? AND ?\n"
            + "        ORDER BY logged_at_epoch_millis DESC\n"
            + "        ";
    return FlowUtil.createFlow(__db, false, new String[] {"habit_entries"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (habitId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, habitId);
        }
        _argIndex = 2;
        final Long _tmp = __databaseConverters.localDateToEpochDay(startDate);
        if (_tmp == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindLong(_argIndex, _tmp);
        }
        _argIndex = 3;
        final Long _tmp_1 = __databaseConverters.localDateToEpochDay(endDate);
        if (_tmp_1 == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindLong(_argIndex, _tmp_1);
        }
        final int _columnIndexOfEntryId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "entry_id");
        final int _columnIndexOfHabitId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "habit_id");
        final int _columnIndexOfEntryDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "entry_date_epoch_day");
        final int _columnIndexOfLoggedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "logged_at_epoch_millis");
        final int _columnIndexOfValue = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "value");
        final List<HabitEntryEntity> _result = new ArrayList<HabitEntryEntity>();
        while (_stmt.step()) {
          final HabitEntryEntity _item;
          final String _tmpEntryId;
          if (_stmt.isNull(_columnIndexOfEntryId)) {
            _tmpEntryId = null;
          } else {
            _tmpEntryId = _stmt.getText(_columnIndexOfEntryId);
          }
          final String _tmpHabitId;
          if (_stmt.isNull(_columnIndexOfHabitId)) {
            _tmpHabitId = null;
          } else {
            _tmpHabitId = _stmt.getText(_columnIndexOfHabitId);
          }
          final LocalDate _tmpEntryDate;
          final Long _tmp_2;
          if (_stmt.isNull(_columnIndexOfEntryDate)) {
            _tmp_2 = null;
          } else {
            _tmp_2 = _stmt.getLong(_columnIndexOfEntryDate);
          }
          _tmpEntryDate = __databaseConverters.epochDayToLocalDate(_tmp_2);
          final Instant _tmpLoggedAt;
          final Long _tmp_3;
          if (_stmt.isNull(_columnIndexOfLoggedAt)) {
            _tmp_3 = null;
          } else {
            _tmp_3 = _stmt.getLong(_columnIndexOfLoggedAt);
          }
          _tmpLoggedAt = __databaseConverters.epochMillisToInstant(_tmp_3);
          final double _tmpValue;
          _tmpValue = _stmt.getDouble(_columnIndexOfValue);
          _item = new HabitEntryEntity(_tmpEntryId,_tmpHabitId,_tmpEntryDate,_tmpLoggedAt,_tmpValue);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<List<HabitEntryEntity>> observeEntriesForDate(final LocalDate date) {
    final String _sql = "\n"
            + "        SELECT * FROM habit_entries\n"
            + "        WHERE entry_date_epoch_day = ?\n"
            + "        ORDER BY logged_at_epoch_millis DESC\n"
            + "        ";
    return FlowUtil.createFlow(__db, false, new String[] {"habit_entries"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        final Long _tmp = __databaseConverters.localDateToEpochDay(date);
        if (_tmp == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindLong(_argIndex, _tmp);
        }
        final int _columnIndexOfEntryId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "entry_id");
        final int _columnIndexOfHabitId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "habit_id");
        final int _columnIndexOfEntryDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "entry_date_epoch_day");
        final int _columnIndexOfLoggedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "logged_at_epoch_millis");
        final int _columnIndexOfValue = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "value");
        final List<HabitEntryEntity> _result = new ArrayList<HabitEntryEntity>();
        while (_stmt.step()) {
          final HabitEntryEntity _item;
          final String _tmpEntryId;
          if (_stmt.isNull(_columnIndexOfEntryId)) {
            _tmpEntryId = null;
          } else {
            _tmpEntryId = _stmt.getText(_columnIndexOfEntryId);
          }
          final String _tmpHabitId;
          if (_stmt.isNull(_columnIndexOfHabitId)) {
            _tmpHabitId = null;
          } else {
            _tmpHabitId = _stmt.getText(_columnIndexOfHabitId);
          }
          final LocalDate _tmpEntryDate;
          final Long _tmp_1;
          if (_stmt.isNull(_columnIndexOfEntryDate)) {
            _tmp_1 = null;
          } else {
            _tmp_1 = _stmt.getLong(_columnIndexOfEntryDate);
          }
          _tmpEntryDate = __databaseConverters.epochDayToLocalDate(_tmp_1);
          final Instant _tmpLoggedAt;
          final Long _tmp_2;
          if (_stmt.isNull(_columnIndexOfLoggedAt)) {
            _tmp_2 = null;
          } else {
            _tmp_2 = _stmt.getLong(_columnIndexOfLoggedAt);
          }
          _tmpLoggedAt = __databaseConverters.epochMillisToInstant(_tmp_2);
          final double _tmpValue;
          _tmpValue = _stmt.getDouble(_columnIndexOfValue);
          _item = new HabitEntryEntity(_tmpEntryId,_tmpHabitId,_tmpEntryDate,_tmpLoggedAt,_tmpValue);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<Integer> observeTotalEntryCount() {
    final String _sql = "SELECT COUNT(*) FROM habit_entries";
    return FlowUtil.createFlow(__db, false, new String[] {"habit_entries"}, (_connection) -> {
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
  public Object deleteEntry(final String habitEntryId,
      final Continuation<? super Unit> $completion) {
    final String _sql = "DELETE FROM habit_entries WHERE entry_id = ?";
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (habitEntryId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, habitEntryId);
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
