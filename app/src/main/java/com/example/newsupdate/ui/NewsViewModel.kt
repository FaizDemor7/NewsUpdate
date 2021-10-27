package com.example.newsupdate.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsupdate.ui.models.Article
import com.example.newsupdate.ui.models.NewsResponse
import com.example.newsupdate.ui.repository.NewsRepository
import com.example.newsupdate.ui.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    val newsRepository :NewsRepository

) : ViewModel() {

    val breakingNews : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var breakingNewsPage = 1


    fun getBreakingNews(counrtyCode : String) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        val response = newsRepository.getBreakingNews(counrtyCode, breakingNewsPage)
        breakingNews.postValue(handleBreakingNewsResponse(response))
    }


    private fun handleBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse>{
        if (response.isSuccessful)
        {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun saveArticle(article: Article)= viewModelScope.launch {
        newsRepository.upsert(article)
    }

        fun getSavedNews() = newsRepository.getSavedNews()

    fun deleteArticle(article: Article)= viewModelScope.launch {
        newsRepository.deleteArticle(article)
    }
}
