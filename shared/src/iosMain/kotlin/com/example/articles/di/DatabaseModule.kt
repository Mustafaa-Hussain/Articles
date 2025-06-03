package com.example.articles.di

import app.cash.sqldelight.db.SqlDriver
import articles.db.ArticlesDB
import com.example.articles.data.DatabaseDriverFactory
import org.koin.dsl.module


val databaseModule = module {
    single<SqlDriver> { DatabaseDriverFactory().createDriver() }
    single<ArticlesDB> { ArticlesDB(get()) }
}