package com.verimsolution.tailwindplugin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.verimsolution.tailwind_prod.BottomNavigationVariant
import com.verimsolution.tailwind_prod.BottomSheetProperties
import com.verimsolution.tailwind_prod.BottomSheetState
import com.verimsolution.tailwind_prod.BottomSheetVariant
import com.verimsolution.tailwind_prod.ButtonVariant
import com.verimsolution.tailwind_prod.DefaultDragHandle
import com.verimsolution.tailwind_prod.LocalContentColor
import com.verimsolution.tailwind_prod.ModalPlacement
import com.verimsolution.tailwind_prod.ModalType
import com.verimsolution.tailwind_prod.ModalVariant
import com.verimsolution.tailwind_prod.NavigationItem
import com.verimsolution.tailwind_prod.TailwindBottomNavigationBar
import com.verimsolution.tailwind_prod.TailwindBottomNavigationBarFlexible
import com.verimsolution.tailwind_prod.TailwindBottomSheetModal
import com.verimsolution.tailwind_prod.TailwindButton
import com.verimsolution.tailwind_prod.TailwindDefaults
import com.verimsolution.tailwind_prod.TailwindIcon
import com.verimsolution.tailwind_prod.TailwindModal
import com.verimsolution.tailwind_prod.TailwindText
import com.verimsolution.tailwindplugin.ui.themes.TailwindPluginTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TailwindPluginTheme {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            DarkBottomSheetModalExample()
                            BottomSheetModalExample()
                        }
                    }
                    BottomNavigationExample()
                }
            }
        }
    }
}

@Composable
fun BottomNavigationExample() {
    var selectedIndex by remember { mutableIntStateOf(0) }

    TailwindBottomNavigationBar(
        items = listOf(
            NavigationItem(
                label = "Accueil",
                icon = {
                    TailwindIcon(
                        painter = painterResource(R.drawable.ic_home),
                        contentDescription = "Accueil",
                        modifier = Modifier.size(24.dp)
                    )
                },
                onClick = { /* Action pour Accueil */ },
                iconTextSpacing = 5.dp
            ),
            NavigationItem(
                label = null, // Uniquement icône
                icon = {
                    TailwindIcon(
                        painter = painterResource(R.drawable.ic_profile),
                        contentDescription = "Profil",
                        modifier = Modifier.size(24.dp)
                    )
                },
                onClick = { /* Action pour Profil */ }
            ),
            NavigationItem(
                label = "Params", // Uniquement texte
                icon = null,
                onClick = { /* Action pour Paramètres */ }
            )
        ),
        selectedIndex = selectedIndex,
        onItemSelected = { index -> selectedIndex = index },
        variant = BottomNavigationVariant.Default,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun BottomSheetModalExample() {
    var showSheet by remember { mutableStateOf(false) }

    TailwindButton(
        text = "Ouvrir Bottom Sheet",
        onClick = { showSheet = true }
    )

    if (showSheet) {
        TailwindBottomSheetModal(
            onDismissRequest = { showSheet = false },
            initialState = BottomSheetState.Expanded,
            variant = BottomSheetVariant.Default,
            properties = BottomSheetProperties(shouldDismissOnBackPress = true)
        ) {
            TailwindText(
                text = "Contenu du Bottom Sheet",
                style = TextStyle(fontSize = 16.sp),
                modifier = Modifier.padding(bottom = 16.dp)
            )
            TailwindButton(
                text = "Fermer",
                onClick = { showSheet = false },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
fun DarkBottomSheetModalExample() {
    var showSheet by remember { mutableStateOf(false) }

    TailwindButton(
        text = "Ouvrir Dark Bottom Sheet",
        onClick = { showSheet = true }
    )

    if (showSheet) {
        TailwindBottomSheetModal(
            onDismissRequest = { showSheet = false },
            initialState = BottomSheetState.PartiallyExpanded,
            skipPartiallyExpanded = false,
            variant = BottomSheetVariant.Dark,
            dragHandle = { DefaultDragHandle(BottomSheetVariant.Dark) }
        ) {
            TailwindText(
                text = "Contenu sombre",
                style = TextStyle(fontSize = 16.sp, color = Color.White),
                modifier = Modifier.padding(bottom = 16.dp)
            )
            TailwindButton(
                text = "Action",
                onClick = { showSheet = false },
                variant = ButtonVariant.Light,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}