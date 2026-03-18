package io.scanbot.sdk.example.kmp.doc_code_snippets.document.scanner.common_use_cases

// @Tag("Single Page")
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.common.sdk.configuration.SdkConfiguration
import io.scanbot.sdk.kmp.page.DocumentData
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.document.configuration.DocumentScanningFlow
import io.scanbot.sdk.kmp.ui_v2.document.configuration.PageSnapCheckMarkAnimation
import io.scanbot.sdk.kmp.ui_v2.document.configuration.PageSnapFunnelAnimation

fun rtuUiSinglePageScanningUseCase(): DocumentScanningFlow {
    // Create the default configuration object.
    val configuration = DocumentScanningFlow().apply {

        // Disable the multiple page behavior
        outputSettings.pagesScanLimit = 1
        // Enable/Disable the review screen.
        screens.review.enabled = false
        // Enable/Disable Auto Snapping behavior
        screens.camera.cameraConfiguration.autoSnappingEnabled = true

        /**
         * Configure the animation
         * You can choose between genie animation or checkmark animation
         * Note: Both modes can be further configured to your liking
         * E.g., for genie animation
         */
        screens.camera.captureFeedback.snapFeedbackMode = PageSnapFunnelAnimation()
        // or for checkmark animation
        screens.camera.captureFeedback.snapFeedbackMode = PageSnapCheckMarkAnimation()

        // Hide the auto snapping enable/disable button
        screens.camera.bottomBar.autoSnappingModeButton.visible = false
        screens.camera.bottomBar.manualSnappingModeButton.visible = false
        screens.camera.bottomBar.importButton.title.visible = true
        screens.camera.bottomBar.torchOnButton.title.visible = true
        screens.camera.bottomBar.torchOffButton.title.visible = true

        // Set colors
        palette.sbColorPrimary = ScanbotColor("#C8193CFF")
        palette.sbColorOnPrimary = ScanbotColor("#ffffff")

        // Configure the hint texts for different scenarios
        screens.camera.userGuidance.statesTitles.tooDark =
            "Need more lighting to detect a document"
        screens.camera.userGuidance.statesTitles.tooSmall = "Document too small"
        screens.camera.userGuidance.statesTitles.noDocumentFound =
            "Could not detect a document"

    }
    return configuration
}

fun startSinglePageScanning(
    onResultHandler: (DocumentData) -> Unit,
    onErrorHandler: (error: Throwable) -> Unit
) {
    ScanbotSDK.document.startScanner(
        configuration = rtuUiSinglePageScanningUseCase(),
        onResult = { result ->
            result.onSuccess {
                onResultHandler(it)
            }.onFailure {
                onErrorHandler(it)
            }
        }
    )
}

@Composable
fun DocumentScannerExample() {

    // Initialize the SDK:
    LaunchedEffect(Unit) {
        ScanbotSDK.initialize(
            SdkConfiguration(
                licenseKey = "" // add your license key here
            )
        )
    }

    Button(onClick = {
        // Create the configuration:
        val config = DocumentScanningFlow().apply {
            // TODO: configure as needed
        }

        // Launch the document scanner:
        ScanbotSDK.document.startScanner(
            configuration = config,
            onResult = { result ->
                result.onSuccess { scanResult ->
                    // Document Scanner result callback:
                    // Get the first scanned page from the result object...
                    val firstPage = scanResult.pages.firstOrNull()
                    // ... and process the result as needed, for example, print to console:
                    println("Scanned page count: ${scanResult.pages.size}")
                }.onFailure { error ->
                    // Optional failure handling to understand why scanner result is not provided
                    println("Scanning failed: ${error.message}")
                }
            },
            onCanceled = {
                // Optional: handle user cancellation
            }
        )
    }) {
        Text("Start Document Scanner")
    }
}
// @EndTag("SinglePageScanning")