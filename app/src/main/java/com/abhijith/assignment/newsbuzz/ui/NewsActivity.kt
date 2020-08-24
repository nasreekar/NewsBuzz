package com.abhijith.assignment.newsbuzz.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.abhijith.assignment.newsbuzz.R
import com.abhijith.assignment.newsbuzz.data.ArticleDatabase
import com.abhijith.assignment.newsbuzz.repository.NewsRepository
import kotlinx.android.synthetic.main.activity_news.*

// single activity with multiple fragments
// Step1: Create a NavHost fragment -> fragment container which hosts all fragments
// Step2: Create all dummy fragments required for the app and folder structure
// Step3: define nav graph and set the connections
// we are not defining transitions between breakingNews fragment, search news fragment and saved news fragment as we are calling them from bottom navigation view
// any article clicked on any of the above fragments open a article fragment to load the news
// Step4: Connect bottom navigation view with navigation components
// Step5: Setup Retrofit
// Create response objects that we need - response as JSON
// install JSON to kotlin class plugin in case you need a tool to easy convert the JSON response to kotlin objects
// Step6: define API layer (NewsAPI interface)
// Step7: Define Retrofit Singleton class to get instance of api
// Step8: Articles DAO setup, Entity annotation on Article class
// Step9: Setup Database class for Room and type converter for Room
// Step10: Setup Recycler view with DiffUtil
// Step11: Setup ViewModel, Repository and ViewModelProvider factory, wrapper class around network class

class NewsActivity : AppCompatActivity() {

    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val repository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(repository)

        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)
        bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())

    }
}