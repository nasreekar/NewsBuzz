package com.abhijith.assignment.newsbuzz.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.abhijith.assignment.newsbuzz.R
import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        // single activity with multiple fragments
        // Step1: Create a NavHost fragment -> fragment container which hosts all fragments
        // Step2: Create all dummy fragments required for the app and folder structure
        // Step3: define nav graph and set the connections
            // we are not defining transitions between breakingNews fragment, search news fragment and saved news fragment as we are calling them from bottom navigation view
            // any article clicked on any of the above fragments open a article fragment to load the news
        // Step4: Connect bottom navigation view with navigation components


        bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())

    }
}