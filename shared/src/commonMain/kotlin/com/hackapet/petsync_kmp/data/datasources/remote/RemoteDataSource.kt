package com.hackapet.petsync_kmp.data.datasources.remote

interface RemoteDataSource {

    suspend fun getPets(): Response<List<PetDto>>

    suspend fun getById(id: Long): Response<PetDto>
}