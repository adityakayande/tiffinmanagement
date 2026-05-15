package com.example.tiffin.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ6\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0014H\u0086@\u00a2\u0006\u0002\u0010\u0015J*\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00180\u00172\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010J&\u0010\u001a\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0082@\u00a2\u0006\u0002\u0010\u001bJ\u0016\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u0019H\u0086@\u00a2\u0006\u0002\u0010\u001eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/example/tiffin/repository/TiffinAbsenceRepository;", "", "absenceDao", "Lcom/example/tiffin/data/TiffinAbsenceDao;", "paymentDao", "Lcom/example/tiffin/data/PaymentDao;", "customerDao", "Lcom/example/tiffin/data/CustomerDao;", "(Lcom/example/tiffin/data/TiffinAbsenceDao;Lcom/example/tiffin/data/PaymentDao;Lcom/example/tiffin/data/CustomerDao;)V", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "addAbsence", "", "customerId", "", "year", "", "month", "dateEpochDay", "slot", "", "(JIIJLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeAbsences", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/tiffin/data/TiffinAbsenceEntity;", "recalculateMonthlyBill", "(JIILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removeAbsence", "entity", "(Lcom/example/tiffin/data/TiffinAbsenceEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class TiffinAbsenceRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.tiffin.data.TiffinAbsenceDao absenceDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.tiffin.data.PaymentDao paymentDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.tiffin.data.CustomerDao customerDao = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.sync.Mutex mutex = null;
    
    public TiffinAbsenceRepository(@org.jetbrains.annotations.NotNull()
    com.example.tiffin.data.TiffinAbsenceDao absenceDao, @org.jetbrains.annotations.NotNull()
    com.example.tiffin.data.PaymentDao paymentDao, @org.jetbrains.annotations.NotNull()
    com.example.tiffin.data.CustomerDao customerDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.tiffin.data.TiffinAbsenceEntity>> observeAbsences(long customerId, int year, int month) {
        return null;
    }
    
    /**
     * Record a tiffin absence for the given date and slot ("LUNCH" or "DINNER").
     * Automatically recalculates and upserts the payment record for that month.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addAbsence(long customerId, int year, int month, long dateEpochDay, @org.jetbrains.annotations.NotNull()
    java.lang.String slot, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Remove a specific absence record and recalculate the monthly bill.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object removeAbsence(@org.jetbrains.annotations.NotNull()
    com.example.tiffin.data.TiffinAbsenceEntity entity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Recomputes amountToPay for the given month based on the number of missed meals.
     * Billing rule: each absent meal (Lunch or Dinner) deducts 1 × tiffinPrice.
     */
    private final java.lang.Object recalculateMonthlyBill(long customerId, int year, int month, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}