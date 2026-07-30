package io.github.wifi_password_manager.ui.screen.setting.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewWrapper
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import io.github.wifi_password_manager.ui.icons.Info
import io.github.wifi_password_manager.ui.theme.ScaffoldWrapper

@Composable
fun SettingSection(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    title: String,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(modifier = modifier) {
        CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.primary) {
            Row(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Icon(
                    imageVector = imageVector,
                    contentDescription = title,
                    modifier = Modifier.size(20.dp),
                )
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }

        ElevatedCard { content() }
    }
}

@PreviewLightDark
@Composable
@PreviewWrapper(ScaffoldWrapper::class)
private fun SettingBoxPreview() {
    SettingSection(imageVector = Info, title = LoremIpsum(2).values.joinToString(" ")) {
        ListItem(supportingContent = { Text(text = LoremIpsum(10).values.joinToString(" ")) }) {
            Text(text = LoremIpsum(5).values.joinToString(" "))
        }
    }
}
