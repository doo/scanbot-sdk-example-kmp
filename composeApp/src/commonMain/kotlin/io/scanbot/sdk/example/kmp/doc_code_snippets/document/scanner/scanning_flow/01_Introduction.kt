package io.scanbot.sdk.example.kmp.doc_code_snippets.document.scanner.scanning_flow

// @Tag("Introduction")
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.common.configuration.StyledText
import io.scanbot.sdk.kmp.ui_v2.document.configuration.DocumentScanningFlow
import io.scanbot.sdk.kmp.ui_v2.document.configuration.IntroImage
import io.scanbot.sdk.kmp.ui_v2.document.configuration.IntroListEntry
import io.scanbot.sdk.kmp.ui_v2.document.configuration.IntroductionScreenConfiguration

fun introductionFlowConfig(): DocumentScanningFlow {
    // Create the default configuration object.
    val configuration = DocumentScanningFlow()

    // Configure the introduction screen
    configuration.screens.camera.introduction =
        IntroductionScreenConfiguration().apply {

            showAutomatically = true

            items = listOf(
                // First introduction item
                IntroListEntry().apply {
                    image = IntroImage.receiptsIntroImage()
                    text = StyledText(
                        text = "Some text explaining how to scan a receipt",
                        color = ScanbotColor("#000000")
                    )
                },

                // Second introduction item
                IntroListEntry().apply {
                    image = IntroImage.checkIntroImage()
                    text = StyledText(
                        text = "Some text explaining how to scan a check",
                        color = ScanbotColor("#000000")
                    )
                }
            )

            title = StyledText(
                text = "Introduction",
                color = ScanbotColor("#000000")
            )
        }

    return configuration
}

fun startScanningWithIntroductionFlow() =
    ScanbotSDK.document.startScanner(
        configuration = introductionFlowConfig(),
        onResult = {
            it.onSuccess { TODO("Handle scanned document result") }
            it.onFailure { TODO("Handle error") }
        }
    )
// @EndTag("Introduction")