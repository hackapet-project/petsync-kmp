package com.hackapet.petsync_kmp.ui.home

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
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable


@Serializable
sealed class PetItem(val itemId: Long) {

    data object CreteNewPetItem : PetItem(-1)

    class DetailPetItem(itemId: Long, val pet: Pet) : PetItem(itemId)
}

data class PetListUiState(val loaded: Boolean, val pets: List<PetItem> = emptyList())

class HomeViewModel(
    private val getPetsUseCase: GetPetsUseCase,
) : ViewModel() {

    private val _petList = MutableStateFlow(PetListUiState(false))
    val petList: StateFlow<PetListUiState> = _petList.asStateFlow()

    fun loadPets(): StateFlow<PetListUiState> {
        viewModelScope.launch {
            _petList.value =
                PetListUiState(true, listOf(CreteNewPetItem) + getPetsUseCase().toPetItemList())
        }
        return petList
    }

    private inline fun Pet.toPetItem() = PetItem.DetailPetItem(id, this)

    private inline fun List<Pet>.toPetItemList() = this.map { it.toPetItem() }
}

