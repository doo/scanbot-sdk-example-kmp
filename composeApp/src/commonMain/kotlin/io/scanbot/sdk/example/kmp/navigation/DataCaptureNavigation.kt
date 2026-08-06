package io.scanbot.sdk.example.kmp.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import io.scanbot.sdk.example.kmp.ui.data_capture.CheckResultPreviewScreen
import io.scanbot.sdk.example.kmp.ui.data_capture.CreditCardResultPreviewScreen
import io.scanbot.sdk.example.kmp.ui.data_capture.DocumentDataResultPreviewScreen
import io.scanbot.sdk.example.kmp.ui.data_capture.MrzResultPreviewScreen
import io.scanbot.sdk.example.kmp.ui.data_capture.TextPatternResultPreviewScreen
import io.scanbot.sdk.example.kmp.ui.data_capture.VinResultPreviewScreen
import io.scanbot.sdk.example.kmp.ui.data_capture.prettyPrinted
import io.scanbot.sdk.kmp.check.CheckScanningResult
import io.scanbot.sdk.kmp.creditcard.CreditCardScanningResult
import io.scanbot.sdk.kmp.documentdata.DocumentDataExtractionResult
import io.scanbot.sdk.kmp.genericdocument.GenericDocument
import io.scanbot.sdk.kmp.image.ImageRef
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
    internal var resultStatus by mutableStateOf<String?>(null)
        private set
    internal var resultImage by mutableStateOf<ImageRef?>(null)
        private set
    internal var resultJson by mutableStateOf("")
        private set
    internal var genericDocument by mutableStateOf<GenericDocument?>(null)
        private set

    override fun showVinResult(result: VinScannerUiResult) {
        updateResultState(
            status = if (result.textResult.validationSuccessful) "Valid" else "Invalid",
            image = null,
            json = result.toJson().prettyPrinted(),
            document = null
        )
        navigate(
            Route.VinResult(
                textVin = result.textResult.rawText,
                barcodeVin = result.barcodeResult.extractedVIN,
                barcodeStatus = result.barcodeResult.status.name,
                barcodeRectangle = result.barcodeResult.rectangle
                    .takeIf(List<*>::isNotEmpty)
                    ?.joinToString { "(${it.x}, ${it.y})" }
            )
        )
    }

    override fun showCheckResult(result: CheckScannerUiResult) {
        updateResultState(
            status = result.recognitionStatus.name,
            image = result.croppedImage,
            json = result.toJson().prettyPrinted(),
            document = result.check
        )
        navigate(Route.CheckResult)
    }

    override fun showCheckImageResult(result: CheckScanningResult) {
        updateResultState(
            status = result.status.name,
            image = result.croppedImage,
            json = result.toJson().prettyPrinted(),
            document = result.check
        )
        navigate(Route.CheckResult)
    }

    override fun showMrzResult(result: MrzScannerUiResult) {
        updateResultState(
            status = null,
            image = result.mrzDocument?.crop,
            json = result.toJson().prettyPrinted(),
            document = result.mrzDocument
        )
        navigate(
            Route.MrzResult(
                rawMrz = result.rawMRZ
            )
        )
    }

    override fun showMrzImageResult(result: MrzScannerResult) {
        updateResultState(
            status = null,
            image = result.document?.crop,
            json = result.toJson().prettyPrinted(),
            document = result.document
        )
        navigate(
            Route.MrzResult(
                rawMrz = result.rawMRZ
            )
        )
    }

    override fun showDocumentDataResult(result: DocumentDataExtractorUiResult) {
        updateResultState(
            status = result.recognitionStatus.name,
            image = result.croppedImage,
            json = result.toJson().prettyPrinted(),
            document = result.document
        )
        navigate(
            Route.DocumentDataResult(
                documentType = result.document?.type?.name
            )
        )
    }

    override fun showDocumentDataImageResult(result: DocumentDataExtractionResult) {
        updateResultState(
            status = result.status.name,
            image = result.croppedImage,
            json = result.toJson().prettyPrinted(),
            document = result.document
        )
        navigate(
            Route.DocumentDataResult(
                documentType = result.document?.type?.name
            )
        )
    }

    override fun showTextPatternResult(result: TextPatternScannerUiResult) {
        updateResultState(
            status = null,
            image = null,
            json = result.toJson().prettyPrinted(),
            document = null
        )
        navigate(
            Route.TextPatternResult(
                rawText = result.rawText,
                words = result.wordBoxes.map { it.text }
            )
        )
    }

    override fun showCreditCardResult(result: CreditCardScannerUiResult) {
        updateResultState(
            status = result.recognitionStatus.name,
            image = result.creditCard?.crop,
            json = result.toJson().prettyPrinted(),
            document = result.creditCard
        )
        navigate(Route.CreditCardResult)
    }

    override fun showCreditCardImageResult(result: CreditCardScanningResult) {
        updateResultState(
            status = result.scanningStatus.name,
            image = result.creditCard?.crop,
            json = result.toJson().prettyPrinted(),
            document = result.creditCard
        )
        navigate(Route.CreditCardResult)
    }

    private fun updateResultState(
        status: String?,
        image: ImageRef?,
        json: String,
        document: GenericDocument?,
    ) {
        resultStatus = status
        resultImage = image
        resultJson = json
        genericDocument = document
    }
}

internal fun NavGraphBuilder.dataCaptureResultDestinations(
    state: DataCaptureNavigationState,
    onPopBackStack: () -> Unit,
) {
    composable<Route.VinResult> { backStackEntry ->
        val route: Route.VinResult = backStackEntry.toRoute()
        VinResultPreviewScreen(
            status = state.resultStatus,
            image = state.resultImage,
            rawJson = state.resultJson,
            textVin = route.textVin,
            barcodeVin = route.barcodeVin,
            barcodeStatus = route.barcodeStatus,
            barcodeRectangle = route.barcodeRectangle,
            onPopBackStack = onPopBackStack
        )
    }

    composable<Route.CheckResult> {
        CheckResultPreviewScreen(
            status = state.resultStatus,
            image = state.resultImage,
            rawJson = state.resultJson,
            document = state.genericDocument,
            onPopBackStack = onPopBackStack
        )
    }

    composable<Route.MrzResult> { backStackEntry ->
        val route: Route.MrzResult = backStackEntry.toRoute()
        MrzResultPreviewScreen(
            status = state.resultStatus,
            image = state.resultImage,
            rawJson = state.resultJson,
            document = state.genericDocument,
            rawMrz = route.rawMrz,
            onPopBackStack = onPopBackStack
        )
    }

    composable<Route.DocumentDataResult> { backStackEntry ->
        val route: Route.DocumentDataResult = backStackEntry.toRoute()
        DocumentDataResultPreviewScreen(
            status = state.resultStatus,
            image = state.resultImage,
            rawJson = state.resultJson,
            document = state.genericDocument,
            documentType = route.documentType,
            onPopBackStack = onPopBackStack
        )
    }

    composable<Route.TextPatternResult> { backStackEntry ->
        val route: Route.TextPatternResult = backStackEntry.toRoute()
        TextPatternResultPreviewScreen(
            status = state.resultStatus,
            image = state.resultImage,
            rawJson = state.resultJson,
            rawText = route.rawText,
            words = route.words,
            onPopBackStack = onPopBackStack
        )
    }

    composable<Route.CreditCardResult> {
        CreditCardResultPreviewScreen(
            status = state.resultStatus,
            image = state.resultImage,
            rawJson = state.resultJson,
            document = state.genericDocument,
            onPopBackStack = onPopBackStack
        )
    }
}
