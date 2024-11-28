package com.hackapet.petsync_kmp.di

import android.content.Context
import com.hackapet.petsync_kmp.di.AppModule.*

class AndroidAppModule {

    class Builder : AppModule.Builder {

        private var url: String? = null

        private var keys: Keys? = null

        private var context: Context? = null

        fun withContext(context: Context): Builder {
            this.context = context
            return this
        }

        fun withUrl(url: String): Builder {
            this.url = url
            return this
        }

        fun withKeys(keys: Keys): Builder {
            this.keys = keys
            return this
        }

        override fun build(): AppModule {

            val context = checkNotNull(context) { "Context factory cannot be null" }

            val baseUrl = checkNotNull(url) { "Url cannot be null" }
            val apiKeys = checkNotNull(keys) { "Keys cannot be null" }

            return AppModule(apiKeys, baseUrl)
        }
    }
}