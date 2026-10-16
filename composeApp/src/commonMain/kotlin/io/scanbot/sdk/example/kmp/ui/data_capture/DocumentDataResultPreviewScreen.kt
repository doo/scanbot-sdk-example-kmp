package io.scanbot.sdk.example.kmp.ui.data_capture

import androidx.compose.runtime.Composable
import io.scanbot.sdk.kmp.genericdocument.GenericDocument
import io.scanbot.sdk.kmp.genericdocument.document.DeHealthInsuranceCardFront
import io.scanbot.sdk.kmp.genericdocument.document.DeIdCardBack
import io.scanbot.sdk.kmp.genericdocument.document.DeIdCardFront
import io.scanbot.sdk.kmp.genericdocument.document.DePassport
import io.scanbot.sdk.kmp.genericdocument.document.DeResidencePermitBack
import io.scanbot.sdk.kmp.genericdocument.document.DeResidencePermitFront
import io.scanbot.sdk.kmp.genericdocument.document.EuropeanDriverLicenseBack
import io.scanbot.sdk.kmp.genericdocument.document.EuropeanDriverLicenseFront
import io.scanbot.sdk.kmp.genericdocument.document.EuropeanHealthInsuranceCard
import io.scanbot.sdk.kmp.image.ImageRef

@Composable
internal fun DocumentDataResultPreviewScreen(
    status: String?,
    image: ImageRef?,
    rawJson: String,
    document: GenericDocument?,
    documentType: String?,
    onPopBackStack: () -> Unit,
) {
    val sections = buildList {
        status?.let {
            add(ResultSection("Summary", listOf(ResultField("Status", it, true))))
        }
        documentType?.takeIf(String::isNotBlank)?.let {
            add(ResultSection("Additional data", listOf(ResultField("Document type", it))))
        }
        document?.let {
            add(ResultSection("Extracted document data", it.toTypedResultFields()))
        }
    }

    DataCapturePreview(
        title = "Document Data Result",
        sections = sections,
        rawJson = rawJson,
        image = image,
        onPopBackStack = onPopBackStack
    )
}

