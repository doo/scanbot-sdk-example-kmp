package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.image_recognizers

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.image.ImageRef
import io.scanbot.sdk.kmp.mrz.MrzIncompleteResultHandling
import io.scanbot.sdk.kmp.mrz.MrzScannerConfiguration
import io.scanbot.sdk.kmp.mrz.MrzScannerResult
import io.scanbot.sdk.kmp.utils.Result

// @Tag("Scan MRZ from image")
fun recognizeMrzDocumentOnImage(image: ImageRef): Result<MrzScannerResult> {
    val configuration = MrzScannerConfiguration()
    configuration.incompleteResultHandling = MrzIncompleteResultHandling.REJECT
    // Configure other parameters as needed.

    return ScanbotSDK.mrz.scanFromImage(
        image = image,
        configuration = configuration,
    )
}
// @EndTag("Scan MRZ from image")
