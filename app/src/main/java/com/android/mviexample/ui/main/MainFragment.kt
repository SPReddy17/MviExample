package com.android.mviexample.ui.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.mviexample.R
import com.android.mviexample.ui.main.state.MainStateEvent
import com.android.mviexample.ui.main.state.MainStateEvent.*
import java.lang.Exception

class MainFragment : Fragment(){

    lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = activity?.run {
            ViewModelProvider(this).get(MainViewModel::class.java)
        }?:throw Exception("Invalid activity")
        subscribeObservers()
    }

    fun subscribeObservers(){
        viewModel.dataState.observe(viewLifecycleOwner, Observer {dataState ->

            println("DEBUG : DataState : ${dataState}")

            // handle Data<T>
            dataState.data?.let {mainViewState ->
                mainViewState.blogPosts?.let {
                    // set BlogPosts Data
                    viewModel.setBlogListData(it)
                }
                mainViewState.user?.let {
                    // set User Data
                    viewModel.setUser(it)
                }

            }

            // Handle error
            dataState.message?.let {

            }

            // handle loading
            dataState.loading.let {

            }

        })

        viewModel.viewState.observe(viewLifecycleOwner, Observer {viewState ->
            viewState.blogPosts?.let {
                println("DEBUG : Setting blog posts to RecyclerView : ${it}")
            }
            viewState.user?.let {
                println("DEBUG : Setting user data : ${it}")
            }
        })
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_get_user -> triggerGetUserEvent()
            R.id.action_get_blog -> triggerGetBlogEvent()
        }
        return super.onOptionsItemSelected(item)

    }

    private fun triggerGetBlogEvent() {
        viewModel.setStateEvent(GetBlogPostsEvent())
    }

    private fun triggerGetUserEvent() {
        viewModel.setStateEvent(GetUserEvent("1"))
    }




}