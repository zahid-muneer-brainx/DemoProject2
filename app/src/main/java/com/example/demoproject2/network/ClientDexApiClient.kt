package com.example.demoproject2.network

import com.example.demoproject2.models.ChatModel
import com.example.demoproject2.models.ChatResponseModel
import com.example.demoproject2.models.UserModel
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.*

interface ClientDexApiClient {
    @POST("/api/v1/users/sign_in.json")
    suspend fun signIn(@Body body: JsonObject?): Response<UserModel>

    @GET("/api/v1/client/chats.json")
    suspend fun GetChatList(
        @Query("current_page") pageIndex: Int
    ): Response<ChatResponseModel>


}