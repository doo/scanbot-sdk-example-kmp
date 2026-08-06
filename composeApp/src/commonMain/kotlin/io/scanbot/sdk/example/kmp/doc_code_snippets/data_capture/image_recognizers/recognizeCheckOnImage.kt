package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.image_recognizers

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.check.CheckDocumentDetectionMode
import io.scanbot.sdk.kmp.check.CheckScannerConfiguration
import io.scanbot.sdk.kmp.check.CheckScanningResult
import io.scanbot.sdk.kmp.image.ImageRef
import io.scanbot.sdk.kmp.utils.Result

// @Tag("Scan check from image")
fun recognizeCheckOnImage(image: ImageRef): Result<CheckScanningResult> {
    val configuration = CheckScannerConfiguration()
    configuration.documentDetectionMode = CheckDocumentDetectionMode.DETECT_DOCUMENT
    // Configure other parameters as needed.

    return ScanbotSDK.check.scanFromImage(
        image = image,
        configuration = configuration,
    )
}
// @EndTag("Scan check from image")
