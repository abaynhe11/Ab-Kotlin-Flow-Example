package com.google.firebase.quickstart.auth.ab_kotlin_flow_example.data.api

import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.data.model.ApiUser
import kotlinx.coroutines.flow.Flow

interface ApiHelper {

    fun getUsers(): Flow<List<ApiUser>>

    fun getMoreUsers(): Flow<List<ApiUser>>

    fun getUsersWithError(): Flow<List<ApiUser>>

}