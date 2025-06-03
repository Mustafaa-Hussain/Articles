package com.example.articles.data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import articles.db.ArticlesDB

actual class DatabaseDriverFactory() {
    actual fun createDriver(): SqlDriver =
        NativeSqliteDriver(
            schema = ArticlesDB.Companion.Schema,
            name = "ArticlesDatabase.database.db"
        )
}