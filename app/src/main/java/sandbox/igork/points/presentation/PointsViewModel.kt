package sandbox.igork.points.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import sandbox.igork.points.di.appComponent
import sandbox.igork.points.domain.DataPoint
import sandbox.igork.points.domain.GetPointsUseCase
import javax.inject.Inject

sealed interface ViewState {

    object Progress : ViewState
    data class Error(val pointsCount: Int, val message: String) : ViewState
    data class Content(val pointsCount: Int, val points: List<DataPoint>) : ViewState
}

class PointsViewModel : ViewModel() {

    init {
        appComponent.inject(this)
    }

    @Inject
    lateinit var getPointsUseCase: GetPointsUseCase

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Content(3, emptyList()))
    val viewState: StateFlow<ViewState> = _viewState.asStateFlow()

    private val pointsCount
        get() = when (val state = viewState.value) {
            is ViewState.Content -> state.pointsCount
            is ViewState.Error -> state.pointsCount
            ViewState.Progress -> null
        }

    fun requestPoints() {
        val pointsCount = this.pointsCount ?: return

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val points = getPointsUseCase(pointsCount)
                _viewState.value = ViewState.Content(pointsCount, points)
            } catch (e: Throwable) {
                _viewState.value =
                    ViewState.Error(pointsCount, "Error occurred: ${e.message}")
            }
        }
    }

    fun updatePointsCount(countString: String) {
        val content = viewState.value as? ViewState.Content ?: return
        val newCount = countString.toIntOrNull() ?: return
        _viewState.value = content.copy(pointsCount = newCount)
    }
}