package io.scanbot.sdk.example.kmp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import io.scanbot.sdk.example.kmp.navigation.NavigationRoot
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.common.SdkConfiguration
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        ScanbotSDK.initialize(configuration = SdkConfiguration())
        NavigationRoot()
    }
}