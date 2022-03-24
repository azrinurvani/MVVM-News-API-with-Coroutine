package com.mobile.azri.mvvmnewsapiwithcoroutine.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.azri.mvvmnewsapiwithcoroutine.R
import com.mobile.azri.mvvmnewsapiwithcoroutine.adapters.NewsAdapter
import com.mobile.azri.mvvmnewsapiwithcoroutine.databinding.FragmentBreakingNewsBinding
import com.mobile.azri.mvvmnewsapiwithcoroutine.db.ArticleDatabase
import com.mobile.azri.mvvmnewsapiwithcoroutine.repository.NewsRepository
import com.mobile.azri.mvvmnewsapiwithcoroutine.ui.NewsViewModel
import com.mobile.azri.mvvmnewsapiwithcoroutine.ui.NewsViewModelProviderFactory
import com.mobile.azri.mvvmnewsapiwithcoroutine.util.Resource

class BreakingNewsFragment : Fragment() {

    private lateinit var viewModel : NewsViewModel
    private lateinit var newsAdapter: NewsAdapter

    private var _binding: FragmentBreakingNewsBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBreakingNewsBinding.inflate(inflater,container,false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val newsRepository = NewsRepository(ArticleDatabase(requireContext()))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(NewsViewModel::class.java)

//        viewModel = (activity as NewsActivity).viewModel
        setupRecyclerView()
        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article",it)
            }
            Log.d(TAG, "onViewCreated: bundle : $bundle")
            findNavController().navigate(
                R.id.action_breakingNewsFragment_to_articleFragment,
                bundle
            )
        }

        viewModel.breakingNews.observe(viewLifecycleOwner, { response->
            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG,"An error occured : $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        binding?.paginationProgressBar?.visibility = View.INVISIBLE
    }

    private fun showProgressBar(){
        binding?.paginationProgressBar?.visibility = View.VISIBLE
    }


    private fun setupRecyclerView(){
        newsAdapter = NewsAdapter()
        binding?.rvBreakingNews?.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    companion object {
        private const val TAG = "BreakingNewsFragment"
    }
}