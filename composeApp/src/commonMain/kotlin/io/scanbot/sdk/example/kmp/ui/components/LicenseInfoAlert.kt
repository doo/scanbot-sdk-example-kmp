package io.scanbot.sdk.example.kmp.ui.components

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
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("License Info") },
        text = {
            Text(
                text = ScanbotSDK
                    .getLicenseInfo()
                    .toJson()
                    .toString(),
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