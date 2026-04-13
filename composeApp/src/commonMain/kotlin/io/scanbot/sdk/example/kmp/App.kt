package io.scanbot.sdk.example.kmp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import io.scanbot.sdk.example.kmp.navigation.NavigationRoot
import io.scanbot.sdk.example.kmp.utils.getDemoStorageBaseDirectory
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.common.sdk.configuration.FileEncryptionMode
import io.scanbot.sdk.kmp.common.sdk.configuration.SdkConfiguration

const val SCANBOT_SDK_LICENSE_KEY = "" // TODO: Replace with your Scanbot SDK license key
const val FILE_ENCRYPTION_ENABLED = false

@Composable
fun App() {
    MaterialTheme {
        LaunchedEffect(Unit) {
            val config = SdkConfiguration(
                licenseKey = SCANBOT_SDK_LICENSE_KEY,
                loggingEnabled = true, // Consider switching logging OFF in production builds for security and performance reasons!
                storageBaseDirectory = getDemoStorageBaseDirectory()
            )

            // Set the following properties to enable encryption.
            if (FILE_ENCRYPTION_ENABLED) {
                config.fileEncryptionMode = FileEncryptionMode.AES256
                config.fileEncryptionPassword = "SomeSecretPaSSw0rdForFileEncryption"
            }

            ScanbotSDK.initialize(config).onSuccess { licenseInfo ->
                print("Scanbot SDK initialized successfully. License status: ${licenseInfo.status}")
            }.onFailure { error ->
                println("Error initializing Scanbot SDK: ${error.message}")
            }
        }

        NavigationRoot()
    }
}

