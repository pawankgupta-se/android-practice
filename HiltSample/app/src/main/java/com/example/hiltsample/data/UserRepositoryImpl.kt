package com.example.hiltsample.data

import android.util.Log
import com.example.hiltsample.User
import com.example.hiltsample.network.ApiService

class UserRepositoryImpl(private val api: ApiService, private val homeTranslations: HomeTranslations): UserRepository {
    override fun getUserDetails(id: Long): User {
        Log.d("UserRepositoryImpl", "Fetching user details")
        Log.d("UserRepositoryImpl",  homeTranslations.getError())
        return User()


    }
}
