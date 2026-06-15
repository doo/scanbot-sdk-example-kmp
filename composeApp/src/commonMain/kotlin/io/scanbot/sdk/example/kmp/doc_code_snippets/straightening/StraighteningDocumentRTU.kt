package io.scanbot.sdk.example.kmp.doc_code_snippets.straightening

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.documentscanner.DocumentStraighteningMode
import io.scanbot.sdk.kmp.geometry.AspectRatio
import io.scanbot.sdk.kmp.ui_v2.document.configuration.DocumentScanningFlow

fun straighteningDocument() {
    /** Create the default configuration instance */
    val configuration = DocumentScanningFlow()
    val straighteningParameters = configuration.outputSettings.straighteningParameters

    /** Configure the straightening mode as needed **/
    straighteningParameters.straighteningMode = DocumentStraighteningMode.STRAIGHTEN

    /**
     * The straightening parameters can be customized to fit the expected aspect ratio of the document
     * to be straightened. This can help the straightening algorithm to achieve better results.
     */
    straighteningParameters.aspectRatios = listOf(
        AspectRatio(5.0, 7.0),
        AspectRatio(1.0, 1.0),
        AspectRatio(16.0, 9.0),
        AspectRatio(3.0, 4.0),
    )

    /** Start the Document Scanner UI */
    ScanbotSDK.document.startScanner(
        configuration = configuration, onResult = { result ->
            result.onSuccess {
                // Handle the scanned document result with straightening applied
            }.onFailure {
                // Handle error
            }
        }
    )
}