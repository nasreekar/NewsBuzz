package com.abhijith.assignment.newsbuzz.data

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.abhijith.assignment.newsbuzz.models.Article

// database classes for room should always be abstract
@Database(
    entities = [Article::class],
    version = 2
)
@TypeConverters(Converters::class)
abstract class ArticleDatabase: RoomDatabase() {
    // create a function that returns Article Dao
    abstract fun getArticleDao(): ArticleDao

    companion object {

        // create an instance of article database
        @Volatile // other threads can immediately see if a thread changes this instance
        private var instance:ArticleDatabase? = null

        // use to synchronize the instance
        // to really make sure a single instance of database is available at any time
        private val LOCK = Any()

        // whenever articleDatabase is instantiated or initialized, this function is also called
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            // this piece of code is not accessed by other threads
            // check again if instance is null or not and if null, create a new database
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ArticleDatabase::class.java,
            "article_db.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}