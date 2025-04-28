package com.verimsolution.tailwind_prod

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//region TopAppBar Variants

/**
 * Small TopAppBar inspired by Tailwind/Flowbite.
 */
@Composable
fun TailwindTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable (RowScope.() -> Unit)? = null,
    expandedHeight: Dp = 64.dp,
    backgroundColor: Color = Color(0xFF1F2937), // gray-800
    scrolledBackgroundColor: Color = Color(0xFF111827), // gray-900
    contentColor: Color = Color.White,
    elevation: Dp = 4.dp,
    scrollBehavior: TailwindTopAppBarScrollBehavior? = null
) {
    SingleRowTailwindTopAppBar(
        modifier = modifier,
        title = title,
        titleTextStyle = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = contentColor
        ),
        centeredTitle = false,
        navigationIcon = navigationIcon,
        actions = actions,
        expandedHeight = expandedHeight,
        backgroundColor = backgroundColor,
        scrolledBackgroundColor = scrolledBackgroundColor,
        contentColor = contentColor,
        elevation = elevation,
        scrollBehavior = scrollBehavior
    )
}

/**
 * Center-aligned Small TopAppBar inspired by Tailwind/Flowbite.
 */
@Composable
fun TailwindCenterAlignedTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable (RowScope.() -> Unit)? = null,
    expandedHeight: Dp = 64.dp,
    backgroundColor: Color = Color(0xFF1F2937),
    scrolledBackgroundColor: Color = Color(0xFF111827),
    contentColor: Color = Color.White,
    elevation: Dp = 4.dp,
    scrollBehavior: TailwindTopAppBarScrollBehavior? = null
) {
    SingleRowTailwindTopAppBar(
        modifier = modifier,
        title = title,
        titleTextStyle = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = contentColor
        ),
        centeredTitle = true,
        navigationIcon = navigationIcon,
        actions = actions,
        expandedHeight = expandedHeight,
        backgroundColor = backgroundColor,
        scrolledBackgroundColor = scrolledBackgroundColor,
        contentColor = contentColor,
        elevation = elevation,
        scrollBehavior = scrollBehavior
    )
}

/**
 * Medium TopAppBar with two rows inspired by Tailwind/Flowbite.
 */
@Composable
fun TailwindMediumTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable (RowScope.() -> Unit)? = null,
    collapsedHeight: Dp = 64.dp,
    expandedHeight: Dp = 128.dp,
    backgroundColor: Color = Color(0xFF1F2937),
    scrolledBackgroundColor: Color = Color(0xFF111827),
    contentColor: Color = Color.White,
    elevation: Dp = 4.dp,
    scrollBehavior: TailwindTopAppBarScrollBehavior? = null
) {
    require(expandedHeight >= collapsedHeight) { "expandedHeight must be >= collapsedHeight" }
    TwoRowsTailwindTopAppBar(
        modifier = modifier,
        title = title,
        titleTextStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, color = contentColor),
        smallTitleTextStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = contentColor),
        titleBottomPadding = 24.dp,
        navigationIcon = navigationIcon,
        actions = actions,
        collapsedHeight = collapsedHeight,
        expandedHeight = expandedHeight,
        backgroundColor = backgroundColor,
        scrolledBackgroundColor = scrolledBackgroundColor,
        contentColor = contentColor,
        elevation = elevation,
        scrollBehavior = scrollBehavior
    )
}

/**
 * Large TopAppBar with two rows inspired by Tailwind/Flowbite.
 */
@Composable
fun TailwindLargeTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable (RowScope.() -> Unit)? = null,
    collapsedHeight: Dp = 64.dp,
    expandedHeight: Dp = 152.dp,
    backgroundColor: Color = Color(0xFF1F2937),
    scrolledBackgroundColor: Color = Color(0xFF111827),
    contentColor: Color = Color.White,
    elevation: Dp = 4.dp,
    scrollBehavior: TailwindTopAppBarScrollBehavior? = null
) {
    require(expandedHeight >= collapsedHeight) { "expandedHeight must be >= collapsedHeight" }
    TwoRowsTailwindTopAppBar(
        modifier = modifier,
        title = title,
        titleTextStyle = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, color = contentColor),
        smallTitleTextStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = contentColor),
        titleBottomPadding = 28.dp,
        navigationIcon = navigationIcon,
        actions = actions,
        collapsedHeight = collapsedHeight,
        expandedHeight = expandedHeight,
        backgroundColor = backgroundColor,
        scrolledBackgroundColor = scrolledBackgroundColor,
        contentColor = contentColor,
        elevation = elevation,
        scrollBehavior = scrollBehavior
    )
}

