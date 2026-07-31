package io.github.wifi_password_manager.domain.model

import android.os.Build
import kotlinx.serialization.Serializable

@Serializable
data class DeviceInfo(
    val brand: String = Build.BRAND ?: "Unknown",
    val model: String = Build.MODEL ?: "Unknown",
    val manufacturer: String = Build.MANUFACTURER ?: "Unknown",
    val osVersion: String = "${Build.VERSION.RELEASE} (API ${Build.VERSION.SDK_INT})",
)
