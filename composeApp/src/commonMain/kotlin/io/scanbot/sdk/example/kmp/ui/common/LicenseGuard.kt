package io.scanbot.sdk.example.kmp.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import io.scanbot.sdk.kmp.ScanbotSDK

@Composable
fun LicenseGuard(
    content: @Composable (checkLicense: (() -> Unit) -> Unit) -> Unit
) {
    var licenseError by rememberSaveable { mutableStateOf<String?>(null) }

    val checkLicense: (() -> Unit) -> Unit = { action ->
        ScanbotSDK.getLicenseInfo().onSuccess { info ->
            if (info.isValid) {
                action()
            } else {
                licenseError = info.licenseStatusMessage
            }
        }.onFailure { error ->
            licenseError = "Error getting license info. ${error.message}"
        }
    }

    content(checkLicense)

    if (licenseError != null) {
        ErrorDialog(
            "License Error",
            "Your Scanbot SDK license is not valid. $licenseError",
            { licenseError = null })
    }
}