//endregion

//region BottomAppBar

/**
 * BottomAppBar inspired by Tailwind/Flowbite.
 */
@Composable
fun TailwindBottomAppBar(
    actions: @Composable RowScope.() -> Unit,
    modifier: Modifier = Modifier,
    floatingActionButton: @Composable (() -> Unit)? = null,
    containerColor: Color = Color(0xFF1F2937),
    contentColor: Color = Color.White,
    elevation: Dp = 4.dp,
    scrollBehavior: TailwindBottomAppBarScrollBehavior? = null
) {
    val animatedColor by animateColorAsState(
        targetValue = scrollBehavior?.let {
            if (it.state.collapsedFraction > 0.5f) Color(0xFF111827) else containerColor
        } ?: containerColor,
        animationSpec = spring()
    )

    val dragModifier = scrollBehavior?.let {
        if (!it.isPinned) {
            Modifier.draggable(
                orientation = Orientation.Vertical,
                state = rememberDraggableState { delta -> it.state.heightOffset -= delta },
                onDragStopped = { /* TODO: Implement fling */ }
            )
        } else Modifier
    } ?: Modifier

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp + (scrollBehavior?.state?.heightOffset?.toInt()?.dp ?: 0.dp))
            .background(animatedColor)
            .then(if (elevation > 0.dp) Modifier.shadow(elevation) else Modifier)
            .then(dragModifier)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                content = actions
            )
            floatingActionButton?.let {
                Box(
                    modifier = Modifier.padding(end = 8.dp),
                    contentAlignment = Alignment.Center
                ) { it() }
            }
        }
    }
}

//endregion

//region Internal Implementations

@Composable
private fun SingleRowTailwindTopAppBar(
    modifier: Modifier,
    title: @Composable () -> Unit,
    titleTextStyle: TextStyle,
    centeredTitle: Boolean,
    navigationIcon: @Composable (() -> Unit)?,
    actions: @Composable (RowScope.() -> Unit)?,
    expandedHeight: Dp,
    backgroundColor: Color,
    scrolledBackgroundColor: Color,
    contentColor: Color,
    elevation: Dp,
    scrollBehavior: TailwindTopAppBarScrollBehavior?
) {
    val animatedColor by animateColorAsState(
        targetValue = scrollBehavior?.let {
            if (it.state.collapsedFraction > 0.5f) scrolledBackgroundColor else backgroundColor
        } ?: backgroundColor,
        animationSpec = spring()
    )

    val dragModifier = scrollBehavior?.let {
        if (!it.isPinned) {
            Modifier.draggable(
                orientation = Orientation.Vertical,
                state = rememberDraggableState { delta -> it.state.heightOffset += delta },
                onDragStopped = { /* TODO: Implement fling */ }
            )
        } else Modifier
    } ?: Modifier

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(expandedHeight)
            .background(animatedColor)
            .padding(horizontal = 16.dp)
            .then(if (elevation > 0.dp) Modifier.shadow(elevation) else Modifier)
            .then(dragModifier)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = if (centeredTitle) Arrangement.Center else Arrangement.SpaceBetween
        ) {
            if (!centeredTitle) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    navigationIcon?.let { Box(modifier = Modifier.padding(end = 8.dp)) { it() } }
                    title()
                }
            } else {
                navigationIcon?.let { Box(modifier = Modifier.padding(end = 8.dp)) { it() } }
                title()
            }
            actions?.let {
                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    content = it
                )
            }
        }
    }
}

