package com.example.task

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DashBoardViewModel @Inject constructor():ViewModel() {
    private val _currentIndex = MutableStateFlow(0)

    //Expose immutable flow using asStateFlow()
    val currentIndex = _currentIndex.asStateFlow()

    fun updateCurrentIndex() {
        // Update overlay state based on the current index
        if (_currentIndex.value <= 3) {
            when (_currentIndex.value) {
                0 -> _homeoverlayState.update { true }
                1, 2 -> _homeoverlayState.update { false }
            }

            // Increment the current index
            _currentIndex.update { it + 1 }
        } else {
            // Reset index to 0 if it's beyond 3
            _currentIndex.update { 0 }
        }
    }
    private val _bottomNavState = MutableStateFlow(false)
    val bottomNavState: StateFlow<Boolean> get() = _bottomNavState
    fun updateBottomNavState(newState: Boolean) {
        _bottomNavState.value = newState
    }

    // for home screen tips
    private val _homeoverlayState = MutableStateFlow(true)
    val homeoverlayState: StateFlow<Boolean> get() = _homeoverlayState
       fun updateHomeOverlayState(newState: Boolean) {
           _homeoverlayState.value = newState
       }

    // for connector Screen
    private val _connectorState = MutableStateFlow(true)
    val connectorState: StateFlow<Boolean> get() = _connectorState
    fun updateConnectorState(newState: Boolean) {
        _connectorState.value = newState
    }
    // for Qestions Screen
    private val _questionsState = MutableStateFlow(true)
    val questionsState: StateFlow<Boolean> get() = _questionsState
    fun updateQuestionsState(newState: Boolean) {
        _questionsState.value = newState
    }
    //for writing tab
    private val _writingState = MutableStateFlow(false)
    val writingState: StateFlow<Boolean> get() = _writingState
    fun updateWritingState(newState: Boolean) {
        _writingState.value = newState
    }
}
