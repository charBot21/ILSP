package com.carlostorres.ilsp.ui.viewmodel

import android.app.Application
import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import com.carlostorres.ilsp.data.network.repository.NumbersRepository
import com.carlostorres.ilsp.model.intrfacs.NumbersListener
import com.carlostorres.ilsp.ui.utils.Coroutines
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NumbersVM(application: Application): AndroidViewModel(application) {

    // Listener
    var numbersListener: NumbersListener ?= null

    // Inputs
    val startNumber = ObservableField("")
    val endNumber = ObservableField("")

    fun validateData(view: View) {

        numbersListener?.showProgress()

        if ( !startNumber.get().isNullOrEmpty() && !endNumber.get().isNullOrEmpty() ) {

            if ( startNumber.get()?.toInt()!! < 200 && endNumber.get()?.toInt()!! < 200 ) {
                if ( startNumber.get()?.toInt()!! < endNumber.get()?.toInt()!!) {

                    val response = NumbersRepository().getNumbers(startNumber.get().toString(), endNumber.get().toString())
                    response.enqueue(object : Callback<List<Int>> {
                        override fun onResponse(call: Call<List<Int>>, response: Response<List<Int>>) {
                            if (response.body() != null) {
                                numbersListener?.onSuccess(response.body()!!)
                            }
                        }

                        override fun onFailure(call: Call<List<Int>>, t: Throwable) {
                            numbersListener?.onErrorConnection()
                        }

                    })
                } else {
                    numbersListener?.onInvalidValues()
                }
            } else {
                numbersListener?.onBeGreaterThan()
            }

        } else {
            numbersListener?.onError()
        }
        numbersListener?.hideProgress()

    }

    private fun validateNumbers( startNumber: Int, endNumber: Int): Boolean {
        if ( startNumber < endNumber ) {
            if ( endNumber <= 200 ) {
                return true
            }
        }
        return false
    }
}