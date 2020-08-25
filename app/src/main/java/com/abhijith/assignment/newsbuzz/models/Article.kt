package com.abhijith.assignment.newsbuzz.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "articles")
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null, // we are setting it null because not every article have an id.
    @SerializedName("author")
    val author: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("publishedAt")
    val publishedAt: String?,
    // Room can handle only primitive datatypes. so we need to create a type
    // converter class to tell room on how to interpret this Source class
    @SerializedName("source")
    val source: Source?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("urlToImage")
    val urlToImage: String?
) : Serializable

// we are setting this class as serializable as it is not a primitive data type and cannot be passed
// as safeArgs (for fragment to fragment transaction in navHost)