@Composable
private fun TwoRowsTailwindTopAppBar(
    modifier: Modifier,
    title: @Composable () -> Unit,
    titleTextStyle: TextStyle,
    smallTitleTextStyle: TextStyle,
    titleBottomPadding: Dp,
    navigationIcon: @Composable (() -> Unit)?,
    actions: @Composable (RowScope.() -> Unit)?,
    collapsedHeight: Dp,
    expandedHeight: Dp,
    backgroundColor: Color,
    scrolledBackgroundColor: Color,
    contentColor: Color,
    elevation: Dp,
    scrollBehavior: TailwindTopAppBarScrollBehavior?
) {
    val animatedColor by animateColorAsState(
        targetValue = scrollBehavior?.let {
            if (it.state.collapsedFraction > 0.5f) scrolledBackgroundColor else backgroundColor
        } ?: backgroundColor,
        animationSpec = spring()
    )

    val dragModifier = scrollBehavior?.let {
        if (!it.isPinned) {
            Modifier.draggable(
                orientation = Orientation.Vertical,
                state = rememberDraggableState { delta -> it.state.heightOffset += delta },
                onDragStopped = { /* TODO: Implement fling */ }
            )
        } else Modifier
    } ?: Modifier

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(expandedHeight + (scrollBehavior?.state?.heightOffset?.toInt()?.dp ?: 0.dp))
            .background(animatedColor)
            .then(if (elevation > 0.dp) Modifier.shadow(elevation) else Modifier)
            .then(dragModifier)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(collapsedHeight)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                navigationIcon?.let { Box(modifier = Modifier.padding(end = 8.dp)) { it() } }
                TailwindText(
                    text = "", // Small title could be different, simplified here
                    style = smallTitleTextStyle
                )
            }
            actions?.let { Row(content = it) }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(expandedHeight - collapsedHeight)
                .padding(bottom = titleBottomPadding),
            contentAlignment = Alignment.BottomStart
        ) {
            title()
        }
    }
}

//endregion

//region Custom Components (Replacements for Material)

/**
 * Custom IconButton replacement.
 */
@Composable
fun TailwindIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .size(48.dp) // Tailwind "h-12 w-12"
            .clip(CircleShape)
            .clickable(onClick = onClick)
            .padding(12.dp), // Tailwind "p-3"
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

/**
 * Custom FAB replacement.
 */
@Composable
fun TailwindFAB(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .size(56.dp) // Tailwind "h-14 w-14"
            .clip(CircleShape)
            .background(Color(0xFF3B82F6)) // Tailwind "blue-500"
            .clickable(onClick = onClick)
            .padding(16.dp) // Tailwind "p-4"
            .shadow(6.dp), // Tailwind "shadow-lg"
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

//endregion

//region Scroll Behaviors

@Stable
interface TailwindTopAppBarScrollBehavior {
    val state: TailwindTopAppBarState
    val isPinned: Boolean
}

@Stable
interface TailwindBottomAppBarScrollBehavior {
    val state: TailwindBottomAppBarState
    val isPinned: Boolean
}

@Stable
class TailwindTopAppBarState(
    initialHeightOffsetLimit: Float = -Float.MAX_VALUE,
    initialHeightOffset: Float = 0f,
    initialContentOffset: Float = 0f
) {
    var heightOffsetLimit by mutableFloatStateOf(initialHeightOffsetLimit)
    var heightOffset by mutableFloatStateOf(initialHeightOffset)
    var contentOffset by mutableFloatStateOf(initialContentOffset)

    val collapsedFraction: Float
        get() = if (heightOffsetLimit != 0f) heightOffset / heightOffsetLimit else 0f
}

@Stable
class TailwindBottomAppBarState(
    initialHeightOffsetLimit: Float = -Float.MAX_VALUE,
    initialHeightOffset: Float = 0f,
    initialContentOffset: Float = 0f
) {
    var heightOffsetLimit by mutableFloatStateOf(initialHeightOffsetLimit)
    var heightOffset by mutableFloatStateOf(initialHeightOffset)
    var contentOffset by mutableFloatStateOf(initialContentOffset)

    val collapsedFraction: Float
        get() = if (heightOffsetLimit != 0f) heightOffset / heightOffsetLimit else 0f
}

@Composable
fun rememberTailwindTopAppBarState(): TailwindTopAppBarState {
    return rememberSaveable { TailwindTopAppBarState() }
}

@Composable
fun rememberTailwindBottomAppBarState(): TailwindBottomAppBarState {
    return rememberSaveable { TailwindBottomAppBarState() }
}

class PinnedTopAppBarScrollBehavior(
    override val state: TailwindTopAppBarState
) : TailwindTopAppBarScrollBehavior {
    override val isPinned: Boolean = true
}

class PinnedBottomAppBarScrollBehavior(
    override val state: TailwindBottomAppBarState
) : TailwindBottomAppBarScrollBehavior {
    override val isPinned: Boolean = true
}
//endregion
//endregion

//region Utilities

fun Modifier.shadow(elevation: Dp): Modifier = this.then(
    Modifier.background(
        color = Color.Black.copy(alpha = elevation.value / 100f)
    )
)

//endregion