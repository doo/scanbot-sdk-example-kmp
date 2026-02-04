package io.scanbot.sdk.example.kmp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import io.scanbot.sdk.example.kmp.navigation.NavigationRoot
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.common.sdk.configuration.SdkConfiguration

@Composable
fun App() {
    MaterialTheme {
        ScanbotSDK.initialize(configuration = SdkConfiguration(licenseKey = ""))
        NavigationRoot()
    }
}