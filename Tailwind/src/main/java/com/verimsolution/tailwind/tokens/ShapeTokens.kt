package com.verimsolution.tailwind.tokens

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

internal object ShapeTokens {
    // Correspond à "rounded-3xl" par exemple
    val CornerExtraLarge = RoundedCornerShape(28.0.dp)
    val CornerExtraLargeTop = RoundedCornerShape(
        topStart = 28.0.dp,
        topEnd = 28.0.dp,
        bottomEnd = 0.0.dp,
        bottomStart = 0.0.dp
    )

    // Correspond à "rounded-sm"
    val CornerExtraSmall = RoundedCornerShape(4.0.dp)
    val CornerExtraSmallTop = RoundedCornerShape(
        topStart = 4.0.dp,
        topEnd = 4.0.dp,
        bottomEnd = 0.0.dp,
        bottomStart = 0.0.dp
    )

    // Utilisé pour des formes circulaires
    val CornerFull = CircleShape

    // Correspond à "rounded-xl" ou "rounded-2xl"
    val CornerLarge = RoundedCornerShape(16.0.dp)
    val CornerLargeEnd = RoundedCornerShape(
        topStart = 0.0.dp,
        topEnd = 16.0.dp,
        bottomEnd = 16.0.dp,
        bottomStart = 0.0.dp
    )
    val CornerLargeTop = RoundedCornerShape(
        topStart = 16.0.dp,
        topEnd = 16.0.dp,
        bottomEnd = 0.0.dp,
        bottomStart = 0.0.dp
    )

    // Correspond à "rounded-lg" ou "rounded-md"
    val CornerMedium = RoundedCornerShape(12.0.dp)

    // Pour aucune bordure arrondie
    val CornerNone = RectangleShape

    // Correspond à "rounded" ou "rounded-default"
    val CornerSmall = RoundedCornerShape(8.0.dp)
}
