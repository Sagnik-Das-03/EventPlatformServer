package com.example.data.local.repo_impl

import com.example.data.local.RepositoryUser
import com.example.data.models.User
import org.bson.types.ObjectId
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class RepositoryUserImpl(
    private val db: CoroutineDatabase
): RepositoryUser {

    private val users = db.getCollection<User>("user")
    override suspend fun getAllUsers() : List<User> {
        return users.find().ascendingSort(User::name).toList()
    }

    override suspend fun getUserForId(id: String): User? {
        return users.findOneById(id)
    }
    override suspend fun addOrUpdateUser(user: User): Boolean {
        val userExists = users.findOneById(user.userId) != null
        return if (userExists) {
            users.updateOneById(user.userId, user).wasAcknowledged()
        } else {
            users.insertOne(user).wasAcknowledged()
        }
    }
    override suspend fun deleteUser(id: String): Boolean {
        val user = users.findOne(User::userId eq id)
        user?.let { user ->
            return users.deleteOneById(user.userId).wasAcknowledged()
        } ?: return false
    }

}