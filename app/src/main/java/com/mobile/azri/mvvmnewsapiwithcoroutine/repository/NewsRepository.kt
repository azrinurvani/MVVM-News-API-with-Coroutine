package com.mobile.azri.mvvmnewsapiwithcoroutine.repository

import com.mobile.azri.mvvmnewsapiwithcoroutine.api.RetrofitInstance
import com.mobile.azri.mvvmnewsapiwithcoroutine.db.ArticleDatabase

class NewsRepository(
    val db: ArticleDatabase
) {

    suspend fun getBreakingNews(countryCode:String,pageNumber:Int) =
        RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)

    suspend fun searchNews(searchQuery:String,pageNUmber:Int) =
        RetrofitInstance.api.searchForNews(searchQuery,pageNUmber)
}