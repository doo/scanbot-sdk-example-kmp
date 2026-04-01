package io.scanbot.sdk.example.kmp.doc_code_snippets

/*
    NOTE: this snippet of code is to be used only as a part of the website documentation.
    This code is not intended for any use outside of the support of documentation by Scanbot SDK GmbH employees.
*/

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.page.DocumentData
import io.scanbot.sdk.kmp.ui_v2.document.configuration.CroppingConfiguration

// @Tag("Start cropping screen for document page")
fun startCroppingScreen(
    documentUuid: String,
    pageUuid: String,
    handleResult: (DocumentData) -> Unit,
    handleError: (Throwable) -> Unit,
) {
    val configuration = CroppingConfiguration(
        documentUuid = documentUuid,
        pageUuid = pageUuid,
        // Optional: customize cropping screen parameters as needed
    )
    ScanbotSDK.document.startCroppingScreen(
        configuration = configuration,
        onResult = { result ->
            result.onSuccess { handleResult(it) }
            result.onFailure { handleError(it) }
        },
        onCanceled = { }
    )
}
// @EndTag("Start cropping screen for document page")
