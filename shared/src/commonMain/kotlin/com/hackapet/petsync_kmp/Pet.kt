package com.hackapet.petsync_kmp

data class Pet(
    val id: Long,
    val type: Type,
    val race: String,
    val name: String,
    val description: String
) {
    enum class Type { DOG, CAT }
}