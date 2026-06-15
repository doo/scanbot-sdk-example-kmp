package io.scanbot.sdk.example.kmp.doc_code_snippets.straightening

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.common.document.configuration.ModifyPageOptions
import io.scanbot.sdk.kmp.documentscanner.DocumentStraighteningMode
import io.scanbot.sdk.kmp.documentscanner.DocumentStraighteningParameters
import io.scanbot.sdk.kmp.geometry.AspectRatio

fun straighteningPage(pageUuid: String, documentUuid: String) {
    // @Tag("Straightening a scanned page")
    /** Create the default configuration instance */
    val straighteningParameters = DocumentStraighteningParameters()

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

    /** Modify the page with the straightening parameters **/
    ScanbotSDK.document.modifyPage(
        pageUuid,
        documentUuid,
        options = ModifyPageOptions(
            straighteningParameters = straighteningParameters
        )
    )
    // @EndTag("Straightening a scanned page")
}