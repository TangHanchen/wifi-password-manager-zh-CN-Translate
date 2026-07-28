package io.github.wifi_password_manager.ui.shared

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TooltipAnchorPosition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import io.github.wifi_password_manager.R
import io.github.wifi_password_manager.navigation.LocalNavBackStack
import io.github.wifi_password_manager.ui.icons.ArrowBack
import io.github.wifi_password_manager.ui.theme.WiFiPasswordManagerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackButton(modifier: Modifier = Modifier, onClick: (() -> Unit)? = null) {
    val navBackStack = LocalNavBackStack.current

    TooltipIconButton(
        modifier = modifier,
        onClick = { if (onClick != null) onClick() else navBackStack.removeLastOrNull() },
        imageVector = ArrowBack,
        tooltip = stringResource(R.string.back),
        positioning = TooltipAnchorPosition.Below,
    )
}

@PreviewLightDark
@Composable
private fun BackButtonPreview() {
    WiFiPasswordManagerTheme {
        BackButton()
    }
}