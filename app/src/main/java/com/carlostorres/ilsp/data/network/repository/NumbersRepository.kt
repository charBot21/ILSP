package com.carlostorres.ilsp.data.network.repository

import android.util.Log
import com.carlostorres.ilsp.data.network.NumbersService
import io.reactivex.Single
import retrofit2.Call

class NumbersRepository {

    fun getNumbers(startNumber: String, endNumber: String ): Call<List<Int>> {
        return NumbersService().getPrimeNumbers(startNumber, endNumber)
    }

}