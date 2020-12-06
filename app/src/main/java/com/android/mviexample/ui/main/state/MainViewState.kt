package com.android.mviexample.ui.main.state

import com.android.mviexample.model.BlogPost
import com.android.mviexample.model.User

data class MainViewState(
    var blogPosts : List<BlogPost>? = null,
    var user: User? = null
)