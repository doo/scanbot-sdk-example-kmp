package io.scanbot.sdk.example.kmp.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import io.scanbot.sdk.kmp.ScanbotSDK

@Composable
fun LicenseGuard(
    content: @Composable (runWithValidLicense: (action: () -> Unit) -> Unit) -> Unit,
) {
    var licenseErrorMessage by rememberSaveable { mutableStateOf<String?>(null) }

    val runWithValidLicense: (action: () -> Unit) -> Unit = remember {
        { action: () -> Unit ->
            ScanbotSDK.getLicenseInfo().fold(
                onSuccess = { info ->
                    if (info.isValid) {
                        action()
                    } else {
                        licenseErrorMessage =
                            "Your Scanbot SDK license is not valid. ${info.licenseStatusMessage}"
                    }
                },
                onFailure = { error ->
                    licenseErrorMessage =
                        "Could not check the Scanbot SDK license. ${error.message}"
                },
            )
        }
    }

    content(runWithValidLicense)

    licenseErrorMessage?.let { message ->
        ErrorDialog(
            title = "License Error",
            message = message,
            onDismiss = { licenseErrorMessage = null },
        )
    }
}
