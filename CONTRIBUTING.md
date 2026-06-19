# Contributing to WiFi Password Manager

Thank you for your interest in contributing.

These guidelines are meant to help you get started smoothly, not rules. Use your best judgment and feel free to suggest improvements.

## Getting Started

### Requirements

- [Android Studio](https://developer.android.com/studio) (latest stable version is recommended)
- JDK 17 or higher (configured in Android Studio)
- Git

### Testing With Root access

If you don’t have a rooted device, you can:

1. Create an Android emulator using Android Studio
2. Root it using [rootAVD](https://gitlab.com/newbit/rootAVD)

## Android Wifi Internals (Optional Reference)

You only need this section if you are working on core Wifi logic or compatibility issues.

Android’s Wi-Fi implementation changes across API levels. The following references may be useful:

| Android Version | IWifiManager | WifiConfiguration | WifiShellCommand |
| :--- | :--- | :--- | :--- |
| **API 30 (Android 11)** | [IWifiManager.aidl](https://android.googlesource.com/platform/frameworks/base/+/refs/heads/android11-release/wifi/java/android/net/wifi/IWifiManager.aidl) | [WifiConfiguration.java](https://android.googlesource.com/platform/frameworks/base/+/refs/heads/android11-release/wifi/java/android/net/wifi/WifiConfiguration.java) | [WifiShellCommand.java](https://android.googlesource.com/platform/frameworks/opt/net/wifi/+/refs/heads/android11-release/service/java/com/android/server/wifi/WifiShellCommand.java) |
| **API 31 (Android 12)** | [IWifiManager.aidl](https://android.googlesource.com/platform/packages/modules/Wifi/+/refs/heads/android12-release/framework/java/android/net/wifi/IWifiManager.aidl) | [WifiConfiguration.java](https://android.googlesource.com/platform/packages/modules/Wifi/+/refs/heads/android12-release/framework/java/android/net/wifi/WifiConfiguration.java) | [WifiShellCommand.java](https://android.googlesource.com/platform/packages/modules/Wifi/+/refs/heads/android12-release/service/java/com/android/server/wifi/WifiShellCommand.java) |
| **API 32 (Android 12L)** | [IWifiManager.aidl](https://android.googlesource.com/platform/packages/modules/Wifi/+/refs/heads/android12L-release/framework/java/android/net/wifi/IWifiManager.aidl) | [WifiConfiguration.java](https://android.googlesource.com/platform/packages/modules/Wifi/+/refs/heads/android12L-release/framework/java/android/net/wifi/WifiConfiguration.java) | [WifiShellCommand.java](https://android.googlesource.com/platform/packages/modules/Wifi/+/refs/heads/android12L-release/service/java/com/android/server/wifi/WifiShellCommand.java) |
| **API 33 (Android 13)** | [IWifiManager.aidl](https://android.googlesource.com/platform/packages/modules/Wifi/+/refs/heads/android13-release/framework/java/android/net/wifi/IWifiManager.aidl) | [WifiConfiguration.java](https://android.googlesource.com/platform/packages/modules/Wifi/+/refs/heads/android13-release/framework/java/android/net/wifi/WifiConfiguration.java) | [WifiShellCommand.java](https://android.googlesource.com/platform/packages/modules/Wifi/+/refs/heads/android13-release/service/java/com/android/server/wifi/WifiShellCommand.java) |
| **API 34 (Android 14)** | [IWifiManager.aidl](https://android.googlesource.com/platform/packages/modules/Wifi/+/refs/heads/android14-release/framework/java/android/net/wifi/IWifiManager.aidl) | [WifiConfiguration.java](https://android.googlesource.com/platform/packages/modules/Wifi/+/refs/heads/android14-release/framework/java/android/net/wifi/WifiConfiguration.java) | [WifiShellCommand.java](https://android.googlesource.com/platform/packages/modules/Wifi/+/refs/heads/android14-release/service/java/com/android/server/wifi/WifiShellCommand.java) |
| **API 35 (Android 15)** | [IWifiManager.aidl](https://android.googlesource.com/platform/packages/modules/Wifi/+/refs/heads/android15-release/framework/java/android/net/wifi/IWifiManager.aidl) | [WifiConfiguration.java](https://android.googlesource.com/platform/packages/modules/Wifi/+/refs/heads/android15-release/framework/java/android/net/wifi/WifiConfiguration.java) | [WifiShellCommand.java](https://android.googlesource.com/platform/packages/modules/Wifi/+/refs/heads/android15-release/service/java/com/android/server/wifi/WifiShellCommand.java) |
| **API 36 (Android 16)** | [IWifiManager.aidl](https://android.googlesource.com/platform/packages/modules/Wifi/+/refs/heads/android16-release/framework/java/android/net/wifi/IWifiManager.aidl) | [WifiConfiguration.java](https://android.googlesource.com/platform/packages/modules/Wifi/+/refs/heads/android16-release/framework/java/android/net/wifi/WifiConfiguration.java) | [WifiShellCommand.java](https://android.googlesource.com/platform/packages/modules/Wifi/+/refs/heads/android16-release/service/java/com/android/server/wifi/WifiShellCommand.java) |
| **API 37 (Android 17)** | [IWifiManager.aidl](https://android.googlesource.com/platform/packages/modules/Wifi/+/refs/heads/android17-release/framework/java/android/net/wifi/IWifiManager.aidl) | [WifiConfiguration.java](https://android.googlesource.com/platform/packages/modules/Wifi/+/refs/heads/android17-release/framework/java/android/net/wifi/WifiConfiguration.java) | [WifiShellCommand.java](https://android.googlesource.com/platform/packages/modules/Wifi/+/refs/heads/android17-release/service/java/com/android/server/wifi/WifiShellCommand.java) |

What these are:

- **IWifiManager**: Interface used to talk to the system Wifi service.
- **WifiConfiguration**: Data class for Wifi network configuration (SSID, security, credentials).
- **WifiShellCommand**: Shell interface for Wifi debugging.

You can safely skip this section if you are working on UI or non-Wifi logic

## How to Contribute

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/feature-name`).
3. Make your changes.
4. Commit your changes.
5. Push your branch (`git push origin feature/feature-name`).
6. Open a Pull Request.

## Reporting Bugs or Requesting Features

If you find a bug or have a feature request, please open an issue on the [GitHub Issues](https://github.com/Khh-vu/wifi-password-manager/issues) page.

When reporting a bug, please include:

- Device or emulator information (Model, Android version).
- Steps to reproduce.
- Expected vs actual behavior.
- Logs or screenshots, if available.

---

Thank you for contributing. Every improvement matters.
