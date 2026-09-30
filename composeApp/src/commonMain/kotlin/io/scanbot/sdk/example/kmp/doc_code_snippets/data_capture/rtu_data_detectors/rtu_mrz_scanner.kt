package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtu_data_detectors

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.mrz.MrzIncompleteResultHandling
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.common.configuration.StyledText
import io.scanbot.sdk.kmp.ui_v2.mrz.configuration.MrzScannerScreenConfiguration
import io.scanbot.sdk.kmp.ui_v2.mrz.configuration.MrzScannerUiResult

// @Tag("RTU MRZ Scanner")
fun startRtuMrzScanner(
    onResultHandler: (MrzScannerUiResult) -> Unit,
    onErrorHandler: (Throwable) -> Unit,
) {
    // Always make sure you have a valid license on runtime via ScanbotSDK.getLicenseInfo()
    val configuration = MrzScannerScreenConfiguration().apply {
        palette.sbColorPrimary = ScanbotColor("#C8193C")
        palette.sbColorOnPrimary = ScanbotColor("#FFFFFF")

        topUserGuidance.title = StyledText(
            text = "Scan MRZ",
            color = ScanbotColor("#C8193C"),
            useShadow = true,
        )

        actionBar.flipCameraButton.visible = false
        actionBar.flashButton.activeForegroundColor = ScanbotColor("#C8193C")
        scannerConfiguration.incompleteResultHandling = MrzIncompleteResultHandling.ACCEPT
    }
    // Configure other parameters as needed.

    ScanbotSDK.mrz.startScanner(
        configuration = configuration,
        onResult = { result ->
            result.onSuccess(onResultHandler).onFailure(onErrorHandler)
        },
    )
}
// @EndTag("RTU MRZ Scanner")
