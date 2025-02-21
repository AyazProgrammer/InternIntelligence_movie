package com.example.movie.presentation.di

import com.example.movie.common.factory.CoreViewModelFactory
import com.example.movie.data.local.data_source.MovieLocalDataSourceImpl
import com.example.movie.data.local.database.DatabaseInstance
import com.example.movie.data.remote.data_source.movie.MovieRemoteDataSourceImpl
import com.example.movie.data.remote.data_source.review.ReviewRemoteDataSourceImpl
import com.example.movie.data.remote.network.RetrofitInstance
import com.example.movie.data.repository.MovieRepositoryImpl
import com.example.movie.data.repository.ReviewRepositoryImpl
import com.example.movie.domain.repository.MovieRepository
import com.example.movie.domain.usecase.detail.ChangeFavMovieUseCase
import com.example.movie.domain.usecase.detail.CheckMovieUseCase
import com.example.movie.domain.usecase.detail.GetPersonsMovieUseCase
import com.example.movie.domain.usecase.detail.GetDetailMovieUseCase
import com.example.movie.domain.usecase.detail.GetReviewMovieUseCase
import com.example.movie.domain.usecase.detail.GetSimilarMoviesUseCase
import com.example.movie.domain.usecase.detail.GetVideosMovieUseCase
import com.example.movie.presentation.viewmodel.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    single {
        RetrofitInstance.movieApiService
        RetrofitInstance.reviewApiService
    }


    single {
        DatabaseInstance.getDatabase(get())
    }


    single {
        MovieRepositoryImpl(
            remoteDataSource = get(),
            localDataSource = get()
        )

    }
//
//
//    single { GetDetailMovieUseCase(get()) }
//    single { GetSimilarMoviesUseCase(get()) }
//    single { GetPersonsMovieUseCase(get()) }
//    single { GetVideosMovieUseCase(get()) }
//    single { GetReviewMovieUseCase(get()) }
//    single { SaveFavoriteMovieUseCase(get()) }
//
//
//    viewModel {
//        DetailViewModel(
//
//            getSimilarMoviesUseCase = get(),
//            context = get(),
//            getMovieDetailUseCase = get(),
//            getPersonsMovieUseCase = get(),
//            getVideosMovieUseCase = get(),
//            getReviewMovieUseCase = get(),
//            saveFavoriteMovieUseCase = get(),
//        )
//    }

    single {
        CoreViewModelFactory(context = get()) { context ->
            val movieRepository = MovieRepositoryImpl(
                MovieRemoteDataSourceImpl(RetrofitInstance.movieApiService),
                MovieLocalDataSourceImpl(DatabaseInstance.getDatabase(context).MovieDao())
            )

            val reviewRepository = ReviewRepositoryImpl(
                ReviewRemoteDataSourceImpl(RetrofitInstance.reviewApiService)
            )

            DetailViewModel(
                GetDetailMovieUseCase(movieRepository),
                GetSimilarMoviesUseCase(movieRepository),
                GetPersonsMovieUseCase(movieRepository),
                GetVideosMovieUseCase(movieRepository),
                GetReviewMovieUseCase(reviewRepository),
                CheckMovieUseCase(movieRepository),
                ChangeFavMovieUseCase(movieRepository),
                context = get()
            )
        }
    }



}

