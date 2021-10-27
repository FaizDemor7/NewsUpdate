package com.example.newsupdate.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsupdate.R
import com.example.newsupdate.ui.NewsActivity
import com.example.newsupdate.ui.NewsViewModel
import com.example.newsupdate.ui.adapters.NewsAdapter
import com.example.newsupdate.ui.util.Resource

class BreakingNewsFragment: Fragment(R.layout.fragment_breaking_news) {

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    val TAG ="BreakingNewsFragment"
    

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
        setupRecyclerView()

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer {
            response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)

                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.messge?.let { message ->
                        Log.e(TAG, "An eroor occured, $message" )
                    }
                }
                 is Resource.Loading ->{
                     showProgressBar()
                 }

            }
        })

    }

    private fun showProgressBar() {

    }

    private fun hideProgressBar() {
        val paginationProgressBar : ProgressBar
        paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun setupRecyclerView(){

       newsAdapter= NewsAdapter()
       val rvBreakingNews : RecyclerView
       rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)

        }


   }

}