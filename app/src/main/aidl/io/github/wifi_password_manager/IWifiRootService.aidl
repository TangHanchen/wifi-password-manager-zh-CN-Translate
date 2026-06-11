package io.github.wifi_password_manager;

import io.github.wifi_password_manager.ipc.WifiNetworkParcel;
import io.github.wifi_password_manager.ipc.WifiInfoParcel;

interface IWifiRootService {
    List<WifiNetworkParcel> getPrivilegedConfiguredNetworks();

    boolean addOrUpdateNetworkPrivileged(in WifiNetworkParcel config);

    boolean removeNetwork(int netId);

    WifiInfoParcel getConnectionInfo();

    void persistEphemeralNetworks();
}

