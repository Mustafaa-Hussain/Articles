import SwiftUI
import shared


struct ArticlesScreen: View {
    @StateObject private var wrapper = ArticlesViewModelWrapper()

    var body: some View {
        ScrollView {
            LazyVStack(spacing: 12) {
                ForEach(wrapper.articlesState.articles, id: \.title) { article in
                    ArticleItemView(article: article)
                }
            }
            .padding()
        }
        .onAppear {
            wrapper.startObserving()
        }
        .navigationTitle("Articles")
    }
}


struct ArticleItemView: View {
    var article: Article

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            AsyncImage(url: URL(string: article.imageUrl)) { phase in
                if let image = phase.image {
                    image
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                } else if phase.error != nil {
                    Text("Image Load Error")
                        .foregroundColor(.red)
                } else {
                    ProgressView()
                }
            }
            Text(article.title)
                .font(.title3)
                .fontWeight(.bold)
            Text(article.description_)
                .font(.body)
            Text(article.date)
                .font(.caption)
                .frame(maxWidth: .infinity, alignment: .trailing)
                .foregroundColor(.gray)
        }
        .padding(16)
    }
}


extension ArticlesScreen {
    @MainActor
    class ArticlesViewModelWrapper: ObservableObject {
        let articlesViewModel: ArticlesViewModel

        init() {
            articlesViewModel = ArticleInjector().articleViewModel
            articlesState = articlesViewModel.articlesState.value
        }

        @Published var articlesState: ArticlesState

        func startObserving() {
            Task {
                for await newState in articlesViewModel.articlesState {
                    self.articlesState = newState
                }
            }
        }
    }
}
