package io.scanbot.sdk.example.kmp.doc_code_snippets.detailed_setup_guide

// @Tag("Storage and encryption imports")
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.common.sdk.configuration.FileEncryptionMode
import io.scanbot.sdk.kmp.common.sdk.configuration.SdkConfiguration
// @EndTag("Storage and encryption imports")

/*
    NOTE: this snippet of code is to be used only as a part of the website documentation.
    This code is not intended for any use outside of the support of documentation by Scanbot SDK GmbH employees.
*/

fun customStorageDirectory() {
    // @Tag("Custom storage directory")
    val customStorageDir = "file:///path/to/my-custom-storage-dir"

    val configuration = SdkConfiguration(
        licenseKey = "YOUR_SCANBOT_SDK_LICENSE_KEY",
        storageBaseDirectory = customStorageDir
    )

    ScanbotSDK.initialize(configuration)
    // @EndTag("Custom storage directory")
}

fun enableEncryptionInSDKInitializerSnippet() {
    // @Tag("Enable Encryption")
    val configuration = SdkConfiguration(
        licenseKey = "YOUR_SCANBOT_SDK_LICENSE_KEY",
        fileEncryptionMode = FileEncryptionMode.AES256, // or FileEncryptionMode.AES128
        fileEncryptionPassword = "any_user_password"
    )

    ScanbotSDK.initialize(configuration)
    // @EndTag("Enable Encryption")
}

fun completeInitializationWithEncryption() {
    // @Tag("Complete initialization with encryption")
    // Initialize SDK with custom storage and encryption
    val configuration = SdkConfiguration(
        licenseKey = "YOUR_SCANBOT_SDK_LICENSE_KEY",
        storageBaseDirectory = "file:///path/to/custom/storage",
        fileEncryptionMode = FileEncryptionMode.AES256,
        fileEncryptionPassword = "SecurePassword123!"
    )

    ScanbotSDK.initialize(configuration)
    // @EndTag("Complete initialization with encryption")
}