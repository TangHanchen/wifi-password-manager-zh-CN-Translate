package io.github.wifi_password_manager.ui.screen.setting.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.retain.retain
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewWrapper
import androidx.compose.ui.unit.dp
import io.github.wifi_password_manager.R
import io.github.wifi_password_manager.domain.model.Settings
import io.github.wifi_password_manager.ui.UiConfig
import io.github.wifi_password_manager.ui.theme.SurfaceWrapper

@Composable
fun LanguageItem(language: Settings.Language, onLanguageChange: (Settings.Language) -> Unit) {
    var showBottomSheet by retain { mutableStateOf(false) }

    ListItem(
        onClick = { showBottomSheet = true },
        supportingContent = { Text(text = stringResource(R.string.language_description)) },
        trailingContent = { Text(text = language.displayName) },
        shapes = UiConfig.listItemShapes(),
    ) {
        Text(text = stringResource(R.string.language_title))
    }

    if (showBottomSheet) {
        LanguageSelectionSheet(
            onDismiss = { showBottomSheet = false },
            language = language,
            onLanguageChange = onLanguageChange,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LanguageSelectionSheet(
    onDismiss: () -> Unit,
    language: Settings.Language,
    onLanguageChange: (Settings.Language) -> Unit,
) {
    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(modifier = Modifier.padding(horizontal = 12.dp)) {
            Settings.Language.entries.sortedBy { it.code }.forEach {
                ListItem(
                    onClick = { onLanguageChange(it) },
                    selected = it == language,
                    leadingContent = { RadioButton(selected = it == language, onClick = null) },
                    colors = ListItemDefaults.colors(containerColor = BottomSheetDefaults.ContainerColor),
                ) {
                    Text(text = it.displayName)
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
@PreviewWrapper(SurfaceWrapper::class)
private fun LanguageItemPreview() {
    LanguageItem(language = Settings.Language.ENGLISH, onLanguageChange = {})
}

@PreviewLightDark
@Composable
@PreviewWrapper(SurfaceWrapper::class)
private fun LanguageSelectionSheetPreview() {
    LanguageSelectionSheet(
        onDismiss = {},
        language = Settings.Language.entries.random(),
        onLanguageChange = {},
    )
}
