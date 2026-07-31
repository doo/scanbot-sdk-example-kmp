package io.scanbot.sdk.example.kmp.ui.data_capture

import io.scanbot.sdk.kmp.check.CheckScanningResult
import io.scanbot.sdk.kmp.creditcard.CreditCardScanningResult
import io.scanbot.sdk.kmp.documentdata.DocumentDataExtractionResult
import io.scanbot.sdk.kmp.mrz.MrzScannerResult
import io.scanbot.sdk.kmp.ui_v2.check.configuration.CheckScannerUiResult
import io.scanbot.sdk.kmp.ui_v2.creditcard.configuration.CreditCardScannerUiResult
import io.scanbot.sdk.kmp.ui_v2.documentdata.configuration.DocumentDataExtractorUiResult
import io.scanbot.sdk.kmp.ui_v2.mrz.configuration.MrzScannerUiResult
import io.scanbot.sdk.kmp.ui_v2.textpattern.configuration.TextPatternScannerUiResult
import io.scanbot.sdk.kmp.ui_v2.vin.configuration.VinScannerUiResult

interface DataCaptureResultNavigator {
    fun showVinResult(result: VinScannerUiResult)
    fun showCheckResult(result: CheckScannerUiResult)
    fun showCheckImageResult(result: CheckScanningResult)
    fun showMrzResult(result: MrzScannerUiResult)
    fun showMrzImageResult(result: MrzScannerResult)
    fun showDocumentDataResult(result: DocumentDataExtractorUiResult)
    fun showDocumentDataImageResult(result: DocumentDataExtractionResult)
    fun showTextPatternResult(result: TextPatternScannerUiResult)
    fun showCreditCardResult(result: CreditCardScannerUiResult)
    fun showCreditCardImageResult(result: CreditCardScanningResult)
}
