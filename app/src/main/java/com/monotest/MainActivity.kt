package com.monotest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.monotest.ui.theme.MonoTestTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class MainActivity : ComponentActivity() {

    companion object {
        const val COLUMNS_NUMBER = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val meaningfulEntitiesState = MutableStateFlow(meaningfulEntities)

        fun swap(fromIndex: Int, toIndex: Int) {
            meaningfulEntitiesState.update { it.toMutableList().apply { add(toIndex, removeAt(fromIndex)) } }
        }

        setContent {
            MonoTestTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val uiState by meaningfulEntitiesState.collectAsState()
                    Grid(meaningfulEntities = uiState, onCellsExchanged = { from, to -> swap(from, to) })
                }
            }
        }
    }
}