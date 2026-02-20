package io.scanbot.sdk.example.kmp.doc_code_snippets.document

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.documentscanner.DocumentScannerConfiguration
import io.scanbot.sdk.kmp.documentscanner.DocumentScanningResult
import io.scanbot.sdk.kmp.documentscanner.PartiallyVisibleDocumentConfiguration
import io.scanbot.sdk.kmp.image.ImageRef
import io.scanbot.sdk.kmp.utils.Result

fun scanDocumentFromImage(
    image: ImageRef,
): Result<DocumentScanningResult> {
    val configuration = DocumentScannerConfiguration()
    configuration.partiallyVisibleDocumentConfiguration = PartiallyVisibleDocumentConfiguration(
        allowPartiallyVisibleDocuments = true,
    )
    // Configure other parameters as needed.

    return ScanbotSDK.document.scanFromImage(
        image = image,
        configuration = configuration,
    )
}
