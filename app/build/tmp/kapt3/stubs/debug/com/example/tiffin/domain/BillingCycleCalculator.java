package com.example.tiffin.domain;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J \u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tJ\u0016\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006\u00a8\u0006\r"}, d2 = {"Lcom/example/tiffin/domain/BillingCycleCalculator;", "", "()V", "getCurrentCycle", "Lcom/example/tiffin/domain/BillingCycle;", "joinDateMs", "", "getCycleByYearMonth", "year", "", "month", "getCycleForDate", "targetDateMs", "app_debug"})
public final class BillingCycleCalculator {
    @org.jetbrains.annotations.NotNull()
    public static final com.example.tiffin.domain.BillingCycleCalculator INSTANCE = null;
    
    private BillingCycleCalculator() {
        super();
    }
    
    /**
     * Given a customer's joined timestamp and a target timestamp (usually 'now' or an absence date),
     * computes the distinct billing cycle that the target date falls into.
     * The cycle is bounded by the exact Join Day of every month.
     */
    @org.jetbrains.annotations.NotNull()
    public final com.example.tiffin.domain.BillingCycle getCycleForDate(long joinDateMs, long targetDateMs) {
        return null;
    }
    
    /**
     * Helper for obtaining the cycle of 'today' based on the join date.
     */
    @org.jetbrains.annotations.NotNull()
    public final com.example.tiffin.domain.BillingCycle getCurrentCycle(long joinDateMs) {
        return null;
    }
    
    /**
     * Finds the specific BillingCycle bounds that match the given start year and month.
     * Guaranteed to return exact month cycle boundaries matching the join date.
     */
    @org.jetbrains.annotations.Nullable()
    public final com.example.tiffin.domain.BillingCycle getCycleByYearMonth(long joinDateMs, int year, int month) {
        return null;
    }
}