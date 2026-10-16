package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.image_recognizers

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.creditcard.CreditCardScannerConfiguration
import io.scanbot.sdk.kmp.creditcard.CreditCardScanningResult
import io.scanbot.sdk.kmp.image.ImageRef
import io.scanbot.sdk.kmp.utils.Result

// @Tag("Scan credit card from image")
fun recognizeCreditCardOnImage(image: ImageRef): Result<CreditCardScanningResult> {
    val configuration = CreditCardScannerConfiguration()
    configuration.requireCardholderName = true
    // Configure other parameters as needed.

    return ScanbotSDK.creditCard.scanFromImage(
        image = image,
        configuration = configuration,
    )
}
// @EndTag("Scan credit card from image")
