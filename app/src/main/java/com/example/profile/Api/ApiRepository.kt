package com.example.profile.Api

import com.example.profile.Api.Response.ApiResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

object ApiRepository: BaseApiRepository() {
    fun requestSearchImage(params: String): Observable<Response<ApiResponse>> {
        return getApiService().getSearchWatch(params)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }
}