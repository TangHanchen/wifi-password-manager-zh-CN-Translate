package io.github.wifi_password_manager.ui.screen.integration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipAnchorPosition
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import io.github.wifi_password_manager.BuildConfig
import io.github.wifi_password_manager.R
import io.github.wifi_password_manager.navigation.LocalNavBackStack
import io.github.wifi_password_manager.receivers.ExportWifiReceiver
import io.github.wifi_password_manager.ui.screen.integration.components.SetupInfoField
import io.github.wifi_password_manager.ui.shared.TooltipIconButton
import io.github.wifi_password_manager.ui.theme.WiFiPasswordManagerTheme
import io.github.wifi_password_manager.utils.plus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExportWifiSetupView() {
    val navBackStack = LocalNavBackStack.current

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    TooltipIconButton(
                        onClick = { navBackStack.removeLastOrNull() },
                        painter = painterResource(R.drawable.ic_arrow_back),
                        tooltip = stringResource(R.string.back),
                        positioning = TooltipAnchorPosition.Below,
                    )
                },
                title = { Text(text = stringResource(R.string.export_wifi_setup_title)) },
            )
        },
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding + PaddingValues(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            // Action
            item {
                SetupInfoField(label = "Action", content = ExportWifiReceiver.ACTION_EXPORT_WIFI)
            }

            // Extras
            item {
                SetupInfoField(
                    label = "Extra",
                    content = ExportWifiReceiver.EXTRA_FORMAT,
                    hint = stringResource(R.string.export_wifi_extra_format_hint),
                )
            }
            item {
                SetupInfoField(
                    label = "Extra",
                    content = ExportWifiReceiver.EXTRA_PASSWORD,
                    hint = stringResource(R.string.export_wifi_extra_password_hint),
                )
            }
            item {
                SetupInfoField(
                    label = "Extra",
                    content = ExportWifiReceiver.EXTRA_FILE_NAME,
                    hint = stringResource(R.string.export_wifi_extra_filename_hint),
                )
            }

            // Package
            item { SetupInfoField(label = "Package", content = BuildConfig.APPLICATION_ID) }

            // Class
            item { SetupInfoField(label = "Class", content = ExportWifiReceiver::class.java.name) }
        }
    }
}

@PreviewLightDark
@Composable
private fun ExportWifiSetupViewPreview() {
    WiFiPasswordManagerTheme { ExportWifiSetupView() }
}
