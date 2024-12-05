package com.hackapet.petsync_kmp.data

import com.hackapet.petsync_kmp.Pet
import com.hackapet.petsync_kmp.PetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class InMemoryPetRepository : PetRepository {

    private val pets = mutableMapOf<Long, Pet>(
        Pair(1, Pet(1, Pet.Type.DOG, "Bulldog", "Bob", "A friendly dog")),
        Pair(2, Pet(2, Pet.Type.CAT, "Siamese", "Milo", "A friendly cat")),
        Pair(3, Pet(3, Pet.Type.DOG, "Golden Retriever", "Max", "A friendly dog")),
        Pair(4, Pet(4, Pet.Type.CAT, "Persian", "Simba", "A friendly cat")),
        Pair(5, Pet(5, Pet.Type.DOG, "Labrador Retriever", "Bella", "A friendly dog")),
        Pair(6, Pet(6, Pet.Type.CAT, "Maine Coon", "Coco", "A friendly cat")),
        Pair(7, Pet(7, Pet.Type.DOG, "German Shepherd", "Buddy", "A friendly dog")),
        Pair(8, Pet(8, Pet.Type.CAT, "Ragdoll", "Oliver", "A friendly cat")),
        Pair(9, Pet(9, Pet.Type.DOG, "Poodle", "Bailey", "A friendly dog")),
        Pair(10, Pet(10, Pet.Type.CAT, "British Shorthair", "Milo", "A friendly cat")),
        Pair(11, Pet(11, Pet.Type.DOG, "Bulldog", "Bob", "A friendly dog")),
        Pair(12, Pet(12, Pet.Type.CAT, "Siamese", "Milo", "A friendly cat")),
        Pair(13, Pet(13, Pet.Type.DOG, "Golden Retriever", "Max", "A friendly dog")),
        Pair(14, Pet(14, Pet.Type.CAT, "Persian", "Simba", "A friendly cat")),
        Pair(15, Pet(15, Pet.Type.DOG, "Labrador Retriever", "Bella", "A friendly dog")),
        Pair(16, Pet(16, Pet.Type.CAT, "Maine Coon", "Coco", "A friendly cat")),
        Pair(17, Pet(17, Pet.Type.DOG, "German Shepherd", "Buddy", "A friendly dog")),
        Pair(18, Pet(18, Pet.Type.CAT, "Ragdoll", "Oliver", "A friendly cat")),
        Pair(19, Pet(19, Pet.Type.DOG, "Poodle", "Bailey", "A friendly dog")),
        Pair(20, Pet(20, Pet.Type.CAT, "British Shorthair", "Milo", "A friendly cat")),
        Pair(21, Pet(21, Pet.Type.DOG, "Bulldog", "Bob", "A friendly dog")),
    )

    override fun findAll(): Flow<List<Pet>> {
        return flowOf(pets.values.toList())
    }

    override fun findById(id: Long): Flow<Pet> {
        return flowOf(pets[id]!!)
    }

    override fun upsert(pet: Pet): Flow<Long> {
        return flow {
            pets[pet.id] = pet
            emit(pet.id)
        }
    }

    override fun remove(pet: Pet): Flow<Long> {
        return flow {
            val removed = pets.remove(pet.id)
            emit(removed?.id ?: -1)
        }
    }

    override fun remove(id: Long): Flow<Long> {
        return flow {
            val removed = pets.remove(id)
            emit(removed?.id ?: -1)
        }
    }
}