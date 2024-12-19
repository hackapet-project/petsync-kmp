package com.hackapet.petsync_kmp.di

import androidx.lifecycle.viewmodel.compose.viewModel
import com.hackapet.petsync_kmp.PetRepository
import com.hackapet.petsync_kmp.data.InMemoryPetRepository
import com.hackapet.petsync_kmp.data.datasources.remote.ApiRemoteDataSource
import com.hackapet.petsync_kmp.data.datasources.remote.RemoteDataSource
import com.hackapet.petsync_kmp.ui.details.DetailViewModel
import com.hackapet.petsync_kmp.ui.details.GetPetUseCase
import com.hackapet.petsync_kmp.ui.home.GetPetsUseCase
import com.hackapet.petsync_kmp.ui.home.HomeViewModel
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module


class Keys(val publicKey: String, val privateKey: String)

class AppModule(
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

private val dataModule = module {
    single<RemoteDataSource> { ApiRemoteDataSource(get()) }
    single<PetRepository> { InMemoryPetRepository() }
    single<HttpClient> {
        createHttpClient(get<String>(named("baseUrl")))
    }
}

private val useCasesModule = module {
    single { GetPetsUseCase(get()) }
    single { GetPetUseCase(get()) }
}

private val viewModelsModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { (id: Long) -> DetailViewModel(get(), id) }
}

private fun createHttpClient(baseUrl: String) = HttpClient {

    install(ContentNegotiation) {
        json(Json { ignoreUnknownKeys = true })
    }

    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                Napier.v("HTTP Client", null, message)
            }
        }
        level = LogLevel.HEADERS

    }.also { Napier.base(DebugAntilog()) }

    defaultRequest { url(baseUrl) }
}

fun initKoin(
    config: KoinAppDeclaration? = null,
    appModule: AppModule,
    modules: List<Module> = emptyList()
) {
    startKoin {
        config?.invoke(this)
        modules(modules + dataModule + useCasesModule + viewModelsModule + appModule.moduleKoin())
    }
}