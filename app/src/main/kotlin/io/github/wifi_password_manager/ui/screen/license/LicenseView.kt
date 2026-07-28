package io.github.wifi_password_manager.ui.screen.license

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewWrapper
import com.mikepenz.aboutlibraries.ui.compose.LibraryDefaults
import com.mikepenz.aboutlibraries.ui.compose.android.produceLibraries
import com.mikepenz.aboutlibraries.ui.compose.m3.LibrariesContainer
import com.mikepenz.aboutlibraries.ui.compose.m3.libraryColors
import io.github.wifi_password_manager.R
import io.github.wifi_password_manager.ui.shared.BackButton
import io.github.wifi_password_manager.ui.theme.ThemeWrapper

@Composable
fun LicenseView() {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = { BackButton() },
                title = { Text(text = stringResource(R.string.license_title)) },
            )
        },
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
    ) { innerPadding ->
        val libraries by produceLibraries(R.raw.aboutlibraries)
        LibrariesContainer(
            libraries = libraries,
            contentPadding = innerPadding,
            colors = LibraryDefaults.libraryColors(libraryBackgroundColor = MaterialTheme.colorScheme.surfaceContainer),
            licenseDialogConfirmText = stringResource(R.string.ok),
        )
    }
}

@PreviewLightDark
@Composable
@PreviewWrapper(ThemeWrapper::class)
private fun LicenseViewPreview() {
    LicenseView()
}
