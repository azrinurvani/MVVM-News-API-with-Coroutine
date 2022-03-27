package com.mobile.azri.mvvmnewsapiwithcoroutine.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.mobile.azri.mvvmnewsapiwithcoroutine.databinding.FragmentArticleBinding
import com.mobile.azri.mvvmnewsapiwithcoroutine.db.ArticleDatabase
import com.mobile.azri.mvvmnewsapiwithcoroutine.repository.NewsRepository
import com.mobile.azri.mvvmnewsapiwithcoroutine.ui.NewsViewModel
import com.mobile.azri.mvvmnewsapiwithcoroutine.ui.NewsViewModelProviderFactory
import kotlin.math.log

class ArticleFragment : Fragment() {

    private lateinit var viewModel: NewsViewModel
    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding

    private val args : ArticleFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticleBinding.inflate(layoutInflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel = (activity as NewsActivity).viewModel

        val newsRepository = NewsRepository(ArticleDatabase(requireContext()))
        val viewModelProviderFactory = NewsViewModelProviderFactory(requireActivity().application,newsRepository)
        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(NewsViewModel::class.java)


        val article = args.article

        Log.d(TAG, "onViewCreated: url -> ${article.url}")
        binding?.webView?.apply {
            webViewClient = WebViewClient()
            article.url?.let { loadUrl(it) }
        }

        binding?.fab?.setOnClickListener{
            viewModel.savedArticle(article)
            Snackbar.make(view,"Article saved successfully",Snackbar.LENGTH_LONG).show()
        }



    }

    companion object{
        private const val TAG = "ArticleFragment"
    }

}