package com.mobile.azri.mvvmnewsapiwithcoroutine.ui

import androidx.lifecycle.ViewModel
import com.mobile.azri.mvvmnewsapiwithcoroutine.repository.NewsRepository

class NewsViewModel(
    val newsRepository : NewsRepository
) : ViewModel() {



}