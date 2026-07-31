package io.scanbot.sdk.example.kmp.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.scanbot.sdk.example.kmp.ui.data_capture.CheckResultPreview
import io.scanbot.sdk.example.kmp.ui.data_capture.CreditCardResultPreview
import io.scanbot.sdk.example.kmp.ui.data_capture.DataCaptureResultNavigator
import io.scanbot.sdk.example.kmp.ui.data_capture.DocumentDataResultPreview
import io.scanbot.sdk.example.kmp.ui.data_capture.MrzResultPreview
import io.scanbot.sdk.example.kmp.ui.data_capture.TextPatternResultPreview
import io.scanbot.sdk.example.kmp.ui.data_capture.VinResultPreview
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

internal class DataCaptureNavigationState(
    private val navigate: (Route) -> Unit,
) : DataCaptureResultNavigator {
    internal var vinResult by mutableStateOf<VinScannerUiResult?>(null)
        private set
    internal var checkResult by mutableStateOf<CheckScannerUiResult?>(null)
        private set
    internal var checkImageResult by mutableStateOf<CheckScanningResult?>(null)
        private set
    internal var mrzResult by mutableStateOf<MrzScannerUiResult?>(null)
        private set
    internal var mrzImageResult by mutableStateOf<MrzScannerResult?>(null)
        private set
    internal var documentDataResult by mutableStateOf<DocumentDataExtractorUiResult?>(null)
        private set
    internal var documentDataImageResult by mutableStateOf<DocumentDataExtractionResult?>(null)
        private set
    internal var textPatternResult by mutableStateOf<TextPatternScannerUiResult?>(null)
        private set
    internal var creditCardResult by mutableStateOf<CreditCardScannerUiResult?>(null)
        private set
    internal var creditCardImageResult by mutableStateOf<CreditCardScanningResult?>(null)
        private set

    override fun showVinResult(result: VinScannerUiResult) {
        vinResult = result
        navigate(Route.VinResult)
    }

    override fun showCheckResult(result: CheckScannerUiResult) {
        checkResult = result
        checkImageResult = null
        navigate(Route.CheckResult)
    }

    override fun showCheckImageResult(result: CheckScanningResult) {
        checkResult = null
        checkImageResult = result
        navigate(Route.CheckResult)
    }

    override fun showMrzResult(result: MrzScannerUiResult) {
        mrzResult = result
        mrzImageResult = null
        navigate(Route.MrzResult)
    }

    override fun showMrzImageResult(result: MrzScannerResult) {
        mrzResult = null
        mrzImageResult = result
        navigate(Route.MrzResult)
    }

    override fun showDocumentDataResult(result: DocumentDataExtractorUiResult) {
        documentDataResult = result
        documentDataImageResult = null
        navigate(Route.DocumentDataResult)
    }

    override fun showDocumentDataImageResult(result: DocumentDataExtractionResult) {
        documentDataResult = null
        documentDataImageResult = result
        navigate(Route.DocumentDataResult)
    }

    override fun showTextPatternResult(result: TextPatternScannerUiResult) {
        textPatternResult = result
        navigate(Route.TextPatternResult)
    }

    override fun showCreditCardResult(result: CreditCardScannerUiResult) {
        creditCardResult = result
        creditCardImageResult = null
        navigate(Route.CreditCardResult)
    }

    override fun showCreditCardImageResult(result: CreditCardScanningResult) {
        creditCardResult = null
        creditCardImageResult = result
        navigate(Route.CreditCardResult)
    }
}

internal fun NavGraphBuilder.dataCaptureResultDestinations(
    state: DataCaptureNavigationState,
    onPopBackStack: () -> Unit,
) {
    composable<Route.VinResult> {
        state.vinResult?.let { result ->
            VinResultPreview(result, onPopBackStack)
        }
    }

    composable<Route.CheckResult> {
        state.checkResult?.let { result ->
            CheckResultPreview(result, onPopBackStack)
        } ?: state.checkImageResult?.let { result ->
            CheckResultPreview(result, onPopBackStack)
        }
    }

    composable<Route.MrzResult> {
        state.mrzResult?.let { result ->
            MrzResultPreview(result, onPopBackStack)
        } ?: state.mrzImageResult?.let { result ->
            MrzResultPreview(result, onPopBackStack)
        }
    }

    composable<Route.DocumentDataResult> {
        state.documentDataResult?.let { result ->
            DocumentDataResultPreview(result, onPopBackStack)
        } ?: state.documentDataImageResult?.let { result ->
            DocumentDataResultPreview(result, onPopBackStack)
        }
    }

    composable<Route.TextPatternResult> {
        state.textPatternResult?.let { result ->
            TextPatternResultPreview(result, onPopBackStack)
        }
    }

    composable<Route.CreditCardResult> {
        state.creditCardResult?.let { result ->
            CreditCardResultPreview(result, onPopBackStack)
        } ?: state.creditCardImageResult?.let { result ->
            CreditCardResultPreview(result, onPopBackStack)
        }
    }
}
