package com.example.articles

import com.example.articles.data.modules.Article
import com.example.articles.data.ArticlesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ArticlesViewModel(
    private val repo: ArticlesRepository,
) : BaseViewModel() {
    private val _articlesState = MutableStateFlow<ArticlesState>(ArticlesState())
    val articlesState = _articlesState.asStateFlow()

    init {
        fetchArticles()
    }

    private fun fetchArticles() {
        scope.launch {
            _articlesState.value = ArticlesState(
                isLoading = false,
                articles = repo.fetchArticles()
            )
        }
    }
}