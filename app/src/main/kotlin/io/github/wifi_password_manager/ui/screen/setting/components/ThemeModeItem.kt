package io.github.wifi_password_manager.ui.screen.setting.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
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
import androidx.compose.ui.unit.dp
import io.github.wifi_password_manager.R
import io.github.wifi_password_manager.domain.model.Settings
import io.github.wifi_password_manager.ui.UiConfig
import io.github.wifi_password_manager.ui.theme.WiFiPasswordManagerTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ThemeModeItem(themeMode: Settings.ThemeMode, onThemeModeChange: (Settings.ThemeMode) -> Unit) {
    var showBottomSheet by retain { mutableStateOf(false) }

    ListItem(
        onClick = { showBottomSheet = true },
        content = { Text(text = stringResource(R.string.app_theme_title)) },
        supportingContent = { Text(text = stringResource(R.string.app_theme_description)) },
        trailingContent = { Text(text = stringResource(themeMode.resId)) },
        shapes = UiConfig.listItemShapes(),
    )

    if (showBottomSheet) {
        ThemeModeSelectionSheet(
            onDismiss = { showBottomSheet = false },
            themeMode = themeMode,
            onThemeModeChange = onThemeModeChange,
        )
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun ThemeModeSelectionSheet(
    onDismiss: () -> Unit,
    themeMode: Settings.ThemeMode,
    onThemeModeChange: (Settings.ThemeMode) -> Unit,
) {
    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(modifier = Modifier.padding(horizontal = 12.dp)) {
            Settings.ThemeMode.entries.forEach {
                ListItem(
                    onClick = { onThemeModeChange(it) },
                    selected = it == themeMode,
                    leadingContent = { RadioButton(selected = it == themeMode, onClick = null) },
                    content = { Text(text = stringResource(it.resId)) },
                    colors = ListItemDefaults.colors(containerColor = BottomSheetDefaults.ContainerColor),
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun ThemeModeItemPreview() {
    WiFiPasswordManagerTheme {
        ThemeModeItem(themeMode = Settings.ThemeMode.SYSTEM, onThemeModeChange = {})
    }
}

@PreviewLightDark
@Composable
private fun ThemeModeSelectionSheetPreview() {
    WiFiPasswordManagerTheme {
        ThemeModeSelectionSheet(
            onDismiss = {},
            themeMode = Settings.ThemeMode.entries.random(),
            onThemeModeChange = {},
        )
    }
}
