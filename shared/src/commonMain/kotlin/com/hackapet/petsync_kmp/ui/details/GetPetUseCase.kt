package com.hackapet.petsync_kmp.ui.details

import com.hackapet.petsync_kmp.Pet
import com.hackapet.petsync_kmp.PetRepository

class GetPetUseCase(private val petRepository: PetRepository) {

    operator fun invoke(id: Long): Pet? {
        return petRepository.findById(id)
    }

}