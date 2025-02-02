package com.example.projectkotlincompose.domain

import com.example.projectkotlincompose.data.Post
import com.example.projectkotlincompose.data.PostRepository

class GetPostsUseCase(private val repository: PostRepository) {
    suspend fun execute(): List<Post> {
        return repository.getPosts()
    }
}
