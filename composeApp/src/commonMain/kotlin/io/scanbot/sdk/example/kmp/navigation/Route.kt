package io.scanbot.sdk.example.kmp.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
sealed interface Route: NavKey {

    val title: String

    @Serializable
    data object Menu : Route {
        override val title = "Menu"
    }

    @Serializable
    data object BarcodeUseCases : Route {
        override val title = "Barcode Use Cases"
    }

    @Serializable
    data object DocumentUseCases : Route {
        override val title = "Document Use Cases"
    }

    @Serializable
    data class BarcodePreview(
        val resultJson: String
    ) : Route {
        override val title = "Barcode Preview"
    }

    @Serializable
    data class DocumentPreview(
        val resultJson: String
    ) : Route {
        override val title = "Document Preview"
    }

    @Serializable
    data object BarcodeCustomUI : Route {
        override val title = "Barcode Custom UI"
    }
}