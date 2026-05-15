package com.example.tiffin.ui.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\nJN\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u0003R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u001b"}, d2 = {"Lcom/example/tiffin/ui/viewmodel/PaymentHistoryViewModel;", "Landroidx/lifecycle/ViewModel;", "customerId", "", "paymentRepository", "Lcom/example/tiffin/repository/PaymentRepository;", "(JLcom/example/tiffin/repository/PaymentRepository;)V", "payments", "Lkotlinx/coroutines/flow/StateFlow;", "", "Lcom/example/tiffin/data/PaymentEntity;", "getPayments", "()Lkotlinx/coroutines/flow/StateFlow;", "deletePayment", "", "payment", "upsertPayment", "year", "", "month", "amountToPay", "amountPaid", "absentDays", "startDateMs", "endDateMs", "baseMonthlyFee", "tiffinPrice", "app_debug"})
public final class PaymentHistoryViewModel extends androidx.lifecycle.ViewModel {
    private final long customerId = 0L;
    @org.jetbrains.annotations.NotNull()
    private final com.example.tiffin.repository.PaymentRepository paymentRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.tiffin.data.PaymentEntity>> payments = null;
    
    public PaymentHistoryViewModel(long customerId, @org.jetbrains.annotations.NotNull()
    com.example.tiffin.repository.PaymentRepository paymentRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.tiffin.data.PaymentEntity>> getPayments() {
        return null;
    }
    
    public final void upsertPayment(int year, int month, long amountToPay, long amountPaid, int absentDays, long startDateMs, long endDateMs, long baseMonthlyFee, long tiffinPrice) {
    }
    
    public final void deletePayment(@org.jetbrains.annotations.NotNull()
    com.example.tiffin.data.PaymentEntity payment) {
    }
}