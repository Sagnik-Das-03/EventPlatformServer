package com.eventticketsystem.plugins

import com.eventticketsystem.data.local.RepositoryEvent
import com.eventticketsystem.data.local.RepositoryUser
import com.eventticketsystem.di.mainModule
import com.eventticketsystem.routes.eventRoutes
import com.eventticketsystem.routes.home
import com.eventticketsystem.routes.ticketRoute
import com.eventticketsystem.routes.userRoute
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent

fun Application.configureRouting() {
    routing {
        startKoin {
            modules(mainModule)
        }
        val repositoryUser by KoinJavaComponent.inject<RepositoryUser>(RepositoryUser::class.java)
        val repositoryEvent by KoinJavaComponent.inject<RepositoryEvent>(RepositoryEvent::class.java)
        home()
        userRoute(repositoryUser = repositoryUser)
        eventRoutes(repositoryEvent = repositoryEvent)
        ticketRoute(repositoryEvent= repositoryEvent, repositoryUser = repositoryUser)
    }
}

