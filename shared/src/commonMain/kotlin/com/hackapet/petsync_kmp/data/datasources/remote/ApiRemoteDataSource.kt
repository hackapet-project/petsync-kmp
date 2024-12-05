package com.hackapet.petsync_kmp.data.datasources.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.path

internal class ApiRemoteDataSource(private val httpClient: HttpClient) : RemoteDataSource {

    override suspend fun getPets(): Response<List<PetDto>> {
        return httpClient.get { url { path("pets") } }.body()
    }

    override suspend fun getById(id: Long): Response<PetDto> {
        return httpClient.get { url { path("pet/$id") } }.body()
    }
}