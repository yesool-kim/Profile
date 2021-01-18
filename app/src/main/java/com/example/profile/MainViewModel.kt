package com.example.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.profile.Api.ApiRepository
import com.example.profile.Api.Response.ApiResponse
import io.reactivex.disposables.CompositeDisposable

class MainViewModel : ViewModel() {
    val resultLiveData = MutableLiveData<ApiResponse>()

    fun requestInfo(params: String) {
        val compositeDisposable = CompositeDisposable()

        val disposable = ApiRepository.requestSearchImage(params).subscribe({
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