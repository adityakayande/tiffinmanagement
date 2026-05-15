package com.example.tiffin.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class TiffinAbsenceDao_Impl implements TiffinAbsenceDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TiffinAbsenceEntity> __insertionAdapterOfTiffinAbsenceEntity;

  private final EntityInsertionAdapter<TiffinAbsenceEntity> __insertionAdapterOfTiffinAbsenceEntity_1;

  private final EntityDeletionOrUpdateAdapter<TiffinAbsenceEntity> __deletionAdapterOfTiffinAbsenceEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteByCustomer;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllAbsences;

  public TiffinAbsenceDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTiffinAbsenceEntity = new EntityInsertionAdapter<TiffinAbsenceEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR IGNORE INTO `tiffin_absences` (`id`,`customerId`,`month`,`year`,`dateEpochDay`,`slot`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TiffinAbsenceEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getCustomerId());
        statement.bindLong(3, entity.getMonth());
        statement.bindLong(4, entity.getYear());
        statement.bindLong(5, entity.getDateEpochDay());
        if (entity.getSlot() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getSlot());
        }
      }
    };
    this.__insertionAdapterOfTiffinAbsenceEntity_1 = new EntityInsertionAdapter<TiffinAbsenceEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `tiffin_absences` (`id`,`customerId`,`month`,`year`,`dateEpochDay`,`slot`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TiffinAbsenceEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getCustomerId());
        statement.bindLong(3, entity.getMonth());
        statement.bindLong(4, entity.getYear());
        statement.bindLong(5, entity.getDateEpochDay());
        if (entity.getSlot() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getSlot());
        }
      }
    };
    this.__deletionAdapterOfTiffinAbsenceEntity = new EntityDeletionOrUpdateAdapter<TiffinAbsenceEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `tiffin_absences` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TiffinAbsenceEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteByCustomer = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM tiffin_absences WHERE customerId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllAbsences = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM tiffin_absences";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final TiffinAbsenceEntity entity, final Continuation<? super Long> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfTiffinAbsenceEntity.insertAndReturnId(entity);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object insertAllAbsences(final List<TiffinAbsenceEntity> absences,
      final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTiffinAbsenceEntity_1.insert(absences);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object delete(final TiffinAbsenceEntity entity, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfTiffinAbsenceEntity.handle(entity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object deleteByCustomer(final long customerId, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteByCustomer.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, customerId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteByCustomer.release(_stmt);
        }
      }
    }, arg1);
  }

  @Override
  public Object deleteAllAbsences(final Continuation<? super Unit> arg0) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllAbsences.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteAllAbsences.release(_stmt);
        }
      }
    }, arg0);
  }

  @Override
  public Flow<List<TiffinAbsenceEntity>> observeAbsences(final long customerId, final int year,
      final int month) {
    final String _sql = "\n"
            + "        SELECT * FROM tiffin_absences\n"
            + "        WHERE customerId = ? AND year = ? AND month = ?\n"
            + "        ORDER BY dateEpochDay ASC, slot ASC\n"
            + "        ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, customerId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, year);
    _argIndex = 3;
    _statement.bindLong(_argIndex, month);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"tiffin_absences"}, new Callable<List<TiffinAbsenceEntity>>() {
      @Override
      @NonNull
      public List<TiffinAbsenceEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCustomerId = CursorUtil.getColumnIndexOrThrow(_cursor, "customerId");
          final int _cursorIndexOfMonth = CursorUtil.getColumnIndexOrThrow(_cursor, "month");
          final int _cursorIndexOfYear = CursorUtil.getColumnIndexOrThrow(_cursor, "year");
          final int _cursorIndexOfDateEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "dateEpochDay");
          final int _cursorIndexOfSlot = CursorUtil.getColumnIndexOrThrow(_cursor, "slot");
          final List<TiffinAbsenceEntity> _result = new ArrayList<TiffinAbsenceEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TiffinAbsenceEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpCustomerId;
            _tmpCustomerId = _cursor.getLong(_cursorIndexOfCustomerId);
            final int _tmpMonth;
            _tmpMonth = _cursor.getInt(_cursorIndexOfMonth);
            final int _tmpYear;
            _tmpYear = _cursor.getInt(_cursorIndexOfYear);
            final long _tmpDateEpochDay;
            _tmpDateEpochDay = _cursor.getLong(_cursorIndexOfDateEpochDay);
            final String _tmpSlot;
            if (_cursor.isNull(_cursorIndexOfSlot)) {
              _tmpSlot = null;
            } else {
              _tmpSlot = _cursor.getString(_cursorIndexOfSlot);
            }
            _item = new TiffinAbsenceEntity(_tmpId,_tmpCustomerId,_tmpMonth,_tmpYear,_tmpDateEpochDay,_tmpSlot);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object countTotalAbsentMeals(final long customerId, final int year, final int month,
      final Continuation<? super Integer> arg3) {
    final String _sql = "\n"
            + "        SELECT COUNT(*)\n"
            + "        FROM tiffin_absences\n"
            + "        WHERE customerId = ? AND year = ? AND month = ?\n"
            + "        ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, customerId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, year);
    _argIndex = 3;
    _statement.bindLong(_argIndex, month);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, arg3);
  }

  @Override
  public Object getAllAbsences(final Continuation<? super List<TiffinAbsenceEntity>> arg0) {
    final String _sql = "SELECT * FROM tiffin_absences";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<TiffinAbsenceEntity>>() {
      @Override
      @NonNull
      public List<TiffinAbsenceEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCustomerId = CursorUtil.getColumnIndexOrThrow(_cursor, "customerId");
          final int _cursorIndexOfMonth = CursorUtil.getColumnIndexOrThrow(_cursor, "month");
          final int _cursorIndexOfYear = CursorUtil.getColumnIndexOrThrow(_cursor, "year");
          final int _cursorIndexOfDateEpochDay = CursorUtil.getColumnIndexOrThrow(_cursor, "dateEpochDay");
          final int _cursorIndexOfSlot = CursorUtil.getColumnIndexOrThrow(_cursor, "slot");
          final List<TiffinAbsenceEntity> _result = new ArrayList<TiffinAbsenceEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TiffinAbsenceEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpCustomerId;
            _tmpCustomerId = _cursor.getLong(_cursorIndexOfCustomerId);
            final int _tmpMonth;
            _tmpMonth = _cursor.getInt(_cursorIndexOfMonth);
            final int _tmpYear;
            _tmpYear = _cursor.getInt(_cursorIndexOfYear);
            final long _tmpDateEpochDay;
            _tmpDateEpochDay = _cursor.getLong(_cursorIndexOfDateEpochDay);
            final String _tmpSlot;
            if (_cursor.isNull(_cursorIndexOfSlot)) {
              _tmpSlot = null;
            } else {
              _tmpSlot = _cursor.getString(_cursorIndexOfSlot);
            }
            _item = new TiffinAbsenceEntity(_tmpId,_tmpCustomerId,_tmpMonth,_tmpYear,_tmpDateEpochDay,_tmpSlot);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, arg0);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
