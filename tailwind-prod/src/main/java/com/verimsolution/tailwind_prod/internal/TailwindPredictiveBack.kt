package com.verimsolution.tailwind_prod.internal

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing


private val TailwindPredictiveBackEasing: Easing = CubicBezierEasing(0.1f, 0.1f, 0f, 1f)

internal object TailwindPredictiveBack {
    internal fun transform(progress: Float) = TailwindPredictiveBackEasing.transform(progress)
}
