package com.example.profile.Api

import com.example.profile.Api.Response.ApiResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.QueryMap

interface ApiService {

    @Headers("")
    @GET("")
    fun getSearchWatch(
        @QueryMap params: String
    ): Observable<Response<ApiResponse>>
}