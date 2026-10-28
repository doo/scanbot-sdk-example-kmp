package io.scanbot.sdk.example.kmp.ui.data_capture

import androidx.compose.runtime.Composable

@Composable
internal fun TextPatternResultPreviewScreen(
    rawJson: String,
    rawText: String?,
    words: List<String>,
    onPopBackStack: () -> Unit,
) {
    val textPatternFields = buildList {
        addField("Raw text", rawText, true)
        words.forEachIndexed { index, word ->
            addField("Word ${index + 1}", word)
        }
    }
    val sections = buildList {
        if (textPatternFields.isNotEmpty()) {
            add(ResultSection("Text pattern data", textPatternFields))
        }
    }

    DataCapturePreview(
        title = "Text Pattern Result",
        sections = sections,
        rawJson = rawJson,
        image = null,
        onPopBackStack = onPopBackStack
    )
}
