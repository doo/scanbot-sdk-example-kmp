package io.scanbot.sdk.example.kmp.ui.data_capture

import androidx.compose.runtime.Composable
import io.scanbot.sdk.kmp.genericdocument.GenericDocument
import io.scanbot.sdk.kmp.genericdocument.creditcard.CreditCard
import io.scanbot.sdk.kmp.image.ImageRef

@Composable
internal fun CreditCardResultPreviewScreen(
    status: String?,
    image: ImageRef?,
    rawJson: String,
    document: GenericDocument?,
    onPopBackStack: () -> Unit,
) {
    val creditCard = document?.let(::CreditCard)

    val fields = buildList<ResultField> {
        if (creditCard == null) {
            add(ResultField("Info", "No credit card data available"))
        } else {
            addField("Card Number", creditCard.cardNumber.value.text)
            addField("Cardholder Name", creditCard.cardholderName?.value?.text)
            addField("Expiry Date", creditCard.expiryDate?.value?.text)
        }
    }
    val sections = buildList {
        status?.let {
            add(ResultSection("Summary", listOf(ResultField("Status", it, true))))
        }
        add(ResultSection("Credit card data", fields))
    }

    DataCapturePreview(
        title = "Credit Card Result",
        sections = sections,
        rawJson = rawJson,
        image = image,
        onPopBackStack = onPopBackStack
    )
}
