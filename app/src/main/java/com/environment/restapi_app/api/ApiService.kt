package com.environment.restapi_app.api

import com.environment.restapi_app.model.CharacterResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("character")
    fun getCharacters():Call<CharacterResponse>
}