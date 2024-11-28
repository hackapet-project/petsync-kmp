package com.hackapet.petsync_kmp.di

import org.koin.core.qualifier.named
import org.koin.dsl.module


internal class Keys(val publicKey: String, val privateKey: String)

internal class AppModule(
    private val apiKeys: Keys,
    private val baseUrl: String,
) {
    fun moduleKoin() = module {
        single(named("keys")) { apiKeys }
        single(named("baseUrl")) { baseUrl }
    }

    interface Builder {
        fun build(): AppModule
    }
}