package com.oguzdogdu.retrofit_post.service

import com.oguzdogdu.retrofit_post.model.User
import com.oguzdogdu.retrofit_post.model.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RetroService {

    @POST("users")
    @Headers(
        "Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer 8a39f698435bb27206a9f24708c480d8625df4b9dbb806c01aae08b8d31b60de"
    )
    fun createUser(@Body params: User): Call<UserResponse>
}