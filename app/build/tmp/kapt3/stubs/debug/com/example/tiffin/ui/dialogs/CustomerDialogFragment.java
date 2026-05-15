package com.example.tiffin.ui.dialogs;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \"2\u00020\u0001:\u0001\"B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u001c\u001a\u00020\u0016H\u0002J\u0012\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\b\u0010!\u001a\u00020\u0010H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR(\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0017\u001a\n \u0019*\u0004\u0018\u00010\u00180\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u001a\u001a\u0010\u0012\f\u0012\n \u0019*\u0004\u0018\u00010\u00160\u00160\u001bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lcom/example/tiffin/ui/dialogs/CustomerDialogFragment;", "Landroidx/fragment/app/DialogFragment;", "()V", "_binding", "Lcom/example/tiffin/databinding/DialogCustomerBinding;", "binding", "getBinding", "()Lcom/example/tiffin/databinding/DialogCustomerBinding;", "initialData", "Lcom/example/tiffin/ui/dialogs/CustomerDialogResult;", "getInitialData", "()Lcom/example/tiffin/ui/dialogs/CustomerDialogResult;", "setInitialData", "(Lcom/example/tiffin/ui/dialogs/CustomerDialogResult;)V", "onSaveCallback", "Lkotlin/Function1;", "", "getOnSaveCallback", "()Lkotlin/jvm/functions/Function1;", "setOnSaveCallback", "(Lkotlin/jvm/functions/Function1;)V", "photoUri", "Landroid/net/Uri;", "selectedDate", "Ljava/util/Calendar;", "kotlin.jvm.PlatformType", "takePicture", "Landroidx/activity/result/ActivityResultLauncher;", "createTempPhotoUri", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "Companion", "app_debug"})
public final class CustomerDialogFragment extends androidx.fragment.app.DialogFragment {
    @org.jetbrains.annotations.Nullable()
    private com.example.tiffin.databinding.DialogCustomerBinding _binding;
    private java.util.Calendar selectedDate;
    @org.jetbrains.annotations.Nullable()
    private android.net.Uri photoUri;
    @org.jetbrains.annotations.Nullable()
    private com.example.tiffin.ui.dialogs.CustomerDialogResult initialData;
    @org.jetbrains.annotations.Nullable()
    private kotlin.jvm.functions.Function1<? super com.example.tiffin.ui.dialogs.CustomerDialogResult, kotlin.Unit> onSaveCallback;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<android.net.Uri> takePicture = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.tiffin.ui.dialogs.CustomerDialogFragment.Companion Companion = null;
    
    public CustomerDialogFragment() {
        super();
    }
    
    private final com.example.tiffin.databinding.DialogCustomerBinding getBinding() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.example.tiffin.ui.dialogs.CustomerDialogResult getInitialData() {
        return null;
    }
    
    public final void setInitialData(@org.jetbrains.annotations.Nullable()
    com.example.tiffin.ui.dialogs.CustomerDialogResult p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final kotlin.jvm.functions.Function1<com.example.tiffin.ui.dialogs.CustomerDialogResult, kotlin.Unit> getOnSaveCallback() {
        return null;
    }
    
    public final void setOnSaveCallback(@org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function1<? super com.example.tiffin.ui.dialogs.CustomerDialogResult, kotlin.Unit> p0) {
    }
    
    private final android.net.Uri createTempPhotoUri() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.app.Dialog onCreateDialog(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/tiffin/ui/dialogs/CustomerDialogFragment$Companion;", "", "()V", "newInstance", "Lcom/example/tiffin/ui/dialogs/CustomerDialogFragment;", "title", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.tiffin.ui.dialogs.CustomerDialogFragment newInstance(@org.jetbrains.annotations.NotNull()
        java.lang.String title) {
            return null;
        }
    }
}