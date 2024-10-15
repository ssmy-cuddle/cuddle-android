package com.ssmy.cuddle.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * doc 주석
 * @author wookjin
 * @since 10/15/24
 **/
data class Provider(
    val provider: String,
    val access_token: String
)

data class SignUpRequest(
    val provider: Provider,
    val email: String,
    val username: String,
    val full_name: String
)

data class SignUpResponse(
    val username: String,
    val email: String,
    val full_name: String,
    val id: Int,
    val is_active: Boolean
)

data class ValidationError(
    val detail: List<ValidationErrorDetail>
)

data class ValidationErrorDetail(
    val loc: List<Any>,
    val msg: String,
    val type: String
)

interface ApiService {

    @Headers("Content-Type: application/json")
    @POST("users/oauth/signup")
    fun signUp(@Body signUpRequest: SignUpRequest): Call<SignUpResponse>
}
