package com.google.firebase.quickstart.auth.ab_kotlin_flow_example.data.api

import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.data.model.ApiUser
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<ApiUser>

    @GET("more-users")
    suspend fun getMoreUsers(): List<ApiUser>

    @GET("error")
    suspend fun getUsersWithError(): List<ApiUser>

}