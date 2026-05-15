package com.example.tiffin.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
public final class PaymentDao_Impl implements PaymentDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PaymentEntity> __insertionAdapterOfPaymentEntity;

  private final EntityInsertionAdapter<PaymentEntity> __insertionAdapterOfPaymentEntity_1;

  private final EntityDeletionOrUpdateAdapter<PaymentEntity> __deletionAdapterOfPaymentEntity;

  private final EntityDeletionOrUpdateAdapter<PaymentEntity> __updateAdapterOfPaymentEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeletePaymentsByCustomer;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllPayments;

  public PaymentDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPaymentEntity = new EntityInsertionAdapter<PaymentEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `payments` (`id`,`customerId`,`month`,`year`,`amountToPay`,`amountPaid`,`absentDays`,`startDateMs`,`endDateMs`,`baseMonthlyFee`,`tiffinPrice`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PaymentEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getCustomerId());
        statement.bindLong(3, entity.getMonth());
        statement.bindLong(4, entity.getYear());
        statement.bindLong(5, entity.getAmountToPay());
        statement.bindLong(6, entity.getAmountPaid());
        statement.bindLong(7, entity.getAbsentDays());
        statement.bindLong(8, entity.getStartDateMs());
        statement.bindLong(9, entity.getEndDateMs());
        statement.bindLong(10, entity.getBaseMonthlyFee());
        statement.bindLong(11, entity.getTiffinPrice());
      }
    };
    this.__insertionAdapterOfPaymentEntity_1 = new EntityInsertionAdapter<PaymentEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `payments` (`id`,`customerId`,`month`,`year`,`amountToPay`,`amountPaid`,`absentDays`,`startDateMs`,`endDateMs`,`baseMonthlyFee`,`tiffinPrice`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PaymentEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getCustomerId());
        statement.bindLong(3, entity.getMonth());
        statement.bindLong(4, entity.getYear());
        statement.bindLong(5, entity.getAmountToPay());
        statement.bindLong(6, entity.getAmountPaid());
        statement.bindLong(7, entity.getAbsentDays());
        statement.bindLong(8, entity.getStartDateMs());
        statement.bindLong(9, entity.getEndDateMs());
        statement.bindLong(10, entity.getBaseMonthlyFee());
        statement.bindLong(11, entity.getTiffinPrice());
      }
    };
    this.__deletionAdapterOfPaymentEntity = new EntityDeletionOrUpdateAdapter<PaymentEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `payments` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PaymentEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfPaymentEntity = new EntityDeletionOrUpdateAdapter<PaymentEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `payments` SET `id` = ?,`customerId` = ?,`month` = ?,`year` = ?,`amountToPay` = ?,`amountPaid` = ?,`absentDays` = ?,`startDateMs` = ?,`endDateMs` = ?,`baseMonthlyFee` = ?,`tiffinPrice` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PaymentEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getCustomerId());
        statement.bindLong(3, entity.getMonth());
        statement.bindLong(4, entity.getYear());
        statement.bindLong(5, entity.getAmountToPay());
        statement.bindLong(6, entity.getAmountPaid());
        statement.bindLong(7, entity.getAbsentDays());
        statement.bindLong(8, entity.getStartDateMs());
        statement.bindLong(9, entity.getEndDateMs());
        statement.bindLong(10, entity.getBaseMonthlyFee());
        statement.bindLong(11, entity.getTiffinPrice());
        statement.bindLong(12, entity.getId());
      }
    };
    this.__preparedStmtOfDeletePaymentsByCustomer = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM payments WHERE customerId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllPayments = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM payments";
        return _query;
      }
    };
  }

  @Override
  public Object insertPayment(final PaymentEntity payment,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfPaymentEntity.insertAndReturnId(payment);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAllPayments(final List<PaymentEntity> payments,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPaymentEntity_1.insert(payments);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deletePayment(final PaymentEntity payment,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfPaymentEntity.handle(payment);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updatePayment(final PaymentEntity payment,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfPaymentEntity.handle(payment);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deletePaymentsByCustomer(final long customerId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeletePaymentsByCustomer.acquire();
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
          __preparedStmtOfDeletePaymentsByCustomer.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllPayments(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllPayments.acquire();
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
          __preparedStmtOfDeleteAllPayments.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<PaymentEntity>> observePaymentsForCustomer(final long customerId) {
    final String _sql = "SELECT * FROM payments WHERE customerId = ? ORDER BY year ASC, month ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, customerId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"payments"}, new Callable<List<PaymentEntity>>() {
      @Override
      @NonNull
      public List<PaymentEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCustomerId = CursorUtil.getColumnIndexOrThrow(_cursor, "customerId");
          final int _cursorIndexOfMonth = CursorUtil.getColumnIndexOrThrow(_cursor, "month");
          final int _cursorIndexOfYear = CursorUtil.getColumnIndexOrThrow(_cursor, "year");
          final int _cursorIndexOfAmountToPay = CursorUtil.getColumnIndexOrThrow(_cursor, "amountToPay");
          final int _cursorIndexOfAmountPaid = CursorUtil.getColumnIndexOrThrow(_cursor, "amountPaid");
          final int _cursorIndexOfAbsentDays = CursorUtil.getColumnIndexOrThrow(_cursor, "absentDays");
          final int _cursorIndexOfStartDateMs = CursorUtil.getColumnIndexOrThrow(_cursor, "startDateMs");
          final int _cursorIndexOfEndDateMs = CursorUtil.getColumnIndexOrThrow(_cursor, "endDateMs");
          final int _cursorIndexOfBaseMonthlyFee = CursorUtil.getColumnIndexOrThrow(_cursor, "baseMonthlyFee");
          final int _cursorIndexOfTiffinPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "tiffinPrice");
          final List<PaymentEntity> _result = new ArrayList<PaymentEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PaymentEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpCustomerId;
            _tmpCustomerId = _cursor.getLong(_cursorIndexOfCustomerId);
            final int _tmpMonth;
            _tmpMonth = _cursor.getInt(_cursorIndexOfMonth);
            final int _tmpYear;
            _tmpYear = _cursor.getInt(_cursorIndexOfYear);
            final long _tmpAmountToPay;
            _tmpAmountToPay = _cursor.getLong(_cursorIndexOfAmountToPay);
            final long _tmpAmountPaid;
            _tmpAmountPaid = _cursor.getLong(_cursorIndexOfAmountPaid);
            final int _tmpAbsentDays;
            _tmpAbsentDays = _cursor.getInt(_cursorIndexOfAbsentDays);
            final long _tmpStartDateMs;
            _tmpStartDateMs = _cursor.getLong(_cursorIndexOfStartDateMs);
            final long _tmpEndDateMs;
            _tmpEndDateMs = _cursor.getLong(_cursorIndexOfEndDateMs);
            final long _tmpBaseMonthlyFee;
            _tmpBaseMonthlyFee = _cursor.getLong(_cursorIndexOfBaseMonthlyFee);
            final long _tmpTiffinPrice;
            _tmpTiffinPrice = _cursor.getLong(_cursorIndexOfTiffinPrice);
            _item = new PaymentEntity(_tmpId,_tmpCustomerId,_tmpMonth,_tmpYear,_tmpAmountToPay,_tmpAmountPaid,_tmpAbsentDays,_tmpStartDateMs,_tmpEndDateMs,_tmpBaseMonthlyFee,_tmpTiffinPrice);
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
  public Flow<Long> observeTotalDue(final long customerId) {
    final String _sql = "\n"
            + "        SELECT COALESCE(\n"
            + "            SUM(\n"
            + "                CASE \n"
            + "                    WHEN amountPaid >= amountToPay THEN 0\n"
            + "                    ELSE amountToPay - amountPaid\n"
            + "                END\n"
            + "            ),\n"
            + "        0)\n"
            + "        FROM payments\n"
            + "        WHERE customerId = ?\n"
            + "        ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, customerId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"payments"}, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Long _result;
          if (_cursor.moveToFirst()) {
            final Long _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(0);
            }
            _result = _tmp;
          } else {
            _result = null;
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
  public Flow<PaymentEarliestDue> observeNextDuePayment(final long customerId) {
    final String _sql = "\n"
            + "        SELECT \n"
            + "            id,\n"
            + "            month,\n"
            + "            year,\n"
            + "            amountToPay,\n"
            + "            amountPaid,\n"
            + "            (amountToPay - amountPaid) AS dueAmount\n"
            + "        FROM payments\n"
            + "        WHERE customerId = ?\n"
            + "          AND amountPaid < amountToPay\n"
            + "        ORDER BY year ASC, month ASC\n"
            + "        LIMIT 1\n"
            + "        ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, customerId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"payments"}, new Callable<PaymentEarliestDue>() {
      @Override
      @Nullable
      public PaymentEarliestDue call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = 0;
          final int _cursorIndexOfMonth = 1;
          final int _cursorIndexOfYear = 2;
          final int _cursorIndexOfAmountToPay = 3;
          final int _cursorIndexOfAmountPaid = 4;
          final int _cursorIndexOfDueAmount = 5;
          final PaymentEarliestDue _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final int _tmpMonth;
            _tmpMonth = _cursor.getInt(_cursorIndexOfMonth);
            final int _tmpYear;
            _tmpYear = _cursor.getInt(_cursorIndexOfYear);
            final long _tmpAmountToPay;
            _tmpAmountToPay = _cursor.getLong(_cursorIndexOfAmountToPay);
            final long _tmpAmountPaid;
            _tmpAmountPaid = _cursor.getLong(_cursorIndexOfAmountPaid);
            final long _tmpDueAmount;
            _tmpDueAmount = _cursor.getLong(_cursorIndexOfDueAmount);
            _result = new PaymentEarliestDue(_tmpId,_tmpMonth,_tmpYear,_tmpAmountToPay,_tmpAmountPaid,_tmpDueAmount);
          } else {
            _result = null;
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
  public Object getPaymentByUnique(final long customerId, final int year, final int month,
      final Continuation<? super PaymentEntity> $completion) {
    final String _sql = "\n"
            + "        SELECT * FROM payments\n"
            + "        WHERE customerId = ?\n"
            + "          AND year = ?\n"
            + "          AND month = ?\n"
            + "        LIMIT 1\n"
            + "        ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, customerId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, year);
    _argIndex = 3;
    _statement.bindLong(_argIndex, month);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<PaymentEntity>() {
      @Override
      @Nullable
      public PaymentEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCustomerId = CursorUtil.getColumnIndexOrThrow(_cursor, "customerId");
          final int _cursorIndexOfMonth = CursorUtil.getColumnIndexOrThrow(_cursor, "month");
          final int _cursorIndexOfYear = CursorUtil.getColumnIndexOrThrow(_cursor, "year");
          final int _cursorIndexOfAmountToPay = CursorUtil.getColumnIndexOrThrow(_cursor, "amountToPay");
          final int _cursorIndexOfAmountPaid = CursorUtil.getColumnIndexOrThrow(_cursor, "amountPaid");
          final int _cursorIndexOfAbsentDays = CursorUtil.getColumnIndexOrThrow(_cursor, "absentDays");
          final int _cursorIndexOfStartDateMs = CursorUtil.getColumnIndexOrThrow(_cursor, "startDateMs");
          final int _cursorIndexOfEndDateMs = CursorUtil.getColumnIndexOrThrow(_cursor, "endDateMs");
          final int _cursorIndexOfBaseMonthlyFee = CursorUtil.getColumnIndexOrThrow(_cursor, "baseMonthlyFee");
          final int _cursorIndexOfTiffinPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "tiffinPrice");
          final PaymentEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpCustomerId;
            _tmpCustomerId = _cursor.getLong(_cursorIndexOfCustomerId);
            final int _tmpMonth;
            _tmpMonth = _cursor.getInt(_cursorIndexOfMonth);
            final int _tmpYear;
            _tmpYear = _cursor.getInt(_cursorIndexOfYear);
            final long _tmpAmountToPay;
            _tmpAmountToPay = _cursor.getLong(_cursorIndexOfAmountToPay);
            final long _tmpAmountPaid;
            _tmpAmountPaid = _cursor.getLong(_cursorIndexOfAmountPaid);
            final int _tmpAbsentDays;
            _tmpAbsentDays = _cursor.getInt(_cursorIndexOfAbsentDays);
            final long _tmpStartDateMs;
            _tmpStartDateMs = _cursor.getLong(_cursorIndexOfStartDateMs);
            final long _tmpEndDateMs;
            _tmpEndDateMs = _cursor.getLong(_cursorIndexOfEndDateMs);
            final long _tmpBaseMonthlyFee;
            _tmpBaseMonthlyFee = _cursor.getLong(_cursorIndexOfBaseMonthlyFee);
            final long _tmpTiffinPrice;
            _tmpTiffinPrice = _cursor.getLong(_cursorIndexOfTiffinPrice);
            _result = new PaymentEntity(_tmpId,_tmpCustomerId,_tmpMonth,_tmpYear,_tmpAmountToPay,_tmpAmountPaid,_tmpAbsentDays,_tmpStartDateMs,_tmpEndDateMs,_tmpBaseMonthlyFee,_tmpTiffinPrice);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getAllPayments(final Continuation<? super List<PaymentEntity>> $completion) {
    final String _sql = "SELECT * FROM payments";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<PaymentEntity>>() {
      @Override
      @NonNull
      public List<PaymentEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCustomerId = CursorUtil.getColumnIndexOrThrow(_cursor, "customerId");
          final int _cursorIndexOfMonth = CursorUtil.getColumnIndexOrThrow(_cursor, "month");
          final int _cursorIndexOfYear = CursorUtil.getColumnIndexOrThrow(_cursor, "year");
          final int _cursorIndexOfAmountToPay = CursorUtil.getColumnIndexOrThrow(_cursor, "amountToPay");
          final int _cursorIndexOfAmountPaid = CursorUtil.getColumnIndexOrThrow(_cursor, "amountPaid");
          final int _cursorIndexOfAbsentDays = CursorUtil.getColumnIndexOrThrow(_cursor, "absentDays");
          final int _cursorIndexOfStartDateMs = CursorUtil.getColumnIndexOrThrow(_cursor, "startDateMs");
          final int _cursorIndexOfEndDateMs = CursorUtil.getColumnIndexOrThrow(_cursor, "endDateMs");
          final int _cursorIndexOfBaseMonthlyFee = CursorUtil.getColumnIndexOrThrow(_cursor, "baseMonthlyFee");
          final int _cursorIndexOfTiffinPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "tiffinPrice");
          final List<PaymentEntity> _result = new ArrayList<PaymentEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PaymentEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpCustomerId;
            _tmpCustomerId = _cursor.getLong(_cursorIndexOfCustomerId);
            final int _tmpMonth;
            _tmpMonth = _cursor.getInt(_cursorIndexOfMonth);
            final int _tmpYear;
            _tmpYear = _cursor.getInt(_cursorIndexOfYear);
            final long _tmpAmountToPay;
            _tmpAmountToPay = _cursor.getLong(_cursorIndexOfAmountToPay);
            final long _tmpAmountPaid;
            _tmpAmountPaid = _cursor.getLong(_cursorIndexOfAmountPaid);
            final int _tmpAbsentDays;
            _tmpAbsentDays = _cursor.getInt(_cursorIndexOfAbsentDays);
            final long _tmpStartDateMs;
            _tmpStartDateMs = _cursor.getLong(_cursorIndexOfStartDateMs);
            final long _tmpEndDateMs;
            _tmpEndDateMs = _cursor.getLong(_cursorIndexOfEndDateMs);
            final long _tmpBaseMonthlyFee;
            _tmpBaseMonthlyFee = _cursor.getLong(_cursorIndexOfBaseMonthlyFee);
            final long _tmpTiffinPrice;
            _tmpTiffinPrice = _cursor.getLong(_cursorIndexOfTiffinPrice);
            _item = new PaymentEntity(_tmpId,_tmpCustomerId,_tmpMonth,_tmpYear,_tmpAmountToPay,_tmpAmountPaid,_tmpAbsentDays,_tmpStartDateMs,_tmpEndDateMs,_tmpBaseMonthlyFee,_tmpTiffinPrice);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
