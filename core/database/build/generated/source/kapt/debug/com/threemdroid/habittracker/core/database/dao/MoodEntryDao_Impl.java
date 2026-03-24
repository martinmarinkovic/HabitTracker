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
import com.threemdroid.habittracker.core.database.model.MoodEntryEntity;
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
public final class MoodEntryDao_Impl implements MoodEntryDao {
  private final RoomDatabase __db;

  private final EntityUpsertAdapter<MoodEntryEntity> __upsertAdapterOfMoodEntryEntity;

  private final DatabaseConverters __databaseConverters = new DatabaseConverters();

  public MoodEntryDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__upsertAdapterOfMoodEntryEntity = new EntityUpsertAdapter<MoodEntryEntity>(new EntityInsertAdapter<MoodEntryEntity>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT INTO `mood_entries` (`mood_entry_id`,`entry_date_epoch_day`,`logged_at_epoch_millis`,`mood_token`) VALUES (?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final MoodEntryEntity entity) {
        if (entity.getMoodEntryId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getMoodEntryId());
        }
        final Long _tmp = __databaseConverters.localDateToEpochDay(entity.getEntryDate());
        if (_tmp == null) {
          statement.bindNull(2);
        } else {
          statement.bindLong(2, _tmp);
        }
        final Long _tmp_1 = __databaseConverters.instantToEpochMillis(entity.getLoggedAt());
        if (_tmp_1 == null) {
          statement.bindNull(3);
        } else {
          statement.bindLong(3, _tmp_1);
        }
        if (entity.getMoodToken() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getMoodToken());
        }
      }
    }, new EntityDeleteOrUpdateAdapter<MoodEntryEntity>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE `mood_entries` SET `mood_entry_id` = ?,`entry_date_epoch_day` = ?,`logged_at_epoch_millis` = ?,`mood_token` = ? WHERE `mood_entry_id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final MoodEntryEntity entity) {
        if (entity.getMoodEntryId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getMoodEntryId());
        }
        final Long _tmp = __databaseConverters.localDateToEpochDay(entity.getEntryDate());
        if (_tmp == null) {
          statement.bindNull(2);
        } else {
          statement.bindLong(2, _tmp);
        }
        final Long _tmp_1 = __databaseConverters.instantToEpochMillis(entity.getLoggedAt());
        if (_tmp_1 == null) {
          statement.bindNull(3);
        } else {
          statement.bindLong(3, _tmp_1);
        }
        if (entity.getMoodToken() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getMoodToken());
        }
        if (entity.getMoodEntryId() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getMoodEntryId());
        }
      }
    });
  }

  @Override
  public Object upsertMoodEntry(final MoodEntryEntity moodEntry,
      final Continuation<? super Unit> $completion) {
    if (moodEntry == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __upsertAdapterOfMoodEntryEntity.upsert(_connection, moodEntry);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Flow<List<MoodEntryEntity>> observeMoodEntries() {
    final String _sql = "SELECT * FROM mood_entries ORDER BY logged_at_epoch_millis DESC";
    return FlowUtil.createFlow(__db, false, new String[] {"mood_entries"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfMoodEntryId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "mood_entry_id");
        final int _columnIndexOfEntryDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "entry_date_epoch_day");
        final int _columnIndexOfLoggedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "logged_at_epoch_millis");
        final int _columnIndexOfMoodToken = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "mood_token");
        final List<MoodEntryEntity> _result = new ArrayList<MoodEntryEntity>();
        while (_stmt.step()) {
          final MoodEntryEntity _item;
          final String _tmpMoodEntryId;
          if (_stmt.isNull(_columnIndexOfMoodEntryId)) {
            _tmpMoodEntryId = null;
          } else {
            _tmpMoodEntryId = _stmt.getText(_columnIndexOfMoodEntryId);
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
          final String _tmpMoodToken;
          if (_stmt.isNull(_columnIndexOfMoodToken)) {
            _tmpMoodToken = null;
          } else {
            _tmpMoodToken = _stmt.getText(_columnIndexOfMoodToken);
          }
          _item = new MoodEntryEntity(_tmpMoodEntryId,_tmpEntryDate,_tmpLoggedAt,_tmpMoodToken);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<List<MoodEntryEntity>> observeMoodEntriesForDate(final LocalDate date) {
    final String _sql = "\n"
            + "        SELECT * FROM mood_entries\n"
            + "        WHERE entry_date_epoch_day = ?\n"
            + "        ORDER BY logged_at_epoch_millis DESC\n"
            + "        ";
    return FlowUtil.createFlow(__db, false, new String[] {"mood_entries"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        final Long _tmp = __databaseConverters.localDateToEpochDay(date);
        if (_tmp == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindLong(_argIndex, _tmp);
        }
        final int _columnIndexOfMoodEntryId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "mood_entry_id");
        final int _columnIndexOfEntryDate = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "entry_date_epoch_day");
        final int _columnIndexOfLoggedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "logged_at_epoch_millis");
        final int _columnIndexOfMoodToken = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "mood_token");
        final List<MoodEntryEntity> _result = new ArrayList<MoodEntryEntity>();
        while (_stmt.step()) {
          final MoodEntryEntity _item;
          final String _tmpMoodEntryId;
          if (_stmt.isNull(_columnIndexOfMoodEntryId)) {
            _tmpMoodEntryId = null;
          } else {
            _tmpMoodEntryId = _stmt.getText(_columnIndexOfMoodEntryId);
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
          final String _tmpMoodToken;
          if (_stmt.isNull(_columnIndexOfMoodToken)) {
            _tmpMoodToken = null;
          } else {
            _tmpMoodToken = _stmt.getText(_columnIndexOfMoodToken);
          }
          _item = new MoodEntryEntity(_tmpMoodEntryId,_tmpEntryDate,_tmpLoggedAt,_tmpMoodToken);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<Integer> observeTotalMoodEntryCount() {
    final String _sql = "SELECT COUNT(*) FROM mood_entries";
    return FlowUtil.createFlow(__db, false, new String[] {"mood_entries"}, (_connection) -> {
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
  public Object deleteMoodEntry(final String moodEntryId,
      final Continuation<? super Unit> $completion) {
    final String _sql = "DELETE FROM mood_entries WHERE mood_entry_id = ?";
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (moodEntryId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, moodEntryId);
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
