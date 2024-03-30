package com.monotest.movable

import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.zIndex

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyGridItemScope.MovableItem(
    key: Any,
    state: MovableLazyVerticalGridState,
    modifier: Modifier = Modifier,
    content: @Composable MovableItemScope.(isDragging: Boolean) -> Unit
) {
    val isMoving by remember { derivedStateOf { state.draggingItemKey == key } }
    val settlingTranslation: Offset by animateOffsetAsState(if (isMoving) (state.draggingItemTranslation ?: Offset(0f, 0f)) else Offset(0f, 0f), label = "")
    val isSettling by remember { derivedStateOf { settlingTranslation != Offset(0f, 0f) } }

    val draggableModifier =
        if (isMoving) {
            Modifier
                .zIndex(1f)
                .graphicsLayer {
                    translationX = state.draggingItemTranslation?.x ?: 0f
                    translationY = state.draggingItemTranslation?.y ?: 0f
                }
        } else if (isSettling) {
            Modifier
                .zIndex(1f)
                .graphicsLayer {
                    translationX = settlingTranslation.x
                    translationY = settlingTranslation.y
                }
        } else {
            Modifier.animateItemPlacement()
        }

    Column(modifier = modifier.then(draggableModifier)) {
        MovableItemScopeImpl(key, state).content(isMoving)
    }
}

interface MovableItemScope {
    fun Modifier.longPressDraggable(): Modifier
}

class MovableItemScopeImpl(
    private val key: Any,
    private val state: MovableLazyVerticalGridState
) : MovableItemScope {

    override fun Modifier.longPressDraggable(): Modifier = this.composed {
        pointerInput(Unit) {
            detectDragGesturesAfterLongPress(
                onDragStart = { state.onDragStart(key) },
                onDrag = { _, dragAmount -> state.onDrag(dragAmount) },
                onDragEnd = { state.onDragEnd() },
                onDragCancel = { state.onDragEnd() }
            )
        }
    }
}