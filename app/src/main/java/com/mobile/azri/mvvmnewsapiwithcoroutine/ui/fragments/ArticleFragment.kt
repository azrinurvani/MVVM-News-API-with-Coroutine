package com.mobile.azri.mvvmnewsapiwithcoroutine.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.mobile.azri.mvvmnewsapiwithcoroutine.databinding.FragmentArticleBinding
import kotlin.math.log

class ArticleFragment : Fragment() {

//    lateinit var viewModel: NewsViewModel
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
//        val article = arguments?.let { ArticleFragmentArgs.fromBundle(it).article }
        val article = args.article

        Log.d(TAG, "onViewCreated: url -> ${article.url}")
        binding?.webView?.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }


    }

    companion object{
        private const val TAG = "ArticleFragment"
    }

}