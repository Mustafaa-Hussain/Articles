package com.example.articles.data

import articles.db.ArticlesDB
import com.example.articles.data.modules.Article

class ArticleLocalDataSource(database: ArticlesDB) {

    private val queries = database.articlesDBQueries

    fun insertArticle(
        title: String,
        desc: String?,
        date: String,
        imgUrl: String?
    ) {
        queries.insertArticle(title, desc, date, imgUrl)
    }

     fun getAllArticles(): List<Article> {
        return queries.selectAllArticles(::mapToArticle).executeAsList()
    }

    fun clearAllArticles() {
        queries.removAllArticles()
    }

    private fun mapToArticle(
        title: String,
        desc: String?,
        date: String,
        imgUrl: String?
    ): Article{
        return Article(
            title = title,
            description = desc?:"",
            date = date,
            imageUrl = imgUrl?:""
        )
    }

}