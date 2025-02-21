package com.example.movie.presentation.view.fragment.bottomMenu

import android.content.Intent
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.common.base.fragment.BaseFragment
import com.example.movie.common.factory.CommonViewModelFactory
import com.example.movie.data.local.data_source.GenreLocalDataSourceImpl
import com.example.movie.data.local.data_source.MovieLocalDataSourceImpl
import com.example.movie.data.local.database.DatabaseInstance
import com.example.movie.data.remote.data_source.genre.GenreRemoteDataSourceImpl
import com.example.movie.data.remote.data_source.movie.MovieRemoteDataSourceImpl
import com.example.movie.data.remote.network.RetrofitInstance
import com.example.movie.data.repository.GenreRepositoryImpl
import com.example.movie.data.repository.MovieRepositoryImpl
import com.example.movie.databinding.FragmentHomeBinding
import com.example.movie.domain.usecase.home.GetAllGenresUseCase
import com.example.movie.domain.usecase.home.GetMovieWithGenreUseCase
import com.example.movie.domain.usecase.home.GetNowPlayingUseCase
import com.example.movie.domain.usecase.home.GetTopRatedMoviesUseCase
import com.example.movie.domain.usecase.home.GetPopularMoviesUseCase
import com.example.movie.domain.usecase.home.GetUpComingMoviesUseCase
import com.example.movie.presentation.adapter.home_movie_page.GenreAdapter
import com.example.movie.presentation.adapter.home_movie_page.ImageMovieAdapter
import com.example.movie.presentation.adapter.home_movie_page.MovieAdapter
import com.example.movie.presentation.view.activity.DetailActivity
import com.example.movie.presentation.viewmodel.HomeViewModel
import java.util.Calendar


class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {


    private val homeViewModel: HomeViewModel by viewModels {
        CommonViewModelFactory {
            HomeViewModel(
                GetPopularMoviesUseCase(
                    MovieRepositoryImpl(
                        MovieRemoteDataSourceImpl(
                            RetrofitInstance.movieApiService
                        ),
                        MovieLocalDataSourceImpl(
                            DatabaseInstance.getDatabase(requireContext()).MovieDao()

                        )
                    )

                ),
                GetTopRatedMoviesUseCase(
                    MovieRepositoryImpl(
                        MovieRemoteDataSourceImpl(
                            RetrofitInstance.movieApiService
                        ),
                        MovieLocalDataSourceImpl(
                            DatabaseInstance.getDatabase(requireContext()).MovieDao()

                        )
                    )

                ),
                GetUpComingMoviesUseCase(
                    MovieRepositoryImpl(
                        MovieRemoteDataSourceImpl(
                            RetrofitInstance.movieApiService
                        ),
                        MovieLocalDataSourceImpl(
                            DatabaseInstance.getDatabase(requireContext()).MovieDao()

                        )
                    )

                ),
                GetNowPlayingUseCase(
                    MovieRepositoryImpl(
                        MovieRemoteDataSourceImpl(
                            RetrofitInstance.movieApiService
                        ),
                        MovieLocalDataSourceImpl(
                            DatabaseInstance.getDatabase(requireContext()).MovieDao()

                        )
                    )

                ),

                GetAllGenresUseCase(
                    GenreRepositoryImpl(
                        GenreRemoteDataSourceImpl(
                            RetrofitInstance.genreApiService
                        ),
                        GenreLocalDataSourceImpl(
                            DatabaseInstance.getDatabase(requireContext()).GenreDao()
                        )
                    )
                ),
                GetMovieWithGenreUseCase(
                    MovieRepositoryImpl(
                        MovieRemoteDataSourceImpl(
                            RetrofitInstance.movieApiService
                        ),
                        MovieLocalDataSourceImpl(
                            DatabaseInstance.getDatabase(requireContext()).MovieDao()

                        )
                    )
                )


            )
        }
    }