private fun GenericDocument.toTypedResultFields(): List<ResultField> =
    buildList {
        when (type.name) {
            DeHealthInsuranceCardFront.DOCUMENT_TYPE -> {
                val doc = DeHealthInsuranceCardFront(this@toTypedResultFields)
                addField("Name", doc.name.value.text)
                addField("Card Access Number", doc.cardAccessNumber?.value?.text)
                addField("Issuer Name", doc.issuerName.value.text)
                addField("Issuer Number", doc.issuerNumber.value.text)
                addField("Personal Number", doc.personalNumber.value.text)
            }
            DeIdCardFront.DOCUMENT_TYPE -> {
                val doc = DeIdCardFront(this@toTypedResultFields)
                addField("Birth Date", doc.birthDate.value.text)
                addField("Birth Place", doc.birthplace.value.text)
                addField("Expiry Date", doc.expiryDate.value.text)
                addField("Given Names", doc.givenNames.value.text)
                addField("ID", doc.id.value.text)
                addField("Maiden Name", doc.maidenName?.value?.text)
                addField("Nationality", doc.nationality.value.text)
                addField("Surname", doc.surname.value.text)
            }
            DeResidencePermitFront.DOCUMENT_TYPE -> {
                val doc = DeResidencePermitFront(this@toTypedResultFields)
                addField("Birth Date", doc.birthDate?.value?.text)
                addField("Expiry Date", doc.expiryDate.value.text)
                addField("Given Names", doc.givenNames.value.text)
                addField("ID", doc.id.value.text)
                addField("Nationality", doc.nationality?.value?.text)
                addField("Surname", doc.surname.value.text)
            }
            DeResidencePermitBack.DOCUMENT_TYPE -> {
                val doc = DeResidencePermitBack(this@toTypedResultFields)
                addField("Address", doc.address.value.text)
                addField("Birth Date", doc.birthDate?.value?.text)
                addField("Birth Place", doc.birthplace.value.text)
                addField("Eye Color", doc.eyeColor.value.text)
                addField("Gender", doc.gender?.value?.text)
                addField("Height", doc.height.value.text)
                addField("Issuing Authority", doc.issuingAuthority.value.text)
                addField("Nationality", doc.nationality?.value?.text)
                addField("Raw MRZ", doc.rawMRZ.value.text, true)
                addField("Remarks", doc.remarks?.value?.text)
            }
            DeIdCardBack.DOCUMENT_TYPE -> {
                val doc = DeIdCardBack(this@toTypedResultFields)
                addField("Address", doc.address.value.text)
                addField("Eye Color", doc.eyeColor.value.text)
                addField("Height", doc.height.value.text)
                addField("Issue Date", doc.issueDate.value.text)
                addField("Issuing Authority", doc.issuingAuthority.value.text)
                addField("Pseudonym", doc.pseudonym?.value?.text)
                addField("Raw MRZ", doc.rawMRZ.value.text, true)
            }
            DePassport.DOCUMENT_TYPE -> {
                val doc = DePassport(this@toTypedResultFields)
                addField("Birth Date", doc.birthDate.value.text)
                addField("Birth Place", doc.birthplace.value.text)
                addField("Country Code", doc.countryCode.value.text)
                addField("Expiry Date", doc.expiryDate.value.text)
                addField("Gender", doc.gender.value.text)
                addField("Given Names", doc.givenNames.value.text)
                addField("ID", doc.id.value.text)
                addField("Issue Date", doc.issueDate.value.text)
                addField("Issuing Authority", doc.issuingAuthority.value.text)
                addField("Maiden Name", doc.maidenName?.value?.text)
                addField("Nationality", doc.nationality.value.text)
                addField("Passport Type", doc.passportType.value.text)
                addField("Surname", doc.surname.value.text)
                addField("Raw MRZ", doc.rawMRZ.value.text, true)
            }
            EuropeanHealthInsuranceCard.DOCUMENT_TYPE -> {
                val doc = EuropeanHealthInsuranceCard(this@toTypedResultFields)
                addField("Birth Date", doc.birthDate.value.text)
                addField("Card Number", doc.cardNumber.value.text)
                addField("Country Code", doc.countryCode.value.text)
                addField("Expiry Date", doc.expiryDate.value.text)
                addField("Given Names", doc.givenNames.value.text)
                addField("Issuer Name", doc.issuerName.value.text)
                addField("Issuer Number", doc.issuerNumber.value.text)
                addField("Personal Number", doc.personalNumber.value.text)
                addField("Surname", doc.surname.value.text)
            }
            EuropeanDriverLicenseFront.DOCUMENT_TYPE -> {
                val doc = EuropeanDriverLicenseFront(this@toTypedResultFields)
                addField("Birth Date", doc.birthDate.value.text)
                addField("Issue Date", doc.issueDate?.value?.text)
                addField("Issuing Authority", doc.issuingAuthority?.value?.text)
                addField("Expiry Date", doc.expiryDate.value.text)
                addField("Given Names", doc.givenNames.value.text)
                addField("License Categories", doc.licenseCategories?.value?.text)
                addField("Serial Number", doc.serialNumber?.value?.text)
                addField("Surname", doc.surname.value.text)
            }
            EuropeanDriverLicenseBack.DOCUMENT_TYPE -> {
                val doc = EuropeanDriverLicenseBack(this@toTypedResultFields)
                addField("Restrictions", doc.restrictions?.value?.text)
                addField("Categories A1", doc.categories.a1.validFrom?.value?.text)
                addField("Categories A2", doc.categories.a2.validFrom?.value?.text)
                addField("Categories B1", doc.categories.b1?.validFrom?.value?.text)
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
