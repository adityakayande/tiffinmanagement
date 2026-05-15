package com.example.tiffin.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\n\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000b2\u0006\u0010\r\u001a\u00020\u000eJ\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00100\u000b2\u0006\u0010\r\u001a\u00020\u000eJ\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000b2\u0006\u0010\r\u001a\u00020\u000eJ`\u0010\u0012\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u000e2\b\b\u0002\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u001dR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/example/tiffin/repository/PaymentRepository;", "", "paymentDao", "Lcom/example/tiffin/data/PaymentDao;", "(Lcom/example/tiffin/data/PaymentDao;)V", "deletePayment", "", "payment", "Lcom/example/tiffin/data/PaymentEntity;", "(Lcom/example/tiffin/data/PaymentEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeNextDuePayment", "Lkotlinx/coroutines/flow/Flow;", "Lcom/example/tiffin/data/PaymentEarliestDue;", "customerId", "", "observePaymentsForCustomer", "", "observeTotalDue", "upsertPayment", "year", "", "month", "amountToPay", "amountPaid", "absentDays", "startDateMs", "endDateMs", "baseMonthlyFee", "tiffinPrice", "(JIIJJIJJJJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class PaymentRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.tiffin.data.PaymentDao paymentDao = null;
    
    public PaymentRepository(@org.jetbrains.annotations.NotNull()
    com.example.tiffin.data.PaymentDao paymentDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.tiffin.data.PaymentEntity>> observePaymentsForCustomer(long customerId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Long> observeTotalDue(long customerId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.example.tiffin.data.PaymentEarliestDue> observeNextDuePayment(long customerId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object upsertPayment(long customerId, int year, int month, long amountToPay, long amountPaid, int absentDays, long startDateMs, long endDateMs, long baseMonthlyFee, long tiffinPrice, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deletePayment(@org.jetbrains.annotations.NotNull()
    com.example.tiffin.data.PaymentEntity payment, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}