    private lateinit var popularMovieAdapter: MovieAdapter
    private lateinit var latestMovieAdapter: MovieAdapter
    private lateinit var upComingMovieAdapter: MovieAdapter
    private lateinit var nowPlayingAdapter: MovieAdapter
    private lateinit var viewsMovieAdapter: ImageMovieAdapter
    private lateinit var movieWithGenreAdapter: MovieAdapter

    private lateinit var genreAdapter: GenreAdapter

    override fun setupCreatedUI() {

        popularMovieAdapter = MovieAdapter(
            onPosterClick = { movie ->

                navigateToDetailPage(movie.id)


            },


        )
        latestMovieAdapter = MovieAdapter(
            onPosterClick = { movie ->
                navigateToDetailPage(movie.id)
            }

        )
        upComingMovieAdapter = MovieAdapter(
            onPosterClick = { movie ->
                navigateToDetailPage(movie.id)
            },
        )


        nowPlayingAdapter = MovieAdapter(
            onPosterClick = { movie ->
                navigateToDetailPage(movie.id)

            }
        )

        movieWithGenreAdapter = MovieAdapter(
            onPosterClick = { movie ->
                navigateToDetailPage(movie.id)
            }
        )



        genreAdapter = GenreAdapter(
            onRootClick = { movie ->
               homeViewModel.getAllMovieWithGenre(movie.id,1)
            }
        )
        viewsMovieAdapter = ImageMovieAdapter()

        //popular Movies
        binding.recycleView.layoutManager =  LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recycleView.adapter = popularMovieAdapter
        // all genres
        binding.genreRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.genreRecyclerView.adapter = genreAdapter
        //latest Movies
        binding.recycleViewLate.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recycleViewLate.adapter = latestMovieAdapter
        //Up Coming Movies
        binding.recycleViewUpComming.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recycleViewUpComming.adapter = upComingMovieAdapter
        //Now Playing  Movies
        binding.recycleViewNowPlaying.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recycleViewNowPlaying.adapter =nowPlayingAdapter

        binding.viewPager2.adapter = viewsMovieAdapter
        binding.dotsIndicator.setViewPager2(binding.viewPager2)

        binding.MovieByGenreRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.MovieByGenreRecyclerView.adapter =movieWithGenreAdapter


        homeViewModel.popular.observe(viewLifecycleOwner, Observer { movieList ->
            Log.e("adapter observer","${movieList}")
            popularMovieAdapter.updateList(movieList.results)
        })

        homeViewModel.genres.observe(viewLifecycleOwner) { genreList ->
            Log.e("adapter observer", "${genreList}")
            genreAdapter.updateList(genreList)
        }
        homeViewModel.topRated.observe(viewLifecycleOwner) { movieLatestList ->
            Log.e("adapter observer", "${movieLatestList}")
            latestMovieAdapter.updateList(movieLatestList.results)
        }


        homeViewModel.upComing.observe(viewLifecycleOwner) { items ->
            Log.e("adapter observer", "${items}")
            upComingMovieAdapter.updateList(items.results)
        }
        homeViewModel.data.observe(viewLifecycleOwner) { items ->
            Log.e("adapter observer", "${items}")
            movieWithGenreAdapter.updateList(items.results)
        }


        homeViewModel.nowPlaying.observe(viewLifecycleOwner) { items ->
            Log.e("adapter observer", "${items}")

            val currentYear = Calendar.getInstance().get(Calendar.YEAR)
            val filteredMovies = items.results.filter {
                val releaseYear = it.releaseDate.split("-")[0].toInt() // Get the year from the release date
                releaseYear == currentYear
            }.take(5)
            nowPlayingAdapter.updateList(items.results)
            viewsMovieAdapter.updateList(filteredMovies)

        }


        homeViewModel.getPopularMovies(1)
        homeViewModel.getAllGenres()

    }
    private  fun navigateToDetailPage(id:Int){
        val intent = Intent(requireContext(), DetailActivity::class.java).apply {
            putExtra("movieDetail", id)

        }
        startActivity(intent)
    }




}