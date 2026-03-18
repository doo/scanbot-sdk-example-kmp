package io.scanbot.sdk.example.kmp.navigation

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object Menu : Route

    @Serializable
    data object BarcodeUseCases : Route

    @Serializable
    data object DocumentUseCases : Route

    @Serializable
    data class BarcodePreview(val barcodeJson: String) : Route

    @Serializable
    data class DocumentPreview(val documentDataJson: String) : Route

    @Serializable
    data object BarcodeCustomUI : Route
}
