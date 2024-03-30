package com.monotest

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.monotest.movable.MovableItem
import com.monotest.movable.rememberMovableLazyVerticalGridState

@Composable
fun Grid(
    meaningfulEntities: List<MeaningfulUiEntity>,
    onCellsExchanged: (from: Int, to: Int) -> Unit
) {
    val lazyGridState: LazyGridState = rememberLazyGridState()

    val uiStateWithFixStubs by remember(meaningfulEntities) { // https://www.reddit.com/r/androiddev/comments/171uwfg/hacking_lazylist_in_android_jetpack_compose/
        derivedStateOf { buildList { repeat(MainActivity.COLUMNS_NUMBER) { add(it) } } + meaningfulEntities }
    }
    val movableLazyVerticalGridState = rememberMovableLazyVerticalGridState(lazyGridState) { old, new ->
        val from = old - MainActivity.COLUMNS_NUMBER
        val to = new - MainActivity.COLUMNS_NUMBER
        if (from < 0 || to < 0) return@rememberMovableLazyVerticalGridState
        onCellsExchanged(from, to)
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(count = MainActivity.COLUMNS_NUMBER),
        state = lazyGridState
    ) {
        items(items = uiStateWithFixStubs, key = { (it as? MeaningfulUiEntity)?.uid ?: it }) {
            if (it is MeaningfulUiEntity) {
                var isCellMoving by remember { mutableStateOf(false) }
                val rotation: Float by animateFloatAsState(
                    targetValue = if (isCellMoving) -5f else 0f,
                    animationSpec = tween(durationMillis = 200, easing = LinearEasing),
                    label = ""
                )
                val scale: Float by animateFloatAsState(
                    targetValue = if (isCellMoving) 1.1f else 1f,
                    animationSpec = tween(durationMillis = 200, easing = LinearEasing),
                    label = ""
                )
                val isCellSettling by remember { derivedStateOf { scale != 1f } }

                MovableItem(
                    it.uid,
                    movableLazyVerticalGridState,
                    modifier = Modifier.zIndex(if (isCellSettling) 1f else 0f)
                ) { isMoving ->
                    isCellMoving = isMoving
                    AsyncImage(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .longPressDraggable()
                            .graphicsLayer {
                                rotationZ = rotation
                                scaleX = scale
                                scaleY = scale
                            },
                        contentScale = ContentScale.Crop,
                        model = it.imageUrl,
                        contentDescription = null,
                    )
                }
            } else Spacer(modifier = Modifier.height(1.dp))
        }
    }
}