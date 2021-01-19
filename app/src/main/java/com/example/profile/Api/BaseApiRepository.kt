package com.example.profile.Api

import android.content.Context
import com.example.profile.Fragment.LikesFragment
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class BaseApiRepository {

    private var context: Context? = null

    fun init(context: Context) {
        this.context = context
    }

    /**
     * IMSAPIService 가져오기
     *
     * @return IMSAPIService
     */
    fun getApiService(): ApiService {
        return getRetrofitWithGsonConverter(okHttpClient).create(ApiService::class.java)
    }

    /**
     * 로그를 찍기 위한 HttpLoggingInterceptor 를 가져온다.
     */
    private val httpLoggingInterceptor: HttpLoggingInterceptor
        get() {
            return HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

    /**
     * 로그인 되지 않은 OkHttpClient 를 가져온다.
     */
    private val okHttpClient: OkHttpClient
        get() {
            val okHttpBuilder = OkHttpClient().newBuilder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(25, TimeUnit.SECONDS)
                .addInterceptor {
                    // 공통 헤더
                    it.proceed(it.request().newBuilder().apply {
                    }.build())
                }

            context?.let {
                // 디버깅 모드에서만 Http 로그를 찍는다.
                okHttpBuilder.addInterceptor(httpLoggingInterceptor)
            }

            return okHttpBuilder.build()
        }


    /**
     * Gson Converter 를 사용하는 Retrofit 2 를 가져온다.
     *
     * @return Retrofit
     */
    private fun getRetrofitWithGsonConverter(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://mtm-api.apposter.com:7777/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

}