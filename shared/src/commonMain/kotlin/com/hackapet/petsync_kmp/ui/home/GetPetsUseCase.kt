package com.hackapet.petsync_kmp.ui.home

import com.hackapet.petsync_kmp.Pet
import com.hackapet.petsync_kmp.PetRepository

class GetPetsUseCase(private val petRepository: PetRepository) {

    operator fun invoke(): List<Pet> {
        return petRepository.findAll()
    }

}