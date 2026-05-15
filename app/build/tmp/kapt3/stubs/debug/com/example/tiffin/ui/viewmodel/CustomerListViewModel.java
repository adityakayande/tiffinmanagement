package com.example.tiffin.ui.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004JB\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0016"}, d2 = {"Lcom/example/tiffin/ui/viewmodel/CustomerListViewModel;", "Landroidx/lifecycle/ViewModel;", "customerRepository", "Lcom/example/tiffin/repository/CustomerRepository;", "(Lcom/example/tiffin/repository/CustomerRepository;)V", "customers", "Lkotlinx/coroutines/flow/StateFlow;", "", "Lcom/example/tiffin/data/CustomerListItem;", "getCustomers", "()Lkotlinx/coroutines/flow/StateFlow;", "addCustomer", "", "name", "", "phone", "address", "dateOfJoiningEpochMs", "", "photoUri", "baseMonthlyFee", "tiffinPrice", "app_debug"})
public final class CustomerListViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.tiffin.repository.CustomerRepository customerRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.tiffin.data.CustomerListItem>> customers = null;
    
    public CustomerListViewModel(@org.jetbrains.annotations.NotNull()
    com.example.tiffin.repository.CustomerRepository customerRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.tiffin.data.CustomerListItem>> getCustomers() {
        return null;
    }
    
    public final void addCustomer(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String phone, @org.jetbrains.annotations.Nullable()
    java.lang.String address, long dateOfJoiningEpochMs, @org.jetbrains.annotations.Nullable()
    java.lang.String photoUri, long baseMonthlyFee, long tiffinPrice) {
    }
}