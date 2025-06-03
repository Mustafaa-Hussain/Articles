package com.example.articles

import com.example.articles.di.databaseModule
import com.example.articles.di.sharedKoinModule
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin


fun initKoin() {
    val allModules = sharedKoinModule + databaseModule
    startKoin {
        modules(allModules)
    }
}

class ArticleInjector : KoinComponent {
    val articleViewModel: ArticlesViewModel by inject()
}