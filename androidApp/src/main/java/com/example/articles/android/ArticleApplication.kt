package com.example.articles.android

import android.app.Application
import com.example.articles.android.di.databaseModule
import com.example.articles.di.sharedKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class ArticleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        val allModules = sharedKoinModule + viewModelModule + databaseModule
        startKoin {
            androidContext(this@ArticleApplication)
            modules(allModules)
        }
    }
}