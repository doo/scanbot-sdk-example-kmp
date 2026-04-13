package io.scanbot.sdk.example.kmp.utils

import android.content.Context

lateinit var appContext: Context

actual fun getDemoStorageBaseDirectory(): String {
    return "${appContext.filesDir.path}/my-custom-storage"
}