package io.scanbot.sdk.example.kmp.ui.data_capture

import io.scanbot.sdk.kmp.check.CheckScanningResult
import io.scanbot.sdk.kmp.creditcard.CreditCardScanningResult
import io.scanbot.sdk.kmp.documentdata.DocumentDataExtractionResult
import io.scanbot.sdk.kmp.genericdocument.GenericDocument
import io.scanbot.sdk.kmp.mrz.MrzScannerResult
import io.scanbot.sdk.kmp.ui_v2.check.configuration.CheckScannerUiResult
import io.scanbot.sdk.kmp.ui_v2.creditcard.configuration.CreditCardScannerUiResult
import io.scanbot.sdk.kmp.ui_v2.documentdata.configuration.DocumentDataExtractorUiResult
import io.scanbot.sdk.kmp.ui_v2.mrz.configuration.MrzScannerUiResult
import io.scanbot.sdk.kmp.ui_v2.textpattern.configuration.TextPatternScannerUiResult
import io.scanbot.sdk.kmp.ui_v2.vin.configuration.VinScannerUiResult
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

internal fun VinScannerUiResult.toResultSections(): List<ResultSection> {
    val textFields = buildList {
        add(ResultField("Text VIN", textResult.rawText, emphasize = true))
        add(
            ResultField(
                "Validation",
                if (textResult.validationSuccessful) "Valid" else "Invalid"
            )
        )
        textResult.wordBoxes.forEachIndexed { index, word ->
            add(
                ResultField(
                    "Word ${index + 1}",
                    word.text,
                )
            )
        }
    }
    val barcodeFields = buildList {
        add(ResultField("Barcode VIN", barcodeResult.extractedVIN, emphasize = true))
        add(ResultField("Extraction status", barcodeResult.status.name.toDisplayLabel()))
        if (barcodeResult.rectangle.isNotEmpty()) {
            add(
                ResultField(
                    "Barcode rectangle",
                    barcodeResult.rectangle.joinToString { "(${it.x}, ${it.y})" }
                )
            )
        }
    }

    return listOf(
        ResultSection("Text recognition", textFields),
        ResultSection("Barcode recognition", barcodeFields)
    )
}

internal fun TextPatternScannerUiResult.toResultSections(): List<ResultSection> =
    listOf(
        ResultSection(
            "Recognition",
            buildList {
                add(ResultField("Raw text", rawText, emphasize = true))
                wordBoxes.forEachIndexed { index, word ->
                    add(
                        ResultField(
                            "Word ${index + 1}",
                            word.text,
                        )
                    )
                }
            }
        )
    )

internal fun CheckScannerUiResult.toResultSections(): List<ResultSection> =
    statusSection("Recognition status", recognitionStatus.name) + check.toDocumentSections()

internal fun CheckScanningResult.toResultSections(): List<ResultSection> =
    statusSection("Recognition status", status.name) + check.toDocumentSections()

internal fun MrzScannerUiResult.toResultSections(): List<ResultSection> =
    rawValueSection("Raw MRZ", rawMRZ) + mrzDocument.toDocumentSections()

internal fun MrzScannerResult.toResultSections(): List<ResultSection> =
    rawValueSection("Raw MRZ", rawMRZ) + document.toDocumentSections()

internal fun DocumentDataExtractorUiResult.toResultSections(): List<ResultSection> =
    statusSection("Recognition status", recognitionStatus.name) + document.toDocumentSections()

internal fun DocumentDataExtractionResult.toResultSections(): List<ResultSection> =
    statusSection("Recognition status", status.name) + document.toDocumentSections()

internal fun CreditCardScannerUiResult.toResultSections(): List<ResultSection> =
    statusSection("Recognition status", recognitionStatus.name) + creditCard.toDocumentSections()

internal fun CreditCardScanningResult.toResultSections(): List<ResultSection> =
    statusSection("Recognition status", scanningStatus.name) + creditCard.toDocumentSections()

private fun GenericDocument?.toDocumentSections(): List<ResultSection> {
    if (this == null) return emptyList()

    return buildList {
        fun addDocument(document: GenericDocument) {
            val resultFields = document.fields.mapNotNull { field ->
                val value = field.value?.text?.takeIf(String::isNotBlank) ?: return@mapNotNull null
                ResultField(
                    label = field.type.name.toDisplayLabel(),
                    value = value
                )
            }
            if (resultFields.isNotEmpty()) {
                add(
                    ResultSection(
                        title = document.type.name.toDisplayLabel(),
                        fields = resultFields
                    )
                )
            }
            document.children.forEach(::addDocument)
        }

        addDocument(this@toDocumentSections)
    }
}

private fun statusSection(label: String, status: String): List<ResultSection> =
    listOf(
        ResultSection(
            "Summary",
            listOf(ResultField(label, status.toDisplayLabel(), emphasize = true))
        )
    )

private fun rawValueSection(label: String, value: String): List<ResultSection> =
    value.takeIf(String::isNotBlank)?.let {
        listOf(ResultSection("MRZ code", listOf(ResultField(label, it, emphasize = true))))
    } ?: emptyList()

private fun String.toDisplayLabel(): String =
    substringAfterLast('.')
        .replace('_', ' ')
        .replace(Regex("([a-z0-9])([A-Z])"), "$1 $2")
        .trim()
        .replaceFirstChar(Char::uppercase)

@OptIn(ExperimentalSerializationApi::class)
private val prettyJson = Json {
    prettyPrint = true
    prettyPrintIndent = "  "
}

internal fun JsonObject.prettyPrinted(): String =
    prettyJson.encodeToString(JsonElement.serializer(), this)
