package com.android.mviexample.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.android.mviexample.ui.main.state.MainStateEvent
import com.android.mviexample.ui.main.state.MainStateEvent.*
import com.android.mviexample.ui.main.state.MainViewState
import com.android.mviexample.util.AbsentLiveData

class MainViewModel :ViewModel(){

    private val _stateEvent : MutableLiveData<MainStateEvent> = MutableLiveData()
    private val _viewState : MutableLiveData<MainViewState> = MutableLiveData()

    val viewState : LiveData<MainViewState>
    get() = _viewState

    val dataState :LiveData<MainViewState> =  Transformations

        .switchMap(_stateEvent){stateEvent ->
            stateEvent?.let {
                handleStateEvent(it)
            }

        }

    fun handleStateEvent(stateEvent : MainStateEvent) : LiveData<MainViewState>{
        when(stateEvent){
            is GetBlogPostsEvent -> {
                return AbsentLiveData.create()
            }
            is GetUserEvent -> {
                return AbsentLiveData.create()
            }
            is None -> {
                return AbsentLiveData.create()
            }
        }
    }


}