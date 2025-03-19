package com.verimsolution.tailwind_prod.internal

import androidx.compose.ui.text.PlatformTextStyle


private const val DefaultIncludeFontPadding = false

@Suppress("DEPRECATION")
private val DefaultPlatformTextStyle = PlatformTextStyle()

internal fun defaultPlatformTextStyle(): PlatformTextStyle? = DefaultPlatformTextStyle
