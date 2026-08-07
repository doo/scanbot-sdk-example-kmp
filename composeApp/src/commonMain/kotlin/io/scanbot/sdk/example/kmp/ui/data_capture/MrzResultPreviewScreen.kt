package io.scanbot.sdk.example.kmp.ui.data_capture

import androidx.compose.runtime.Composable
import io.scanbot.sdk.kmp.genericdocument.GenericDocument
import io.scanbot.sdk.kmp.genericdocument.document.MRZ
import io.scanbot.sdk.kmp.image.ImageRef

@Composable
internal fun MrzResultPreviewScreen(
    image: ImageRef?,
    rawJson: String,
    document: GenericDocument?,
    rawMrz: String?,
    onPopBackStack: () -> Unit,
) {
    val fields = buildList {
        addField("Raw MRZ", rawMrz, true)
        document?.let {
            val mrz = MRZ(it)
            addField("Given Names", mrz.givenNames.value.text)
            addField("Surname", mrz.surname.value.text)
            addField("Birth Date", mrz.birthDate.value.text)
            addField("Document Number", mrz.documentNumber?.value?.text)
            addField("Nationality", mrz.nationality.value.text)
            addField("Gender", mrz.gender?.value?.text)
            addField("Expiry Date", mrz.expiryDate?.value?.text)
        }
    }
    val sections = buildList {
        if (fields.isNotEmpty()) {
            add(ResultSection("MRZ data", fields))
        }
    }

    DataCapturePreview(
        title = "MRZ Document Preview",
        sections = sections,
        rawJson = rawJson,
        image = image,
        onPopBackStack = onPopBackStack
    )
}
