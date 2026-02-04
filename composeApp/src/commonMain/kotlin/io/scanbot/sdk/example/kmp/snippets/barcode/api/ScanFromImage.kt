package io.scanbot.sdk.example.kmp.snippets.barcode.api

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.barcode.BarcodeScannerConfiguration
import io.scanbot.sdk.kmp.barcode.BarcodeScannerResult
import io.scanbot.sdk.kmp.image.ImageRef
import io.scanbot.sdk.kmp.utils.Result

fun scanBarcodeFromImage(
    image: ImageRef,
): Result<BarcodeScannerResult> {
    val configuration = BarcodeScannerConfiguration()
    configuration.returnBarcodeImage = true

    return ScanbotSDK.barcode.scanFromImage(
        image = image,
        configuration = configuration,
    )
}
