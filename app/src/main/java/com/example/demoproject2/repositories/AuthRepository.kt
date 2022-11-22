package com.example.demoproject2.repositories

import com.example.demoproject2.SharedPreferences.MySharedPreferences
import com.example.demoproject2.models.ChatModel
import com.example.demoproject2.models.ChatResponseModel
import com.example.demoproject2.models.UserModel
import com.example.demoproject2.network.APICall
import com.example.demoproject2.network.ClientDexApiClient
import com.example.demoproject2.network.ResultWrapper
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import retrofit2.Response
import javax.inject.Inject

class AuthRepository @Inject constructor(
//    region private properties
    private val mUserDataStore: MySharedPreferences,
    private val clientDexApiClient: ClientDexApiClient
//    endregion
) {

    //region Public methods
    suspend fun signIn(
        email: String,
        password: String,
        response: (UserModel?, String?, Boolean) -> Unit
    ) {
        val bodyObj = JsonObject().apply {
            addProperty("email", email)
            addProperty("password", password)
            addProperty("app_platform", "android")
            addProperty("app_version", "1")
        }
        val apiResponse = APICall.call {
            clientDexApiClient.signIn(bodyObj)
        }
        when (val result = apiResponse) {
            is ResultWrapper.Success -> {
                result.value.apply {
                    if (isSuccessful) {
                        val headers = headers()
                        mUserDataStore.apply {
                            clientid = headers["client"]
                            token = headers["access-Token"]
                            uid=headers["uid"]
                        }
                        response(
                            body(),
                            message(),
                            isSuccessful,
                        )
                    } else {
                        response(
                            null,
                            getErrorMessage(),
                            isSuccessful,
                        )
                    }
                }
            }
            is ResultWrapper.GenericError -> response(null, result.error?.message, false)
            else -> {}
        }

    }

    fun <T> Response<T>.getErrorMessage(): String {
        val error = errorBody()?.string()?.toObject(Error::class.java) as? com.example.demoproject2.models.Error
        return error?.error ?: message()

    }
    fun String.toObject(activityClass: Class<*>): Any? {
        return GsonBuilder().create().fromJson(this, activityClass)

    }
    suspend fun getChatList(
        page:Int,
        response: (ChatResponseModel?,String?,Boolean) -> Unit
    ) {
        val apiResponse = APICall.call {
            clientDexApiClient.GetChatList(page)
        }
        when (apiResponse) {
            is ResultWrapper.Success -> {
                apiResponse.value.apply {
                    if (isSuccessful) {
                        val headers = headers()
                        mUserDataStore.apply {
                            clientid = headers["client"]
                            token = headers["access-Token"]
                        }
                        response(
                            body(),
                            message(),
                            isSuccessful,
                        )
                        println("I got this: "+body().toString())
                    } else {
                        response(
                            null,
                            getErrorMessage(),
                            isSuccessful,
                        )
                        println("I got this: "+body().toString())
                    }
                }
            }
            is ResultWrapper.GenericError -> {
                response(null, apiResponse.error?.message, false)
                println(apiResponse.error?.message)
                println("I got this: ")
            }
            else -> {

            }
        }

    }
}