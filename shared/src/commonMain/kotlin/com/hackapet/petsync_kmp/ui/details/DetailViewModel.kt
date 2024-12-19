package com.hackapet.petsync_kmp.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackapet.petsync_kmp.Pet
import com.hackapet.petsync_kmp.PetRepository
import com.hackapet.petsync_kmp.ui.home.PetItem.CreteNewPetItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable


data class PetDetailUiState(val loaded: Boolean, val pet: Pet? = null)

class DetailViewModel(
    private val getPetUseCase: GetPetUseCase,
    private val selectedPetId: Long,
) : ViewModel() {

    private val _petDetail = MutableStateFlow(PetDetailUiState(false))
    val petDetail: StateFlow<PetDetailUiState> = _petDetail.asStateFlow()


    fun loadPet(): StateFlow<PetDetailUiState> {
        viewModelScope.launch {
            _petDetail.value =
                PetDetailUiState(true, getPetUseCase(selectedPetId))
        }

        return petDetail
    }
}

