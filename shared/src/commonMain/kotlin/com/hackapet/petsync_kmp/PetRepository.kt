package com.hackapet.petsync_kmp

import kotlinx.coroutines.flow.Flow

interface PetRepository {

    fun findAll(): Flow<List<Pet>>

    fun findById(id: Long): Flow<Pet>

    fun upsert(pet: Pet): Flow<Long>

    fun remove(pet: Pet): Flow<Long>

    fun remove(id: Long): Flow<Long>
}