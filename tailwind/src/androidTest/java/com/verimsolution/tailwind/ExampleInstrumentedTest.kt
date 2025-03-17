package com.verimsolution.tailwind

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.verimsolution.tailwind.test", appContext.packageName)
    }

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun accordionItem_togglesContentVisibility() {
        val itemTitle = "Test Accordion Item"
        val itemContent = "This is the content"

        // Configurer le contenu Compose pour le test
        composeTestRule.setContent {
            TailwindAccordion(
                items = listOf(
                    TailwindAccordionItem(
                        title = itemTitle,
                        initiallyExpanded = true,
                        content = {
                            TailwindText(itemContent)
                        }
                    )
                )
            )
        }

        // Vérifier que le titre est affiché
        composeTestRule.onNodeWithText(itemTitle).assertIsDisplayed()
        // Vérifier que le contenu est affiché (initialement l'item est déployé)
        composeTestRule.onNodeWithText(itemContent).assertIsDisplayed()

        // Simuler un clic sur le titre pour replier l'item
        composeTestRule.onNodeWithText(itemTitle).performClick()
        // Attendre la fin des animations (si nécessaire)
        composeTestRule.waitForIdle()

        // Vérifier que le contenu n'est plus visible
        composeTestRule.onNodeWithText(itemContent).assertDoesNotExist()
    }
}