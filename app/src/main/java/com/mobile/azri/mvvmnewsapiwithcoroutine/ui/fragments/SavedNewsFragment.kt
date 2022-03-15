package com.mobile.azri.mvvmnewsapiwithcoroutine.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.azri.mvvmnewsapiwithcoroutine.R
import com.mobile.azri.mvvmnewsapiwithcoroutine.adapters.NewsAdapter
import com.mobile.azri.mvvmnewsapiwithcoroutine.databinding.FragmentSavedNewsBinding
import com.mobile.azri.mvvmnewsapiwithcoroutine.databinding.FragmentSearchNewsBinding
import com.mobile.azri.mvvmnewsapiwithcoroutine.db.ArticleDatabase
import com.mobile.azri.mvvmnewsapiwithcoroutine.repository.NewsRepository
import com.mobile.azri.mvvmnewsapiwithcoroutine.ui.NewsActivity
import com.mobile.azri.mvvmnewsapiwithcoroutine.ui.NewsViewModel
import com.mobile.azri.mvvmnewsapiwithcoroutine.ui.NewsViewModelProviderFactory

class SavedNewsFragment : Fragment() {

//    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter

    private var _binding: FragmentSavedNewsBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSavedNewsBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val newsRepository = NewsRepository(ArticleDatabase(requireContext()))
//        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
//        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(NewsViewModel::class.java)
//        viewModel = (activity as NewsActivity).viewModel
        setupRecyclerView()

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article",it)
            }
            findNavController().navigate(
                R.id.action_savedNewsFragment_to_articleFragment,
                bundle
            )
        }
    }

    private fun setupRecyclerView(){
        newsAdapter = NewsAdapter()
        binding?.rvSavedNews?.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}