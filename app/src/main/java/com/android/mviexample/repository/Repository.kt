package com.android.mviexample.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.android.mviexample.api.MyRetrofitBuilder
import com.android.mviexample.ui.main.state.MainViewState
import com.android.mviexample.util.ApiEmptyResponse
import com.android.mviexample.util.ApiErrorResponse
import com.android.mviexample.util.ApiSuccessResponse
import com.android.mviexample.util.DataState

object Repository {
    fun getBlogPosts() : LiveData<DataState<MainViewState>>{
        return Transformations
            .switchMap(MyRetrofitBuilder.apiService.getBlogPosts()){apiResponse ->
                object  : LiveData<DataState<MainViewState>>(){
                    override fun onActive() {
                        super.onActive()
                        when(apiResponse){
                            is ApiSuccessResponse -> {
                                value = DataState.data(
                                    message = null,
                                    data = MainViewState(
                                        blogPosts = apiResponse.body
                                    )
                                )
                            }
                            is ApiErrorResponse -> {
                                value = DataState.error(
                                    message = apiResponse.errorMessage

                                )
                            }
                            is ApiEmptyResponse -> {
                                value = DataState.error(
                                    message = "HTTP 204. Returned NOTHING!"
                                )
                            }
                        }
                    }
                }
            }
    }

    fun getUser(userId : String) :  LiveData<DataState<MainViewState>>{
        return Transformations
            .switchMap(MyRetrofitBuilder.apiService.getUser(userId)){apiResponse ->
                object  :  LiveData<DataState<MainViewState>>(){
                    override fun onActive() {
                        super.onActive()
                        when(apiResponse){
                            is ApiSuccessResponse -> {
                                value = DataState.data(
                                    message = null,
                                    data = MainViewState(
                                        user = apiResponse.body
                                    )
                                )
                            }
                            is ApiErrorResponse -> {
                                value = DataState.error(
                                    message = apiResponse.errorMessage

                                )
                            }
                            is ApiEmptyResponse -> {
                                value = DataState.error(
                                    message = "HTTP 204. Returned NOTHING!"
                                )
                            }
                        }
                    }
                }
            }
    }
}