<p align="center">
  <picture>
    <source
      width="350px"
      media="(prefers-color-scheme: dark)"
      srcset="Logo/Extenre_Green.svg">
    <img src="Logo/Extenre_Green.svg" alt="ExtenRe Logo" width="350px">
  </picture>
</p>
<br>

# 💻 ExtenRe CLI

![GitHub Workflow Status (with event)](https://img.shields.io/github/actions/workflow/status/LuisCupul04/extenre-cli/release.yml)
![GPLv3 License](https://img.shields.io/badge/License-GPL%20v3-yellow.svg)

Command-line application to patch Android apps using the ExtenRe ecosystem.

## ❓ About

ExtenRe CLI is a command-line tool that uses [ExtenRe Patcher](https://github.com/LuisCupul04/extenre-patcher) to modify (patch) Android applications. It is designed to work with the ExtenRe patch format (`.exre` files).

## 💪 Features

- 💉 **Patch apps**: Apply patches from `.exre` bundles to any Android APK or app bundle.
- 📦 **Bundle support**: Automatically merges Android App Bundles (`.aab`) into APKs.
- 🔑 **Signing**: Sign the patched APK with your own keystore (or auto‑generate one).
- 📱 **Install & uninstall**: Install the patched APK via ADB (including root mounting).
- 📋 **List patches**: Inspect available patches, their options, and compatibility.
- ⚙️ **Options management**: Generate and manage patch options JSON files.

## 🔽 Download

You can download the latest version of ExtenRe CLI from the [Releases page](https://github.com/LuisCupul04/extenre-cli/releases/latest).

## 🚀 Usage

Basic usage example:

```bash
java -jar extenre-cli-1.0.0-all.jar patch \
  -p patches.exre \
  -i input.apk \
  -o patched.apk \
  --keystore my.keystore