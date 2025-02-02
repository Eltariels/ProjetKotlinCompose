package com.example.projectkotlincompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectkotlincompose.data.Post
import com.example.projectkotlincompose.domain.GetPostsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostViewModel(private val getPostsUseCase: GetPostsUseCase) : ViewModel() {

    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts

    fun fetchPosts() {
        viewModelScope.launch {
            _posts.value = getPostsUseCase.execute()
        }
    }
}
