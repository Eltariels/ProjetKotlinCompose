package com.example.projectkotlincompose.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostRepository {
    private val api: PostApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(PostApi::class.java)
    }

    suspend fun getPosts(): List<Post> {
        return api.getPosts()
    }
}
