package com.eventticketsystem.di

import com.eventticketsystem.data.local.RepositoryEvent
import com.eventticketsystem.data.local.RepositoryUser
import com.eventticketsystem.data.local.repo_impl.RepositoryEventImpl
import com.eventticketsystem.data.local.repo_impl.RepositoryUserImpl
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