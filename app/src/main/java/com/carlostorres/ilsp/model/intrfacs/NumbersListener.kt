package com.carlostorres.ilsp.model.intrfacs

interface NumbersListener {

    fun onSuccess(items: List<Int>)
    fun onError()
    fun onErrorConnection()
    fun onInvalidValues()
    fun onBeGreaterThan()
    fun showProgress()
    fun hideProgress()
}