package io.scanbot.sdk.example.kmp.navigation

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object DocumentUseCases : Route

    @Serializable
    data class DocumentPagePreview(val documentUuid: String, val pageUuid: String) : Route

    @Serializable
    data class DocumentPreview(val documentUuid: String) : Route
}
