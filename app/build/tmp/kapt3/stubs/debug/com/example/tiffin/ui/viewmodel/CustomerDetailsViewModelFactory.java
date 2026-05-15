package com.example.tiffin.ui.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ%\u0010\u000b\u001a\u0002H\f\"\b\b\u0000\u0010\f*\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\f0\u000fH\u0016\u00a2\u0006\u0002\u0010\u0010R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/example/tiffin/ui/viewmodel/CustomerDetailsViewModelFactory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "customerId", "", "customerRepository", "Lcom/example/tiffin/repository/CustomerRepository;", "paymentRepository", "Lcom/example/tiffin/repository/PaymentRepository;", "absenceRepository", "Lcom/example/tiffin/repository/TiffinAbsenceRepository;", "(JLcom/example/tiffin/repository/CustomerRepository;Lcom/example/tiffin/repository/PaymentRepository;Lcom/example/tiffin/repository/TiffinAbsenceRepository;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "app_debug"})
public final class CustomerDetailsViewModelFactory implements androidx.lifecycle.ViewModelProvider.Factory {
    private final long customerId = 0L;
    @org.jetbrains.annotations.NotNull()
    private final com.example.tiffin.repository.CustomerRepository customerRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.tiffin.repository.PaymentRepository paymentRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.tiffin.repository.TiffinAbsenceRepository absenceRepository = null;
    
    public CustomerDetailsViewModelFactory(long customerId, @org.jetbrains.annotations.NotNull()
    com.example.tiffin.repository.CustomerRepository customerRepository, @org.jetbrains.annotations.NotNull()
    com.example.tiffin.repository.PaymentRepository paymentRepository, @org.jetbrains.annotations.NotNull()
    com.example.tiffin.repository.TiffinAbsenceRepository absenceRepository) {
        super();
    }
    
    @java.lang.Override()
    @kotlin.Suppress(names = {"UNCHECKED_CAST"})
    @org.jetbrains.annotations.NotNull()
    public <T extends androidx.lifecycle.ViewModel>T create(@org.jetbrains.annotations.NotNull()
    java.lang.Class<T> modelClass) {
        return null;
    }
}