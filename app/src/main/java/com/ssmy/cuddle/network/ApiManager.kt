package com.ssmy.cuddle.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * doc 주석
 * @author wookjin
 * @since 10/15/24
 **/
object ApiManager {

    private val apiService: ApiService by lazy {
        RetrofitClient.retrofit.create(ApiService::class.java)
    }

    fun signUp(
        signUpRequest: SignUpRequest,
        onSuccess: (SignUpResponse?) -> Unit,
        onFailure: (Throwable, ValidationError?) -> Unit
    ) {
        apiService.signUp(signUpRequest).enqueue(object : Callback<SignUpResponse> {
            override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                if (response.isSuccessful) {
                    onSuccess(response.body())
                } else {
                    val errorResponse = try {
                        val validationError = response.errorBody()?.let { body ->
                            RetrofitClient.retrofit.responseBodyConverter<ValidationError>(
                                ValidationError::class.java,
                                arrayOfNulls<Annotation>(0)
                            ).convert(body)
                        }
                        validationError
                    } catch (e: Exception) {
                        null
                    }
                    onFailure(Throwable("Error: ${response.code()}"), errorResponse)
                }
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                onFailure(t, null)
            }
        })
    }
}
