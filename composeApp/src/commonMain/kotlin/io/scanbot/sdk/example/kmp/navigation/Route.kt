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
    data object VinResult : Route

    @Serializable
    data object CheckResult : Route

    @Serializable
    data object MrzResult : Route

    @Serializable
    data object DocumentDataResult : Route

    @Serializable
    data object TextPatternResult : Route

    @Serializable
    data object CreditCardResult : Route
}
