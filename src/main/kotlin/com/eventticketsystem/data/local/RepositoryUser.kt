package com.eventticketsystem.data.local

import com.eventticketsystem.data.models.User

interface RepositoryUser {
    suspend fun getAllUsers() : List<User>
    suspend fun addOrUpdateUser(user: User) : Boolean
    suspend fun deleteUser(id: String) : Boolean
    suspend fun getUserForId(id: String): User?
}