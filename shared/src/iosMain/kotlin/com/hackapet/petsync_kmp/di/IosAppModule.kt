package com.hackapet.petsync_kmp.di

import com.hackapet.petsync_kmp.di.AppModule.*

class IosAppModule {

    class Builder : AppModule.Builder {

        private var url: String? = null

        private var keys: Keys? = null

        fun withUrl(url: String): Builder {
            this.url = url
            return this
        }

        fun withKeys(keys: Keys): Builder {
            this.keys = keys
            return this
        }

        override fun build(): AppModule {

            val baseUrl = checkNotNull(url) { "Url cannot be null" }
            val apiKeys = checkNotNull(keys) { "Keys cannot be null" }

            return AppModule(apiKeys, baseUrl)
        }
    }
}