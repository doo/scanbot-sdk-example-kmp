package io.scanbot.sdk.example.kmp.snippets.barcode.scanner.common_use_cases

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeItemMapper
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeMappedData
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeScannerScreenConfiguration
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.MultipleScanningMode

fun rtuUiMappingItemScanningUseCase(): BarcodeScannerScreenConfiguration {
    // Create the default configuration object.
    val configuration = BarcodeScannerScreenConfiguration();

    // Initialize the use case for single scanning.
    val scanningMode = MultipleScanningMode();

    // Set the item mapper.
    scanningMode.barcodeInfoMapping.barcodeItemMapper =
        BarcodeItemMapper { item, onResult, onError ->
            if (item.text.isNotEmpty()) {
                onResult.onResult(
                    BarcodeMappedData(
                        title = "Mapped: ${item.text}",
                        subtitle = item.format.name,
                        barcodeImage = ""
                    )
                )
            } else {
                onError.onError()
            }
        }

    configuration.useCase = scanningMode;

    // Configure other parameters as needed.

    return configuration;
}

fun startMappingItemScanning(
    onResultHandler: (barcodeResult: String) -> Unit
) {
    ScanbotSDK.barcode.startScanner(
        configuration = rtuUiMappingItemScanningUseCase(),
        onResult = { result ->
            result.onSuccess {
                onResultHandler(it.toJson().toString())
            }
        }
    )
}