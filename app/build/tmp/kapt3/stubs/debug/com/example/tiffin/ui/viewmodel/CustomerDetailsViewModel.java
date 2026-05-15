package com.example.tiffin.ui.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u00032\u0006\u0010*\u001a\u00020+J\u0006\u0010,\u001a\u00020(J\u000e\u0010-\u001a\u00020(2\u0006\u0010.\u001a\u00020\u0015JB\u0010/\u001a\u00020(2\u0006\u00100\u001a\u00020+2\u0006\u00101\u001a\u00020+2\b\u00102\u001a\u0004\u0018\u00010+2\u0006\u00103\u001a\u00020\u00032\b\u00104\u001a\u0004\u0018\u00010+2\u0006\u00105\u001a\u00020\u00032\u0006\u00106\u001a\u00020\u0003R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0019\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R#\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\r0\f\u00a2\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0010R\u001f\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\f\u00a2\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u0017\u001a\u0004\b\u001b\u0010\u0010R\u0019\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001d0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010 0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0010R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00030\f\u00a2\u0006\u000e\n\u0000\u0012\u0004\b#\u0010\u0017\u001a\u0004\b$\u0010\u0010R\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00030\f\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0010\u00a8\u00067"}, d2 = {"Lcom/example/tiffin/ui/viewmodel/CustomerDetailsViewModel;", "Landroidx/lifecycle/ViewModel;", "customerId", "", "customerRepository", "Lcom/example/tiffin/repository/CustomerRepository;", "paymentRepository", "Lcom/example/tiffin/repository/PaymentRepository;", "absenceRepository", "Lcom/example/tiffin/repository/TiffinAbsenceRepository;", "(JLcom/example/tiffin/repository/CustomerRepository;Lcom/example/tiffin/repository/PaymentRepository;Lcom/example/tiffin/repository/TiffinAbsenceRepository;)V", "allPayments", "Lkotlinx/coroutines/flow/StateFlow;", "", "Lcom/example/tiffin/data/PaymentEntity;", "getAllPayments", "()Lkotlinx/coroutines/flow/StateFlow;", "currentBillingCycle", "Lcom/example/tiffin/domain/BillingCycle;", "getCurrentBillingCycle", "currentMonthAbsences", "Lcom/example/tiffin/data/TiffinAbsenceEntity;", "getCurrentMonthAbsences$annotations", "()V", "getCurrentMonthAbsences", "currentMonthPayment", "getCurrentMonthPayment$annotations", "getCurrentMonthPayment", "customer", "Lcom/example/tiffin/data/CustomerEntity;", "getCustomer", "nextDuePayment", "Lcom/example/tiffin/data/PaymentEarliestDue;", "getNextDuePayment", "previousDues", "getPreviousDues$annotations", "getPreviousDues", "totalDue", "getTotalDue", "addAbsence", "", "dateEpochDay", "slot", "", "deleteCustomer", "removeAbsence", "entity", "updateCustomer", "name", "phone", "address", "dateOfJoiningEpochMs", "photoUri", "baseMonthlyFee", "tiffinPrice", "app_debug"})
public final class CustomerDetailsViewModel extends androidx.lifecycle.ViewModel {
    private final long customerId = 0L;
    @org.jetbrains.annotations.NotNull()
    private final com.example.tiffin.repository.CustomerRepository customerRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.tiffin.repository.PaymentRepository paymentRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.tiffin.repository.TiffinAbsenceRepository absenceRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.tiffin.data.CustomerEntity> customer = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Long> totalDue = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.tiffin.data.PaymentEarliestDue> nextDuePayment = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.tiffin.data.PaymentEntity>> allPayments = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.tiffin.data.PaymentEntity> currentMonthPayment = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.tiffin.domain.BillingCycle> currentBillingCycle = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Long> previousDues = null;
    
    /**
     * All per-slot absence records for the current billing cycle, ordered by date then slot.
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.tiffin.data.TiffinAbsenceEntity>> currentMonthAbsences = null;
    
    public CustomerDetailsViewModel(long customerId, @org.jetbrains.annotations.NotNull()
    com.example.tiffin.repository.CustomerRepository customerRepository, @org.jetbrains.annotations.NotNull()
    com.example.tiffin.repository.PaymentRepository paymentRepository, @org.jetbrains.annotations.NotNull()
    com.example.tiffin.repository.TiffinAbsenceRepository absenceRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.tiffin.data.CustomerEntity> getCustomer() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Long> getTotalDue() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.tiffin.data.PaymentEarliestDue> getNextDuePayment() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.tiffin.data.PaymentEntity>> getAllPayments() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.tiffin.data.PaymentEntity> getCurrentMonthPayment() {
        return null;
    }
    
    @kotlin.OptIn(markerClass = {kotlinx.coroutines.ExperimentalCoroutinesApi.class})
    @java.lang.Deprecated()
    public static void getCurrentMonthPayment$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.tiffin.domain.BillingCycle> getCurrentBillingCycle() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Long> getPreviousDues() {
        return null;
    }
    
    @kotlin.OptIn(markerClass = {kotlinx.coroutines.ExperimentalCoroutinesApi.class})
    @java.lang.Deprecated()
    public static void getPreviousDues$annotations() {
    }
    
    /**
     * All per-slot absence records for the current billing cycle, ordered by date then slot.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.tiffin.data.TiffinAbsenceEntity>> getCurrentMonthAbsences() {
        return null;
    }
    
    /**
     * All per-slot absence records for the current billing cycle, ordered by date then slot.
     */
    @kotlin.OptIn(markerClass = {kotlinx.coroutines.ExperimentalCoroutinesApi.class})
    @java.lang.Deprecated()
    public static void getCurrentMonthAbsences$annotations() {
    }
    
    public final void updateCustomer(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String phone, @org.jetbrains.annotations.Nullable()
    java.lang.String address, long dateOfJoiningEpochMs, @org.jetbrains.annotations.Nullable()
    java.lang.String photoUri, long baseMonthlyFee, long tiffinPrice) {
    }
    
    /**
     * Add a tiffin absence. Finds the exact manual cycle the absence falls into.
     */
    public final void addAbsence(long dateEpochDay, @org.jetbrains.annotations.NotNull()
    java.lang.String slot) {
    }
    
    /**
     * Remove a specific absence record; billing is auto-recalculated.
     */
    public final void removeAbsence(@org.jetbrains.annotations.NotNull()
    com.example.tiffin.data.TiffinAbsenceEntity entity) {
    }
    
    public final void deleteCustomer() {
    }
}