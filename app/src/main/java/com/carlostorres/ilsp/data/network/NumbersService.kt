package com.carlostorres.ilsp.data.network

import com.carlostorres.ilsp.model.constants.Environment
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface NumbersService {

    @GET("{start}/{end}")
    fun getPrimeNumbers(
            @Path("start") start: String,
            @Path("end") end: String
    ): Call<List<Int>>

    companion object {
        operator fun invoke(): NumbersService {
            return Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Environment.URL_BASE)
                    .build()
                    .create(NumbersService::class.java)
        }
    }
}