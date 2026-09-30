package io.scanbot.sdk.example.kmp.ui.data_capture

import androidx.compose.runtime.Composable
import io.scanbot.sdk.kmp.genericdocument.GenericDocument
import io.scanbot.sdk.kmp.genericdocument.check.AUSCheck
import io.scanbot.sdk.kmp.genericdocument.check.CANCheck
import io.scanbot.sdk.kmp.genericdocument.check.FRACheck
import io.scanbot.sdk.kmp.genericdocument.check.INDCheck
import io.scanbot.sdk.kmp.genericdocument.check.ISRCheck
import io.scanbot.sdk.kmp.genericdocument.check.KWTCheck
import io.scanbot.sdk.kmp.genericdocument.check.UAECheck
import io.scanbot.sdk.kmp.genericdocument.check.USACheck
import io.scanbot.sdk.kmp.image.ImageRef

@Composable
internal fun CheckResultPreviewScreen(
    status: String?,
    image: ImageRef?,
    rawJson: String,
    document: GenericDocument?,
    onPopBackStack: () -> Unit,
) {
    val sections = buildList {
        status?.let {
            add(ResultSection("Summary", listOf(ResultField("Status", it, true))))
        }
        if (document == null) {
            add(ResultSection("Check data", listOf(ResultField("Info", "No check data available"))))
        } else {
            add(ResultSection("Check data", document.toTypedResultFields()))
        }
    }

    DataCapturePreview(
        title = "Check Result",
        sections = sections,
        rawJson = rawJson,
        image = image,
        onPopBackStack = onPopBackStack
    )
}

private fun GenericDocument.toTypedResultFields(): List<ResultField> =
    buildList {
        when (type.name) {
            USACheck.DOCUMENT_TYPE -> {
                val check = USACheck(this@toTypedResultFields)
                addField("Account Number", check.accountNumber.value.text)
                addField("Transit Number", check.transitNumber.value.text)
                addField("Auxiliary On-Us", check.auxiliaryOnUs?.value?.text)
                addField("Raw String", check.rawString.value.text, emphasize = true)
                addField("Font Type", check.fontType?.value?.text)
            }
            UAECheck.DOCUMENT_TYPE -> {
                val check = UAECheck(this@toTypedResultFields)
                addField("Account Number", check.accountNumber.value.text)
                addField("Cheque Number", check.chequeNumber.value.text)
                addField("Routing Number", check.routingNumber.value.text)
                addField("Raw String", check.rawString.value.text, emphasize = true)
                addField("Font Type", check.fontType?.value?.text)
            }
            FRACheck.DOCUMENT_TYPE -> {
                val check = FRACheck(this@toTypedResultFields)
                addField("Account Number", check.accountNumber.value.text)
                addField("Cheque Number", check.chequeNumber.value.text)
                addField("Routing Number", check.routingNumber.value.text)
                addField("Raw String", check.rawString.value.text, emphasize = true)
                addField("Font Type", check.fontType?.value?.text)
            }
            ISRCheck.DOCUMENT_TYPE -> {
                val check = ISRCheck(this@toTypedResultFields)
                addField("Account Number", check.accountNumber.value.text)
                addField("Bank Number", check.bankNumber.value.text)
                addField("Branch Number", check.branchNumber.value.text)
                addField("Cheque Number", check.chequeNumber.value.text)
                addField("Raw String", check.rawString.value.text, emphasize = true)
                addField("Font Type", check.fontType?.value?.text)
            }
            KWTCheck.DOCUMENT_TYPE -> {
                val check = KWTCheck(this@toTypedResultFields)
                addField("Account Number", check.accountNumber.value.text)
                addField("Cheque Number", check.chequeNumber.value.text)
                addField("Sort Code", check.sortCode.value.text)
                addField("Raw String", check.rawString.value.text, emphasize = true)
                addField("Font Type", check.fontType?.value?.text)
            }
            AUSCheck.DOCUMENT_TYPE -> {
                val check = AUSCheck(this@toTypedResultFields)
                addField("Account Number", check.accountNumber.value.text)
                addField("BSB", check.bsb.value.text)
                addField("Transaction Code", check.transactionCode.value.text)
                addField("Aux Domestic", check.auxDomestic?.value?.text)
                addField("Extra Aux Domestic", check.extraAuxDomestic?.value?.text)
                addField("Raw String", check.rawString.value.text, emphasize = true)
                addField("Font Type", check.fontType?.value?.text)
            }
            INDCheck.DOCUMENT_TYPE -> {
                val check = INDCheck(this@toTypedResultFields)
                addField("Account Number", check.accountNumber.value.text)
                addField("Serial Number", check.serialNumber.value.text)
                addField("Transaction Code", check.transactionCode.value.text)
                addField("Sort Number", check.sortNumber?.value?.text)
                addField("Raw String", check.rawString.value.text, emphasize = true)
                addField("Font Type", check.fontType?.value?.text)
            }
            CANCheck.DOCUMENT_TYPE -> {
                val check = CANCheck(this@toTypedResultFields)
                addField("Account Number", check.accountNumber.value.text)
                addField("Bank Number", check.bankNumber.value.text)
                addField("Cheque Number", check.chequeNumber.value.text)
                addField("Transit Number", check.transitNumber.value.text)
                addField("Designation Number", check.designationNumber?.value?.text)
                addField("Transaction Code", check.transactionCode?.value?.text)
                addField("Raw String", check.rawString.value.text, emphasize = true)
                addField("Font Type", check.fontType?.value?.text)
            }
            else -> {
                fun addDocumentFields(document: GenericDocument) {
                    document.fields.forEach { field ->
                        addField(field.type.name, field.value?.text)
                    }
                    document.children.forEach(::addDocumentFields)
                }
                addDocumentFields(this@toTypedResultFields)
            }
        }
    }
