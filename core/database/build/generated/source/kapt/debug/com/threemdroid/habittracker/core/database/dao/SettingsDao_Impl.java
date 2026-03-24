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
import com.threemdroid.habittracker.core.database.model.SettingsEntity;
import java.lang.Class;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class SettingsDao_Impl implements SettingsDao {
  private final RoomDatabase __db;

  private final EntityUpsertAdapter<SettingsEntity> __upsertAdapterOfSettingsEntity;

  public SettingsDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__upsertAdapterOfSettingsEntity = new EntityUpsertAdapter<SettingsEntity>(new EntityInsertAdapter<SettingsEntity>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT INTO `settings` (`settings_id`,`reminder_notifications_enabled`) VALUES (?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final SettingsEntity entity) {
        statement.bindLong(1, entity.getSettingsId());
        final int _tmp = entity.getReminderNotificationsEnabled() ? 1 : 0;
        statement.bindLong(2, _tmp);
      }
    }, new EntityDeleteOrUpdateAdapter<SettingsEntity>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE `settings` SET `settings_id` = ?,`reminder_notifications_enabled` = ? WHERE `settings_id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final SettingsEntity entity) {
        statement.bindLong(1, entity.getSettingsId());
        final int _tmp = entity.getReminderNotificationsEnabled() ? 1 : 0;
        statement.bindLong(2, _tmp);
        statement.bindLong(3, entity.getSettingsId());
      }
    });
  }

  @Override
  public Object upsertSettings(final SettingsEntity settings,
      final Continuation<? super Unit> $completion) {
    if (settings == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __upsertAdapterOfSettingsEntity.upsert(_connection, settings);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Flow<SettingsEntity> observeSettings() {
    final String _sql = "\n"
            + "        SELECT * FROM settings\n"
            + "        WHERE settings_id = 0\n"
            + "        LIMIT 1\n"
            + "        ";
    return FlowUtil.createFlow(__db, false, new String[] {"settings"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfSettingsId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "settings_id");
        final int _columnIndexOfReminderNotificationsEnabled = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "reminder_notifications_enabled");
        final SettingsEntity _result;
        if (_stmt.step()) {
          final int _tmpSettingsId;
          _tmpSettingsId = (int) (_stmt.getLong(_columnIndexOfSettingsId));
          final boolean _tmpReminderNotificationsEnabled;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfReminderNotificationsEnabled));
          _tmpReminderNotificationsEnabled = _tmp != 0;
          _result = new SettingsEntity(_tmpSettingsId,_tmpReminderNotificationsEnabled);
        } else {
          _result = null;
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
