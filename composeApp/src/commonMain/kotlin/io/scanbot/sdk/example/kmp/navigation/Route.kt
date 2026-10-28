package io.scanbot.sdk.example.kmp.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object DocumentUseCases : Route

    @Serializable
    data class DocumentPagePreview(val documentUuid: String, val pageUuid: String) : Route

    @Serializable
    data class DocumentPreview(val documentUuid: String) : Route

    @Serializable
    data class ImagePreview(val imageUuid: String) : Route

    @Serializable
    data class VinResult(
        val textVin: String? = null,
        val barcodeVin: String? = null,
        val barcodeStatus: String? = null,
        val barcodeRectangle: String? = null,
    ) : Route

    @Serializable
    data object CheckResult : Route

    @Serializable
    data class MrzResult(
        val rawMrz: String? = null,
    ) : Route

    @Serializable
    data class DocumentDataResult(
        val documentType: String? = null,
    ) : Route

    @Serializable
    data class TextPatternResult(
        val rawText: String? = null,
        val words: List<String> = emptyList(),
    ) : Route

    @Serializable
    data object CreditCardResult : Route
}
