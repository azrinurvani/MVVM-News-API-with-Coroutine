package com.mobile.azri.mvvmnewsapiwithcoroutine.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mobile.azri.mvvmnewsapiwithcoroutine.R
import com.mobile.azri.mvvmnewsapiwithcoroutine.ui.NewsActivity
import com.mobile.azri.mvvmnewsapiwithcoroutine.ui.NewsViewModel

class ArticleFragment : Fragment(R.layout.fragment_article) {

    lateinit var viewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as NewsActivity).viewModel
    }

}