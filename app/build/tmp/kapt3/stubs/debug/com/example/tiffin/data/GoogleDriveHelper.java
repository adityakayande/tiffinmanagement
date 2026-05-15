package com.example.tiffin.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0018\u0010\f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\rJ\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000f\u001a\u00020\u0007H\u0002J\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\b\u001a\u00020\tJ\u001e\u0010\u0014\u001a\u00020\u00132\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u0004H\u0086@\u00a2\u0006\u0002\u0010\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/example/tiffin/data/GoogleDriveHelper;", "", "()V", "BACKUP_FILE_NAME", "", "MIME_JSON", "buildDriveService", "Lcom/google/api/services/drive/Drive;", "context", "Landroid/content/Context;", "account", "Lcom/google/android/gms/auth/api/signin/GoogleSignInAccount;", "downloadBackup", "(Landroid/content/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "findBackupFileId", "driveService", "getSignInOptions", "Lcom/google/android/gms/auth/api/signin/GoogleSignInOptions;", "isSignedIn", "", "uploadBackup", "jsonContent", "(Landroid/content/Context;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class GoogleDriveHelper {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String BACKUP_FILE_NAME = "TiffinBackup.json";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String MIME_JSON = "application/json";
    @org.jetbrains.annotations.NotNull()
    public static final com.example.tiffin.data.GoogleDriveHelper INSTANCE = null;
    
    private GoogleDriveHelper() {
        super();
    }
    
    /**
     * Returns a configured sign-in options object requesting Drive AppData access.
     */
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.gms.auth.api.signin.GoogleSignInOptions getSignInOptions() {
        return null;
    }
    
    /**
     * Returns true if the user is already signed in with Drive access.
     */
    public final boolean isSignedIn(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return false;
    }
    
    /**
     * Builds a Drive service from the currently signed-in account.
     */
    private final com.google.api.services.drive.Drive buildDriveService(android.content.Context context, com.google.android.gms.auth.api.signin.GoogleSignInAccount account) {
        return null;
    }
    
    /**
     * Uploads the JSON string as TiffinBackup.json to the Drive AppData folder.
     * If a previous backup exists it is replaced.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object uploadBackup(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String jsonContent, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    /**
     * Downloads TiffinBackup.json from Drive AppData folder.
     * Returns null if no backup exists.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object downloadBackup(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    private final java.lang.String findBackupFileId(com.google.api.services.drive.Drive driveService) {
        return null;
    }
}