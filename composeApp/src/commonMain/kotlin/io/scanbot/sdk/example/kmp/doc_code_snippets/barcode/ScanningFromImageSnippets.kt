package io.scanbot.sdk.example.kmp.doc_code_snippets.barcode

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.barcode.BarcodeScannerConfiguration
import io.scanbot.sdk.kmp.barcode.BarcodeScannerResult
import io.scanbot.sdk.kmp.image.ImageRef
import io.scanbot.sdk.kmp.utils.Result

// @Tag("Scanning from Image")
fun scanBarcodeFromImage(
    imageRef: ImageRef
): Result<BarcodeScannerResult> {
    val configuration = BarcodeScannerConfiguration()
    configuration.returnBarcodeImage = true

    return ScanbotSDK.barcode.scanFromImage(
        image = imageRef,
        configuration = configuration
    )
}
// @EndTag("Scanning from Image")

fun processImageSnippet(imageRef: ImageRef) {
// @Tag("Detecting barcodes")
    val configuration = BarcodeScannerConfiguration()
    val result = ScanbotSDK.barcode.scanFromImage(
        image = imageRef,
        configuration = configuration
    )

    result.getOrNull()?.let { scannerResult ->
        // handle the detected barcode(s) from result
    }
// @EndTag("Detecting barcodes")
}

fun handleResult(imageRef: ImageRef) {
// @Tag("Handling the Result")
    val configuration = BarcodeScannerConfiguration()
    val result = ScanbotSDK.barcode.scanFromImage(
        image = imageRef,
        configuration = configuration
    )

    result.getOrNull()?.let { barcodeData ->
        val barcodesTextResult = StringBuilder()
        for (item in barcodeData.barcodes) {
            barcodesTextResult.append(item.format.name + "" + item.text).append("").append("-------------------").append("")
        }
        // Display or process the result as needed
    }
// @EndTag("Handling the Result")
}

