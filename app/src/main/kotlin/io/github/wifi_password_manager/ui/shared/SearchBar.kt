package io.github.wifi_password_manager.ui.shared

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TooltipAnchorPosition
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewWrapper
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import io.github.wifi_password_manager.R
import io.github.wifi_password_manager.ui.icons.ClearAll
import io.github.wifi_password_manager.ui.theme.ThemeWrapper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    showingSearch: Boolean,
    searchText: String,
    onSearchTextChanged: (String) -> Unit,
    onBack: () -> Unit,
    placeholder: String,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(showingSearch) {
        if (showingSearch) {
            focusRequester.requestFocus()
            keyboardController?.show()
        } else {
            keyboardController?.hide()
        }
    }

    Surface(modifier = modifier, color = MaterialTheme.colorScheme.secondaryContainer) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .height(TopAppBarDefaults.TopAppBarExpandedHeight)
                .focusRequester(focusRequester),
            value = searchText,
            onValueChange = onSearchTextChanged,
            singleLine = true,
            placeholder = { Text(text = placeholder) },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search, keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(onSearch = { keyboardController?.hide() }),
            leadingIcon = { BackButton(onClick = onBack) },
            trailingIcon = {
                Row {
                    TooltipIconButton(
                        onClick = { onSearchTextChanged("") },
                        imageVector = ClearAll,
                        tooltip = stringResource(R.string.clear),
                        positioning = TooltipAnchorPosition.Below,
                    )
                }
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
            ),
        )
    }
}

@PreviewLightDark
@Composable
@PreviewWrapper(ThemeWrapper::class)
private fun SearchBarPreview() {
    SearchBar(
        showingSearch = true,
        searchText = "",
        onSearchTextChanged = {},
        onBack = {},
        placeholder = LoremIpsum(2).values.joinToString(" "),
    )
}

@PreviewLightDark
@Composable
@PreviewWrapper(ThemeWrapper::class)
private fun SearchBarWithTextPreview() {
    SearchBar(
        showingSearch = true,
        searchText = LoremIpsum(7).values.joinToString(" "),
        onSearchTextChanged = {},
        onBack = {},
        placeholder = LoremIpsum(2).values.joinToString(" "),
    )
}
