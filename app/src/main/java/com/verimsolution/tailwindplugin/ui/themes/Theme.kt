package com.verimsolution.tailwindplugin.ui.themes

import androidx.compose.runtime.Composable
import com.verimsolution.tailwind.TailwindTheme
import com.verimsolution.tailwind.lightColorScheme

@Composable
fun TailwindPluginTheme(content: @Composable () -> Unit) {

    val lightColorScheme = lightColorScheme()

    TailwindTheme(
        colorScheme = lightColorScheme,
        typography = typography,
        content = content
    )
}