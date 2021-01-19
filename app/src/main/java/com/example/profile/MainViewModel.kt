package com.example.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.profile.Api.ApiRepository
import com.example.profile.Api.Response.ApiResponse
import io.reactivex.disposables.CompositeDisposable

class MainViewModel : ViewModel() {
    val resultLiveData = MutableLiveData<ApiResponse>()

    fun requestSellsWatch(params: MutableMap<String, Any>) {
        val compositeDisposable = CompositeDisposable()

        val disposable = ApiRepository.requestSellsWatch(params).subscribe({
            // onNext
            resultLiveData.value = it.body()
        }, { throwable ->
            // onError
        }, {
            // complete
            compositeDisposable.dispose()
        })
        compositeDisposable.add(disposable)
    }

    fun requestWeeklyPopular(params: MutableMap<String, Any>) {
        val compositeDisposable = CompositeDisposable()

        val disposable = ApiRepository.requestWeeklyPopular(params).subscribe({
            // onNext
            resultLiveData.value = it.body()
        }, { throwable ->
            // onError
        }, {
            // complete
            compositeDisposable.dispose()
        })
        compositeDisposable.add(disposable)
    }
}