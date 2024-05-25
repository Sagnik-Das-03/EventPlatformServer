package com.example.plugins

import com.example.data.local.RepositoryEvent
import com.example.data.local.RepositoryUser
import com.example.di.mainModule
import com.example.routes.eventRoutes
import com.example.routes.home
import com.example.routes.ticketRoute
import com.example.routes.userRoute
import io.ktor.server.application.*
import io.ktor.server.response.*
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

