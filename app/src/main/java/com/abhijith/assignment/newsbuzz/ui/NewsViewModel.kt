package com.abhijith.assignment.newsbuzz.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhijith.assignment.newsbuzz.models.NewsResponse
import com.abhijith.assignment.newsbuzz.repository.NewsRepository
import com.abhijith.assignment.newsbuzz.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    // we cannot use constructor parameters by default in viewmodels
    // to do that, we need to create a ViewModelFactory to define how our viewmodel should be defined
    private val newsRepository: NewsRepository
): ViewModel() {

    val breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    private val breakingNewsPage = 1 // we cannot set this in fragment as it will always be 1 if we rotate the screen

    val searchNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val searchNewsPage = 1

    // function in repository is a suspend function which means we need to call this from another
    // suspend function or coroutine
    // we don't want to use suspend function here because we dont want to propagate the suspend function
    // to fragment. So we need to start a coroutine

    init {
        getBreakingNews("in")
    }

    private fun getBreakingNews(country:String) = viewModelScope.launch {
        // before we make a network call, we need to emit the loading state so that the
        // fragment can handle that
        breakingNews.postValue(Resource.Loading())
        val response = newsRepository.getBreakingNews(country, breakingNewsPage)
        breakingNews.postValue(handleBreakingNewsResponse(response))
    }

    fun searchNews(queryString:String) = viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        val response = newsRepository.searchNews(queryString, searchNewsPage)
        searchNews.postValue(handleSearchNewsResponse(response))
    }

    private fun handleBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        // we decide whether we want to emit success state or error state
        if(response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return  Resource.Error(response.message())
    }


    private fun handleSearchNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        // we decide whether we want to emit success state or error state
        if(response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return  Resource.Error(response.message())
    }


}