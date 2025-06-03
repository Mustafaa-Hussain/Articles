package com.example.articles.data

import com.example.articles.data.modules.Article
import com.example.articles.data.modules.ArticlesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


//api key 8e58f324a063461598c2535e52d14002
//url https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=8e58f324a063461598c2535e52d14002


val client = HttpClient {
    install(ContentNegotiation) {
        json(
            Json {
                ignoreUnknownKeys = true
            }
        )
    }
}

class ArticlesService(private val httpClient: HttpClient) {
    private val country = "us"
    private val category = "business"
    private val apiKey = "8e58f324a063461598c2535e52d14002"

    suspend fun fetchArticles(): List<Article> {
        val response: ArticlesResponse =
            httpClient.get("https://newsapi.org/v2/top-headlines?country=$country&category=$category&apiKey=$apiKey")
                .body()

        return response.articles
    }
}