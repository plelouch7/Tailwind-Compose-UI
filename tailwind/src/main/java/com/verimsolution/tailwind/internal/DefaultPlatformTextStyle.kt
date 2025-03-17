package com.verimsolution.tailwind.internal

import androidx.compose.ui.text.PlatformTextStyle


private const val DefaultIncludeFontPadding = false

@Suppress("DEPRECATION")
private val DefaultPlatformTextStyle = PlatformTextStyle()

internal fun defaultPlatformTextStyle(): PlatformTextStyle? = DefaultPlatformTextStyle
