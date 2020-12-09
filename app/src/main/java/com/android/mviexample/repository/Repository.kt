package com.android.mviexample.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.android.mviexample.api.MyRetrofitBuilder
import com.android.mviexample.model.BlogPost
import com.android.mviexample.model.User
import com.android.mviexample.ui.main.state.MainViewState
import com.android.mviexample.util.*

object Repository {
    fun getBlogPosts() : LiveData<DataState<MainViewState>>{
        return object : NetworkBoundResource<List<BlogPost>,MainViewState> (){
            override fun handleApiSuccessResponse(response: ApiSuccessResponse<List<BlogPost>>) {

                result.value = DataState.data(
                    null,
                    data = MainViewState(
                        blogPosts = response.body
                    )
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<List<BlogPost>>> {

                return MyRetrofitBuilder.apiService.getBlogPosts()
            }

        }.asLiveData()
    }

    fun getUser(userId : String) :  LiveData<DataState<MainViewState>>{
        return object : NetworkBoundResource<User,MainViewState> (){
            override fun handleApiSuccessResponse(response: ApiSuccessResponse<User>) {

                result.value = DataState.data(
                    data = MainViewState(
                        user = response.body
                    )
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<User>> {

                return MyRetrofitBuilder.apiService.getUser(userId)
            }

        }.asLiveData()
    }
}