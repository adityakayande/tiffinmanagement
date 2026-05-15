package com.example.tiffin.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nJ\u0018\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\r\u001a\u00020\u000eJ\u001e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0004J\u0016\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0004\u00a8\u0006\u0016"}, d2 = {"Lcom/example/tiffin/ui/ShareUtils;", "", "()V", "buildDueMessage", "", "customerName", "month", "", "year", "dueAmount", "", "monthName", "month1To12", "locale", "Ljava/util/Locale;", "openSmsApp", "", "context", "Landroid/content/Context;", "phone", "message", "shareText", "app_debug"})
public final class ShareUtils {
    @org.jetbrains.annotations.NotNull()
    public static final com.example.tiffin.ui.ShareUtils INSTANCE = null;
    
    private ShareUtils() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String monthName(int month1To12, @org.jetbrains.annotations.NotNull()
    java.util.Locale locale) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String buildDueMessage(@org.jetbrains.annotations.NotNull()
    java.lang.String customerName, int month, int year, long dueAmount) {
        return null;
    }
    
    public final void shareText(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
    
    public final void openSmsApp(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String phone, @org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
}