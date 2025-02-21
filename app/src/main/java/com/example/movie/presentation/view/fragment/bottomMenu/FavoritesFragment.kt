package com.example.movie.presentation.view.fragment.bottomMenu

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.common.base.fragment.BaseFragment
import com.example.movie.common.factory.CommonViewModelFactory
import com.example.movie.data.local.data_source.MovieLocalDataSourceImpl
import com.example.movie.data.local.database.DatabaseInstance
import com.example.movie.data.remote.data_source.movie.MovieRemoteDataSourceImpl
import com.example.movie.data.remote.network.RetrofitInstance
import com.example.movie.data.repository.MovieRepositoryImpl
import com.example.movie.databinding.FragmentFavoritesBinding
import com.example.movie.domain.usecase.favorites.DeleteFavMovieUseCase
import com.example.movie.domain.usecase.favorites.GetAllFavorMoviesUseCase
import com.example.movie.presentation.adapter.favor_movie_page.FavorMovieAdapter
import com.example.movie.presentation.view.activity.DetailActivity
import com.example.movie.presentation.viewmodel.FavoritesViewModel


class FavoritesFragment :
    BaseFragment<FragmentFavoritesBinding>(FragmentFavoritesBinding::inflate) {
    private val viewmodel: FavoritesViewModel by viewModels {
        CommonViewModelFactory {
            FavoritesViewModel(
                GetAllFavorMoviesUseCase(
                    (
                            MovieRepositoryImpl(
                                MovieRemoteDataSourceImpl(
                                    RetrofitInstance.movieApiService
                                ),
                                MovieLocalDataSourceImpl(
                                    DatabaseInstance.getDatabase(requireContext()).MovieDao()

                                )
                            )
                    )

                ),
                DeleteFavMovieUseCase(
                    (
                            MovieRepositoryImpl(
                                MovieRemoteDataSourceImpl(
                                    RetrofitInstance.movieApiService
                                ),
                                MovieLocalDataSourceImpl(
                                    DatabaseInstance.getDatabase(requireContext()).MovieDao()

                                )
                            )
                            )

                ),

            )
        }
    }
    private lateinit var adapter: FavorMovieAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun setupCreatedUI() {

        viewmodel.getFavMovies()

        adapter = FavorMovieAdapter(
            onPosterClick = { movie ->

                navigateToDetailPage(movie.id)


            },
            onDeleteClick = { movie ->

                viewmodel.deleteFavMovie(movie)
                viewmodel.getFavMovies()
                adapter.notifyDataSetChanged()


            },

            )

        viewmodel.favor.observe(viewLifecycleOwner)  { movieList ->
            Log.e("favori observer","${movieList}")
            adapter.updateList(movieList)




        }
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter =adapter




    }
    private  fun navigateToDetailPage(id:Int){
        val intent = Intent(requireContext(), DetailActivity::class.java).apply {
            putExtra("movieDetail", id)

        }
        startActivity(intent)
    }
}