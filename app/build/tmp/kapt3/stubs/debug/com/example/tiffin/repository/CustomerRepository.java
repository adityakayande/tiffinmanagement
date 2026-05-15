package com.example.tiffin.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u0013J\u0016\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0016H\u0086@\u00a2\u0006\u0002\u0010\u0017J\u0016\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\n2\u0006\u0010\u0011\u001a\u00020\u0012J\u0016\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0016H\u0086@\u00a2\u0006\u0002\u0010\u0017R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/example/tiffin/repository/CustomerRepository;", "", "customerDao", "Lcom/example/tiffin/data/CustomerDao;", "paymentDao", "Lcom/example/tiffin/data/PaymentDao;", "absenceDao", "Lcom/example/tiffin/data/TiffinAbsenceDao;", "(Lcom/example/tiffin/data/CustomerDao;Lcom/example/tiffin/data/PaymentDao;Lcom/example/tiffin/data/TiffinAbsenceDao;)V", "customersWithDue", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/tiffin/data/CustomerListItem;", "getCustomersWithDue", "()Lkotlinx/coroutines/flow/Flow;", "deleteCustomer", "", "customerId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertCustomer", "customer", "Lcom/example/tiffin/data/CustomerEntity;", "(Lcom/example/tiffin/data/CustomerEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeCustomer", "updateCustomer", "app_debug"})
public final class CustomerRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.tiffin.data.CustomerDao customerDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.tiffin.data.PaymentDao paymentDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.tiffin.data.TiffinAbsenceDao absenceDao = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.example.tiffin.data.CustomerListItem>> customersWithDue = null;
    
    public CustomerRepository(@org.jetbrains.annotations.NotNull()
    com.example.tiffin.data.CustomerDao customerDao, @org.jetbrains.annotations.NotNull()
    com.example.tiffin.data.PaymentDao paymentDao, @org.jetbrains.annotations.NotNull()
    com.example.tiffin.data.TiffinAbsenceDao absenceDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.tiffin.data.CustomerListItem>> getCustomersWithDue() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.example.tiffin.data.CustomerEntity> observeCustomer(long customerId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertCustomer(@org.jetbrains.annotations.NotNull()
    com.example.tiffin.data.CustomerEntity customer, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateCustomer(@org.jetbrains.annotations.NotNull()
    com.example.tiffin.data.CustomerEntity customer, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteCustomer(long customerId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}