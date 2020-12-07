package com.android.mviexample.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.android.mviexample.api.MyRetrofitBuilder
import com.android.mviexample.ui.main.state.MainViewState
import com.android.mviexample.util.ApiEmptyResponse
import com.android.mviexample.util.ApiErrorResponse
import com.android.mviexample.util.ApiSuccessResponse

object Repository {
    fun getBlogPosts() : LiveData<MainViewState>{
        return Transformations
            .switchMap(MyRetrofitBuilder.apiService.getBlogPosts()){apiResponse ->
                object  : LiveData<MainViewState>(){
                    override fun onActive() {
                        super.onActive()
                        when(apiResponse){
                            is ApiSuccessResponse -> {
                                value = MainViewState(
                                    blogPosts = apiResponse.body
                                )
                            }
                            is ApiErrorResponse -> {
                                value = MainViewState() // handle error?
                            }
                            is ApiEmptyResponse -> {
                                value = MainViewState() // handle empty/error?
                            }
                        }
                    }
                }
            }
    }

    fun getUser(userId : String) : LiveData<MainViewState>{
        return Transformations
            .switchMap(MyRetrofitBuilder.apiService.getUser(userId)){apiResponse ->
                object  : LiveData<MainViewState>(){
                    override fun onActive() {
                        super.onActive()
                        when(apiResponse){
                            is ApiSuccessResponse -> {
                                value = MainViewState(
                                    user = apiResponse.body
                                )
                            }
                            is ApiErrorResponse -> {
                                value = MainViewState() // handle error?
                            }
                            is ApiEmptyResponse -> {
                                value = MainViewState() // handle empty/error?
                            }
                        }
                    }
                }
            }
    }
}