package io.scanbot.sdk.example.kmp.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import io.scanbot.sdk.kmp.ScanbotSDK

@Composable
fun LicenseGuard(
    content: @Composable (checkLicense: (() -> Unit) -> Unit) -> Unit
) {
    var showLicenseError by remember { mutableStateOf(false) }

    val checkLicense: (() -> Unit) -> Unit = { action ->
        if (isLicenseValid()) {
            action()
        } else {
            showLicenseError = true
        }
    }

    content(checkLicense)

    if (showLicenseError) {
        LicenseInvalidDialog { showLicenseError = false }
    }
}


fun isLicenseValid(): Boolean =
    ScanbotSDK.getLicenseInfo()
        .getOrNull()
        ?.isValid == true
