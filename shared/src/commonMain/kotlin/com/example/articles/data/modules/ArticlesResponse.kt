package com.example.articles.data.modules

import kotlinx.serialization.Serializable

@Serializable
data class ArticlesResponse(
    val articles: List<Article>
)