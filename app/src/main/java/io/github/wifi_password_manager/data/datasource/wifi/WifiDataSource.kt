package io.github.wifi_password_manager.data.datasource.wifi

import android.net.wifi.WifiConfiguration

interface WifiDataSource {
    suspend fun getPrivilegedConfiguredNetworks(): List<WifiConfiguration>

    suspend fun addOrUpdateNetworkPrivileged(config: WifiConfiguration): Boolean

    suspend fun removeNetwork(netId: Int): Boolean

    suspend fun persistEphemeralNetworks()
}
