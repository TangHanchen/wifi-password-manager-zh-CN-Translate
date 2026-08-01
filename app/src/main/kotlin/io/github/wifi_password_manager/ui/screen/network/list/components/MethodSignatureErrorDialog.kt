package io.github.wifi_password_manager.ui.screen.network.list.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewWrapper
import io.github.wifi_password_manager.R
import io.github.wifi_password_manager.ui.theme.SurfaceWrapper

@Composable
fun MethodSignatureErrorDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onOpenMethodInspector: () -> Unit,
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismiss,
        title = { Text(text = stringResource(R.string.method_inspector_error_title)) },
        text = { Text(text = stringResource(R.string.method_inspector_error_message)) },
        confirmButton = {
            TextButton(
                onClick = {
                    onDismiss()
                    onOpenMethodInspector()
                },
                shapes = ButtonDefaults.shapes(),
            ) {
                Text(text = stringResource(R.string.open_method_inspector))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss, shapes = ButtonDefaults.shapes()) {
                Text(text = stringResource(R.string.cancel))
            }
        },
    )
}

@PreviewLightDark
@Composable
@PreviewWrapper(SurfaceWrapper::class)
private fun MethodSignatureErrorDialogPreview() {
    MethodSignatureErrorDialog(onDismiss = {}, onOpenMethodInspector = {})
}
