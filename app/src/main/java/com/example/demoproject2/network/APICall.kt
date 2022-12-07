package com.example.demoproject2.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException

object APICall {


    suspend fun <T> call(
        APICall: suspend () -> T
    ): ResultWrapper<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response = APICall.invoke()
                ResultWrapper.Success(response)
            } catch (throwable: Throwable) {
                when (throwable) {
                    is UnknownHostException -> ResultWrapper.GenericError(
                        ErrorResponse("Network Unavailable", 503)
                    )
                    is IOException -> ResultWrapper.GenericError(
                        ErrorResponse(throwable.localizedMessage, 401)
                    )
                    is HttpException -> {
                        val code = throwable.code()
                        var errorResponse = convertErrorBody(throwable)
                        if (errorResponse == null) {
                            errorResponse =
                                ErrorResponse(
                                    "Server Response Error"
                                )
                        }
                        errorResponse.code = code
                        ResultWrapper.GenericError(errorResponse)
                    }

                    else -> {
                        ResultWrapper.GenericError(
                            ErrorResponse(
                                "Server Error", null
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                ResultWrapper.GenericError(
                    ErrorResponse("Server Error", null)
                )

            }
        }
    }

    private fun convertErrorBody(throwable: HttpException): ErrorResponse? {

        var jobError = throwable.response()?.errorBody()?.string()
        return try {
            var obj = jobError?.let { JSONObject(it) }
            obj?.let {
                ErrorResponse(
                    it.getString(
                        "error"
                    )

                )
            }

        } catch (exception: Exception) {
            jobError = throwable.message().toString()
            return ErrorResponse(
                jobError
            )

        }
    }

}