package com.abhijith.assignment.newsbuzz.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.abhijith.assignment.newsbuzz.models.Article

// Data access object
// Interface such as NewsAPI interface that defines the functions to access the database

@Dao
interface ArticleDao {

    // determines what happens when the article we want to insert already exists in the database
    // in this case we are replacing the entire article
    // upsert -> update or insert :facepalm:
    // returns a Long, that returns an id of the inserted
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article) : Long

    // not a suspend function as it returns a livedata object which doesnt work with suspend function
    @Query("SELECT * FROM articles ")
    fun getAllArticles():LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)

}