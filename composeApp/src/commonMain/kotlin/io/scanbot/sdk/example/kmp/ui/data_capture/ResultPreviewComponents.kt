package io.scanbot.sdk.example.kmp.ui.data_capture

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.decodeToImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.scanbot.sdk.example.kmp.ui.common.TopBar
import io.scanbot.sdk.kmp.image.ImageRef

@Composable
internal fun DataCapturePreview(
    title: String,
    sections: List<ResultSection>,
    rawJson: String,
    image: ImageRef?,
    onPopBackStack: () -> Unit,
) {
    var imageBitmap by remember(image) { mutableStateOf<ImageBitmap?>(null) }

    LaunchedEffect(image) {
        imageBitmap = image
            ?.encode()
            ?.getOrNull()
            ?.takeIf(ByteArray::isNotEmpty)
            ?.decodeToImageBitmap()
    }

    Scaffold(
        topBar = {
            TopBar(
                title = title,
                showBackButton = true,
                onPopBackStack = onPopBackStack
            )
        }
    ) { paddingValues ->
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(paddingValues)
        ) {
            val compactLayout = maxWidth < 600.dp
            val contentPadding = if (compactLayout) 12.dp else 24.dp
            val contentSpacing = if (compactLayout) 12.dp else 16.dp

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(contentPadding),
                verticalArrangement = Arrangement.spacedBy(contentSpacing)
            ) {
                imageBitmap?.let {
                    ResultImageCard(it, compactLayout)
                }

                if (sections.isEmpty()) {
                    EmptyResultCard()
                } else {
                    sections.forEach { section ->
                        ResultSectionCard(section, compactLayout)
                    }
                }

                RawJsonCard(rawJson, compactLayout)
            }
        }
    }
}

@Composable
private fun ResultImageCard(image: ImageBitmap, compactLayout: Boolean) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        SectionTitle("Captured image")
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.Black),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Image(
                bitmap = image,
                contentDescription = "Captured result",
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = if (compactLayout) 240.dp else 360.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Composable
private fun ResultSectionCard(section: ResultSection, compactLayout: Boolean) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        SectionTitle(section.title)
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainer
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column {
                section.fields.forEachIndexed { index, field ->
                    ResultFieldRow(field, compactLayout)
                    if (index < section.fields.lastIndex) {
                        HorizontalDivider(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            color = MaterialTheme.colorScheme.outlineVariant
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ResultFieldRow(field: ResultField, compactLayout: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(if (compactLayout) 12.dp else 16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = field.label,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        SelectionContainer {
            Text(
                text = field.value,
                style = if (field.emphasize) {
                    MaterialTheme.typography.titleMedium
                } else {
                    MaterialTheme.typography.bodyLarge
                },
                fontWeight = if (field.emphasize) FontWeight.SemiBold else FontWeight.Normal,
                softWrap = true
            )
        }
    }
}

@Composable
private fun EmptyResultCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        )
    ) {
        Text(
            text = "No recognized data is available.",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun RawJsonCard(rawJson: String, compactLayout: Boolean) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column {
            TextButton(
                onClick = { expanded = !expanded },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (expanded) "Hide raw JSON" else "Show raw JSON",
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Icon(
                    imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            AnimatedVisibility(expanded) {
                SelectionContainer {
                    Text(
                        text = rawJson,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = if (compactLayout) 12.dp else 16.dp,
                                end = if (compactLayout) 12.dp else 16.dp,
                                bottom = if (compactLayout) 12.dp else 16.dp
                            ),
                        style = MaterialTheme.typography.bodySmall,
                        fontFamily = FontFamily.Monospace,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        softWrap = true,
                        overflow = TextOverflow.Clip
                    )
                }
            }
        }
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.SemiBold
    )
}
