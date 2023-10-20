package com.example.hiltsample.data

import com.example.hiltsample.User

interface UserRepository {
    fun getUserDetails(id: Long): User
}
