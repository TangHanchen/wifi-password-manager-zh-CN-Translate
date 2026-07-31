package io.github.wifi_password_manager.ui.screen.methodinspector

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.vinceglb.filekit.FileKit
import io.github.vinceglb.filekit.delete
import io.github.vinceglb.filekit.dialogs.openFileSaver
import io.github.vinceglb.filekit.exists
import io.github.vinceglb.filekit.writeString
import io.github.wifi_password_manager.R
import io.github.wifi_password_manager.domain.model.MethodInspectorExport
import io.github.wifi_password_manager.utils.UiText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.lsposed.hiddenapibypass.HiddenApiBypass
import kotlin.reflect.KClass
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

@OptIn(FlowPreview::class)
class MethodInspectorViewModel<T : Any>(
    private val forClass: KClass<T>,
    private val json: Json,
) : ViewModel() {
    companion object {
        private const val TAG = "MethodInspectorViewModel"
    }

    @Immutable
    data class State(
        val methods: Map<String, String> = emptyMap(),
        val isExporting: Boolean = false,
        val showingSearch: Boolean = false,
        val searchText: String = "",
    )

    sealed interface Action {
        data object ExportMethods : Action

        data object ToggleSearch : Action

        data class SearchTextChanged(val text: String) : Action
    }

    sealed interface Event {
        data class ShowMessage(val message: UiText) : Event
    }

    private val methods = HiddenApiBypass.getDeclaredMethods(forClass.java).associate {
        it.name to it.toGenericString()
    }

    private val _isExporting = MutableStateFlow(false)
    private val _showingSearch = MutableStateFlow(false)
    private val _searchText = MutableStateFlow("")
    private val _methods =
        _searchText.debounce(200.milliseconds).distinctUntilChanged().map { searchText ->
            val query = searchText.trim()
            if (query.isBlank()) {
                methods
            } else {
                methods.filter { (_, signature) -> signature.contains(query, ignoreCase = true) }
            }
        }

    val state: StateFlow<State> = combine(
        _methods,
        _isExporting,
        _showingSearch,
        _searchText,
    ) { methods, isExporting, showingSearch, searchText ->
        State(
            methods = methods,
            isExporting = isExporting,
            searchText = searchText,
            showingSearch = showingSearch,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5.seconds),
        initialValue = State(),
    )

    private val _event = Channel<Event>()
    val event = _event.receiveAsFlow()

    fun onAction(action: Action) {
        Log.d(TAG, "onAction: $action")
        when (action) {
            is Action.ExportMethods -> onExportMethods()
            is Action.ToggleSearch -> onToggleSearch()
            is Action.SearchTextChanged -> _searchText.update { action.text }
        }
    }

    private fun onToggleSearch() {
        _showingSearch.update { !it }
        _searchText.update { "" }
    }

    private fun onExportMethods() {
        viewModelScope.launch {
            val file = FileKit.openFileSaver(
                suggestedName = "${forClass.simpleName}_methods",
                defaultExtension = "json",
            ) ?: return@launch

            _isExporting.update { true }

            val result = runCatching {
                Dispatchers.IO {
                    val data = MethodInspectorExport(methods = methods.values.toList())
                    val jsonString = json.encodeToString(data)
                    file.writeString(jsonString)
                }
            }

            result.fold(
                onSuccess = {
                    _event.send(Event.ShowMessage(UiText.StringResource(R.string.export_methods_success)))
                },
                onFailure = {
                    Log.e(TAG, "Error exporting methods", it)
                    _event.send(Event.ShowMessage(UiText.StringResource(R.string.export_methods_failed)))
                    if (file.exists()) file.delete()
                },
            )

            _isExporting.update { false }
        }
    }
}