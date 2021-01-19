package com.example.profile.Api

import com.example.profile.Api.Response.ApiResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

object ApiRepository: BaseApiRepository() {
    fun requestSellsWatch(params: MutableMap<String, Any>): Observable<Response<ApiResponse>> {
        return getApiService().getSellsWatch(params)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun requestWeeklyPopular(params: MutableMap<String, Any>): Observable<Response<ApiResponse>>{
        return getApiService().getWeeklyPopular(params)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }
}