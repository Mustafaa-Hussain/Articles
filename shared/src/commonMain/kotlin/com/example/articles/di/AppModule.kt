package com.example.articles.di

import com.example.articles.ArticlesViewModel
import com.example.articles.data.ArticleLocalDataSource
import com.example.articles.data.ArticlesRepository
import com.example.articles.data.ArticlesService
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }
    }
}

val articleModule = module{
    single { ArticlesService(get()) }
    single { ArticlesRepository(get(), get()) }
    single { ArticlesViewModel(get()) }
    single { ArticleLocalDataSource(get()) }
}

val sharedKoinModule = listOf(
    networkModule,
    articleModule
)