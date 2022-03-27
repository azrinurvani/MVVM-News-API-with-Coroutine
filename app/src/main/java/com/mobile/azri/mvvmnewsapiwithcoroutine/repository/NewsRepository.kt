package com.mobile.azri.mvvmnewsapiwithcoroutine.repository

import com.mobile.azri.mvvmnewsapiwithcoroutine.api.RetrofitInstance
import com.mobile.azri.mvvmnewsapiwithcoroutine.db.ArticleDatabase
import com.mobile.azri.mvvmnewsapiwithcoroutine.models.Article

class NewsRepository(
    private val db: ArticleDatabase
) {

    suspend fun getBreakingNews(countryCode:String,pageNumber:Int) =
        RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)

    suspend fun searchNews(searchQuery:String,pageNUmber:Int) =
        RetrofitInstance.api.searchForNews(searchQuery,pageNUmber)

    suspend fun upsert(article:Article) = db.getArticleDao().upsert(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)
}