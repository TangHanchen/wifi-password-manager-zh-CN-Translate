package io.github.wifi_password_manager.ui.shared

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.retain.retain
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewWrapper
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import io.github.wifi_password_manager.ui.icons.KeyboardArrowDown
import io.github.wifi_password_manager.ui.theme.ThemeWrapper

@Composable
fun ExpansionItem(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    itemsContent: @Composable ColumnScope.() -> Unit
) {
    var isExpanded by retain { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f,
        animationSpec = tween(durationMillis = 300),
    )

    ListItem(
        modifier = modifier, onClick = { isExpanded = !isExpanded },
        trailingContent = {
            Icon(
                imageVector = KeyboardArrowDown,
                contentDescription = "Dropdown Arrow",
                modifier = Modifier.graphicsLayer { rotationZ = rotationAngle },
            )
        },
        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
        verticalAlignment = Alignment.CenterVertically,
        content = content,
    )

    AnimatedVisibility(
        visible = isExpanded,
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically(),
    ) {
        Column {
            HorizontalDivider()
            itemsContent()
        }
    }
}

@PreviewLightDark
@Composable
@PreviewWrapper(ThemeWrapper::class)
private fun ExpansionItemPreview() {
    val text = LoremIpsum(2).values.joinToString(" ")
    ExpansionItem(content = { ListItem { Text(text = text) } }) {
        ListItem { Text(text = text) }
        ListItem { Text(text = text) }
    }
}
