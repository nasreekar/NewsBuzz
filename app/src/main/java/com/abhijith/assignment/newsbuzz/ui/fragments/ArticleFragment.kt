package com.abhijith.assignment.newsbuzz.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.abhijith.assignment.newsbuzz.R
import com.abhijith.assignment.newsbuzz.ui.NewsActivity
import com.abhijith.assignment.newsbuzz.ui.NewsViewModel

class ArticleFragment: Fragment(R.layout.fragment_article) {
    lateinit var  viewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
    }
}