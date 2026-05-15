package com.example.tiffin;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0016R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\n8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\b\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u000e\u001a\u00020\u000f8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0012\u0010\b\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0013\u001a\u00020\u00148FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0017\u0010\b\u001a\u0004\b\u0015\u0010\u0016\u00a8\u0006\u001a"}, d2 = {"Lcom/example/tiffin/TiffinApp;", "Landroid/app/Application;", "()V", "absenceRepository", "Lcom/example/tiffin/repository/TiffinAbsenceRepository;", "getAbsenceRepository", "()Lcom/example/tiffin/repository/TiffinAbsenceRepository;", "absenceRepository$delegate", "Lkotlin/Lazy;", "customerRepository", "Lcom/example/tiffin/repository/CustomerRepository;", "getCustomerRepository", "()Lcom/example/tiffin/repository/CustomerRepository;", "customerRepository$delegate", "database", "Lcom/example/tiffin/data/AppDatabase;", "getDatabase", "()Lcom/example/tiffin/data/AppDatabase;", "database$delegate", "paymentRepository", "Lcom/example/tiffin/repository/PaymentRepository;", "getPaymentRepository", "()Lcom/example/tiffin/repository/PaymentRepository;", "paymentRepository$delegate", "onCreate", "", "app_debug"})
public final class TiffinApp extends android.app.Application {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy database$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy customerRepository$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy paymentRepository$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy absenceRepository$delegate = null;
    
    public TiffinApp() {
        super();
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.tiffin.data.AppDatabase getDatabase() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.tiffin.repository.CustomerRepository getCustomerRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.tiffin.repository.PaymentRepository getPaymentRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.tiffin.repository.TiffinAbsenceRepository getAbsenceRepository() {
        return null;
    }
}