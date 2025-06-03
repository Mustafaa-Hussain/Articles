package com.example.articles.android.di

import app.cash.sqldelight.db.SqlDriver
import articles.db.ArticlesDB
import com.example.articles.data.DatabaseDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single<SqlDriver> {
        DatabaseDriverFactory(androidContext()).createDriver() }
    single<ArticlesDB> { ArticlesDB(get()) }
}