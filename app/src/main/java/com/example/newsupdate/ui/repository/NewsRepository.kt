package com.example.newsupdate.ui.repository

import com.example.newsupdate.ui.api.RetrofitInstance
import com.example.newsupdate.ui.db.ArticleDatabase
import com.example.newsupdate.ui.models.Article

class NewsRepository(
    val db: ArticleDatabase
) {
 suspend fun getBreakingNews(countryCode : String, pageNumber : Int) =
     RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)


    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    fun getSavedNews() =db.getArticleDao().getAllArticles()


    suspend fun deleteArticle(article: Article) =db.getArticleDao().deleteArticle(article)

}