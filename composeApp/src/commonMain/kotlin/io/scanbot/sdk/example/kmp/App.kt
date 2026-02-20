package io.scanbot.sdk.example.kmp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import io.scanbot.sdk.example.kmp.navigation.NavigationRoot
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.common.sdk.configuration.SdkConfiguration

@Composable
fun App() {
    MaterialTheme {
        // Single execution as the key ensures the effect runs only once because the key never changes
        LaunchedEffect(Unit) {
            val config = SdkConfiguration(
                licenseKey = "" // TODO: Replace with your Scanbot SDK license key
            )
            ScanbotSDK.initialize(config)
        }
        NavigationRoot()
    }
}