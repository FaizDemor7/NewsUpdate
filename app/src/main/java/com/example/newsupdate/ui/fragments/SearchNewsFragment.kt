package com.example.newsupdate.ui.fragments
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.newsupdate.R
import com.example.newsupdate.ui.NewsActivity
import com.example.newsupdate.ui.NewsViewModel


class SearchNewsFragment : Fragment(R.layout.fragment_search_news) {

    lateinit var viewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
    }
}