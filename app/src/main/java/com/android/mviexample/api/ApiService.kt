package com.android.mviexample.api

import androidx.lifecycle.LiveData
import com.android.mviexample.model.BlogPost
import com.android.mviexample.model.User
import com.android.mviexample.util.GenericApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("placeholder/user/{userId")
    fun getUser(
        @Path("userId") userId :String
    ) : LiveData<GenericApiResponse<List<BlogPost>>>

    @GET("placeholder/blogs")
    fun getBlogPosts() : LiveData<GenericApiResponse<List<BlogPost>>>

}