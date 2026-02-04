package io.scanbot.sdk.example.kmp.ui.common

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import io.scanbot.sdk.kmp.ScanbotSDK

@Composable
fun LicenseInfoDialog(
    onDismiss: () -> Unit
) {
    val result = ScanbotSDK.getLicenseInfo()

    val text = result.fold(
        onSuccess = { info ->
            "Status: ${info.status.name}\nExpirationDate: ${info.expirationDateString}"
        },
        onFailure = {
            "Error getting license status"
        }
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("License Status") },
        text = {
            Text(
                text = text,
                fontFamily = FontFamily.Monospace
            )
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Close")
            }
        }
    )
}

@Composable
fun LicenseInvalidDialog(
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("License Error") },
        text = {
            Text(
                text = "Your Scanbot SDK license is not valid.\nPlease check your license status.",
                fontFamily = FontFamily.Monospace
            )
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("OK")
            }
        }
    )
}