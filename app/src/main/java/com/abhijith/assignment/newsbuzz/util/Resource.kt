package com.abhijith.assignment.newsbuzz.util

// Recommended by Google to be used to wrap around Network Response
// Generic class used to differentiate between successful and error responses
// and also to helps us to handle loading states - like showing progress bar etc

// sealed class - Kind of abstract class but we can define which classes are allowed to inherit from
// Resource class
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
}