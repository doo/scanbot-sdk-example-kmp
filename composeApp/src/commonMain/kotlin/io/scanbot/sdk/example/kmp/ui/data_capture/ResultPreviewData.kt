package io.scanbot.sdk.example.kmp.ui.data_capture

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

internal data class ResultSection(
    val title: String,
    val fields: List<ResultField>,
)

internal data class ResultField(
    val label: String,
    val value: String,
    val emphasize: Boolean = false,
)

internal fun MutableList<ResultField>.addField(
    label: String,
    value: String?,
    emphasize: Boolean = false,
) {
    value?.takeIf(String::isNotBlank)?.let {
        add(ResultField(label = label, value = it, emphasize = emphasize))
    }
}

@OptIn(ExperimentalSerializationApi::class)
private val prettyJson = Json {
    prettyPrint = true
    prettyPrintIndent = "  "
}

internal fun JsonObject.prettyPrinted(): String =
    prettyJson.encodeToString(JsonElement.serializer(), this)
