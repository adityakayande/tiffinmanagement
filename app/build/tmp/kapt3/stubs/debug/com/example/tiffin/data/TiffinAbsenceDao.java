package com.example.tiffin.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\rJ\u000e\u0010\u000e\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0011J\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\f0\u0013H\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\rJ\u001c\u0010\u0015\u001a\u00020\n2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\f0\u0013H\u00a7@\u00a2\u0006\u0002\u0010\u0017J,\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00130\u00192\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0003H\'\u00a8\u0006\u001a"}, d2 = {"Lcom/example/tiffin/data/TiffinAbsenceDao;", "", "countTotalAbsentMeals", "", "customerId", "", "year", "month", "(JIILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delete", "", "entity", "Lcom/example/tiffin/data/TiffinAbsenceEntity;", "(Lcom/example/tiffin/data/TiffinAbsenceEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAllAbsences", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteByCustomer", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllAbsences", "", "insert", "insertAllAbsences", "absences", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeAbsences", "Lkotlinx/coroutines/flow/Flow;", "app_debug"})
@androidx.room.Dao()
public abstract interface TiffinAbsenceDao {
    
    /**
     * Observe all absences for a customer in a specific month, ordered by date then slot.
     */
    @androidx.room.Query(value = "\n        SELECT * FROM tiffin_absences\n        WHERE customerId = :customerId AND year = :year AND month = :month\n        ORDER BY dateEpochDay ASC, slot ASC\n        ")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.tiffin.data.TiffinAbsenceEntity>> observeAbsences(long customerId, int year, int month);
    
    /**
     * Count total missed tiffins (each slot counts as 1).
     * Used for billing: each absent meal deducts 1 × tiffinPrice from the base fee.
     */
    @androidx.room.Query(value = "\n        SELECT COUNT(*)\n        FROM tiffin_absences\n        WHERE customerId = :customerId AND year = :year AND month = :month\n        ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object countTotalAbsentMeals(long customerId, int year, int month, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    /**
     * Silently ignores duplicates (same customer + date + slot).
     */
    @androidx.room.Insert(onConflict = 5)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.example.tiffin.data.TiffinAbsenceEntity entity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.example.tiffin.data.TiffinAbsenceEntity entity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM tiffin_absences WHERE customerId = :customerId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteByCustomer(long customerId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM tiffin_absences")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllAbsences(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.tiffin.data.TiffinAbsenceEntity>> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertAllAbsences(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.tiffin.data.TiffinAbsenceEntity> absences, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM tiffin_absences")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAllAbsences(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}