package com.example.articles.data

import com.example.articles.data.modules.Article


class ArticlesRepository(
    private val articlesService: ArticlesService,
    private val articleLocalDataSource: ArticleLocalDataSource
) {
    suspend fun fetchArticles(): List<Article> {
        var articles = articleLocalDataSource.getAllArticles()

        if (articles.isEmpty()) {
            val remoteArticle = articlesService.fetchArticles()
            remoteArticle.forEach {
                articleLocalDataSource.insertArticle(
                    title = it.title,
                    desc = it.description,
                    date = it.date,
                    imgUrl = it.imageUrl
                )
            }
            articles = articleLocalDataSource.getAllArticles()
        }

        return articleLocalDataSource.getAllArticles()
    }
}