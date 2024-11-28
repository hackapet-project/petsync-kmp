package com.hackapet.petsync_kmp.data.datasources.remote

import kotlinx.serialization.Serializable

@Serializable
class PetDto(val id: Long, val race: String, val name: String)