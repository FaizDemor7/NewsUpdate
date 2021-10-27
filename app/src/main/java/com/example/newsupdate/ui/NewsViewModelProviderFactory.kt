package com.example.newsupdate.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsupdate.ui.repository.NewsRepository

class NewsViewModelProviderFactory(
    val newsRespository : NewsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(newsRespository) as T
    }
}