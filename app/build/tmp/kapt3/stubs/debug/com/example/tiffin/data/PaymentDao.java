package com.example.tiffin.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u0004J(\u0010\u000f\u001a\u0004\u0018\u00010\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u0013J\u001c\u0010\u0014\u001a\u00020\u00032\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00070\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u0016J\u0016\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0018\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u00192\u0006\u0010\n\u001a\u00020\u000bH\'J\u001c\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000e0\u00192\u0006\u0010\n\u001a\u00020\u000bH\'J\u0016\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00192\u0006\u0010\n\u001a\u00020\u000bH\'J\u0016\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\b\u00a8\u0006\u001e"}, d2 = {"Lcom/example/tiffin/data/PaymentDao;", "", "deleteAllPayments", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deletePayment", "payment", "Lcom/example/tiffin/data/PaymentEntity;", "(Lcom/example/tiffin/data/PaymentEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deletePaymentsByCustomer", "customerId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllPayments", "", "getPaymentByUnique", "year", "", "month", "(JIILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertAllPayments", "payments", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertPayment", "observeNextDuePayment", "Lkotlinx/coroutines/flow/Flow;", "Lcom/example/tiffin/data/PaymentEarliestDue;", "observePaymentsForCustomer", "observeTotalDue", "updatePayment", "app_debug"})
@androidx.room.Dao()
public abstract interface PaymentDao {
    
    @androidx.room.Query(value = "SELECT * FROM payments WHERE customerId = :customerId ORDER BY year ASC, month ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.tiffin.data.PaymentEntity>> observePaymentsForCustomer(long customerId);
    
    @androidx.room.Query(value = "\n        SELECT COALESCE(\n            SUM(\n                CASE \n                    WHEN amountPaid >= amountToPay THEN 0\n                    ELSE amountToPay - amountPaid\n                END\n            ),\n        0)\n        FROM payments\n        WHERE customerId = :customerId\n        ")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Long> observeTotalDue(long customerId);
    
    @androidx.room.Query(value = "\n        SELECT \n            id,\n            month,\n            year,\n            amountToPay,\n            amountPaid,\n            (amountToPay - amountPaid) AS dueAmount\n        FROM payments\n        WHERE customerId = :customerId\n          AND amountPaid < amountToPay\n        ORDER BY year ASC, month ASC\n        LIMIT 1\n        ")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.example.tiffin.data.PaymentEarliestDue> observeNextDuePayment(long customerId);
    
    @androidx.room.Query(value = "\n        SELECT * FROM payments\n        WHERE customerId = :customerId\n          AND year = :year\n          AND month = :month\n        LIMIT 1\n        ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPaymentByUnique(long customerId, int year, int month, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.tiffin.data.PaymentEntity> $completion);
    
    @androidx.room.Query(value = "DELETE FROM payments WHERE customerId = :customerId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deletePaymentsByCustomer(long customerId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertPayment(@org.jetbrains.annotations.NotNull()
    com.example.tiffin.data.PaymentEntity payment, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updatePayment(@org.jetbrains.annotations.NotNull()
    com.example.tiffin.data.PaymentEntity payment, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deletePayment(@org.jetbrains.annotations.NotNull()
    com.example.tiffin.data.PaymentEntity payment, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM payments")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllPayments(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.tiffin.data.PaymentEntity>> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertAllPayments(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.tiffin.data.PaymentEntity> payments, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM payments")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAllPayments(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}