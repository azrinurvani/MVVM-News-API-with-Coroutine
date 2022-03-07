package com.mobile.azri.mvvmnewsapiwithcoroutine.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.mobile.azri.mvvmnewsapiwithcoroutine.models.NewsResponse
import com.mobile.azri.mvvmnewsapiwithcoroutine.repository.NewsRepository
import com.mobile.azri.mvvmnewsapiwithcoroutine.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    val newsRepository : NewsRepository
) : ViewModel() {

    val breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var breakingNewsPage = 1


    init {
        getBreakingNews("us")
    }

    /*viewModelScope berfungsi untuk proses yang berjalan tetap di scope nya view model,
     selama view model nya alive atau berjalan */
    fun getBreakingNews(countryCode:String) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        // pada tahap ini apabila ada error atau tidak pada response
        // maka di view model scope akan tetap menjalankan ke baris selanjutnya
        // maka pada variable response akan menyimpan apapun yang terjadi pada response api
        val response = newsRepository.getBreakingNews(countryCode,breakingNewsPage)
        breakingNews.postValue(handleBreakingNewsResponse(response))
    }

    //method untuk handle response dari repository
    private fun handleBreakingNewsResponse(response:Response<NewsResponse>) : Resource<NewsResponse>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}