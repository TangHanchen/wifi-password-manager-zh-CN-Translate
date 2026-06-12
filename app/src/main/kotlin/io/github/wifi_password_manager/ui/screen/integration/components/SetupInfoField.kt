package io.github.wifi_password_manager.ui.screen.integration.components

import android.content.ClipData
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipAnchorPosition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboard
import androidx.compose.ui.platform.toClipEntry
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import io.github.wifi_password_manager.R
import io.github.wifi_password_manager.ui.shared.TooltipIconButton
import io.github.wifi_password_manager.ui.theme.WiFiPasswordManagerTheme
import kotlinx.coroutines.launch

@Composable
fun SetupInfoField(
    modifier: Modifier = Modifier,
    label: String,
    content: String,
    hint: String? = null,
) {
    val clipboard = LocalClipboard.current
    val scope = rememberCoroutineScope()

    OutlinedTextField(
        value = content,
        onValueChange = {},
        singleLine = true,
        modifier = modifier.fillMaxWidth(),
        readOnly = true,
        label = { Text(text = label) },
        trailingIcon = {
            TooltipIconButton(
                onClick = {
                    scope.launch {
                        clipboard.setClipEntry(ClipData.newPlainText(label, content).toClipEntry())
                    }
                },
                painter = painterResource(R.drawable.ic_content_copy),
                tooltip = stringResource(R.string.copy_action),
                positioning = TooltipAnchorPosition.Above,
            )
        },
        supportingText = hint.takeUnless { it.isNullOrBlank() }
            ?.let { @Composable { Text(text = it) } },
    )
}

@PreviewLightDark
@Composable
private fun SetupInfoFieldPreview() {
    WiFiPasswordManagerTheme { Surface { SetupInfoField(label = "Label", content = "Content") } }
}
