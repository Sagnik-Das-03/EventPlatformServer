package com.example.data.local

import com.example.data.models.User

interface RepositoryUser {
    suspend fun getAllUsers() : List<User>
    suspend fun addOrUpdateUser(user: User) : Boolean
    suspend fun deleteUser(id: String) : Boolean
    suspend fun getUserForId(id: String): User?
}