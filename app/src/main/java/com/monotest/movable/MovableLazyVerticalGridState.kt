package com.monotest.movable

import androidx.compose.foundation.lazy.grid.LazyGridItemInfo
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset

@Composable
fun rememberMovableLazyVerticalGridState(
    lazyGridState: LazyGridState,
    onItemMove: (oldIndex: Int, newIndex: Int) -> Unit,
): MovableLazyVerticalGridState {
    return remember {
        MovableLazyVerticalGridState(lazyGridState, onItemMove)
    }
}

class MovableLazyVerticalGridState(
    private val lazyGridState: LazyGridState,
    private val onItemMove: (oldIndex: Int, newIndex: Int) -> Unit
) {
    val draggingItemKey: Any? get() = startDraggingItemInfo?.key
    private var startDraggingItemInfo by mutableStateOf<LazyGridItemInfo?>(null)
    private val draggingItemInfo: LazyGridItemInfo? get() = lazyGridState.layoutInfo.visibleItemsInfo.firstOrNull { item -> item.key == draggingItemKey }

    private val initialDraggingItemOffset get() = startDraggingItemInfo?.offset

    private var draggingItemDelta: Offset by mutableStateOf(Offset(x = 0f, y = 0f))

    private val translatedDraggingItemOffset: Offset?
        get() {
            val initialInfo = initialDraggingItemOffset ?: return null

            return Offset(
                x = initialInfo.x.toFloat() + draggingItemDelta.x,
                y = initialInfo.y.toFloat() + draggingItemDelta.y
            )
        }

    val draggingItemTranslation: Offset?
        get() {
            val translatedOffset = translatedDraggingItemOffset ?: return null
            val draggingItemInfo = draggingItemInfo ?: return null

            return Offset(
                x = translatedOffset.x - draggingItemInfo.offset.x.toFloat(),
                y = translatedOffset.y - draggingItemInfo.offset.y.toFloat()
            )
        }

    fun onDragStart(key: Any) {
        startDraggingItemInfo = lazyGridState.layoutInfo.visibleItemsInfo.firstOrNull { item -> item.key == key }
    }

    fun onDrag(dragAmount: Offset) {
        draggingItemDelta += dragAmount

        val translatedOffset = translatedDraggingItemOffset ?: return
        val draggingItemInfo = draggingItemInfo ?: return
        val horizontalRange = translatedOffset.x..(translatedOffset.x + draggingItemInfo.size.width)
        val verticalRange = translatedOffset.y..(translatedOffset.y + draggingItemInfo.size.height)

        lazyGridState.layoutInfo.visibleItemsInfo
            .find { item ->
                (item.offset.x.toFloat() + item.size.width / 2) in horizontalRange && (item.offset.y.toFloat() + item.size.height / 2) in verticalRange && item.key != draggingItemKey
            }
            ?.let { targetItemInfo ->
                onItemMove(draggingItemInfo.index, targetItemInfo.index)
            }
    }

    fun onDragEnd() {
        startDraggingItemInfo = null
        draggingItemDelta = Offset(x = 0f, y = 0f)
    }
}