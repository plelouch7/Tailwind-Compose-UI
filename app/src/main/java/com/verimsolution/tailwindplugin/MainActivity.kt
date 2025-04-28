package com.verimsolution.tailwindplugin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.verimsolution.tailwind_prod.TailwindScaffold
import com.verimsolution.tailwind_prod.TailwindText
import com.verimsolution.tailwind_prod.TailwindTopAppBar
import com.verimsolution.tailwind_prod.pinnedScrollBehavior
import com.verimsolution.tailwindplugin.ui.themes.TailwindPluginTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TailwindPluginTheme {
                TailwindScaffoldExample()
            }
        }
    }
}

@Composable
fun TailwindScaffoldExample() {
    TailwindScaffold(
        topBar = {
            TailwindTopBar(
                title = "Mon Application",
                backgroundColor = Color(0xFF3B82F6) // bg-blue-500
            )
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
            }
        }
    )
}

/**
 * Exemple de TopBar simple.
 */
@Composable
fun TailwindTopBar(
    title: String,
    backgroundColor: Color = Color(0xFFFFFFFF),
    contentColor: Color = Color(0xFF1F2937)
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        TailwindTopAppBar(
            title = {
                TailwindText(
                    text = title,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start
                    )
                )
            },
            navigationIcon = {
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    BasicText(text = "←", style = TextStyle(fontSize = 24.sp))
                }
            },
            actions = { },
            scrollBehavior = pinnedScrollBehavior()
        )
    }
}


// Exemple d'utilisation
@Composable
fun SimpleTailwindTopAppBar(
    titleText: String,
    onNavigationClick: () -> Unit,
    actionContent: @Composable RowScope.() -> Unit
) {

}