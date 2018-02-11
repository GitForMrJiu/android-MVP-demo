# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/xyczero/xyczero/android-sdk-macosx/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-keepnames class android.widget.AbsListView{
    android.widget.AbsListView$RecycleBin mRecycler;
}

-keepnames class android.widget.AbsListView$RecycleBin{
    void clear();
}

-keepnames class android.support.v7.widget.RecyclerView{
    android.support.v7.widget.RecyclerView$Recycler mRecycler;
}

-keepnames class android.support.v7.widget.RecyclerView$Recycler{
    public void clear();
}