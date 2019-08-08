-dontwarn rx.**
-dontwarn okio.**
-dontwarn javax.annotation.**

-dontwarn okhttp3.**
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *; }
-keep interface com.squareup.okhttp3.** { *; }
-dontwarn javax.annotation.Nullable
-dontwarn javax.annotation.ParametersAreNonnullByDefault

-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}
-dontwarn com.google.errorprone.annotations.*
-dontwarn javax.naming.directory.*
-dontwarn javax.naming.*
-dontwarn junit.textui.*
-dontnote org.apache.commons.codec.**
-dontwarn org.conscrypt.*
-keep public class com.koohpar.oghli.data.model.** { *;}
