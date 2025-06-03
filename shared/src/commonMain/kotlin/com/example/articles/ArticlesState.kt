package com.example.articles

import com.example.articles.data.modules.Article

data class ArticlesState(
    val isLoading: Boolean = false,
    val articles: List<Article> = emptyList(),
    val error: String? = null
)