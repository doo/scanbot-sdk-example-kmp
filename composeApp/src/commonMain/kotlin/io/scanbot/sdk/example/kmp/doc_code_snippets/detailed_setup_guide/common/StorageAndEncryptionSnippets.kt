package io.scanbot.sdk.example.kmp.doc_code_snippets.detailed_setup_guide.common

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.common.sdk.configuration.FileEncryptionMode
import io.scanbot.sdk.kmp.common.sdk.configuration.SdkConfiguration

/*
    NOTE: this snippet of code is to be used only as a part of the website documentation.
    This code is not intended for any use outside of the support of documentation by Scanbot SDK GmbH employees.
*/

fun initSDKSnippet() {
    // @Tag("Initialize SDK")
    // Example for using a custom storage directory:
    val customStorageDir = "file:///path/to/my-custom-storage-dir"

    val configuration = SdkConfiguration(
        licenseKey = "YOUR_SCANBOT_SDK_LICENSE_KEY",
        storageBaseDirectory = customStorageDir
    )

    ScanbotSDK.initialize(configuration)
    // @EndTag("Initialize SDK")
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