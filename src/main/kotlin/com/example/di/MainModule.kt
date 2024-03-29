package com.example.di

import com.example.data.local.RepositoryEvent
import com.example.data.local.RepositoryUser
import com.example.data.local.repo_impl.RepositoryEventImpl
import com.example.data.local.repo_impl.RepositoryUserImpl
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val mainModule = module {
    single {
        KMongo.createClient()
            .coroutine
            .getDatabase("events_db")
    }
    single<RepositoryUser>{
        RepositoryUserImpl(get())
    }
    single<RepositoryEvent> {
        RepositoryEventImpl(get())
    }
}