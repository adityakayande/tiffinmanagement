package com.example.tiffin.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u001c\u0010\r\u001a\u00020\u00032\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\u0012J\u0018\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u00142\u0006\u0010\u0006\u001a\u00020\u0007H\'J\u0014\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\n0\u0014H\'J\u0016\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\u0012\u00a8\u0006\u0018"}, d2 = {"Lcom/example/tiffin/data/CustomerDao;", "", "deleteAllCustomers", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteCustomerById", "customerId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllCustomers", "", "Lcom/example/tiffin/data/CustomerEntity;", "getCustomerOnce", "insertAllCustomers", "customers", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertCustomer", "customer", "(Lcom/example/tiffin/data/CustomerEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeCustomer", "Lkotlinx/coroutines/flow/Flow;", "observeCustomersWithDue", "Lcom/example/tiffin/data/CustomerListItem;", "updateCustomer", "app_debug"})
@androidx.room.Dao()
public abstract interface CustomerDao {
    
    @androidx.room.Query(value = "\n        SELECT \n            c.id AS id,\n            c.name AS name,\n            c.phone AS phone,\n            c.photoUri AS photoUri,\n            COALESCE(SUM(\n                CASE \n                    WHEN p.amountPaid >= p.amountToPay THEN 0\n                    ELSE p.amountToPay - p.amountPaid\n                END\n            ), 0) AS totalDue\n        FROM customers c\n        LEFT JOIN payments p ON p.customerId = c.id\n        GROUP BY c.id, c.name, c.phone, c.photoUri\n        ORDER BY c.name ASC\n        ")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.tiffin.data.CustomerListItem>> observeCustomersWithDue();
    
    @androidx.room.Query(value = "SELECT * FROM customers WHERE id = :customerId")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.example.tiffin.data.CustomerEntity> observeCustomer(long customerId);
    
    @androidx.room.Query(value = "SELECT * FROM customers WHERE id = :customerId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCustomerOnce(long customerId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.tiffin.data.CustomerEntity> $completion);
    
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertCustomer(@org.jetbrains.annotations.NotNull()
    com.example.tiffin.data.CustomerEntity customer, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateCustomer(@org.jetbrains.annotations.NotNull()
    com.example.tiffin.data.CustomerEntity customer, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM customers WHERE id = :customerId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteCustomerById(long customerId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM customers")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllCustomers(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.tiffin.data.CustomerEntity>> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertAllCustomers(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.tiffin.data.CustomerEntity> customers, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM customers")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAllCustomers(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}