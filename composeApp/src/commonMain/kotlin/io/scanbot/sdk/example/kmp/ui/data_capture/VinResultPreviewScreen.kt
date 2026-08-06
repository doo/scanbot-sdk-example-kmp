package io.scanbot.sdk.example.kmp.ui.data_capture

import androidx.compose.runtime.Composable
import io.scanbot.sdk.kmp.image.ImageRef

@Composable
internal fun VinResultPreviewScreen(
    status: String?,
    image: ImageRef?,
    rawJson: String,
    textVin: String?,
    barcodeVin: String?,
    barcodeStatus: String?,
    barcodeRectangle: String?,
    onPopBackStack: () -> Unit,
) {
    val vinFields = buildList {
        addField("Text VIN", textVin, true)
        addField("Barcode VIN", barcodeVin, true)
        addField("Barcode status", barcodeStatus)
        addField("Barcode rectangle", barcodeRectangle)
    }
    val sections = buildList {
        status?.let {
            add(ResultSection("Summary", listOf(ResultField("Status", it, true))))
        }
        if (vinFields.isNotEmpty()) {
            add(ResultSection("VIN data", vinFields))
        }
    }

    DataCapturePreview(
        title = "VIN Result",
        sections = sections,
        rawJson = rawJson,
        image = image,
        onPopBackStack = onPopBackStack
    )
}
