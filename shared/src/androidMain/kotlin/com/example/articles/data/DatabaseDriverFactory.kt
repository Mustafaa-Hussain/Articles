package com.example.articles.data

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import articles.db.ArticlesDB

actual class DatabaseDriverFactory(
    private val context: Context
) {
    actual fun createDriver(): SqlDriver =
        AndroidSqliteDriver(
            schema = ArticlesDB.Companion.Schema,
            context = context,
            name = "ArticlesDatabase.database.db"
        )
}