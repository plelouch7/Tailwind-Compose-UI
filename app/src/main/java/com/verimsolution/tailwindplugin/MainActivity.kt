package com.verimsolution.tailwindplugin

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.verimsolution.tailwind.Surface
import com.verimsolution.tailwind.TailwindAccordion
import com.verimsolution.tailwind.TailwindAccordionItem
import com.verimsolution.tailwind.TailwindText
import com.verimsolution.tailwind.TailwindTheme
import com.verimsolution.tailwindplugin.ui.themes.TailwindPluginTheme

class MainActivity : AppCompatActivity() {
//    val accordionItems = listOf(
//        TailwindAccordionItem(
//            title = "What is Flowbite?",
//            initiallyExpanded = true,
//            content = {
//                TailwindText("Flowbite is an open-source library of interactive components built on top of Tailwind CSS.")
//                TailwindText("It includes buttons, dropdowns, modals, and more.")
//            }
//        ),
//        TailwindAccordionItem(
//            title = "What is Flowbite?",
//            content = {
//                TailwindText("Flowbite is an open-source library of interactive components built on top of Tailwind CSS.")
//                TailwindText("It includes buttons, dropdowns, modals, and more.")
//            }
//        ),
//        TailwindAccordionItem(
//            title = "Is there a Figma file available?",
//            content = {
//                TailwindText("Flowbite is conceptualized and designed using Figma.")
//                TailwindText("Check out the Figma design system for more details.")
//            }
//        ),
////        AccordionItem(
////            title = "Differences between Flowbite and Tailwind UI?",
////            content = {
////                TailwindText("Flowbite is open source under MIT, while Tailwind UI is a paid product.")
////                TailwindText("Both can be used together for the best of both worlds.")
////            }
////        )
//    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
//            TailwindPluginTheme {
//
////                Surface {
                    TailwindText("Accordion")
////                }
//            }
//            Text("Hello World")
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Prev() {

//    val accordionItems = listOf(
//        TailwindAccordionItem(
//            title = "What is Flowbite?",
//            initiallyExpanded = true,
//            content = {
//                TailwindText("Flowbite is an open-source library of interactive components built on top of Tailwind CSS.")
//                TailwindText("It includes buttons, dropdowns, modals, and more.")
//            }
//        ),
//        TailwindAccordionItem(
//            title = "Is there a Figma file available?",
//            initiallyExpanded = false,
//            content = {
//                TailwindText("Flowbite is conceptualized and designed using Figma.")
//                TailwindText("Check out the Figma design system for more details.")
//            }
//        ),
//        TailwindAccordionItem(
//            title = "Differences between Flowbite and Tailwind UI?",
//            content = {
//                TailwindText("Flowbite is open source under MIT, while Tailwind UI is a paid product.")
//                TailwindText("Both can be used together for the best of both worlds.")
//            }
//        )
//    )

    TailwindPluginTheme {

//        TailwindAccordion(
//            items = accordionItems,
//            allowMultiple = true,
//            headerContentColor = TailwindTheme.colorScheme.onBackground,
//            headerBackgroundColor = TailwindTheme.colorScheme.background,
//            contentContentColor = TailwindTheme.colorScheme.onBackground,
//            contentBackgroundColor = TailwindTheme.colorScheme.background
//        )

    }
}