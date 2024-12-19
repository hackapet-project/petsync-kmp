package com.hackapet.petsync_kmp

interface PetRepository {

    fun findAll(): List<Pet>

    fun findById(id: Long): Pet?

    fun upsert(pet: Pet): Long

    fun remove(pet: Pet): Long

    fun remove(id: Long): Long
}