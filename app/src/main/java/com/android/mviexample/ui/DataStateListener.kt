package com.android.mviexample.ui

import com.android.mviexample.util.DataState

interface DataStateListener {

    fun onDataStateChange(dataState : DataState<*>?)


}