package com.abhijith.assignment.newsbuzz.ui

import androidx.lifecycle.ViewModel
import com.abhijith.assignment.newsbuzz.repository.NewsRepository

class NewsViewModel(
    // we cannot use constructor parameters by default in viewmodels
    // to do that, we need to create a ViewModelFactory to define how our viewmodel should be defined
    val newsRepository: NewsRepository
): ViewModel() {
}